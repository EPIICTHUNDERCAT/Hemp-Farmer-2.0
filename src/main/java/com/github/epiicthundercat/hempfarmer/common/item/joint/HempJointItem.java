package com.github.epiicthundercat.hempfarmer.common.item.joint;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.github.epiicthundercat.hempfarmer.util.UtilTools;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class HempJointItem extends Item {
    public static final String MESSAGE_NEED_LIGHT = "message.need_light";
    public HempJointItem(Item.Properties itemProperties) {
        super(itemProperties);
    }


    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity pEntity) {
        return 40;
    }


    @Override
    public InteractionResult use(Level level, Player pPlayer, InteractionHand interactionHand) {
        ItemStack itemstack = pPlayer.getItemInHand(interactionHand);

        ItemStack flintItem = pPlayer.getOffhandItem();

        if (itemstack.is(Registration.REGS_JOINT.get()) && !flintItem.is(Registration.LIGHTER.get())) {
            pPlayer.sendOverlayMessage(UtilTools.translate(MESSAGE_NEED_LIGHT).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC));
            return InteractionResult.FAIL;

        } else if (itemstack.is(Registration.REGS_JOINT.get()) && flintItem.is(Registration.LIGHTER.get())) {
            pPlayer.startUsingItem(interactionHand);
            pPlayer.getCooldowns().addCooldown(itemstack, getUseDuration(itemstack, pPlayer));

        }

        return InteractionResult.CONSUME;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack itemStack, Level level, LivingEntity livingEntity) {
        super.finishUsingItem(itemStack, level, livingEntity);
        ItemStack flintItem = livingEntity.getOffhandItem();

        if (livingEntity instanceof ServerPlayer) {
            ServerPlayer serverplayer = (ServerPlayer) livingEntity;
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, itemStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (!level.isClientSide()) {
            livingEntity.removeEffect(MobEffects.POISON);
            livingEntity.addEffect(new MobEffectInstance(Registration.HIGH.getHolder().orElseThrow(), 2500, 1));

            itemStack.shrink(1);
            if (flintItem.is(Registration.LIGHTER.get())) {
                flintItem.hurtAndBreak(1, livingEntity, EquipmentSlot.OFFHAND);
            }

        } else {
            livingEntity.playSound(Registration.COUGH.get(), 1f, 1f);
        }


        return itemStack;
    }


    @Override
    public void onUseTick(Level pLevel, LivingEntity pLivingEntity, ItemStack pStack, int pRemainingUseDuration) {
        // onUseTick fires client-side only in Forge 1.18.2
        if (!pLevel.isClientSide()) return;

        // First tick: play smoke sound once
        if (pRemainingUseDuration == getUseDuration(pStack, pLivingEntity) - 1) {
            pLivingEntity.playSound(Registration.SMOKE.get(), 1f, 1f);
        }
        // Every 2 ticks: emit particles from in front of the player's face
        if (pRemainingUseDuration % 2 == 0) {
            Vec3 look = pLivingEntity.getLookAngle();
            double mouthX = pLivingEntity.getX() + look.x * 0.5;
            double mouthY = pLivingEntity.getY() + pLivingEntity.getEyeHeight() - 0.1;
            double mouthZ = pLivingEntity.getZ() + look.z * 0.5;
            for (int i = 0; i < 3; i++) {
                pLevel.addParticle(ParticleTypes.SMOKE,
                        mouthX + (Math.random() - 0.5) * 0.1,
                        mouthY + (Math.random() - 0.5) * 0.1,
                        mouthZ + (Math.random() - 0.5) * 0.1,
                        look.x * 0.04 + (Math.random() - 0.5) * 0.01,
                        0.03 + Math.random() * 0.02,
                        look.z * 0.04 + (Math.random() - 0.5) * 0.01);
            }
        }
    }

    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.SPYGLASS;
    }
}