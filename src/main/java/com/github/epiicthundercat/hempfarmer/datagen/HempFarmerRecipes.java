package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
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
        //ADD TAG FOR MILK
        ShapedRecipeBuilder.shaped(Registration.POT_BROWNIE.get())
                .pattern("mxm")
                .pattern("x#x")
                .pattern("#x#")
                .define('x', Registration.HEMP_MILK_BUCKET.get())
                .define('#', Tags.Items.EGGS)
                .define('m', Items.COCOA_BEANS)
                .group("hempfarmer")
                .unlockedBy("powah", InventoryChangeTrigger.TriggerInstance.hasItems(Items.RAW_IRON))
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


        //Power Battery
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
