package com.github.epiicthundercat.hempfarmer.common.entity;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import net.minecraft.world.entity.*;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.animal.horse.SkeletonHorse;
import net.minecraft.world.entity.animal.horse.ZombieHorse;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.ForgeEventFactory;

//Some WIP
public class ShotLeafEntity extends ThrowableItemProjectile {

    public ShotLeafEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    public ShotLeafEntity(LivingEntity entity, Level world) {
        super(Registration.SHOT_LEAF_ENTITY.get(), entity, world);
    }



    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entityHit = result.getEntity();
        Entity entity = this.getOwner();
        LivingEntity livingEntity = entity instanceof LivingEntity ? (LivingEntity) entity : null;
        if (!this.level().isClientSide) {
            if (entityHit instanceof ZombifiedPiglin piglin) {
                transfusePigMan(piglin);
                particleSpawn();
            }
            if (entityHit instanceof Zombie zombie) {

                transfuseZombie(zombie);
                particleSpawn();

            }
            if (entityHit instanceof Witch witch) {
                transfuseWitch(witch);
                particleSpawn();
            }
            if (entityHit instanceof Skeleton skelly) {
                transfuseSkeleton(skelly);
                particleSpawn();
            }
            if (entityHit instanceof Sheep sheep) {
                transfuseSheep(sheep);
                particleSpawn();
            }
            if (entityHit instanceof MagmaCube magmaCube) {
                // MagmaCube extends Slime — must check MagmaCube first or it matches the Slime branch
                transfuseMagmaCube(magmaCube);
                particleSpawn();
            } else if (entityHit instanceof Slime slime) {
                transfuseSlime(slime);
                particleSpawn();
            }
            if (entityHit instanceof ZombieHorse zombieHorse) {
                transfuseZombieHorse(zombieHorse);
                particleSpawn();
            }
            if (entityHit instanceof SkeletonHorse skeletonHorse) {
                transfuseSkeletonHorse(skeletonHorse);
                particleSpawn();
            }

        }

    }


    protected void transfuseZombie(Zombie zombie) {
        Villager villager = zombie.convertTo(EntityType.VILLAGER, false);


        if (villager != null) {
            villager.moveTo(zombie.getX(), zombie.getY(), zombie.getZ(), zombie.getYRot(), zombie.getXRot());
            villager.finalizeSpawn((ServerLevelAccessor) this.level(), this.level().getCurrentDifficultyAt(villager.blockPosition()), MobSpawnType.CONVERSION, null);
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
            villager.finalizeSpawn((ServerLevelAccessor) this.level(), this.level().getCurrentDifficultyAt(villager.blockPosition()), MobSpawnType.CONVERSION, null);
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
            pig.finalizeSpawn((ServerLevelAccessor) this.level(), this.level().getCurrentDifficultyAt(pig.blockPosition()), MobSpawnType.CONVERSION, null);
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
            zombie.finalizeSpawn((ServerLevelAccessor) this.level(), this.level().getCurrentDifficultyAt(zombie.blockPosition()), MobSpawnType.CONVERSION, null);
            if (skeleton.hasCustomName()) {
                zombie.setCustomName(skeleton.getCustomName());
                zombie.setCustomNameVisible(skeleton.isCustomNameVisible());
            }
            zombie.setPersistenceRequired();
            particleSpawn();
            ForgeEventFactory.onLivingConvert(skeleton, zombie);
        }
    }

    // Sheep: no entity type change — just randomise the wool colour in place
    protected void transfuseSheep(Sheep sheep) {
        DyeColor[] colors = DyeColor.values();
        sheep.setColor(colors[this.random.nextInt(colors.length)]);
    }

    // Slime → MagmaCube (size is assigned by finalizeSpawn — setSize is protected in Slime/MagmaCube)
    protected void transfuseSlime(Slime slime) {
        MagmaCube magmaCube = slime.convertTo(EntityType.MAGMA_CUBE, false);
        if (magmaCube != null) {
            magmaCube.moveTo(slime.getX(), slime.getY(), slime.getZ(), slime.getYRot(), slime.getXRot());
            magmaCube.finalizeSpawn((ServerLevelAccessor) this.level(), this.level().getCurrentDifficultyAt(magmaCube.blockPosition()), MobSpawnType.CONVERSION, null);
            if (slime.hasCustomName()) {
                magmaCube.setCustomName(slime.getCustomName());
                magmaCube.setCustomNameVisible(slime.isCustomNameVisible());
            }
            magmaCube.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(slime, magmaCube);
        }
    }

    // MagmaCube → Slime
    protected void transfuseMagmaCube(MagmaCube magmaCube) {
        Slime slime = magmaCube.convertTo(EntityType.SLIME, false);
        if (slime != null) {
            slime.moveTo(magmaCube.getX(), magmaCube.getY(), magmaCube.getZ(), magmaCube.getYRot(), magmaCube.getXRot());
            slime.finalizeSpawn((ServerLevelAccessor) this.level(), this.level().getCurrentDifficultyAt(slime.blockPosition()), MobSpawnType.CONVERSION, null);
            if (magmaCube.hasCustomName()) {
                slime.setCustomName(magmaCube.getCustomName());
                slime.setCustomNameVisible(magmaCube.isCustomNameVisible());
            }
            slime.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(magmaCube, slime);
        }
    }

    // ZombieHorse → Horse
    protected void transfuseZombieHorse(ZombieHorse zombieHorse) {
        Horse horse = zombieHorse.convertTo(EntityType.HORSE, false);
        if (horse != null) {
            horse.moveTo(zombieHorse.getX(), zombieHorse.getY(), zombieHorse.getZ(), zombieHorse.getYRot(), zombieHorse.getXRot());
            horse.finalizeSpawn((ServerLevelAccessor) this.level(), this.level().getCurrentDifficultyAt(horse.blockPosition()), MobSpawnType.CONVERSION, null);
            if (zombieHorse.hasCustomName()) {
                horse.setCustomName(zombieHorse.getCustomName());
                horse.setCustomNameVisible(zombieHorse.isCustomNameVisible());
            }
            horse.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(zombieHorse, horse);
        }
    }

    // SkeletonHorse → ZombieHorse
    protected void transfuseSkeletonHorse(SkeletonHorse skeletonHorse) {
        ZombieHorse zombieHorse = skeletonHorse.convertTo(EntityType.ZOMBIE_HORSE, false);
        if (zombieHorse != null) {
            zombieHorse.moveTo(skeletonHorse.getX(), skeletonHorse.getY(), skeletonHorse.getZ(), skeletonHorse.getYRot(), skeletonHorse.getXRot());
            zombieHorse.finalizeSpawn((ServerLevelAccessor) this.level(), this.level().getCurrentDifficultyAt(zombieHorse.blockPosition()), MobSpawnType.CONVERSION, null);
            if (skeletonHorse.hasCustomName()) {
                zombieHorse.setCustomName(skeletonHorse.getCustomName());
                zombieHorse.setCustomNameVisible(skeletonHorse.isCustomNameVisible());
            }
            zombieHorse.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(skeletonHorse, zombieHorse);
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

    // Used by entity transmutations (no block position available)
    // Uses ServerLevel.sendParticles() because this is always called from server-side code;
    // level.addParticle() on the server has no effect — only the client renders particles.
    protected void particleSpawn() {
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.POOF,
                    this.getX(), this.getY(), this.getZ(),
                    8, 0.4, 0.4, 0.4, 0.05);
        }
    }

    // Used by block transmutations — spawns a scattered burst around the block center.
    // ServerLevel.sendParticles() broadcasts a packet to nearby clients; count=12, spread=0.6
    protected void particleSpawn(BlockPos pos) {
        if (this.level() instanceof ServerLevel serverLevel) {
            double cx = pos.getX() + 0.5;  // center X of the block
            double cy = pos.getY() + 0.5;  // center Y of the block
            double cz = pos.getZ() + 0.5;  // center Z of the block
            serverLevel.sendParticles(ParticleTypes.POOF, cx, cy, cz, 12, 0.6, 0.6, 0.6, 0.05);
        }
    }


    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!this.level().isClientSide && !this.isRemoved()) {
            Entity entity = this.getOwner();
            BlockPos blockPos = new BlockPos(pResult.getBlockPos());
            BlockState blockstate = level().getBlockState(blockPos);
//            System.out.println("onHitBlock: " + blockstate.getBlock().getRegistryName()
//                    + " isLeaves=" + blockstate.is(BlockTags.LEAVES)
//                    + " isSmallFlower=" + blockstate.is(BlockTags.SMALL_FLOWERS)
//                    + " hitType=" + pResult.getType());
            if (entity instanceof ServerPlayer serverplayer) {

                if (serverplayer.level() == this.level() /*&& serverplayer.hasEffect(Registration.HIGH.get())*/) {
                    if (pResult.getType().equals(BlockHitResult.Type.BLOCK)) {

                        // Use the actual hit blockstate for the guard, not getBlockStateOn()
                        // (getBlockStateOn() returns the block below the projectile's feet, not the hit block)
                        if (!blockstate.is(Blocks.TALL_GRASS) && !blockstate.is(Blocks.SHORT_GRASS)) {

                            BlockState newState = null;

                            if (blockstate.is(BlockTags.SMALL_FLOWERS)) {
                                // Any small flower (dandelion, poppy, tulips, etc.) transmutes
                                // into a randomly chosen fully-grown hemp crop
                                int choice = this.random.nextInt(3);             // 0, 1, or 2
                                Block cropBlock = choice == 0                    // pick the crop type
                                        ? Registration.HEMP_CROP.get()
                                        : choice == 1
                                        ? Registration.SATIVA_CROP.get()
                                        : Registration.INDICA_CROP.get();
                                CropBlock crop = (CropBlock) cropBlock;          // cast to access CropBlock methods
                                newState = crop.defaultBlockState()              // start from default (age 0)
                                        .setValue(CropBlock.AGE, crop.getMaxAge()); // set to fully grown (age 7)
                                level().setBlockAndUpdate(blockPos.below(), Blocks.FARMLAND.defaultBlockState()); // convert soil to farmland
                                particleSpawn(blockPos);
                            } else if (blockstate.is(Blocks.COBBLESTONE)) {
                                newState = Blocks.GRAVEL.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(Blocks.SAND)) {
                                newState = Blocks.CLAY.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(Blocks.GRAVEL)) {
                                newState = Blocks.SAND.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(Blocks.GRAY_WOOL)) {
                                newState = Blocks.COAL_ORE.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(BlockTags.LEAVES)) {
                                newState = Blocks.GRAY_WOOL.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(Registration.OILY_DIRT.get())) {
                                newState = Blocks.GLOWSTONE.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(Registration.VIOLET_DIRT.get())) {
                                newState = Blocks.IRON_ORE.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(Registration.LIME_DIRT.get())) {
                                newState = Blocks.EMERALD_ORE.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(Registration.RESIN_DIRT.get())) {
                                newState = Blocks.REDSTONE_ORE.defaultBlockState();
                                particleSpawn(blockPos);
                            }

                            if (newState != null) {
                                level().destroyBlock(blockPos, false);
                                level().setBlockAndUpdate(blockPos, newState);
                                level().playSound(null, blockPos, SoundEvents.ENCHANTMENT_TABLE_USE,
                                        SoundSource.BLOCKS, 0.02f, 0.8f + random.nextFloat() * 0.4f);
                            }

                        }


                    }
                }
            }
            this.discard();
        }

    }


    @Override
    protected void defineSynchedData(SynchedEntityData.Builder pBuilder) {
        super.defineSynchedData(pBuilder);
    }


    @Override
    public void tick() {
        Entity entity = this.getOwner();
        if (entity instanceof Player && !entity.isAlive()) {
            this.discard();
            return;
        }

        // Flowers (dandelion, poppy) use noCollission() so the ray-cast in super.tick()
        // never registers a block hit on them. Check by position overlap before moving.
        if (!level().isClientSide && !this.isRemoved()) {
            BlockPos currentPos = this.blockPosition();
            BlockState currentState = level().getBlockState(currentPos);
            //System.out.println("tick pos=" + currentPos + " block=" + currentState.getBlock().getRegistryName());
            if (currentState.is(BlockTags.SMALL_FLOWERS)) {
               // System.out.println("flower overlap detected, triggering transmutation");
                onHitBlock(new BlockHitResult(this.position(), Direction.UP, currentPos, false));
                return;
            }
        }

        super.tick();
    }
}
