package com.github.epiicthundercat.hempfarmer.common.entity;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
//Some WIP
public class ShotLeafEntity extends ThrowableItemProjectile {

    public ShotLeafEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    public ShotLeafEntity(LivingEntity entity, Level world) {
        super(Registration.SHOT_LEAF_ENTITY.get(), entity, world);
    }

    //TODO - Implement Villager Heal/Transfusion

//    @Override
//    protected void onHitEntity(EntityHitResult pResult) {
//        super.onHitEntity(pResult);
//    }

    @Override
    public ItemStack getItem() {
        ItemStack itemstack = new ItemStack(this.getDefaultItem());
        return itemstack;
    }

    @Override
    protected Item getDefaultItem() {
        return Registration.SHOT_LEAF.get();
    }

    protected void particleSpawn() {


        this.level.addParticle(ParticleTypes.POOF, this.getX(), this.getY(), this.getZ(), this.random.nextGaussian(), 0.0D, this.random.nextGaussian());

    }

    //Todo Can only work when High Effect is present (add if statement)
    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!this.level.isClientSide && !this.isRemoved()) {
            Entity entity = this.getOwner();
            BlockPos blockPos = new BlockPos(pResult.getBlockPos());
            BlockState blockstate = level.getBlockState(blockPos);
            if (entity instanceof ServerPlayer serverplayer) {

                if (serverplayer.connection.getConnection().isConnected() && serverplayer.level == this.level && serverplayer.hasEffect(Registration.HIGH.get())) {
                    if (pResult.getType().equals(BlockHitResult.Type.BLOCK)) {
                        Block block = this.getBlockStateOn().getBlock();

                        if (!block.equals(Blocks.TALL_GRASS) && !block.equals(Blocks.GRASS)) {


                            int blockId = Block.getId(blockstate);
                            // System.out.println("block ID = " + blockId);
                            BlockState newState = null;
                            boolean changeable = false;
                            switch (blockId) {
                                //Cobblestone
                                case 14 -> {

                                    changeable = true;
                                    newState = Blocks.GRAVEL.defaultBlockState();
                                    particleSpawn();
                                }
                                //Sand
                                case 66 -> {
                                    changeable = true;
                                    newState = Blocks.CLAY.defaultBlockState();
                                    particleSpawn();
                                }
                                //Gravel
                                case 68 -> {
                                    changeable = true;
                                    newState = Blocks.SAND.defaultBlockState();
                                    particleSpawn();
                                }
                                //Gray Wool ->
                                case 1447 -> {
                                    changeable = true;
                                    newState = Blocks.COAL_ORE.defaultBlockState();
                                    particleSpawn();
                                }
                                //Leaves
                                case 179, 181, 183, 151, 155, 159, 157, 149, 209, 174, 230, 202, 153 -> {
                                    changeable = true;
                                    newState = Blocks.GRAY_WOOL.defaultBlockState();
                                    particleSpawn();
                                }
                                //TODO - Cant Get Collisions to work yet
//                                //Dandelion
//                                case 1412 -> {
//                                    changeable = true;
//                                    Block hemp = random.nextBoolean() ? Registration.SATIVA_CROP.get() : Registration.INDICA_CROP.get();
//                                    newState = hemp.defaultBlockState();
//                                }
//                                //Poppy
//                                case 1413  -> {
//                                    changeable = true;
//                                    newState = Registration.HEMP_CROP.get().defaultBlockState();
//                                }
                                default -> changeable = false;

                            }

                            if (blockId == Registration.getIdFromBlock(Registration.OILY_DIRT.get().defaultBlockState())) {
                                changeable = true;
                                newState = Blocks.GLOWSTONE.defaultBlockState();
                                particleSpawn();
                            }
                            if (blockId == Registration.getIdFromBlock(Registration.VIOLET_DIRT.get().defaultBlockState())) {
                                changeable = true;
                                newState = Blocks.IRON_ORE.defaultBlockState();
                                particleSpawn();
                            }
                            if (blockId == Registration.getIdFromBlock(Registration.LIME_DIRT.get().defaultBlockState())) {
                                changeable = true;
                                newState = Blocks.EMERALD_ORE.defaultBlockState();
                                particleSpawn();
                            }
                            if (blockId == Registration.getIdFromBlock(Registration.RESIN_DIRT.get().defaultBlockState())) {
                                changeable = true;
                                newState = Blocks.REDSTONE_ORE.defaultBlockState();
                                particleSpawn();
                            }


                            if (changeable) {

                                level.destroyBlock(blockPos, false);
                                level.setBlockAndUpdate(blockPos, newState);
                            }

                        }


                    }
                }
            }
            this.discard();
        }

    }

//    @Override
//    protected void onHit(HitResult pResult) {
//        super.onHit(pResult);
//
//        for (int i = 0; i < 32; ++i) {
//            this.level.addParticle(ParticleTypes.ITEM_SLIME, this.getX(), this.getY(), this.getZ(), this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
//        }
//
//        if (!this.level.isClientSide && !this.isRemoved()) {
//            Entity entity = this.getOwner();
//            BlockPos blockPos = new BlockPos(this.blockPosition());
//
//            //BlockPos blockPos1 = blockPos + this.getBlockY();
//            BlockState blockstate = level.getBlockState(blockPos);
//            System.out.println("this is thhe blcok" + blockstate + blockPos);
//            if (entity instanceof ServerPlayer serverplayer) {
//                if (serverplayer.connection.getConnection().isConnected() && serverplayer.level == this.level) {
//                    if (pResult.getType().equals(HitResult.Type.BLOCK)) {
//                        Block block = this.getBlockStateOn().getBlock();
//                        if (!block.equals(Blocks.TALL_GRASS) && !block.equals(Blocks.GRASS)) {
//                            System.out.println("Are we getting hree?");
//                            int blockId = Block.getId(blockstate);
//                            System.out.println("blockID= " + blockId);
//                            BlockState newState = null;
//                            boolean changeable = false;
//                            switch (blockId) {
//                                case 4 -> {
//                                    changeable = true;
//                                    newState = Blocks.GRAVEL.defaultBlockState();
//                                }
//                                case 12 -> {
//                                    changeable = true;
//                                    newState = Blocks.CLAY.defaultBlockState();
//                                }
//                                case 13 -> {
//                                    changeable = true;
//                                    newState = Blocks.SAND.defaultBlockState();
//                                }
//                                case 30 -> {
//                                    changeable = true;
//                                    newState = Blocks.COAL_ORE.defaultBlockState();
//                                }
//                                case 179, 151, 209, 174, 230, 202 -> {
//                                    changeable = true;
//                                    newState = Blocks.COBWEB.defaultBlockState();
//                                }
//                                case 37 -> {
//                                    changeable = true;
//                                    Block hemp = random.nextBoolean() ? Registration.SATIVA_CROP.get() : Registration.INDICA_CROP.get();
//                                    newState = hemp.defaultBlockState();
//                                }
//                                case 38 -> {
//                                    changeable = true;
//                                    newState = Registration.HEMP_CROP.get().defaultBlockState();
//                                }
//                                default -> changeable = false;
//                            }
//                            if (blockId == Registration.getIdFromBlock(Registration.OILY_DIRT.get().defaultBlockState())) {
//                                changeable = true;
//                                newState = Blocks.GLOWSTONE.defaultBlockState();
//                            }
//                            if (blockId == Registration.getIdFromBlock(Registration.VIOLET_DIRT.get().defaultBlockState())) {
//                                changeable = true;
//                                newState = Blocks.IRON_ORE.defaultBlockState();
//                            }
//                            if (blockId == Registration.getIdFromBlock(Registration.LIME_DIRT.get().defaultBlockState())) {
//                                changeable = true;
//                                newState = Blocks.EMERALD_ORE.defaultBlockState();
//                            }
//                            if (blockId == Registration.getIdFromBlock(Registration.RESIN_DIRT.get().defaultBlockState())) {
//                                changeable = true;
//                                newState = Blocks.REDSTONE_ORE.defaultBlockState();
//                            }
//
//                            if (changeable) {
//                                level.destroyBlock(blockPos, false);
//                                level.setBlockAndUpdate(blockPos, newState);
//                            }
//
//                        }
//
//
//                    }
//                }
//            }
//            this.discard();
//        }
//
//    }

    @Override
    protected void defineSynchedData() {

    }


    @Override
    public void tick() {
        Entity entity = this.getOwner();
        if (entity instanceof Player && !entity.isAlive()) {
            this.discard();
        } else {
            super.tick();
        }

    }
}
