package com.github.epiicthundercat.hempfarmer.blocks.powerbattery;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class PowerBatteryContainer extends AbstractContainerMenu {

    private final PowerBatteryBE blockEntity;
    private final Player playerEntity;
    private final IItemHandler playerInventory;
    private int energyCache = 0;
    private int burnTimeCache = 0;
    private int burnLengthCache = 0;

    public PowerBatteryContainer(int windowId, BlockPos pos, Inventory playerInventory, Player player) {
        super(Registration.POWER_BATTERY_CONTAINER.get(), windowId);
        blockEntity = (PowerBatteryBE) player.level().getBlockEntity(pos);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);

        if (blockEntity != null) {
            blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(h -> {
                addSlot(new SlotItemHandler(h, 0, 64, 24));
            });
        }
        layoutPlayerInventorySlots(9, 70);
        trackPower();

        // Pre-seed all caches from the client BE on open — same reason as energyCache in GrinderContainer.
        // burnTime/burnLength are plain DataSlots so they'd only arrive on change; reading the fresh
        // client BE avoids showing empty bars until the first tick update.
        if (blockEntity != null) {
            energyCache     = blockEntity.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::getEnergyStored).orElse(0);
            burnTimeCache   = blockEntity.getCounter();
            burnLengthCache = blockEntity.getBurnLength();
        }
    }

    // Syncs energy and fuel-burn progress from server to client via DataSlots.
    // Energy is split across two 16-bit slots because DataSlots are transmitted as signed shorts.
    // get() is called server-side each tick; set() updates local cache client-side on change.
    private void trackPower() {
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity.getCapability(ForgeCapabilities.ENERGY)
                        .map(IEnergyStorage::getEnergyStored).orElse(0) & 0xffff;
            }
            @Override
            public void set(int value) {
                energyCache = (energyCache & 0xffff0000) | (value & 0xffff);
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return (blockEntity.getCapability(ForgeCapabilities.ENERGY)
                        .map(IEnergyStorage::getEnergyStored).orElse(0) >> 16) & 0xffff;
            }
            @Override
            public void set(int value) {
                energyCache = (energyCache & 0x0000ffff) | (value << 16);
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity != null ? blockEntity.getCounter() : 0;
            }
            @Override
            public void set(int value) {
                burnTimeCache = value;
            }
        });
        addDataSlot(new DataSlot() {
            @Override
            public int get() {
                return blockEntity != null ? blockEntity.getBurnLength() : 0;
            }
            @Override
            public void set(int value) {
                burnLengthCache = value;
            }
        });
    }

    public int getEnergy() {
        return energyCache;
    }

    public int getMaxEnergy() {
        return blockEntity.getCapability(ForgeCapabilities.ENERGY).map(IEnergyStorage::getMaxEnergyStored).orElse(0);
    }

    public int getBurnTime() {
        return burnTimeCache;
    }

    public int getBurnLength() {
        return burnLengthCache;
    }

    @Override
    public boolean stillValid(Player playerIn) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()), playerEntity, Registration.POWER_BATTERY.get());
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
                //if item is smeltable, add it to our slot
                if (blockEntity.getLevel() != null && blockEntity.getLevel().fuelValues().isFuel(stack)) {
                    if (!this.moveItemStackTo(stack, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
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
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
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