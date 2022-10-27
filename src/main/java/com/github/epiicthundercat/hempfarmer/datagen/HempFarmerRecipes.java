package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Item;
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
        ShapelessRecipeBuilder.shapeless(Registration.REGS_JOINT.get())
                .requires(Items.PAPER)
                .requires(Registration.BUD.get())
                .requires(Registration.BUD.get())
                .requires(Registration.BUD.get())
                .group("hempfarmer")
                .unlockedBy("bud", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.BUD.get()))
                .save(consumer);


        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_HEMP.get()), Registration.SEEDS_HEMP_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_HEMP.get())).save(consumer, "toasted_hemp_seed");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_INDICA.get()), Registration.SEEDS_INDICA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_INDICA.get())).save(consumer, "toasted_indica_seed");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_SATIVA.get()), Registration.SEEDS_SATIVA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_SATIVA.get())).save(consumer, "toasted_sativa_seed");


    }
}
