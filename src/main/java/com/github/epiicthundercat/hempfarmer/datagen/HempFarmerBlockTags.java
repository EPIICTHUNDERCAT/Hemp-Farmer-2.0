package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class HempFarmerBlockTags extends BlockTagsProvider {

    public HempFarmerBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper helper) {
        super(output, lookupProvider, HempFarmer.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.CROPS)
                .add(Registration.HEMP_CROP.get())
                .add(Registration.SATIVA_CROP.get())
                .add(Registration.INDICA_CROP.get());
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.GRINDER.get())
                .add(Registration.POWER_BATTERY.get());
        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(Registration.GRINDER.get())
                .add(Registration.POWER_BATTERY.get());
        tag(BlockTags.DIRT)
                .add(Registration.LIME_DIRT.get())
                .add(Registration.OILY_DIRT.get())
                .add(Registration.RESIN_DIRT.get())
                .add(Registration.VIOLET_DIRT.get());
        tag(Registration.OILY_DIRT_TAG)
                .add(Registration.LIME_DIRT.get())
                .add(Registration.OILY_DIRT.get())
                .add(Registration.RESIN_DIRT.get())
                .add(Registration.VIOLET_DIRT.get());
    }

    @Override
    public String getName() {
        return "Hemp Farmer Block Tags";
    }
}
