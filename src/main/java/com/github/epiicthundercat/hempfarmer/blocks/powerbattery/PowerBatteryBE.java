package com.github.epiicthundercat.hempfarmer.blocks.powerbattery;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.github.epiicthundercat.hempfarmer.util.HempFarmerEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;

public class PowerBatteryBE extends BlockEntity {

    public static net.minecraftforge.registries.RegistryObject<BlockEntityType<PowerBatteryBE>> blockEntityType;

    // Never create lazy optionals in getCapability. Always place them as fields in the tile entity:
    private final ItemStackHandler itemHandler = createHandler();
    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    private final HempFarmerEnergyStorage energyStorage = createEnergy();
    private final LazyOptional<IEnergyStorage> energy = LazyOptional.of(() -> energyStorage);

    private int counter;
    private int burnLength = 0;
    private int syncTick = 0;

    public PowerBatteryBE(BlockPos pos, BlockState state) {
        super(blockEntityType.get(), pos, state);
    }


    @Override
    public void preRemoveSideEffects(BlockPos pos, BlockState state) {
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                Block.popResource(this.level, pos, stack);
            }
        }
    }

    @Override
    public void setRemoved() {
        super.setRemoved();
        handler.invalidate();
        energy.invalidate();
    }

    public void tickServer() {
        boolean energyFull = energyStorage.getEnergyStored() >= energyStorage.getMaxEnergyStored();

        if (counter > 0 && !energyFull) {
            energyStorage.addEnergy(PowerBatteryConfig.POWER_BATTERY_GENERATE.get());
            counter--;
            setChanged();
        }

        if (counter <= 0 && !energyFull) {
            ItemStack stack = itemHandler.getStackInSlot(0);
            int burnTime = level.fuelValues().burnDuration(stack, RecipeType.SMELTING);
            if (burnTime > 0) {
                itemHandler.extractItem(0, 1, false);
                burnLength = burnTime;
                counter = burnTime;
                setChanged();
            }
        }

        BlockState blockState = level.getBlockState(worldPosition);
        if (blockState.getValue(BlockStateProperties.POWERED) != counter > 0) {
            level.setBlock(worldPosition, blockState.setValue(BlockStateProperties.POWERED, counter > 0),
                    Block.UPDATE_ALL);
        }

        sendOutPower();

        // Pulse every second so the client BE stays at most ~1 s stale.
        // Container caches are pre-seeded from the client BE on open, keeping bars accurate.
        if (++syncTick >= 20) {
            syncTick = 0;
            level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
        }
    }

    private void sendOutPower() {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
            for (Direction direction : Direction.values()) {
                BlockEntity blockEntity = level.getBlockEntity(worldPosition.relative(direction));
                if (blockEntity != null) {
                    boolean doContinue = blockEntity.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite()).map(handler -> {
                                if (handler.canReceive()) {
                                    int received = handler.receiveEnergy(Math.min(capacity.get(), PowerBatteryConfig.POWER_BATTERY_SEND.get()), false);

                                    capacity.addAndGet(-received);
                                    energyStorage.consumeEnergy(received);
                                    setChanged();
                                    return capacity.get() > 0;
                                } else {
                                    return true;
                                }
                            }
                    ).orElse(true);
                    if (!doContinue) {
                        return;
                    }
                }
            }
        }
    }

    public int getCounter() { return counter; }
    public int getBurnLength() { return burnLength; }

    @Override
    protected void loadAdditional(ValueInput input) {
        input.read("Inventory", CompoundTag.CODEC).ifPresent(tag -> itemHandler.deserializeNBT(input.lookup(), tag));
        input.getInt("Energy").ifPresent(v -> energyStorage.setEnergy(v));
        input.child("Info").ifPresent(info -> {
            counter = info.getIntOr("Counter", 0);
            burnLength = info.getIntOr("BurnLength", 0);
        });
        super.loadAdditional(input);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.store("Inventory", CompoundTag.CODEC, itemHandler.serializeNBT(this.level.registryAccess()));
        output.putInt("Energy", energyStorage.getEnergyStored());
        ValueOutput info = output.child("Info");
        info.putInt("Counter", counter);
        info.putInt("BurnLength", burnLength);
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(1) {

            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
                if (level != null && !level.isClientSide()) {
                    level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
                }
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return level != null && level.fuelValues().isFuel(stack);
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (level == null || !level.fuelValues().isFuel(stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    private HempFarmerEnergyStorage createEnergy() {
        return new HempFarmerEnergyStorage(PowerBatteryConfig.POWER_BATTERY_CAPACITY.get(), 0) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    public ItemStackHandler getItemHandler() {
        return itemHandler;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return handler.cast();
        }
        if (cap == ForgeCapabilities.ENERGY) {
            return energy.cast();
        }
        return super.getCapability(cap, side);
    }
}

