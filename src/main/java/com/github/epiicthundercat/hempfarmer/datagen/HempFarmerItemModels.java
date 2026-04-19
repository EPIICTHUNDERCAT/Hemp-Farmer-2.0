package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class HempFarmerItemModels extends ItemModelProvider {

    public HempFarmerItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, HempFarmer.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //BURLAP
        singleTexture(Registration.BURLAP_HELMET.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/burlap_helmet"));
        singleTexture(Registration.BURLAP_CHESTPLATE.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/burlap_chestplate"));
        singleTexture(Registration.BURLAP_LEGGINGS.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/burlap_leggings"));
        singleTexture(Registration.BURLAP_BOOTS.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/burlap_boots"));

        //SATIVA
        singleTexture(Registration.SATIVA_JOINT.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/sativa_joint"));
        singleTexture(Registration.SATIVA_BUD.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/sativa_bud"));
        singleTexture(Registration.GROUND_SATIVA_BUD.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/ground_sativa_bud"));
        singleTexture(Registration.SEEDS_SATIVA.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/seeds_sativa"));
        singleTexture(Registration.SEEDS_SATIVA_CRUSHED.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/seeds_sativa_crushed"));
        singleTexture(Registration.SEEDS_SATIVA_TOASTED.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/seeds_sativa_toasted"));

        //LIME
        singleTexture(Registration.LIME_BURLAP_ITEM.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/lime_burlap"));
        singleTexture(Registration.LIME_DRY_HEMP.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/lime_dry_hemp"));
        singleTexture(Registration.LIME_HEMP_HEARTS.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/lime_hemp_hearts"));
        singleTexture(Registration.LIME_OIL.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/lime_oil"));
        singleTexture(Registration.LIME_RAW_HEMP.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/lime_raw_hemp"));
        singleTexture(Registration.BOWL_LIME_HEMP_HEARTS.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/bowl_lime_hemp_hearts"));

        //HEMP
        singleTexture(Registration.SEEDS_HEMP_CRUSHED.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/seeds_hemp_crushed"));
        singleTexture(Registration.SEEDS_HEMP_TOASTED.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/seeds_hemp_toasted"));
        singleTexture(Registration.HEMP_HEARTS.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/hemp_hearts"));
        singleTexture(Registration.BUD.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/bud"));
        singleTexture(Registration.GROUND_BUD.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/ground_bud"));
        singleTexture(Registration.REGS_JOINT.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/regs_joint"));
        singleTexture(Registration.HEMP_OIL.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/hemp_oil"));
        singleTexture(Registration.HEMP_MILK_BUCKET.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/hemp_milk_bucket"));
        singleTexture(Registration.HEMP_PAPER.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/hemp_paper"));
        singleTexture(Registration.BOWL_HEMP_HEARTS.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/bowl_hemp_hearts"));
        singleTexture(Registration.DRY_HEMP.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/dry_hemp"));
        singleTexture(Registration.RAW_HEMP.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/raw_hemp"));
        singleTexture(Registration.SEEDS_HEMP.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/seeds_hemp"));

        //VIOLET / INDICA
        singleTexture(Registration.VIOLET_RAW_HEMP.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/violet_raw_hemp"));
        singleTexture(Registration.VIOLET_HEMP_HEARTS.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/violet_hemp_hearts"));
        singleTexture(Registration.BOWL_VIOLET_HEMP_HEARTS.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/bowl_violet_hemp_hearts"));
        singleTexture(Registration.SEEDS_INDICA.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/seeds_indica"));
        singleTexture(Registration.SEEDS_INDICA_CRUSHED.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/seeds_indica_crushed"));
        singleTexture(Registration.SEEDS_INDICA_TOASTED.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/seeds_indica_toasted"));
        singleTexture(Registration.VIOLET_BURLAP_ITEM.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/violet_burlap"));
        singleTexture(Registration.VIOLET_OIL.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/violet_oil"));
        singleTexture(Registration.VIOLET_DRY_HEMP.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/violet_dry_hemp"));
        singleTexture(Registration.INDICA_JOINT.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/indica_joint"));
        singleTexture(Registration.INDICA_BUD.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/indica_bud"));
        singleTexture(Registration.GROUND_INDICA_BUD.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/ground_indica_bud"));

        //RANDOM
        singleTexture(Registration.BROKEN_SUPERIOR_LEAF_WAND.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/broken_superior_leaf_wand"));
        singleTexture(Registration.LEAF_WAND.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/leaf_wand"));
        singleTexture(Registration.SUPERIOR_LEAF_WAND.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/superior_leaf_wand"));
        singleTexture(Registration.NELLY_SONG_MUSIC_DISC.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/nelly_song_music_disc"));
        singleTexture(Registration.RESIN.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/resin"));
        singleTexture(Registration.BURLAP_ITEM.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/burlap"));
        singleTexture(Registration.SHOT_LEAF.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/shot_leaf"));
        singleTexture(Registration.ROLLING_PAPER.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/rolling_paper"));
        singleTexture(Registration.RESIN_BURLAP_ITEM.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/resin_burlap"));
        singleTexture(Registration.OILY_BURLAP_ITEM.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/oily_burlap"));
        singleTexture(Registration.LEAF.getId().getPath(), mcLoc("item/generated"), "layer0", ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID,"item/leaf"));

        // Block item models
        withExistingParent(Registration.GRINDER_ITEM.getId().getPath(), modLoc("block/grinder"));
        withExistingParent(Registration.OILY_DIRT_ITEM.getId().getPath(), modLoc("block/oily_dirt"));
        withExistingParent(Registration.LIME_DIRT_ITEM.getId().getPath(), modLoc("block/lime_dirt"));
        withExistingParent(Registration.VIOLET_DIRT_ITEM.getId().getPath(), modLoc("block/violet_dirt"));
        withExistingParent(Registration.RESIN_DIRT_ITEM.getId().getPath(), modLoc("block/resin_dirt"));
        withExistingParent(Registration.BURLAP_BLOCK_ITEM.getId().getPath(), modLoc("block/burlap_carpet"));
        withExistingParent(Registration.OILY_BURLAP_BLOCK_ITEM.getId().getPath(), modLoc("block/oily_burlap_carpet"));
        withExistingParent(Registration.LIME_BURLAP_BLOCK_ITEM.getId().getPath(), modLoc("block/lime_burlap_carpet"));
        withExistingParent(Registration.VIOLET_BURLAP_BLOCK_ITEM.getId().getPath(), modLoc("block/violet_burlap_carpet"));
        withExistingParent(Registration.RESIN_BURLAP_BLOCK_ITEM.getId().getPath(), modLoc("block/resin_carpet"));
        withExistingParent(Registration.POWER_BATTERY_ITEM.getId().getPath(), modLoc("block/powerbattery_off"));
    }
}
