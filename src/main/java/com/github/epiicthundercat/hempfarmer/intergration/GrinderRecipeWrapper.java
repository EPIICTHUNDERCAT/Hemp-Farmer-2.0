package com.github.epiicthundercat.hempfarmer.intergration;

import net.minecraft.world.item.ItemStack;

import java.util.List;

public class GrinderRecipeWrapper {

    private final List<List<ItemStack>>  INPUTS;
    private final ItemStack OUTPUT;

    public GrinderRecipeWrapper(List<List<ItemStack>> input, ItemStack output) {
        INPUTS = input;
        OUTPUT = output;
    }

    public List<List<ItemStack>> getInputs() {
        return INPUTS;
    }

    public ItemStack getOutput() {
        return OUTPUT;
    }
}
