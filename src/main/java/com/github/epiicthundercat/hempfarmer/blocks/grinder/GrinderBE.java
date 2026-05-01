package com.github.epiicthundercat.hempfarmer.blocks.grinder;


import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.github.epiicthundercat.hempfarmer.util.HempFarmerEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.atomic.AtomicInteger;




public class GrinderBE extends BlockEntity {

    public static net.minecraftforge.registries.RegistryObject<BlockEntityType<GrinderBE>> blockEntityType;

    private final HempFarmerEnergyStorage energy = createEnergyStorage();
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energy);
    public final int SLOT_INPUT_1 = 0;
    public final int SLOT_OUTPUT_1 = 1;
    private int grindTime = -1;
    private int grindLength = -1;
    private float xp = 0f;

    private int counter;
    private int syncTick = 0;


    public final ContainerData blockData = new ContainerData() {
        public int get(int id) {
            return switch (id) {
                case 0 -> GrinderBE.this.grindTime;
                case 1 -> GrinderBE.this.grindLength;
                default -> 0;
            };
        }

        public void set(int id, int value) {
            switch (id) {
                case 0 -> GrinderBE.this.grindTime = value;
                case 1 -> GrinderBE.this.grindLength = value;
            }

        }

        @Override
        public int getCount() {
            return 2;
        }
    };


    /**
     * This is the processing code for the recipes...
     * This is where power is drained
     * where recipe start is iniated and the methods to convert the items get called, now we should be able to add recipes manually and be good.
     */

    public void tickServer() {
        this.setPlayersInside(this.getPlayersInside());
        AtomicInteger capacity = new AtomicInteger(energy.getEnergyStored());
        if (!this.level.isClientSide()) {
            // Pulse a block update every second so the client BE stays fresh.
            // Container caches are pre-seeded from the client BE on open, so this
            // ensures the screen shows correct values even if nothing changed recently.
            if (++syncTick >= 20) {
                syncTick = 0;
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }

            GrinderRecipeHandler recipe = canCraft();
            if (recipe != null) {
                int craftTimeXenergy = GrinderConfig.ENERGY_NEED.get() * recipe.getCraftTime();
                if (!(capacity.get() <= GrinderConfig.ENERGY_NEED.get())) {

                    if (getGrindTime() > 0) {
                        int consumedEnergy = GrinderConfig.ENERGY_NEED.get();
                        capacity.addAndGet(-consumedEnergy);
                        energy.consumeEnergy(consumedEnergy);

                        setGrindTime(getGrindTime() - 1);

                    } else {
                        if (getGrindTime() == 0) {

                            finishCraft(recipe);

                        }


                        if (getGrindTime() == -1 && getGrindLength() == -1) {
                            if (capacity.get() >= craftTimeXenergy) {

                                startCraft(recipe);
                            }
                        }
                    }

                }
            } else {

                stopCrafting();
            }
        }

    }

    /**
     * Returns a recipe that is ready to be crafted, returns null if nothing can be crafted.
     */
    public GrinderRecipeHandler canCraft() {
        GrinderRecipeHandler recipe = getRecipeFromContents();

        if (energy.getEnergyStored() < GrinderConfig.ENERGY_NEED.get()) {
            return null;
        } else

        /**
         * Checks to see if the recipe is not broken if its not proceed
         */

            if (recipe != null) {

                /**
                 *  checks if the item in the output slot is empty, process the recipe
                 */
                if (itemHandler.getStackInSlot(SLOT_OUTPUT_1).isEmpty()) {
                    return recipe;
                }
                /**
                 * Checks the item in the recipe output, if the item in the output is the same, and its stackable, and the count is less than max stack size, it will attach it.
                 */

                ItemStack output = itemHandler.getStackInSlot(SLOT_OUTPUT_1);
                if (recipe.getOutput().getItem() == output.getItem() && output.isStackable() && output.getCount() + recipe.getOutput().getCount() <= output.getMaxStackSize()) {

                    return recipe;
                }
            }
        return null;
    }

    /**
     * Returns the recipe that can be crafted from this tile's input slots.
     */
    public GrinderRecipeHandler getRecipeFromContents() {
        if (!(level instanceof ServerLevel serverLevel)) return null;
        for (RecipeHolder<?> holder : serverLevel.recipeAccess().getRecipes()) {
            if (holder.value() instanceof GrinderRecipeHandler recipe) {
                if (recipe.matches(itemHandler)) {
                    return recipe;
                }
            }
        }
        return null;
    }

    /**
     * Called when the craft progress starts. Updates blockstate and sets cook length from recipe.
     */
    public void startCraft(GrinderRecipeHandler recipe) {

        int totalEnergyForCraft = GrinderConfig.ENERGY_NEED.get() * recipe.getCraftTime();
        if (energy.getEnergyStored() < totalEnergyForCraft) {

            stopCrafting();
        }
        this.getLevel().setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(GrinderBlock.IS_ON, true));
        setGrindLength(recipe.getCraftTime());
        setGrindTime(getGrindLength());
    }

    /**
     * Called when the craft needs to be halted. Set values to default.
     */
    public void stopCrafting() {
        this.getLevel().setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(GrinderBlock.IS_ON, false));
        setGrindLength(-1);
        setGrindTime(-1);
    }


    /**
     * Called when the craft progress is complete. Shrink inputs and places output item.
     */
    private void finishCraft(GrinderRecipeHandler recipe) {
        stopCrafting();

        ItemStack inputStack = itemHandler.getStackInSlot(SLOT_INPUT_1);
        for (Ingredient ingredient : recipe.getIngredients()) {
            if (ingredient.test(inputStack)) {
                inputStack.shrink(1);
                break;
            }
        }

        ItemStack output = itemHandler.getStackInSlot(SLOT_OUTPUT_1);
        if (output.isEmpty()) {
            itemHandler.setStackInSlot(SLOT_OUTPUT_1, recipe.getOutput());
        } else if (output.isStackable()) {
            ItemStack newOutput = recipe.getOutput();
            newOutput.grow(output.getCount());
            itemHandler.setStackInSlot(SLOT_OUTPUT_1, newOutput);
        }

        this.setXP(this.getXP() + recipe.getXP());
    }

    /**
     * Returns the tile's held experience.
     */
    public float getXP() {
        return this.xp;
    }

    public void setXP(float xp) {
        this.xp = xp;
        this.setChanged();
    }

    /**
     * Returns the time in ticks of the current crafting progess.
     */
    public int getGrindTime() {
        return this.grindTime;
    }

    public void setGrindTime(int tick) {
        this.grindTime = tick;
        this.setChanged();
    }

    /**
     * Returns the time in ticks needed to craft the current recipe.
     */
    public int getGrindLength() {
        return this.grindLength;
    }

    public void setGrindLength(int ticks) {
        this.grindLength = ticks;
        this.setChanged();
    }


    @Override
    protected void loadAdditional(ValueInput input) {
        input.read("Inventory", CompoundTag.CODEC).ifPresent(tag -> this.itemHandler.deserializeNBT(input.lookup(), tag));
        input.getInt("Energy").ifPresent(v -> this.energy.setEnergy(v));
        input.getInt("CookTime").ifPresent(v -> this.grindTime = v);
        input.getInt("CookLength").ifPresent(v -> this.grindLength = v);
        this.xp = input.getFloatOr("XP", 0f);
        input.child("Info").ifPresent(info -> this.counter = info.getIntOr("Counter", 0));
        super.loadAdditional(input);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        output.store("Inventory", CompoundTag.CODEC, this.itemHandler.serializeNBT(this.level.registryAccess()));
        output.putInt("Energy", this.energy.getEnergyStored());
        if (this.getGrindTime() != -1) output.putInt("CookTime", this.getGrindTime());
        if (this.getGrindLength() != -1) output.putInt("CookLength", this.getGrindLength());
        if (this.getXP() != 0) output.putFloat("XP", this.getXP());
        output.child("Info").putInt("Counter", counter);
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
        energyHandler.invalidate();
        items.invalidate();
    }


    private int playersInside;

    /**
     * Sets how many players have this tile's gui open. Handles swapping blockstate and sound effects.
     */
    public void setPlayersInside(int amt) {
        if (amt == playersInside) {
            return;
        }

        if (playersInside == 0 && amt > 0) {
            if (!level.isClientSide()) {
                this.getLevel().setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(GrinderBlock.IS_OPEN, true));
            }
            this.getLevel().playLocalSound(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.25f, 0.5f, false);
        }

        this.playersInside = amt;

        if (playersInside == 0) {
            if (!level.isClientSide()) {
                this.getLevel().setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(GrinderBlock.IS_OPEN, false));
            }
            this.getLevel().playLocalSound(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.25f, 0.5f, false);
        }

    }

    /**
     * Returns how many players have this tile's gui open.
     */
    public int getPlayersInside() {
        int amt = 0;
        float x = this.getBlockPos().getX();
        float y = this.getBlockPos().getY();
        float z = this.getBlockPos().getZ();
        float distance = 5.0F;
        for (Player playerentity : level.getEntitiesOfClass(Player.class, new AABB(x - distance, y - distance, z - distance, x + 1 + distance, y + 1 + distance, z + 1 + distance))) {
            if (playerentity.containerMenu instanceof GrinderContainer) {
                if (((GrinderContainer) playerentity.containerMenu).getBE().getBlockPos() == this.getBlockPos()) {
                    amt++;
                }
            }
        }
        return amt;
    }

    /**
     * Changed from 4 to 2 since it only has two slots
     */


    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    protected final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            if (level != null && !level.isClientSide()) {
                level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
            }
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return slot < SLOT_OUTPUT_1;
        }

        @Override
        @Nonnull
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (slot == SLOT_OUTPUT_1 && !simulate && (int) xp > 0) {
                getLevel().addFreshEntity(new ExperienceOrb(getLevel(), getBlockPos().getX() + 0.5f, getBlockPos().getY() + 1, getBlockPos().getZ() + 0.5f, (int) getXP()));
                setXP(getXP() - (int) getXP());
            }
            return super.extractItem(slot, amount, simulate);
        }

    };

    private final LazyOptional<IItemHandler> items = LazyOptional.of(() -> itemHandler);

    private final IItemHandler hopperhandler = new IItemHandler() {
        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return itemHandler.isItemValid(slot, stack);
        }

        @Override
        public int getSlots() {
            return itemHandler.getSlots();
        }

        @Override
        public ItemStack getStackInSlot(int slot) {
            return itemHandler.getStackInSlot(slot);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            return itemHandler.insertItem(slot, stack, simulate);
        }

        @Override
        public ItemStack extractItem(int slot, int amount, boolean simulate) {
            if (slot < SLOT_OUTPUT_1) {
                return ItemStack.EMPTY;
            }
            return itemHandler.extractItem(slot, amount, simulate);
        }

        @Override
        public int getSlotLimit(int slot) {
            return itemHandler.getSlotLimit(slot);
        }
    };
    private final LazyOptional<IItemHandler> hopper = LazyOptional.of(() -> hopperhandler);

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            if (side == Direction.DOWN) {
                return hopper.cast();
            }
            return items.cast();
        } else if (cap == ForgeCapabilities.ENERGY) {
            return energyHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }


    public GrinderBE(BlockPos pos, BlockState state) {
        super(blockEntityType.get(), pos, state);
    }


    private HempFarmerEnergyStorage createEnergyStorage() {
        return new HempFarmerEnergyStorage(GrinderConfig.ENERGY_CAPACITY.get(), GrinderConfig.ENERGY_RECEIVE.get()) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

}