package com.github.epiicthundercat.hempfarmer.common.item;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;

public class HempMilkBucketItem extends Item {
    public HempMilkBucketItem(Properties properties) {
        super(properties);
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        livingEntity.removeAllEffects();
        ItemStack result = super.finishUsingItem(stack, level, livingEntity);
        return result.isEmpty() ? new ItemStack(Items.BUCKET) : result;
    }
}
