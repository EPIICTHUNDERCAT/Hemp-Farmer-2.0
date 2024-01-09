package com.github.epiicthundercat.hempfarmer.common.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
//WIP
public class HighEffect extends MobEffect {
    public HighEffect(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }



    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
       playerShootWand();
    }

    @Override
    public boolean isDurationEffectTick(int pDuration, int pAmplifier) {
        return super.isDurationEffectTick(pDuration, pAmplifier);
    }


    public boolean playerShootWand(){
        return true;
    }
}
