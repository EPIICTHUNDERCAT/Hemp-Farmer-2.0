package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class HempFarmerRecipes extends RecipeProvider {

    public HempFarmerRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Registration.POT_BROWNIE.get())
                .pattern("mxm").pattern("bsb").pattern("#x#")
                .define('x', Registration.MILK_ITEM).define('#', Tags.Items.EGGS)
                .define('m', Items.COCOA_BEANS).define('s', Items.SUGAR)
                .define('b', Registration.BUD_ITEM).group("hempfarmer")
                .unlockedBy("cocoa", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.HEMP_MILK_BUCKET.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.LIGHTER.get())
                .pattern(" i ").pattern("ifi").pattern("iii")
                .define('i', Tags.Items.INGOTS_IRON).define('f', Items.FLINT)
                .group("hempfarmer")
                .unlockedBy("iron", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.HEMP_MILK_BUCKET.get())
                .pattern("sss").pattern("sss").pattern(" w ")
                .define('s', Registration.SEED_TRIAD).define('w', Items.BUCKET)
                .group("hempfarmer")
                .unlockedBy("water_bucket_milk", InventoryChangeTrigger.TriggerInstance.hasItems(Items.BUCKET))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Registration.LIME_HEMP_HEARTS.get())
                .pattern("m m").pattern("mmm").pattern(" m ")
                .define('m', Registration.SEEDS_SATIVA_CRUSHED.get()).group("hempfarmer")
                .unlockedBy("hemp_hearts", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_SATIVA_CRUSHED.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Registration.VIOLET_HEMP_HEARTS.get())
                .pattern("m m").pattern("mmm").pattern(" m ")
                .define('m', Registration.SEEDS_INDICA_CRUSHED.get()).group("hempfarmer")
                .unlockedBy("hemp_hearts", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_INDICA_CRUSHED.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.FOOD, Registration.HEMP_HEARTS.get())
                .pattern("m m").pattern("mmm").pattern(" m ")
                .define('m', Registration.SEEDS_HEMP_CRUSHED.get()).group("hempfarmer")
                .unlockedBy("hemp_hearts", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_HEMP_CRUSHED.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.VIOLET_OIL.get())
                .pattern("mmm").pattern("xmx").pattern(" x ")
                .define('x', Tags.Items.GLASS_BLOCKS).define('m', Registration.SEEDS_INDICA.get())
                .group("hempfarmer")
                .unlockedBy("oily", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_INDICA.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.LIME_OIL.get())
                .pattern("mmm").pattern("xmx").pattern(" x ")
                .define('x', Tags.Items.GLASS_BLOCKS).define('m', Registration.SEEDS_SATIVA.get())
                .group("hempfarmer")
                .unlockedBy("oily", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_SATIVA.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.HEMP_OIL.get())
                .pattern("mmm").pattern("xmx").pattern(" x ")
                .define('x', Tags.Items.GLASS_BLOCKS).define('m', Registration.SEEDS_HEMP.get())
                .group("hempfarmer")
                .unlockedBy("oily", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_HEMP.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.POWER_BATTERY.get())
                .pattern("mxm").pattern("xox").pattern("#x#")
                .define('x', Tags.Items.INGOTS_IRON).define('#', Tags.Items.DUSTS_REDSTONE)
                .define('m', Tags.Items.INGOTS_GOLD).define('o', Items.REDSTONE_TORCH)
                .group("hempfarmer")
                .unlockedBy("powah", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.GRINDER.get())
                .pattern("mxm").pattern("x#x").pattern("#x#")
                .define('x', Tags.Items.INGOTS_IRON).define('#', Tags.Items.DUSTS_REDSTONE)
                .define('m', Tags.Items.INGOTS_GOLD).group("hempfarmer")
                .unlockedBy("grinder", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Registration.LEAF_WAND.get())
                .pattern(" x ").pattern("s  ").pattern("   ")
                .define('x', Registration.LEAF.get()).define('s', Items.STICK)
                .group("hempfarmer")
                .unlockedBy("leaf", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LEAF.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.REGS_JOINT.get())
                .requires(Registration.ROLLING_PAPER.get())
                .requires(Registration.GROUND_BUD.get()).requires(Registration.GROUND_BUD.get()).requires(Registration.GROUND_BUD.get())
                .group("hempfarmer")
                .unlockedBy("bud", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BUD.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.SATIVA_JOINT.get())
                .requires(Registration.ROLLING_PAPER.get())
                .requires(Registration.GROUND_SATIVA_BUD.get()).requires(Registration.GROUND_SATIVA_BUD.get()).requires(Registration.GROUND_SATIVA_BUD.get())
                .group("hempfarmer")
                .unlockedBy("sativa_bud", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SATIVA_BUD.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.INDICA_JOINT.get())
                .requires(Registration.ROLLING_PAPER.get())
                .requires(Registration.GROUND_INDICA_BUD.get()).requires(Registration.GROUND_INDICA_BUD.get()).requires(Registration.GROUND_INDICA_BUD.get())
                .group("hempfarmer")
                .unlockedBy("indica_bud", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.INDICA_BUD.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.HEMP_CROP.get())
                .requires(Registration.SEEDS_HEMP.get()).group("hempfarmer")
                .unlockedBy("seedlings", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_HEMP.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.INDICA_CROP.get())
                .requires(Registration.SEEDS_INDICA.get()).group("hempfarmer")
                .unlockedBy("seedlings", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_INDICA.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.SATIVA_CROP.get())
                .requires(Registration.SEEDS_SATIVA.get()).group("hempfarmer")
                .unlockedBy("seedlings", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.SEEDS_SATIVA.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.HEMP_PAPER.get())
                .requires(Registration.DRY_HEMP_ITEM).requires(Registration.DRY_HEMP_ITEM)
                .group("hempfarmer")
                .unlockedBy("dry_hemp", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Registration.DRY_HEMP_ITEM).build()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.BURLAP_ITEM.get())
                .pattern("xx ").pattern("xx ").pattern("   ")
                .define('x', Registration.DRY_HEMP.get()).group("hempfarmer")
                .unlockedBy("burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.DRY_HEMP.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.OILY_BURLAP_ITEM.get())
                .requires(Registration.HEMP_OIL.get()).requires(Registration.BURLAP_ITEM.get())
                .group("hempfarmer")
                .unlockedBy("oily_burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.HEMP_OIL.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.OILY_DIRT.get())
                .requires(Registration.HEMP_OIL.get()).requires(Registration.OILY_DIRT_ITEM_TAG)
                .group("hempfarmer")
                .unlockedBy("oily_burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.HEMP_OIL.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.LIME_DIRT.get())
                .requires(Registration.LIME_OIL.get()).requires(Registration.OILY_DIRT_ITEM_TAG)
                .group("hempfarmer")
                .unlockedBy("lime_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LIME_OIL.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.RESIN_DIRT.get())
                .requires(Registration.RESIN.get()).requires(Registration.OILY_DIRT_ITEM_TAG)
                .group("hempfarmer")
                .unlockedBy("resin", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.RESIN.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.VIOLET_BURLAP_ITEM.get())
                .pattern("xx ").pattern("xx ").pattern("   ")
                .define('x', Registration.VIOLET_DRY_HEMP.get()).group("hempfarmer")
                .unlockedBy("violet_burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.VIOLET_DRY_HEMP.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.LIME_BURLAP_ITEM.get())
                .pattern("xx ").pattern("xx ").pattern("   ")
                .define('x', Registration.LIME_DRY_HEMP.get()).group("hempfarmer")
                .unlockedBy("lime_burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LIME_DRY_HEMP.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.VIOLET_DIRT.get())
                .requires(Registration.VIOLET_OIL.get()).requires(Registration.OILY_DIRT_ITEM_TAG)
                .group("hempfarmer")
                .unlockedBy("violet_dirt", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.VIOLET_OIL.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Registration.BOWL_HEMP_HEARTS.get())
                .requires(Registration.HEMP_HEARTS.get()).requires(Registration.MILK_ITEM).requires(Items.BOWL)
                .group("hempfarmer")
                .unlockedBy("hemp_bowl", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.HEMP_HEARTS.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Registration.BOWL_LIME_HEMP_HEARTS.get())
                .requires(Registration.LIME_HEMP_HEARTS.get()).requires(Registration.MILK_ITEM).requires(Items.BOWL)
                .group("hempfarmer")
                .unlockedBy("lime_hemp_bowl", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LIME_HEMP_HEARTS.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Registration.BOWL_VIOLET_HEMP_HEARTS.get())
                .requires(Registration.VIOLET_HEMP_HEARTS.get()).requires(Registration.MILK_ITEM).requires(Items.BOWL)
                .group("hempfarmer")
                .unlockedBy("violet_hemp_bowl", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.VIOLET_HEMP_HEARTS.get()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.ROLLING_PAPER.get(), 3)
                .requires(Registration.PAPER_ITEM).group("hempfarmer")
                .unlockedBy("rolling_paper", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Registration.PAPER_ITEM).build()))
                .save(output);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, Registration.RESIN_BURLAP_ITEM.get(), 1)
                .requires(Registration.RESIN.get()).requires(Registration.BURLAP_ITEM.get())
                .group("hempfarmer")
                .unlockedBy("resin", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(Registration.RESIN.get()).build()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.VIOLET_BURLAP_CARPET_BLOCK.get())
                .pattern("xxx").pattern("   ").pattern("   ")
                .define('x', Registration.VIOLET_BURLAP_ITEM.get()).group("hempfarmer")
                .unlockedBy("violet_burlap_carpet", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.VIOLET_BURLAP_ITEM.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.LIME_BURLAP_CARPET_BLOCK.get())
                .pattern("xxx").pattern("   ").pattern("   ")
                .define('x', Registration.LIME_BURLAP_ITEM.get()).group("hempfarmer")
                .unlockedBy("lime_burlap_carpet", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LIME_BURLAP_ITEM.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.BURLAP_CARPET_BLOCK.get())
                .pattern("xxx").pattern("   ").pattern("   ")
                .define('x', Registration.BURLAP_ITEM.get()).group("hempfarmer")
                .unlockedBy("burlap_carpet", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BURLAP_ITEM.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.OILY_BURLAP_CARPET_BLOCK.get())
                .pattern("xxx").pattern("   ").pattern("   ")
                .define('x', Registration.OILY_BURLAP_ITEM.get()).group("hempfarmer")
                .unlockedBy("oily_burlap_carpet", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.OILY_BURLAP_ITEM.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, Registration.RESIN_CARPET_BLOCK.get())
                .pattern("xxx").pattern("   ").pattern("   ")
                .define('x', Registration.RESIN_BURLAP_ITEM.get()).group("hempfarmer")
                .unlockedBy("resin_burlap_carpet", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.RESIN_BURLAP_ITEM.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, Registration.SUPERIOR_LEAF_WAND.get())
                .pattern(" x ").pattern("fsf").pattern("fff")
                .define('f', Registration.LEAF.get()).define('s', Items.STICK)
                .define('x', Registration.LEAF_WAND.get()).group("hempfarmer")
                .unlockedBy("leaf", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.LEAF.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Registration.BURLAP_HELMET.get())
                .pattern("xxx").pattern("x x").pattern("   ")
                .define('x', Registration.BURLAP_ITEM.get()).group("hempfarmer")
                .unlockedBy("burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BURLAP_ITEM.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Registration.BURLAP_CHESTPLATE.get())
                .pattern("x x").pattern("xxx").pattern("xxx")
                .define('x', Registration.BURLAP_ITEM.get()).group("hempfarmer")
                .unlockedBy("burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BURLAP_ITEM.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Registration.BURLAP_LEGGINGS.get())
                .pattern("xxx").pattern("x x").pattern("x x")
                .define('x', Registration.BURLAP_ITEM.get()).group("hempfarmer")
                .unlockedBy("burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BURLAP_ITEM.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, Registration.BURLAP_BOOTS.get())
                .pattern("   ").pattern("x x").pattern("x x")
                .define('x', Registration.BURLAP_ITEM.get()).group("hempfarmer")
                .unlockedBy("burlap", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BURLAP_ITEM.get()))
                .save(output);

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_HEMP.get()), RecipeCategory.FOOD, Registration.SEEDS_HEMP_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_HEMP.get())).save(output, "toasted_hemp_seed");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_INDICA.get()), RecipeCategory.FOOD, Registration.SEEDS_INDICA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_INDICA.get())).save(output, "toasted_indica_seed");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_SATIVA.get()), RecipeCategory.FOOD, Registration.SEEDS_SATIVA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_SATIVA.get())).save(output, "toasted_sativa_seed");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.RAW_HEMP.get()), RecipeCategory.MISC, Registration.DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.RAW_HEMP.get())).save(output, "dry_hemp");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.LIME_RAW_HEMP.get()), RecipeCategory.MISC, Registration.LIME_DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.LIME_RAW_HEMP.get())).save(output, "lime_dry_hemp");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.VIOLET_RAW_HEMP.get()), RecipeCategory.MISC, Registration.VIOLET_DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.VIOLET_RAW_HEMP.get())).save(output, "violet_dry_hemp");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.OIL), RecipeCategory.MISC, Registration.RESIN.get(), 1.0f, 100)
                .unlockedBy("has_hemp_oil", has(Registration.OIL)).save(output, "resin");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.SEEDS_SATIVA.get()), RecipeCategory.FOOD, Registration.SEEDS_SATIVA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_SATIVA.get())).save(output, "toasted_sativa_seed_campfire");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.SEEDS_INDICA.get()), RecipeCategory.FOOD, Registration.SEEDS_INDICA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_INDICA.get())).save(output, "toasted_indica_seed_campfire");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.SEEDS_HEMP.get()), RecipeCategory.FOOD, Registration.SEEDS_HEMP_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_HEMP.get())).save(output, "toasted_hemp_seed_campfire");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.VIOLET_RAW_HEMP.get()), RecipeCategory.MISC, Registration.VIOLET_DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.VIOLET_RAW_HEMP.get())).save(output, "violet_dry_hemp_campfire");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.LIME_RAW_HEMP.get()), RecipeCategory.MISC, Registration.LIME_DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.LIME_RAW_HEMP.get())).save(output, "lime_dry_hemp_campfire");

        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(Registration.RAW_HEMP.get()), RecipeCategory.MISC, Registration.DRY_HEMP.get(), 1.0f, 100)
                .unlockedBy("has_raw_hemp", has(Registration.RAW_HEMP.get())).save(output, "dry_hemp_campfire");


    }
}
