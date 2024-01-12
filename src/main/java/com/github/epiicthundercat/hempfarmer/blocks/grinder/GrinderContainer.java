package com.github.epiicthundercat.hempfarmer.blocks.grinder;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.github.epiicthundercat.hempfarmer.util.HempFarmerEnergyStorage;
import com.mojang.serialization.Decoder;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class GrinderContainer extends AbstractContainerMenu {

    private final GrinderBE blockEntity;
    protected final Level level;
    private final ContainerData data;
    private final Player playerEntity;
    private final IItemHandler playerInventory;

    public GrinderContainer(int ID, BlockPos pos, Inventory playerInventory, Player playerIn) {
        this(ID, pos, playerInventory, playerIn, new SimpleContainerData(2));
    }

    public GrinderContainer(int ID, BlockPos pos, Inventory playerInventory, Player player, ContainerData blockData) {
        super(Registration.GRINDER_CONTAINER.get(), ID);
        checkContainerDataCount(blockData, 2);
        this.blockEntity = (GrinderBE) player.getCommandSenderWorld().getBlockEntity(pos);
        this.playerEntity = player;
        this.level = playerInventory.player.level;
        this.playerInventory = new InvWrapper(playerInventory);
        this.data = blockData;
        if (blockEntity != null) {
            blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, blockEntity.SLOT_INPUT_1, 46, 31));
                addSlot(new SlotItemHandler(h, blockEntity.SLOT_OUTPUT_1, 118, 31));

            });
        }
        //tracks player inventory as well
        layoutPlayerInventorySlots(10, 70);
        trackPower();
    }

    public GrinderBE getBE() {
        return this.blockEntity;
    }

    // Setup syncing of power from server to client so that the GUI can show the amount of power in the block
    private void trackPower() {
        // Unfortunatelly on a dedicated server ints are actually truncated to short so we need
        // to split our integer here (split our 32 bit integer into two 16 bit integers)
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return getEnergy() & 0xffff;
            }

            @Override
            public void set(int value) {
                blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0xffff0000;
                    ((HempFarmerEnergyStorage) h).setEnergy(energyStored + (value & 0xffff));
                });
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (getEnergy() >> 16) & 0xffff;
            }

            @Override
            public void set(int value) {
                blockEntity.getCapability(CapabilityEnergy.ENERGY).ifPresent(h -> {
                    int energyStored = h.getEnergyStored() & 0x0000ffff;
                    ((HempFarmerEnergyStorage) h).setEnergy(energyStored | (value << 16));
                });
            }
        });
    }


    public int getGrindTime() {
        return this.data.get(0);
    }

    public int getGrindLength() {
        return this.data.get(1);
    }

    public int getEnergy() {
        return blockEntity.getCapability(CapabilityEnergy.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, Registration.GRINDER.get());
    }


    @Override
    public ItemStack quickMoveStack(Player playerIn, int index) {

        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack stack = slot.getItem();
            itemstack = stack.copy();
            //If is in our Block Slot, move to inventory
            if (index == 0) {
                if (!this.moveItemStackTo(stack, 1, 37, true)) {
                    return ItemStack.EMPTY;
                }
                slot.onQuickCraft(stack, itemstack);
            } else {
                //if item is grindable, add it to our slot // need to call recipe handler and pull item cook time // removed check, any item can be shift clicked, also, need to fix move from output to inv.
                // GrinderRecipeHandler recipe = getBE().canCraft();

                //  if (ForgeHooks.getBurnTime(stack, GrinderRecipeHandler.TYPE) > 0) {
                //  if (this.level.getRecipeManager().getRecipeFor(GrinderRecipeHandler.TYPE, new SimpleContainer(itemstack) , level)) {
                if (!this.moveItemStackTo(stack, 0, 1, false)) {
                    return ItemStack.EMPTY;
                    // }
                } else if (index < 28) {
                    //if its in the inv keep it in the inv
                    if (!this.moveItemStackTo(stack, 28, 37, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < 37 && !this.moveItemStackTo(stack, 1, 28, false)) {
                    return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (stack.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, stack);
        }

        return itemstack;
    }


    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }
}