package com.github.epiicthundercat.hempfarmer.blocks.grinder;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.github.epiicthundercat.hempfarmer.util.HempFarmerEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.IntTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * BUGS:
 * Power drain when crafting recipe continues stopping craft process
 * * -Power Drain not smooth/Overtime // May supplement with a progress bar using the cooktime value... // FIXED
 * * -Running recipes TWICE, consuming twice as much power // FIXED!
 * -Proccessing without power // FIXED ADDED CHECK
 * -Unable to shift Click into // Kinda Fixed...
 * -Items do not stack in output // FIXEDDDD FIXED SLOTs
 * -Breaking voids items! // FIXED - Had to do with Data Tags being named wrong. inv is now saved via NBT
 * -Visuals obviously // FIXED
 */

public class GrinderBE extends BlockEntity {


    private final HempFarmerEnergyStorage energy = createEnergyStorage();
    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> energy);
    public final int SLOT_INPUT_1 = 0;
    public final int SLOT_OUTPUT_1 = 1;
    private int grindTime = -1;
    private int grindLength = -1;
    private float xp = 0f;

    private int counter;


    protected final ContainerData blockData = new ContainerData() {
        public int get(int id) {
            switch (id) {
                case 0:
                    return GrinderBE.this.grindTime;
                case 1:
                    return GrinderBE.this.grindLength;
                default:
                    return 0;
            }
        }

        public void set(int id, int value) {
            switch (id) {
                case 0:
                    GrinderBE.this.grindTime = value;
                    break;
                case 1:
                    GrinderBE.this.grindLength = value;
                    break;
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

            GrinderRecipeHandler recipe = canCraft();
            if (recipe != null && !(capacity.get() <= 0)) {
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

                        startCraft(recipe);

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
        if (energy.getEnergyStored() < GrinderConfig.ENERGY_NEED.get()) {
            return null;
        }
        GrinderRecipeHandler recipe = getRecipeFromContents();
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
             * Checks the item in the recipe output, if the item in the output is the same, and its stackable, and the count is less than max stack size, it will att it.
             */

            ItemStack output = itemHandler.getStackInSlot(SLOT_OUTPUT_1);
            if (recipe.getResultItem().getItem() == output.getItem() && output.isStackable() && output.getCount() + recipe.getResultItem().getCount() <= output.getMaxStackSize()) {

                return recipe;
            }
        }
        return null;
    }

    /**
     * Returns the recipe that can be crafted from this tile's input slots.
     */
    public GrinderRecipeHandler getRecipeFromContents() {
        GrinderRecipeHandler toCraft = null;
        for (final GrinderRecipeHandler recipe : level.getRecipeManager().getAllRecipesFor(GrinderRecipeHandler.TYPE)) {
            if (recipe.matches(itemHandler)) {
                toCraft = recipe;
                break;
            }

        }
        return toCraft;
    }

    /**
     * Called when the craft progress starts. Updates blockstate and sets cook length from recipe.
     */
    public void startCraft(GrinderRecipeHandler recipe) {
        System.out.println("HOW MANY TIMES ARE WE RUNNING START CRAFT IN ONE ITERATION???");
        // Not enough energy, don't even try
        if (energy.getEnergyStored() < GrinderConfig.ENERGY_NEED.get()) {

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

        Map<ItemStack, Boolean> inventoryItems = new HashMap<>();
        for (int slot = 0; slot < 1; slot++) {
            inventoryItems.put(itemHandler.getStackInSlot(slot), false);
        }
        for (Ingredient ingredient : recipe.getIngredients()) {
            for (int slot = 0; slot < 1; slot++) {
                if (ingredient.test(itemHandler.getStackInSlot(slot))) {
                    if (!inventoryItems.get(itemHandler.getStackInSlot(slot))) {
                        inventoryItems.remove(itemHandler.getStackInSlot(slot));
                        inventoryItems.put(itemHandler.getStackInSlot(slot), true);
                        itemHandler.getStackInSlot(slot).shrink(ingredient.getItems()[0].getCount());
                    }
                }
            }
        }

        ItemStack output = itemHandler.getStackInSlot(SLOT_OUTPUT_1);
        if (output.isEmpty()) {
            itemHandler.setStackInSlot(SLOT_OUTPUT_1, recipe.getOutput());
        }
        if (output.isStackable()) {
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


    /**
     * These are the data components that save to the block - how it maintains energy and items after being broken and placed!
     */
    @Override
    public void load(CompoundTag tag) {


        if (tag.contains("Inventory")) {
            this.itemHandler.deserializeNBT(tag.getCompound("Inventory"));
        }
        if (tag.contains("Energy")) {
            this.energy.deserializeNBT(tag.get("Energy"));
        }
        if (tag.contains("CookTime", IntTag.TAG_INT)) {
            this.grindTime = tag.getInt("CookTime");
        }
        if (tag.contains("CookLength", IntTag.TAG_INT)) {
            this.grindLength = tag.getInt("CookLength");
        }
        if (tag.contains("XP", IntTag.TAG_FLOAT)) {
            this.xp = tag.getFloat("XP");
        }
        if (tag.contains("Info")) {
            this.counter = tag.getCompound("Info").getInt("Counter");
        }
        super.load(tag);
    }

    /**
     * These are the data components that save to the block - how it maintains energy and items after being broken and placed!
     */

    @Override
    public void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", itemHandler.serializeNBT());
        tag.put("Energy", energy.serializeNBT());
        if (this.getGrindTime() != -1) {
            tag.putInt("CookTime", this.getGrindTime());
        }
        if (this.getGrindLength() != -1) {
            tag.putInt("CookLength", this.getGrindTime());
        }
        if (this.getXP() != 0) {
            tag.putFloat("XP", this.getXP());
        }
        CompoundTag infoTag = new CompoundTag();
        infoTag.putInt("Counter", counter);
        tag.put("Info", infoTag);

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
            if (!level.isClientSide) {
                this.getLevel().setBlockAndUpdate(this.getBlockPos(), this.getBlockState().setValue(GrinderBlock.IS_OPEN, true));
            }
            this.getLevel().playLocalSound(this.getBlockPos().getX(), this.getBlockPos().getY(), this.getBlockPos().getZ(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS, 0.25f, 0.5f, false);
        }

        this.playersInside = amt;

        if (playersInside == 0) {
            if (!level.isClientSide) {
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
     *   Changed from 4 to 2 since it only has two slots
     */



    protected final ItemStackHandler itemHandler = new ItemStackHandler(2) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
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
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            if (side == Direction.DOWN) {
                return hopper.cast();
            }
            return items.cast();
        } else if (cap == CapabilityEnergy.ENERGY) {
            return energyHandler.cast();
        } else {
            return super.getCapability(cap, side);
        }
    }


    public GrinderBE(BlockPos pos, BlockState state) {
        super(Registration.GRINDER_BE.get(), pos, state);
    }


    private HempFarmerEnergyStorage createEnergyStorage() {
        return new HempFarmerEnergyStorage(GrinderConfig.ENERGY_CAPACITY.get(), GrinderConfig.ENERGY_RECEIVE.get()) {
            @Override
            protected void onEnergyChanged() {
                setChanged();
            }
        };
    }

    /**
     * The getUpdateTag()/handleUpdateTag() pair is called whenever the client receives a new chunk
     * it hasn't seen before. i.e. the chunk is loaded
     */
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = super.getUpdateTag();
        this.saveAdditional(tag);
        return tag;
    }



//    @Override
//    public void handleUpdateTag(CompoundTag tag) {
//        if (tag != null) {
//        }
//    }


}