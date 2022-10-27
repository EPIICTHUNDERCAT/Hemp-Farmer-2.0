package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class HempFarmerItemModels extends ItemModelProvider {


    public HempFarmerItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, HempFarmer.MODID, existingFileHelper);
    }


    @Override
    protected void registerModels() {
        //SATIVA
        singleTexture(Registration.SATIVA_JOINT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/sativa_joint"));
        singleTexture(Registration.SATIVA_BUD.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/sativa_bud"));
        singleTexture(Registration.SEEDS_SATIVA.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/seeds_sativa"));
        singleTexture(Registration.SEEDS_SATIVA_CRUSHED.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/seeds_sativa_crushed"));
        singleTexture(Registration.SEEDS_SATIVA_TOASTED.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/seeds_sativa_toasted"));
        //LIME
        singleTexture(Registration.LIME_BURLAP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/lime_burlap"));
        singleTexture(Registration.LIME_DRY_HEMP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/lime_dry_hemp"));
        singleTexture(Registration.LIME_HEMP_HEARTS.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/lime_hemp_hearts"));
        singleTexture(Registration.LIME_OIL.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/lime_oil"));
        singleTexture(Registration.LIME_RAW_HEMP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/lime_raw_hemp"));
        singleTexture(Registration.BOWL_LIME_HEMP_HEARTS.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/bowl_lime_hemp_hearts"));

        //HEMP
        singleTexture(Registration.SEEDS_HEMP_CRUSHED.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/seeds_hemp_crushed"));
        singleTexture(Registration.SEEDS_HEMP_TOASTED.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/seeds_hemp_toasted"));
        singleTexture(Registration.HEMP_HEARTS.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/hemp_hearts"));
        singleTexture(Registration.BUD.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/bud"));
        singleTexture(Registration.REGS_JOINT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/regs_joint"));
        singleTexture(Registration.HEMP_OIL.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/hemp_oil"));
        singleTexture(Registration.HEMP_MILK_BUCKET.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/hemp_milk_bucket"));
        singleTexture(Registration.HEMP_PAPER.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/hemp_paper"));
        singleTexture(Registration.BOWL_HEMP_HEARTS.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/bowl_hemp_hearts"));
        singleTexture(Registration.DRY_HEMP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/dry_hemp"));
        singleTexture(Registration.RAW_HEMP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/raw_hemp"));
        singleTexture(Registration.SEEDS_HEMP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/seeds_hemp"));

        //VIOLET / INDICA
        singleTexture(Registration.VIOLET_RAW_HEMP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/violet_raw_hemp"));
        singleTexture(Registration.VIOLET_HEMP_HEARTS.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/violet_hemp_hearts"));
        singleTexture(Registration.BOWL_VIOLET_HEMP_HEARTS.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/bowl_violet_hemp_hearts"));
        singleTexture(Registration.SEEDS_INDICA.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/seeds_indica"));
        singleTexture(Registration.SEEDS_INDICA_CRUSHED.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/seeds_indica_crushed"));
        singleTexture(Registration.SEEDS_INDICA_TOASTED.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/seeds_indica_toasted"));
        singleTexture(Registration.VIOLET_BURLAP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/violet_burlap"));
        singleTexture(Registration.VIOLET_OIL.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/violet_oil"));
        singleTexture(Registration.VIOLET_DRY_HEMP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/violet_dry_hemp"));
        singleTexture(Registration.INDICA_JOINT.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/indica_joint"));
        singleTexture(Registration.INDICA_BUD.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/indica_bud"));


        //RANDOM
        singleTexture(Registration.BROKEN_SUPERIOR_LEAF_WAND.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/broken_superior_leaf_wand"));
        singleTexture(Registration.LEAF_WAND.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/leaf_wand"));

        singleTexture(Registration.SUPERIOR_LEAF_WAND.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/superior_leaf_wand"));

        singleTexture(Registration.POT_BROWNIE.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/pot_brownie"));

        singleTexture(Registration.RESIN.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/resin"));
        singleTexture(Registration.HEMP_HEARTS.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/hemp_hearts"));

        singleTexture(Registration.BURLAP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/burlap"));


        singleTexture(Registration.SHOT_LEAF.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/shot_leaf"));

        singleTexture(Registration.ROLLING_PAPER.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/rolling_paper"));


        singleTexture(Registration.RESIN_BURLAP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/resin_burlap"));

        singleTexture(Registration.OILY_BURLAP.get().getRegistryName().getPath(),
                mcLoc("item/generated"),
                "layer0", new ResourceLocation(HempFarmer.MODID, "items/oily_burlap"));


        //BLOCK ITEMs
        // withExistingParent(Registration.GENERATOR_ITEM.get().getRegistryName().getPath(), modLoc("block/generator"));
        withExistingParent(Registration.POWER_BATTERY_ITEM.get().getRegistryName().getPath(), modLoc("block/powerbattery_off"));
    }

}
