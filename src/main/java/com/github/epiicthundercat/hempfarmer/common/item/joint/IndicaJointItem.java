package com.github.epiicthundercat.hempfarmer.common.item.joint;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.github.epiicthundercat.hempfarmer.util.UtilTools;
import net.minecraft.ChatFormatting;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;

import static com.github.epiicthundercat.hempfarmer.common.item.joint.HempJointItem.MESSAGE_NEED_LIGHT;

public class IndicaJointItem extends Item {

    public IndicaJointItem(Item.Properties properties) {
        super(properties);
    }

    @Override
    public int getUseDuration(ItemStack itemStack) {
        return 40;

    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand interactionHand) {
        ItemStack itemstack = pPlayer.getItemInHand(interactionHand);

        ItemStack flintItem = pPlayer.getOffhandItem();

        if (itemstack.is(Registration.INDICA_JOINT.get()) && !flintItem.is(Registration.LIGHTER.get())) {
            pPlayer.displayClientMessage(UtilTools.translate(MESSAGE_NEED_LIGHT).withStyle(ChatFormatting.GOLD).withStyle(ChatFormatting.ITALIC), true);

            return InteractionResultHolder.fail(itemstack);

        } else if (itemstack.is(Registration.INDICA_JOINT.get()) && flintItem.is(Registration.LIGHTER.get())) {
            spawnFoundParticles(pPlayer);
            pPlayer.startUsingItem(interactionHand);
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
            livingEntity.removeEffect(MobEffects.BAD_OMEN);
            livingEntity.addEffect(new MobEffectInstance(Registration.HIGH.get(), 2500, 1));

            itemStack.shrink(1);
            if (flintItem.is(Registration.LIGHTER.get())) {
                flintItem.hurtAndBreak(1, livingEntity, (e) -> {
                    e.broadcastBreakEvent(livingEntity.getUsedItemHand());

                });
            }

        } else {
            livingEntity.playSound(Registration.COUGH.get(), 1f, 1f);
        }


        return itemStack;
    }


    public UseAnim getUseAnimation(ItemStack itemStack) {


        return UseAnim.SPYGLASS;
    }


    private void spawnFoundParticles(LivingEntity positionClicked) {
        for (int i = 0; i < 360; i++) {
            if (i % 20 == 0) {

                positionClicked.getLevel().addParticle(ParticleTypes.SMOKE,
                        positionClicked.getX() , positionClicked.getY() + 1.5d, positionClicked.getZ() ,
                        0.03d, 0.05d, /*Math.sin(i)*/ 0.03d);

            }
        }
    }

}
