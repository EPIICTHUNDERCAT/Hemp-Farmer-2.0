package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class HempFarmerItemTags extends VanillaItemTagsProvider {

    public HempFarmerItemTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                               ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, HempFarmer.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(Registration.SEED_TRIAD)
                .add(Registration.SEEDS_SATIVA.get())
                .add(Registration.SEEDS_INDICA.get())
                .add(Registration.SEEDS_HEMP.get());

        tag(Tags.Items.SEEDS)
                .add(Registration.SEEDS_SATIVA.get())
                .add(Registration.SEEDS_INDICA.get())
                .add(Registration.SEEDS_HEMP.get());

        tag(Registration.BUD_ITEM)
                .add(Registration.SATIVA_BUD.get())
                .add(Registration.INDICA_BUD.get())
                .add(Registration.BUD.get());

        tag(Tags.Items.SLIME_BALLS)
                .add(Items.SLIME_BALL)
                .add(Registration.RESIN.get());

        tag(Registration.PAPER_ITEM)
                .add(Registration.HEMP_PAPER.get())
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

        tag(Registration.OIL)
                .add(Registration.HEMP_OIL.get())
                .add(Registration.LIME_OIL.get())
                .add(Registration.VIOLET_OIL.get());
    }

    @Override
    public String getName() {
        return "Hemp Farmer Item Tags";
    }
}
