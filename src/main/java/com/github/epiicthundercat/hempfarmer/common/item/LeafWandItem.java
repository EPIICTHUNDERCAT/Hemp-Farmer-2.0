package com.github.epiicthundercat.hempfarmer.common.item;

import com.github.epiicthundercat.hempfarmer.common.entity.ShotLeafEntity;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.github.epiicthundercat.hempfarmer.util.UtilTools;
import net.minecraft.ChatFormatting;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class LeafWandItem extends Item {
    public static final String MESSAGE_NOT_HIGH = "message.nothing";

    public LeafWandItem(Item.Properties pProperties) {
        super(pProperties);
    }


    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (pLevel.getRandom().nextFloat() * 0.4F + 0.8F));
        pPlayer.getCooldowns().addCooldown(this, 10);
        if (!pLevel.isClientSide) {
            if (pPlayer.hasEffect(Registration.HIGH.get())) {
                ShotLeafEntity shotLeafEntity = new ShotLeafEntity(pPlayer, pLevel);
                shotLeafEntity.shootFromRotation(pPlayer, pPlayer.getXRot(), pPlayer.getYRot(), 0.0F, 1.5F, 1.0F);
                pLevel.addFreshEntity(shotLeafEntity);
            } else {
                pPlayer.displayClientMessage(UtilTools.translate(MESSAGE_NOT_HIGH).withStyle(ChatFormatting.YELLOW).withStyle(ChatFormatting.ITALIC), true);
            }
        }

        pPlayer.awardStat(Stats.ITEM_USED.get(this));
        if (!pPlayer.getAbilities().instabuild && pPlayer.hasEffect(Registration.HIGH.get())) {
            itemstack.hurtAndBreak(1, pPlayer, (p_41303_) -> {
                p_41303_.broadcastBreakEvent(pHand);
            });
        }

        return InteractionResultHolder.sidedSuccess(itemstack, pLevel.isClientSide());
    }
}
