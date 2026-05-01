package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ForgeRecipeProvider;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;

import java.util.concurrent.CompletableFuture;

public class HempFarmerRecipes extends ForgeRecipeProvider.Runner {

    public HempFarmerRecipes(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider);
    }

    @Override
    public String getName() {
        return "HempFarmer Recipes";
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            protected void buildRecipes() {

                shaped(RecipeCategory.FOOD, Registration.POT_BROWNIE.get())
                        .pattern("mxm").pattern("bsb").pattern("#x#")
                        .define('x', Registration.MILK_ITEM).define('#', Tags.Items.EGGS)
                        .define('m', Items.COCOA_BEANS).define('s', Items.SUGAR)
                        .define('b', Registration.BUD_ITEM).group("hempfarmer")
                        .unlockedBy("cocoa", has(Registration.HEMP_MILK_BUCKET.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.LIGHTER.get())
                        .pattern(" i ").pattern("ifi").pattern("iii")
                        .define('i', Tags.Items.INGOTS_IRON).define('f', Items.FLINT)
                        .group("hempfarmer")
                        .unlockedBy("iron", has(Items.IRON_INGOT))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.HEMP_MILK_BUCKET.get())
                        .pattern("sss").pattern("sss").pattern(" w ")
                        .define('s', Registration.SEED_TRIAD).define('w', Items.BUCKET)
                        .group("hempfarmer")
                        .unlockedBy("water_bucket_milk", has(Items.BUCKET))
                        .save(output);

                shaped(RecipeCategory.FOOD, Registration.LIME_HEMP_HEARTS.get())
                        .pattern("m m").pattern("mmm").pattern(" m ")
                        .define('m', Registration.SEEDS_SATIVA_CRUSHED.get()).group("hempfarmer")
                        .unlockedBy("hemp_hearts", has(Registration.SEEDS_SATIVA_CRUSHED.get()))
                        .save(output);

                shaped(RecipeCategory.FOOD, Registration.VIOLET_HEMP_HEARTS.get())
                        .pattern("m m").pattern("mmm").pattern(" m ")
                        .define('m', Registration.SEEDS_INDICA_CRUSHED.get()).group("hempfarmer")
                        .unlockedBy("hemp_hearts", has(Registration.SEEDS_INDICA_CRUSHED.get()))
                        .save(output);

                shaped(RecipeCategory.FOOD, Registration.HEMP_HEARTS.get())
                        .pattern("m m").pattern("mmm").pattern(" m ")
                        .define('m', Registration.SEEDS_HEMP_CRUSHED.get()).group("hempfarmer")
                        .unlockedBy("hemp_hearts", has(Registration.SEEDS_HEMP_CRUSHED.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.VIOLET_OIL.get())
                        .pattern("mmm").pattern("xmx").pattern(" x ")
                        .define('x', Tags.Items.GLASS_BLOCKS).define('m', Registration.SEEDS_INDICA.get())
                        .group("hempfarmer")
                        .unlockedBy("oily", has(Registration.SEEDS_INDICA.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.LIME_OIL.get())
                        .pattern("mmm").pattern("xmx").pattern(" x ")
                        .define('x', Tags.Items.GLASS_BLOCKS).define('m', Registration.SEEDS_SATIVA.get())
                        .group("hempfarmer")
                        .unlockedBy("oily", has(Registration.SEEDS_SATIVA.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.HEMP_OIL.get())
                        .pattern("mmm").pattern("xmx").pattern(" x ")
                        .define('x', Tags.Items.GLASS_BLOCKS).define('m', Registration.SEEDS_HEMP.get())
                        .group("hempfarmer")
                        .unlockedBy("oily", has(Registration.SEEDS_HEMP.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.POWER_BATTERY.get())
                        .pattern("mxm").pattern("xox").pattern("#x#")
                        .define('x', Tags.Items.INGOTS_IRON).define('#', Tags.Items.DUSTS_REDSTONE)
                        .define('m', Tags.Items.INGOTS_GOLD).define('o', Items.REDSTONE_TORCH)
                        .group("hempfarmer")
                        .unlockedBy("powah", has(Items.RAW_IRON))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.GRINDER.get())
                        .pattern("mxm").pattern("x#x").pattern("#x#")
                        .define('x', Tags.Items.INGOTS_IRON).define('#', Tags.Items.DUSTS_REDSTONE)
                        .define('m', Tags.Items.INGOTS_GOLD).group("hempfarmer")
                        .unlockedBy("grinder", has(Items.RAW_IRON))
                        .save(output);

                shaped(RecipeCategory.TOOLS, Registration.LEAF_WAND.get())
                        .pattern(" x ").pattern("s  ").pattern("   ")
                        .define('x', Registration.LEAF.get()).define('s', Items.STICK)
                        .group("hempfarmer")
                        .unlockedBy("leaf", has(Registration.LEAF.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.REGS_JOINT.get())
                        .requires(Registration.ROLLING_PAPER.get())
                        .requires(Registration.GROUND_BUD.get()).requires(Registration.GROUND_BUD.get()).requires(Registration.GROUND_BUD.get())
                        .group("hempfarmer")
                        .unlockedBy("bud", has(Registration.BUD.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.SATIVA_JOINT.get())
                        .requires(Registration.ROLLING_PAPER.get())
                        .requires(Registration.GROUND_SATIVA_BUD.get()).requires(Registration.GROUND_SATIVA_BUD.get()).requires(Registration.GROUND_SATIVA_BUD.get())
                        .group("hempfarmer")
                        .unlockedBy("sativa_bud", has(Registration.SATIVA_BUD.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.INDICA_JOINT.get())
                        .requires(Registration.ROLLING_PAPER.get())
                        .requires(Registration.GROUND_INDICA_BUD.get()).requires(Registration.GROUND_INDICA_BUD.get()).requires(Registration.GROUND_INDICA_BUD.get())
                        .group("hempfarmer")
                        .unlockedBy("indica_bud", has(Registration.INDICA_BUD.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.HEMP_CROP.get())
                        .requires(Registration.SEEDS_HEMP.get()).group("hempfarmer")
                        .unlockedBy("seedlings", has(Registration.SEEDS_HEMP.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.INDICA_CROP.get())
                        .requires(Registration.SEEDS_INDICA.get()).group("hempfarmer")
                        .unlockedBy("seedlings", has(Registration.SEEDS_INDICA.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.SATIVA_CROP.get())
                        .requires(Registration.SEEDS_SATIVA.get()).group("hempfarmer")
                        .unlockedBy("seedlings", has(Registration.SEEDS_SATIVA.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.HEMP_PAPER.get())
                        .requires(Registration.DRY_HEMP_ITEM).requires(Registration.DRY_HEMP_ITEM)
                        .group("hempfarmer")
                        .unlockedBy("dry_hemp", has(Registration.DRY_HEMP_ITEM))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.BURLAP_ITEM.get())
                        .pattern("xx ").pattern("xx ").pattern("   ")
                        .define('x', Registration.DRY_HEMP.get()).group("hempfarmer")
                        .unlockedBy("burlap", has(Registration.DRY_HEMP.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.OILY_BURLAP_ITEM.get())
                        .requires(Registration.HEMP_OIL.get()).requires(Registration.BURLAP_ITEM.get())
                        .group("hempfarmer")
                        .unlockedBy("oily_burlap", has(Registration.HEMP_OIL.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.OILY_DIRT.get())
                        .requires(Registration.HEMP_OIL.get()).requires(Registration.OILY_DIRT_ITEM_TAG)
                        .group("hempfarmer")
                        .unlockedBy("oily_burlap", has(Registration.HEMP_OIL.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.LIME_DIRT.get())
                        .requires(Registration.LIME_OIL.get()).requires(Registration.OILY_DIRT_ITEM_TAG)
                        .group("hempfarmer")
                        .unlockedBy("lime_dirt", has(Registration.LIME_OIL.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.RESIN_DIRT.get())
                        .requires(Registration.RESIN.get()).requires(Registration.OILY_DIRT_ITEM_TAG)
                        .group("hempfarmer")
                        .unlockedBy("resin", has(Registration.RESIN.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.VIOLET_BURLAP_ITEM.get())
                        .pattern("xx ").pattern("xx ").pattern("   ")
                        .define('x', Registration.VIOLET_DRY_HEMP.get()).group("hempfarmer")
                        .unlockedBy("violet_burlap", has(Registration.VIOLET_DRY_HEMP.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.LIME_BURLAP_ITEM.get())
                        .pattern("xx ").pattern("xx ").pattern("   ")
                        .define('x', Registration.LIME_DRY_HEMP.get()).group("hempfarmer")
                        .unlockedBy("lime_burlap", has(Registration.LIME_DRY_HEMP.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.VIOLET_DIRT.get())
                        .requires(Registration.VIOLET_OIL.get()).requires(Registration.OILY_DIRT_ITEM_TAG)
                        .group("hempfarmer")
                        .unlockedBy("violet_dirt", has(Registration.VIOLET_OIL.get()))
                        .save(output);

                shapeless(RecipeCategory.FOOD, Registration.BOWL_HEMP_HEARTS.get())
                        .requires(Registration.HEMP_HEARTS.get()).requires(Registration.MILK_ITEM).requires(Items.BOWL)
                        .group("hempfarmer")
                        .unlockedBy("hemp_bowl", has(Registration.HEMP_HEARTS.get()))
                        .save(output);

                shapeless(RecipeCategory.FOOD, Registration.BOWL_LIME_HEMP_HEARTS.get())
                        .requires(Registration.LIME_HEMP_HEARTS.get()).requires(Registration.MILK_ITEM).requires(Items.BOWL)
                        .group("hempfarmer")
                        .unlockedBy("lime_hemp_bowl", has(Registration.LIME_HEMP_HEARTS.get()))
                        .save(output);

                shapeless(RecipeCategory.FOOD, Registration.BOWL_VIOLET_HEMP_HEARTS.get())
                        .requires(Registration.VIOLET_HEMP_HEARTS.get()).requires(Registration.MILK_ITEM).requires(Items.BOWL)
                        .group("hempfarmer")
                        .unlockedBy("violet_hemp_bowl", has(Registration.VIOLET_HEMP_HEARTS.get()))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.ROLLING_PAPER.get(), 3)
                        .requires(Registration.PAPER_ITEM).group("hempfarmer")
                        .unlockedBy("rolling_paper", has(Registration.PAPER_ITEM))
                        .save(output);

                shapeless(RecipeCategory.MISC, Registration.RESIN_BURLAP_ITEM.get(), 1)
                        .requires(Registration.RESIN.get()).requires(Registration.BURLAP_ITEM.get())
                        .group("hempfarmer")
                        .unlockedBy("resin", has(Registration.RESIN.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.VIOLET_BURLAP_CARPET_BLOCK.get())
                        .pattern("xxx").pattern("   ").pattern("   ")
                        .define('x', Registration.VIOLET_BURLAP_ITEM.get()).group("hempfarmer")
                        .unlockedBy("violet_burlap_carpet", has(Registration.VIOLET_BURLAP_ITEM.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.LIME_BURLAP_CARPET_BLOCK.get())
                        .pattern("xxx").pattern("   ").pattern("   ")
                        .define('x', Registration.LIME_BURLAP_ITEM.get()).group("hempfarmer")
                        .unlockedBy("lime_burlap_carpet", has(Registration.LIME_BURLAP_ITEM.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.BURLAP_CARPET_BLOCK.get())
                        .pattern("xxx").pattern("   ").pattern("   ")
                        .define('x', Registration.BURLAP_ITEM.get()).group("hempfarmer")
                        .unlockedBy("burlap_carpet", has(Registration.BURLAP_ITEM.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.OILY_BURLAP_CARPET_BLOCK.get())
                        .pattern("xxx").pattern("   ").pattern("   ")
                        .define('x', Registration.OILY_BURLAP_ITEM.get()).group("hempfarmer")
                        .unlockedBy("oily_burlap_carpet", has(Registration.OILY_BURLAP_ITEM.get()))
                        .save(output);

                shaped(RecipeCategory.MISC, Registration.RESIN_CARPET_BLOCK.get())
                        .pattern("xxx").pattern("   ").pattern("   ")
                        .define('x', Registration.RESIN_BURLAP_ITEM.get()).group("hempfarmer")
                        .unlockedBy("resin_burlap_carpet", has(Registration.RESIN_BURLAP_ITEM.get()))
                        .save(output);

                shaped(RecipeCategory.TOOLS, Registration.SUPERIOR_LEAF_WAND.get())
                        .pattern(" x ").pattern("fsf").pattern("fff")
                        .define('f', Registration.LEAF.get()).define('s', Items.STICK)
                        .define('x', Registration.LEAF_WAND.get()).group("hempfarmer")
                        .unlockedBy("leaf", has(Registration.LEAF.get()))
                        .save(output);

                shaped(RecipeCategory.COMBAT, Registration.BURLAP_HELMET.get())
                        .pattern("xxx").pattern("x x").pattern("   ")
                        .define('x', Registration.BURLAP_ITEM.get()).group("hempfarmer")
                        .unlockedBy("burlap", has(Registration.BURLAP_ITEM.get()))
                        .save(output);

                shaped(RecipeCategory.COMBAT, Registration.BURLAP_CHESTPLATE.get())
                        .pattern("x x").pattern("xxx").pattern("xxx")
                        .define('x', Registration.BURLAP_ITEM.get()).group("hempfarmer")
                        .unlockedBy("burlap", has(Registration.BURLAP_ITEM.get()))
                        .save(output);

                shaped(RecipeCategory.COMBAT, Registration.BURLAP_LEGGINGS.get())
                        .pattern("xxx").pattern("x x").pattern("x x")
                        .define('x', Registration.BURLAP_ITEM.get()).group("hempfarmer")
                        .unlockedBy("burlap", has(Registration.BURLAP_ITEM.get()))
                        .save(output);

                shaped(RecipeCategory.COMBAT, Registration.BURLAP_BOOTS.get())
                        .pattern("   ").pattern("x x").pattern("x x")
                        .define('x', Registration.BURLAP_ITEM.get()).group("hempfarmer")
                        .unlockedBy("burlap", has(Registration.BURLAP_ITEM.get()))
                        .save(output);

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_HEMP.get()), RecipeCategory.FOOD, CookingBookCategory.FOOD, Registration.SEEDS_HEMP_TOASTED.get(), 1.0f, 100)
                        .unlockedBy("has_seed", has(Registration.SEEDS_HEMP.get())).save(output, "toasted_hemp_seed");

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_INDICA.get()), RecipeCategory.FOOD, CookingBookCategory.FOOD, Registration.SEEDS_INDICA_TOASTED.get(), 1.0f, 100)
                        .unlockedBy("has_seed", has(Registration.SEEDS_INDICA.get())).save(output, "toasted_indica_seed");

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_SATIVA.get()), RecipeCategory.FOOD, CookingBookCategory.FOOD, Registration.SEEDS_SATIVA_TOASTED.get(), 1.0f, 100)
                        .unlockedBy("has_seed", has(Registration.SEEDS_SATIVA.get())).save(output, "toasted_sativa_seed");

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.RAW_HEMP.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Registration.DRY_HEMP.get(), 1.0f, 100)
                        .unlockedBy("has_raw_hemp", has(Registration.RAW_HEMP.get())).save(output, "dry_hemp");

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.LIME_RAW_HEMP.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Registration.LIME_DRY_HEMP.get(), 1.0f, 100)
                        .unlockedBy("has_raw_hemp", has(Registration.LIME_RAW_HEMP.get())).save(output, "lime_dry_hemp");

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.VIOLET_RAW_HEMP.get()), RecipeCategory.MISC, CookingBookCategory.MISC, Registration.VIOLET_DRY_HEMP.get(), 1.0f, 100)
                        .unlockedBy("has_raw_hemp", has(Registration.VIOLET_RAW_HEMP.get())).save(output, "violet_dry_hemp");

                SimpleCookingRecipeBuilder.smelting(Ingredient.of(registries.lookupOrThrow(Registries.ITEM).getOrThrow(Registration.OIL)), RecipeCategory.MISC, CookingBookCategory.MISC, Registration.RESIN.get(), 1.0f, 100)
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
        };
    }
}
