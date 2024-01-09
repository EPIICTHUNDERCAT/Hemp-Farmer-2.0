package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.function.Consumer;

public class HempFarmerRecipes extends RecipeProvider {

    public HempFarmerRecipes(DataGenerator generator) {
        super(generator);
    }


    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {


        //Pot Brownie

        ShapedRecipeBuilder.shaped(Registration.POT_BROWNIE.get())
                .pattern("mxm")
                .pattern("bsb")
                .pattern("#x#")
                .define('x', Registration.MILK_ITEM)
                .define('#', Tags.Items.EGGS)
                .define('m', Items.COCOA_BEANS)
                .define('s', Items.SUGAR)
                .define('b', Registration.BUD_ITEM)
                .group("hempfarmer")
                .unlockedBy("cocoa", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.HEMP_MILK_BUCKET.get()))
                .save(consumer);


        //Lime Hemp Hearts
        ShapedRecipeBuilder.shaped(Registration.LIME_HEMP_HEARTS.get())
                .pattern("m m")
                .pattern("mmm")
                .pattern(" m ")


                .define('m', Registration.SEEDS_SATIVA_CRUSHED.get())
                .group("hempfarmer")
                .unlockedBy("hemp_hearts", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_SATIVA_CRUSHED.get()))
                .save(consumer);

        //Violet Hemp Hearts
        ShapedRecipeBuilder.shaped(Registration.VIOLET_HEMP_HEARTS.get())
                .pattern("m m")
                .pattern("mmm")
                .pattern(" m ")

                .define('m', Registration.SEEDS_INDICA_CRUSHED.get())
                .group("hempfarmer")
                .unlockedBy("hemp_hearts", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_INDICA_CRUSHED.get()))
                .save(consumer);

        //Original Hemp Hearts
        ShapedRecipeBuilder.shaped(Registration.HEMP_HEARTS.get())
                .pattern("m m")
                .pattern("mmm")
                .pattern(" m ")

                .define('m', Registration.SEEDS_HEMP_CRUSHED.get())
                .group("hempfarmer")
                .unlockedBy("hemp_hearts", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_HEMP_CRUSHED.get()))
                .save(consumer);


        //Violet Oil
        ShapedRecipeBuilder.shaped(Registration.VIOLET_OIL.get())
                .pattern("mmm")
                .pattern("xmx")
                .pattern(" x ")
                .define('x', Tags.Items.GLASS)

                .define('m', Registration.SEEDS_INDICA.get())
                .group("hempfarmer")
                .unlockedBy("oily", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_INDICA.get()))
                .save(consumer);

        //Lime Oil
        ShapedRecipeBuilder.shaped(Registration.LIME_OIL.get())
                .pattern("mmm")
                .pattern("xmx")
                .pattern(" x ")
                .define('x', Tags.Items.GLASS)

                .define('m', Registration.SEEDS_SATIVA.get())
                .group("hempfarmer")
                .unlockedBy("oily", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_SATIVA.get()))
                .save(consumer);

        //Hemp Oil
        ShapedRecipeBuilder.shaped(Registration.HEMP_OIL.get())
                .pattern("mmm")
                .pattern("xmx")
                .pattern(" x ")
                .define('x', Tags.Items.GLASS)

                .define('m', Registration.SEEDS_HEMP.get())
                .group("hempfarmer")
                .unlockedBy("oily", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_HEMP.get()))
                .save(consumer);


        //Power Battery
        ShapedRecipeBuilder.shaped(Registration.POWER_BATTERY.get())
                .pattern("mxm")
                .pattern("x#x")
                .pattern("#x#")
                .define('x', Tags.Items.INGOTS_IRON)
                .define('#', Tags.Items.DUSTS_REDSTONE)
                .define('m', Tags.Items.INGOTS_GOLD)
                .group("hempfarmer")
                .unlockedBy("powah", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .save(consumer);


        //Grinder
        ShapedRecipeBuilder.shaped(Registration.GRINDER.get())
                .pattern("mxm")
                .pattern("x#x")
                .pattern("#x#")
                .define('x', Tags.Items.INGOTS_IRON)
                .define('#', Tags.Items.DUSTS_REDSTONE)
                .define('m', Tags.Items.INGOTS_GOLD)
                .group("hempfarmer")
                .unlockedBy("grinder", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .save(consumer);
        //Leaf Wand
        ShapedRecipeBuilder.shaped(Registration.LEAF_WAND.get())
                .pattern(" x ")
                .pattern("s  ")
                .pattern("   ")
                .define('x', Registration.LEAF.get())
                .define('s', Items.STICK)
                .group("hempfarmer")
                .unlockedBy("leaf", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LEAF.get()))
                .save(consumer);


        //Regs Joint
        ShapelessRecipeBuilder.shapeless(Registration.REGS_JOINT.get())
                .requires(Registration.ROLLING_PAPER.get())
                .requires(Registration.BUD.get())
                .requires(Registration.BUD.get())
                .requires(Registration.BUD.get())
                .group("hempfarmer")
                .unlockedBy("bud", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BUD.get()))
                .save(consumer);

        //Sativa Joint
        ShapelessRecipeBuilder.shapeless(Registration.SATIVA_JOINT.get())
                .requires(Registration.ROLLING_PAPER.get())
                .requires(Registration.SATIVA_BUD.get())
                .requires(Registration.SATIVA_BUD.get())
                .requires(Registration.SATIVA_BUD.get())
                .group("hempfarmer")
                .unlockedBy("sativa_bud", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SATIVA_BUD.get()))
                .save(consumer);

        //Indica Joint
        ShapelessRecipeBuilder.shapeless(Registration.INDICA_JOINT.get())
                .requires(Registration.ROLLING_PAPER.get())
                .requires(Registration.INDICA_BUD.get())
                .requires(Registration.INDICA_BUD.get())
                .requires(Registration.INDICA_BUD.get())
                .group("hempfarmer")
                .unlockedBy("indica_bud", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.INDICA_BUD.get()))
                .save(consumer);

        //Hemp Seedling
        ShapelessRecipeBuilder.shapeless(Registration.HEMP_CROP_ITEM.get())
                .requires(Registration.SEEDS_HEMP.get())
                .group("hempfarmer")
                .unlockedBy("seedlings", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_HEMP.get()))
                .save(consumer);
        //Indica Seedling
        ShapelessRecipeBuilder.shapeless(Registration.INDICA_CROP_ITEM.get())
                .requires(Registration.SEEDS_INDICA.get())
                .group("hempfarmer")
                .unlockedBy("seedlings", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_INDICA.get()))
                .save(consumer);

        //Sativa Seedling
        ShapelessRecipeBuilder.shapeless(Registration.SATIVA_CROP_ITEM.get())
                .requires(Registration.SEEDS_SATIVA.get())
                .group("hempfarmer")
                .unlockedBy("seedlings", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_SATIVA.get()))
                .save(consumer);


        //Hemp Paper
        ShapelessRecipeBuilder.shapeless(Registration.HEMP_PAPER.get())
                .requires(Registration.DRY_HEMP_ITEM)
                .requires(Registration.DRY_HEMP_ITEM)
                .group("hempfarmer")
                .unlockedBy("dry_hemp", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Registration.DRY_HEMP_ITEM).build()))
                .save(consumer);


        //Burlap
        ShapedRecipeBuilder.shaped(Registration.BURLAP_ITEM.get())
                .pattern("xx ")
                .pattern("xx ")
                .pattern("   ")
                .define('x', Registration.DRY_HEMP.get())
                .group("hempfarmer")
                .unlockedBy("burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.DRY_HEMP.get()))
                .save(consumer);

        //Oily Burlap
        ShapelessRecipeBuilder.shapeless(Registration.OILY_BURLAP_ITEM.get())
                .requires(Registration.HEMP_OIL.get())
                .requires(Registration.BURLAP_ITEM.get())
                .group("hempfarmer")
                .unlockedBy("oily_burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.HEMP_OIL.get()))
                .save(consumer);
        //Oily dirt
        ShapelessRecipeBuilder.shapeless(Registration.OILY_DIRT.get())
                .requires(Registration.HEMP_OIL.get())
                .requires(Registration.OILY_DIRT_ITEM_TAG)
                .group("hempfarmer")
                .unlockedBy("oily_burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.HEMP_OIL.get()))
                .save(consumer);


        //lime dirt
        ShapelessRecipeBuilder.shapeless(Registration.LIME_DIRT.get())
                .requires(Registration.LIME_OIL.get())
                .requires(Registration.OILY_DIRT_ITEM_TAG)
                .group("hempfarmer")
                .unlockedBy("lime_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LIME_OIL.get()))
                .save(consumer);
        //Resin dirt
        ShapelessRecipeBuilder.shapeless(Registration.RESIN_DIRT.get())
                .requires(Registration.RESIN.get())
                .requires(Registration.OILY_DIRT_ITEM_TAG)
                .group("hempfarmer")
                .unlockedBy("resin", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.RESIN.get()))
                .save(consumer);


        //VIOLET BURLAP
        ShapedRecipeBuilder.shaped(Registration.VIOLET_BURLAP_ITEM.get())
                .pattern("xx ")
                .pattern("xx ")
                .pattern("   ")
                .define('x', Registration.VIOLET_DRY_HEMP.get())
                .group("hempfarmer")
                .unlockedBy("violet_burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.VIOLET_DRY_HEMP.get()))
                .save(consumer);

        //LIME BURLAP
        ShapedRecipeBuilder.shaped(Registration.LIME_BURLAP_ITEM.get())
                .pattern("xx ")
                .pattern("xx ")
                .pattern("   ")
                .define('x', Registration.LIME_DRY_HEMP.get())
                .group("hempfarmer")
                .unlockedBy("lime_burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LIME_DRY_HEMP.get()))
                .save(consumer);


        //violet dirt
        ShapelessRecipeBuilder.shapeless(Registration.VIOLET_DIRT.get())
                .requires(Registration.VIOLET_OIL.get())
                .requires(Registration.OILY_DIRT_ITEM_TAG)
                .group("hempfarmer")
                .unlockedBy("violet_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.VIOLET_OIL.get()))
                .save(consumer);

        //Hemp Bowl
        ShapelessRecipeBuilder.shapeless(Registration.BOWL_HEMP_HEARTS.get())
                .requires(Registration.HEMP_HEARTS.get())
                .requires(Registration.MILK_ITEM)
                .requires(Items.BOWL)
                .group("hempfarmer")
                .unlockedBy("hemp_bowl", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.HEMP_HEARTS.get()))
                .save(consumer);

        //Lime Hemp Bowl
        ShapelessRecipeBuilder.shapeless(Registration.BOWL_LIME_HEMP_HEARTS.get())
                .requires(Registration.LIME_HEMP_HEARTS.get())
                .requires(Registration.MILK_ITEM)
                .requires(Items.BOWL)
                .group("hempfarmer")
                .unlockedBy("lime_hemp_bowl", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LIME_HEMP_HEARTS.get()))
                .save(consumer);

        //Violet Hemp Bowl
        ShapelessRecipeBuilder.shapeless(Registration.BOWL_VIOLET_HEMP_HEARTS.get())
                .requires(Registration.VIOLET_HEMP_HEARTS.get())
                .requires(Registration.MILK_ITEM)
                .requires(Items.BOWL)
                .group("hempfarmer")
                .unlockedBy("violet_hemp_bowl", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.VIOLET_HEMP_HEARTS.get()))
                .save(consumer);
        //Rolling paper

        ShapelessRecipeBuilder.shapeless(Registration.ROLLING_PAPER.get(), 3)
                .requires(Registration.PAPER_ITEM)

                .group("hempfarmer")
                .unlockedBy("rolling_paper", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Registration.PAPER_ITEM).build()))
                .save(consumer);

        //Resin Burlap

        ShapelessRecipeBuilder.shapeless(Registration.RESIN_BURLAP_ITEM.get(), 1)
                .requires(Registration.RESIN.get())
                .requires(Registration.BURLAP_ITEM.get())
                .group("hempfarmer")
                .unlockedBy("resin", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Registration.RESIN.get()).build()))
                .save(consumer);

        //Violet Carpet
        ShapedRecipeBuilder.shaped(Registration.VIOLET_BURLAP_CARPET_BLOCK.get())
                .pattern("xxx")
                .pattern("   ")
                .pattern("   ")
                .define('x', Registration.VIOLET_BURLAP_ITEM.get())
                .group("hempfarmer")
                .unlockedBy("violet_burlap_carpet", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.VIOLET_BURLAP_ITEM.get()))
                .save(consumer);
        //Lime Carpet
        ShapedRecipeBuilder.shaped(Registration.LIME_BURLAP_CARPET_BLOCK.get())
                .pattern("xxx")
                .pattern("   ")
                .pattern("   ")
                .define('x', Registration.LIME_BURLAP_ITEM.get())
                .group("hempfarmer")
                .unlockedBy("lime_burlap_carpet", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LIME_BURLAP_ITEM.get()))
                .save(consumer);
        //Burlap Carpet
        ShapedRecipeBuilder.shaped(Registration.BURLAP_CARPET_BLOCK.get())
                .pattern("xxx")
                .pattern("   ")
                .pattern("   ")
                .define('x', Registration.BURLAP_ITEM.get())
                .group("hempfarmer")
                .unlockedBy("burlap_carpet", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BURLAP_ITEM.get()))
                .save(consumer);
        //Oily Carpet
        ShapedRecipeBuilder.shaped(Registration.OILY_BURLAP_CARPET_BLOCK.get())
                .pattern("xxx")
                .pattern("   ")
                .pattern("   ")
                .define('x', Registration.OILY_BURLAP_ITEM.get())
                .group("hempfarmer")
                .unlockedBy("oily_burlap_carpet", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.OILY_BURLAP_ITEM.get()))
                .save(consumer);
        //Resin Carpet
        ShapedRecipeBuilder.shaped(Registration.RESIN_CARPET_BLOCK.get())
                .pattern("xxx")
                .pattern("   ")
                .pattern("   ")
                .define('x', Registration.RESIN_BURLAP_ITEM.get())
                .group("hempfarmer")
                .unlockedBy("resin_burlap_carpet", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.RESIN_BURLAP_ITEM.get()))
                .save(consumer);
        //Resin Carpet
        ShapedRecipeBuilder.shaped(Registration.SUPERIOR_LEAF_WAND.get())
                .pattern(" x ")
                .pattern("fsf")
                .pattern("fff")
                .define('f', Registration.LEAF.get())
                .define('s', Items.STICK)
                .define('x', Registration.LEAF_WAND.get())
                .group("hempfarmer")
                .unlockedBy("leaf", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LEAF.get()))
                .save(consumer);



/**
 TODO Add Recipes for Armor and crushed seeds hemp milk'


 */


        //Toasted Seeds
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_HEMP.get()), Registration.SEEDS_HEMP_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_HEMP.get())).save(consumer, "toasted_hemp_seed");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_INDICA.get()), Registration.SEEDS_INDICA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_INDICA.get())).save(consumer, "toasted_indica_seed");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_SATIVA.get()), Registration.SEEDS_SATIVA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_SATIVA.get())).save(consumer, "toasted_sativa_seed");

        //Dry Materials
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.RAW_HEMP.get()), Registration.DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.RAW_HEMP.get())).save(consumer, "dry_hemp");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.LIME_RAW_HEMP.get()), Registration.LIME_DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.LIME_RAW_HEMP.get())).save(consumer, "lime_dry_hemp");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.VIOLET_RAW_HEMP.get()), Registration.VIOLET_DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.VIOLET_RAW_HEMP.get())).save(consumer, "violet_dry_hemp");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.OIL), Registration.RESIN.get(), 1.0f, 100)
                .unlockedBy("has_hemp_oil", has(Registration.OIL)).save(consumer, "resin");


        //ADDING COOK RECIPES TO CAMPFIRE
        //Toasted Seeds
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.SEEDS_SATIVA.get()), Registration.SEEDS_SATIVA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_SATIVA.get())).save(consumer, "toasted_sativa_seed_campfire");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.SEEDS_INDICA.get()), Registration.SEEDS_INDICA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_INDICA.get())).save(consumer, "toasted_indica_seed_campfire");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.SEEDS_HEMP.get()), Registration.SEEDS_HEMP_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_HEMP.get())).save(consumer, "toasted_hemp_seed_campfire");
        //Dry Hemp
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.VIOLET_RAW_HEMP.get()), Registration.VIOLET_DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.VIOLET_RAW_HEMP.get())).save(consumer, "violet_dry_hemp_campfire");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.LIME_RAW_HEMP.get()), Registration.LIME_DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.LIME_RAW_HEMP.get())).save(consumer, "lime_dry_hemp_campfire");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.RAW_HEMP.get()), Registration.DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.RAW_HEMP.get())).save(consumer, "dry_hemp_campfire");


    }
}
