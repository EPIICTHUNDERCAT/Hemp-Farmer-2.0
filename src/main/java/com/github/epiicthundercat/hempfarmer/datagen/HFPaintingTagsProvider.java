package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.HFPaintings;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.PaintingVariantTagsProvider;
import net.minecraft.tags.PaintingVariantTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.concurrent.CompletableFuture;

public class HFPaintingTagsProvider extends PaintingVariantTagsProvider {

    public HFPaintingTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
                                   ExistingFileHelper helper) {
        super(output, lookupProvider, HempFarmer.MODID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // Adds CANNABIS_FIELD to the minecraft:placeable tag so it can appear when placing a painting.
        tag(PaintingVariantTags.PLACEABLE)
            .addOptional(HFPaintings.CANNABIS_FIELD.location());
    }
}
