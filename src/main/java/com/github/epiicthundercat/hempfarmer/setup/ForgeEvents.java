package com.github.epiicthundercat.hempfarmer.setup;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.util.Result;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HempFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEvents {

    /**
     * Brewing stand recipe: water bottle + cannabis leaf → cannabis tea.
     *
     * The brewing stand has ONE ingredient slot (top) and THREE bottle slots (bottom).
     * Put 3× water bottles in the bottom slots and 1× leaf in the top slot.
     * Ingredient.of(Items.POTION) matches any potion item in the bottle slots,
     * including plain water bottles (Items.POTION with no potion content).
     */
    @SubscribeEvent
    public static void onEffectAdded(MobEffectEvent.Added event) {
        if (event.getEffectInstance().getEffect() == Registration.CALM.getHolder().orElseThrow()) {
            event.getEntity().removeEffect(MobEffects.BLINDNESS);
            event.getEntity().removeEffect(MobEffects.DARKNESS);
            event.getEntity().removeEffect(MobEffects.MINING_FATIGUE);
        }
    }

    @SubscribeEvent
    public static void onEffectApplicable(MobEffectEvent.Applicable event) {
        var effect = event.getEffectInstance().getEffect();
        if (effect == MobEffects.BLINDNESS || effect == MobEffects.DARKNESS || effect == MobEffects.MINING_FATIGUE) {
            if (event.getEntity().hasEffect(Registration.CALM.getHolder().orElseThrow())) {
                event.setResult(Result.DENY);
            }
        }
    }

    @SubscribeEvent
    public static void registerBrewingRecipes(BrewingRecipeRegisterEvent event) {
        event.addRecipe(
            Ingredient.of(Items.POTION),          // base: water bottles (bottom slots)
            Ingredient.of(Registration.LEAF.get()), // ingredient: cannabis leaf (top slot)
            new ItemStack(Registration.CANNABIS_TEA.get())
        );
    }
}
