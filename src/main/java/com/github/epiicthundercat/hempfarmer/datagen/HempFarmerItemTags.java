package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class HempFarmerItemTags extends ItemTagsProvider {


    public HempFarmerItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
        super(generator, blockTags, HempFarmer.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {


        tag(Tags.Items.SEEDS)
                .add(Registration.SEEDS_SATIVA.get())
                .add(Registration.SEEDS_INDICA.get())
                .add(Registration.SEEDS_HEMP.get());

        tag(Registration.BUD_ITEM)
                .add(Registration.SATIVA_BUD.get())
                .add(Registration.INDICA_BUD.get())
                .add(Registration.BUD.get());


        tag(Registration.PAPER_ITEM)
                .add(Registration.HEMP_PAPER.get())
                //.add(Registration.ROLLING_PAPER.get())
                .add(Items.PAPER);

        tag(Registration.DRY_HEMP_ITEM)
                .add(Registration.DRY_HEMP.get())
                .add(Registration.LIME_DRY_HEMP.get())
                .add(Registration.VIOLET_DRY_HEMP.get());

        tag(Registration.OILY_DIRT_ITEM_TAG)
                .add(Registration.OILY_DIRT_ITEM.get())
                .add(Registration.RESIN_DIRT_ITEM.get())
                .add(Registration.LIME_DIRT_ITEM.get())
                .add(Registration.VIOLET_DIRT_ITEM.get());

        tag(Registration.MILK_ITEM)
                .add(Registration.HEMP_MILK_BUCKET.get())
                .add(Items.MILK_BUCKET);

    }
//
//    private void registerModTags() {
//
//
//    }

    @Override
    public String getName() {
        return "Hemp Farmer Tags";
    }
}
