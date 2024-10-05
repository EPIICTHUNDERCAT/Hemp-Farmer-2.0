package com.github.epiicthundercat.hempfarmer.common.item.joint;

import com.github.epiicthundercat.hempfarmer.common.effect.HighEffect;
import com.github.epiicthundercat.hempfarmer.setup.HFSounds;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.particle.FireworkParticles;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;

import java.util.function.Consumer;

public class HempJointItem extends Item {
    public static final int SMOKE_DURATION = 20;

    public HempJointItem(Item.Properties p_40660_) {
        super(p_40660_);
    }


    @Override
    public int getUseDuration(ItemStack p_40680_) {
        return 140;

    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand interactionHand) {
        ItemStack itemstack = pPlayer.getItemInHand(interactionHand);

        ItemStack flintItem = pPlayer.getOffhandItem();

        if (!itemstack.is(Registration.REGS_JOINT.get()) && !flintItem.is(Items.FLINT_AND_STEEL)) {
            return InteractionResultHolder.fail(itemstack);

        } else if (itemstack.is(Registration.REGS_JOINT.get()) && flintItem.is(Items.FLINT_AND_STEEL)) {
            spawnFoundParticles(pPlayer);
            pPlayer.startUsingItem(interactionHand);
//            flintItem.hurtAndBreak(1, pPlayer, (e) -> {
//                e.broadcastBreakEvent(pPlayer.getUsedItemHand());
//
//            });
            pPlayer.playSound(Registration.SMOKE.get(), 1f, 1f);

        }

        return InteractionResultHolder.consume(itemstack);
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

        if (!level.isClientSide) {
            livingEntity.removeEffect(MobEffects.POISON);
            livingEntity.addEffect(new MobEffectInstance(Registration.HIGH.get(), 2500, 1));

            itemStack.shrink(1);
            if (flintItem.is(Items.FLINT_AND_STEEL)){
                flintItem.hurtAndBreak(1, livingEntity, (e) -> {
                    e.broadcastBreakEvent(livingEntity.getUsedItemHand());

                });
            }

        }else {
            livingEntity.playSound(Registration.COUGH.get(), 1f, 1f);
        }


        return itemStack;
    }


    public UseAnim getUseAnimation(ItemStack itemStack) {


        return UseAnim.SPYGLASS;
    }


    private void spawnFoundParticles(LivingEntity positionClicked) {
        for (int i = 0; i < 980; i++) {
            if (i % 20 == 0) {
                positionClicked.getLevel().addParticle(ParticleTypes.SMOKE,
                        positionClicked.getX() /*+ -0.5d*/, positionClicked.getY() + 1.5d, positionClicked.getZ() /*+ 0.5d*/,
                        Math.cos(i) * 0.15d, 0.5d, Math.sin(i) * 0.15d);

            }
        }
    }


}