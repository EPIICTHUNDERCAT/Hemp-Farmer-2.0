package com.github.epiicthundercat.hempfarmer.common;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;

public class FoodValues {

    public static final FoodProperties POT_BROWNIE = new FoodProperties.Builder()
            .nutrition(10).saturationModifier(0.8f).alwaysEdible().build();

    public static final Consumable POT_BROWNIE_CONSUMABLE = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(Registration.HIGH.getHolder().orElseThrow(), 6000, 1), 1.0f))
            .build();

    public static final FoodProperties HEMP_BOWL = new FoodProperties.Builder()
            .nutrition(5).saturationModifier(3.8f).alwaysEdible().build();

    public static final FoodProperties TOASTED_SEEDS = new FoodProperties.Builder()
            .nutrition(2).saturationModifier(0.2f).build();

    public static final FoodProperties CANNABIS_TEA = new FoodProperties.Builder()
            .nutrition(3).saturationModifier(0.8f).alwaysEdible().build();

    public static final Consumable CANNABIS_TEA_CONSUMABLE = Consumable.builder()
            .onConsume(new ApplyStatusEffectsConsumeEffect(
                    new MobEffectInstance(Registration.CALM.getHolder().orElseThrow(), 3500, 1), 1.0f))
            .build();

    public static final FoodProperties HEMP_MILK_FOOD = new FoodProperties.Builder()
            .nutrition(0).saturationModifier(0f).alwaysEdible().build();

    public static final Consumable HEMP_MILK_CONSUMABLE = Consumable.builder()
            .consumeSeconds(1.6f)
            .animation(ItemUseAnimation.DRINK)
            .sound(SoundEvents.GENERIC_DRINK)
            .build();
}
