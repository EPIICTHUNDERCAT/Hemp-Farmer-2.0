package com.github.epiicthundercat.hempfarmer.common.entity;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtOps;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.event.ForgeEventFactory;

//Some WIP
public class ShotLeafEntity extends ThrowableItemProjectile {

    public ShotLeafEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    public ShotLeafEntity(LivingEntity entity, Level world) {
        super(Registration.SHOT_LEAF_ENTITY.get(), entity, world);
    }

    //TODO - Implement Villager Heal/Transfusion
//    public void thunderHit(ServerLevel serverLevel, LightningBolt lightningBolt) {
//        if (serverLevel.getDifficulty() != Difficulty.PEACEFUL && ForgeEventFactory.canLivingConvert(this, EntityType.ZOMBIFIED_PIGLIN, (timer) -> {
//        })) {
//            ZombifiedPiglin zombifiedpiglin = (ZombifiedPiglin)EntityType.ZOMBIFIED_PIGLIN.create(serverLevel);
//            zombifiedpiglin.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.GOLDEN_SWORD));
//            zombifiedpiglin.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
//            zombifiedpiglin.setNoAi(this.isNoAi());
//            zombifiedpiglin.setBaby(this.isBaby());
//            if (this.hasCustomName()) {
//                zombifiedpiglin.setCustomName(this.getCustomName());
//                zombifiedpiglin.setCustomNameVisible(this.isCustomNameVisible());
//            }
//
//            zombifiedpiglin.setPersistenceRequired();
//            ForgeEventFactory.onLivingConvert(this, zombifiedpiglin);
//            serverLevel.addFreshEntity(zombifiedpiglin);
//            this.discard();
//        } else {
//            super.thunderHit(serverLevel, lightningBolt);
//        }
//
//    }
//    @Override
//    protected void onHitEntity(EntityHitResult pResult) {
//        super.onHitEntity(pResult);
//        if (!this.level.isClientSide && !this.isRemoved()) {
//            Entity entity = this.getOwner();
//            System.out.println("test2");
//            if (entity instanceof ServerPlayer serverplayer) {
//                System.out.println("test");
//                if (serverplayer.connection.getConnection().isConnected() && serverplayer.level == this.level /*&& serverplayer.hasEffect(Registration.HIGH.get())*/) {
//                    if (pResult.getType().equals(EntityHitResult.Type.ENTITY)) {
//                        System.out.println("test3");
//                        LivingEntity livingEntity = (LivingEntity) pResult.getEntity();
//                        Mob deadEntity = ((Mob) livingEntity).convertTo(EntityType.ZOMBIE, false);
//                       // if (deadEntity instanceof Zombie zombie) {
//                            System.out.println("entity being hit");
//                            Villager villager = (Villager) EntityType.VILLAGER.create(this.level);
//                            villager.moveTo(this.getX(), this.getY(), this.getZ(), this.getYRot(), this.getXRot());
//                            villager.finalizeSpawn((ServerLevelAccessor) this.level, this.level.getCurrentDifficultyAt(villager.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData) null, (CompoundTag) null);
//                            villager.setNoAi(villager.isNoAi());
//                            if (this.hasCustomName()) {
//                                villager.setCustomName(this.getCustomName());
//                                villager.setCustomNameVisible(this.isCustomNameVisible());
//                            }
//
//                            villager.setPersistenceRequired();
//                            ForgeEventFactory.onLivingConvert(deadEntity, villager);
//                            // p_35409_.addFreshEntityWithPassengers(villager);
//                            //this.releaseAllPois();
//                            this.discard();
//                       // }
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
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entityHit = result.getEntity();
        Entity entity = this.getOwner();
        LivingEntity livingEntity = entity instanceof LivingEntity ? (LivingEntity) entity : null;
        if (!this.level.isClientSide()) {
            if (entityHit instanceof ZombifiedPiglin piglin) {
                transfusePigMan(piglin);
                particleSpawn();
            }
            if (entityHit instanceof Zombie zombie) {
                // result.getEntity().hurt(DamageSource.indirectMobAttack(this, livingEntity).setProjectile(), 1.0f);
                transfuseZombie(zombie);
                particleSpawn();

            }
            if (entityHit instanceof Witch witch) {
                transfuseWitch(witch);
                particleSpawn();
            }
            if(entityHit instanceof Skeleton skelly){
                transfuseSkeleton(skelly);
                particleSpawn();
            }

        }

    }


    protected void transfuseZombie(Zombie zombie) {
        Villager villager = zombie.convertTo(EntityType.VILLAGER, false);


        if (villager != null) {
            villager.moveTo(zombie.getX(), zombie.getY(), zombie.getZ(), zombie.getYRot(), zombie.getXRot());
            villager.finalizeSpawn((ServerLevelAccessor) this.level, this.level.getCurrentDifficultyAt(villager.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData) null, (CompoundTag) null);
            villager.setVillagerXp(villager.getVillagerXp());
            if (zombie.hasCustomName()) {
                villager.setCustomName(zombie.getCustomName());
                villager.setCustomNameVisible(zombie.isCustomNameVisible());
            }
            villager.setPersistenceRequired();
            particleSpawn();
            ForgeEventFactory.onLivingConvert(zombie, villager);
        }
    }

    protected void transfuseWitch(Witch witch) {
        Villager villager = witch.convertTo(EntityType.VILLAGER, false);


        if (villager != null) {
            villager.moveTo(witch.getX(), witch.getY(), witch.getZ(), witch.getYRot(), witch.getXRot());
            villager.finalizeSpawn((ServerLevelAccessor) this.level, this.level.getCurrentDifficultyAt(villager.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData) null, (CompoundTag) null);
            villager.setVillagerXp(villager.getVillagerXp());
            if (witch.hasCustomName()) {
                villager.setCustomName(witch.getCustomName());
                villager.setCustomNameVisible(witch.isCustomNameVisible());
            }
            villager.setPersistenceRequired();
            particleSpawn();
            ForgeEventFactory.onLivingConvert(witch, villager);
        }
    }

    protected void transfusePigMan(ZombifiedPiglin piglin) {
        Pig pig = piglin.convertTo(EntityType.PIG, false);


        if (pig != null) {
            pig.moveTo(piglin.getX(), piglin.getY(), piglin.getZ(), piglin.getYRot(), piglin.getXRot());
            pig.finalizeSpawn((ServerLevelAccessor) this.level, this.level.getCurrentDifficultyAt(pig.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData) null, (CompoundTag) null);
            if (piglin.hasCustomName()) {
                pig.setCustomName(piglin.getCustomName());
                pig.setCustomNameVisible(piglin.isCustomNameVisible());
            }
            pig.setPersistenceRequired();
            particleSpawn();
            ForgeEventFactory.onLivingConvert(piglin, pig);
        }
    }
    protected void transfuseSkeleton(Skeleton skeleton) {
        Zombie zombie = skeleton.convertTo(EntityType.ZOMBIE, false);


        if (zombie != null) {
            zombie.moveTo(skeleton.getX(), skeleton.getY(), skeleton.getZ(), skeleton.getYRot(), skeleton.getXRot());
            zombie.finalizeSpawn((ServerLevelAccessor) this.level, this.level.getCurrentDifficultyAt(zombie.blockPosition()), MobSpawnType.CONVERSION, (SpawnGroupData) null, (CompoundTag) null);
            if (skeleton.hasCustomName()) {
                zombie.setCustomName(skeleton.getCustomName());
                zombie.setCustomNameVisible(skeleton.isCustomNameVisible());
            }
            zombie.setPersistenceRequired();
            particleSpawn();
            ForgeEventFactory.onLivingConvert(skeleton, zombie);
        }
    }

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

                if (serverplayer.connection.getConnection().isConnected() && serverplayer.level == this.level /*&& serverplayer.hasEffect(Registration.HIGH.get())*/) {
                    if (pResult.getType().equals(BlockHitResult.Type.BLOCK)) {

                        Block block = this.getBlockStateOn().getBlock();
                        // System.out.println("Block being hit" + block);
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
