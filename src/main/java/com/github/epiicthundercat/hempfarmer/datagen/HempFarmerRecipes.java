package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class HempFarmerRecipes extends RecipeProvider {

    public HempFarmerRecipes(DataGenerator generator) {
        super(generator);
    }


    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_HEMP_CRUSHED.get()), Registration.SEEDS_HEMP_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_HEMP.get())).save(consumer, "toasted_hemp_seed");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_INDICA_CRUSHED.get()), Registration.SEEDS_INDICA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_INDICA.get())).save(consumer, "toasted_indica_seed");

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(Registration.SEEDS_SATIVA_CRUSHED.get()), Registration.SEEDS_SATIVA_TOASTED.get(), 1.0f, 100)
                .unlockedBy("has_seed", has(Registration.SEEDS_SATIVA.get())).save(consumer, "toasted_sativa_seed");


    }
}
