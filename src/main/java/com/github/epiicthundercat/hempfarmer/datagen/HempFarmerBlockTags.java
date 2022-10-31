package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class HempFarmerBlockTags extends BlockTagsProvider {

    public HempFarmerBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, HempFarmer.MODID, helper);
    }

    @Override
    protected void addTags() {

        tag(net.minecraft.tags.BlockTags.CROPS)
                .add(Registration.HEMP_CROP.get())
                .add(Registration.SATIVA_CROP.get())
                .add(Registration.INDICA_CROP.get());
        tag(net.minecraft.tags.BlockTags.MINEABLE_WITH_PICKAXE)
                .add(Registration.GRINDER.get())
                .add(Registration.POWER_BATTERY.get());
        tag(net.minecraft.tags.BlockTags.NEEDS_IRON_TOOL)
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
        return "Hemp Farmer Tags";
    }
}