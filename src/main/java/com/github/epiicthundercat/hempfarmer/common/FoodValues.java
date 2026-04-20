package com.github.epiicthundercat.hempfarmer.common;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

import java.util.function.Supplier;

public class FoodValues {


    /*
    .nutrition is how much food bars you get from eating, i believe 10 is half the food bar.
    .saturation is how much time before you have to eat, invisible bar
    .alwayseat means you can eat even is your bar is full - needed for things that provide other effects
    .fast is how fast you eat the item
     */

    public static final FoodProperties POT_BROWNIE = (new FoodProperties.Builder())
            .nutrition(10)
            .saturationModifier(0.8f).effect(new MobEffectInstance(Registration.HIGH.getHolder().orElseThrow(), 6000, 1), 1.0f).alwaysEdible().build();

    public static final FoodProperties HEMP_BOWL = (new FoodProperties.Builder())
            .nutrition(5)
            .saturationModifier(3.8f).alwaysEdible().build();


    public static final FoodProperties TOASTED_SEEDS = (new FoodProperties.Builder())
            .nutrition(2)
            .saturationModifier(0.2f).fast().build();

    public static final FoodProperties CANNABIS_TEA = (new FoodProperties.Builder())
            .nutrition(3)
            .saturationModifier(0.8f).effect(new MobEffectInstance(Registration.CALM.getHolder().orElseThrow(), 3500, 1), 1.0f).alwaysEdible().build();

}
