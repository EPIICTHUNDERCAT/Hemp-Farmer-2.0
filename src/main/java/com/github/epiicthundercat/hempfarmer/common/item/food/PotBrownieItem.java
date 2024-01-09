package com.github.epiicthundercat.hempfarmer.common.item.food;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.EnchantedGoldenAppleItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class PotBrownieItem extends Item {
    public PotBrownieItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return super.isFoil(pStack);
    }
//WIP possible implementation of "fentanyl poison?"
//    @Override
//    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
//        return super.finishUsingItem(pStack, pLevel, pLivingEntity);
//    }
}
