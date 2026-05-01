package com.github.epiicthundercat.hempfarmer.common.entity;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import net.minecraft.world.entity.*;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.animal.pig.Pig;
import net.minecraft.world.entity.animal.sheep.Sheep;
import net.minecraft.world.entity.animal.equine.Horse;
import net.minecraft.world.entity.animal.equine.SkeletonHorse;
import net.minecraft.world.entity.animal.equine.ZombieHorse;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.monster.skeleton.Skeleton;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.*;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

//Some WIP
public class ShotLeafEntity extends ThrowableItemProjectile {

    public ShotLeafEntity(EntityType<? extends ThrowableItemProjectile> entityType, Level world) {
        super(entityType, world);
    }

    @SuppressWarnings("unchecked")
    public ShotLeafEntity(LivingEntity entity, Level world) {
        super(
            (EntityType<ShotLeafEntity>) ForgeRegistries.ENTITY_TYPES.getValue(
                Identifier.fromNamespaceAndPath(HempFarmer.MODID, "shot_leaf_entity")),
            entity, world,
            new ItemStack(modItem("shot_leaf"))
        );
    }

    private static Item modItem(String name) {
        return Objects.requireNonNull(ForgeRegistries.ITEMS.getValue(
            Identifier.fromNamespaceAndPath(HempFarmer.MODID, name)));
    }

    private static Block modBlock(String name) {
        return Objects.requireNonNull(ForgeRegistries.BLOCKS.getValue(
            Identifier.fromNamespaceAndPath(HempFarmer.MODID, name)));
    }


    @Override
    protected void onHitEntity(EntityHitResult result) {
        super.onHitEntity(result);
        Entity entityHit = result.getEntity();
        Entity entity = this.getOwner();
        LivingEntity livingEntity = entity instanceof LivingEntity ? (LivingEntity) entity : null;
        if (!this.level().isClientSide()) {
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
        zombie.convertTo(EntityType.VILLAGER, ConversionParams.single(zombie, false, false), (villager) -> {
            if (this.level() instanceof ServerLevel serverLevel) {
                villager.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(villager.blockPosition()), EntitySpawnReason.CONVERSION, null);
            }
            villager.setVillagerXp(villager.getVillagerXp());
            if (zombie.hasCustomName()) {
                villager.setCustomName(zombie.getCustomName());
                villager.setCustomNameVisible(zombie.isCustomNameVisible());
            }
            villager.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(zombie, villager);
        });
    }

    protected void transfuseWitch(Witch witch) {
        witch.convertTo(EntityType.VILLAGER, ConversionParams.single(witch, false, false), (villager) -> {
            if (this.level() instanceof ServerLevel serverLevel) {
                villager.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(villager.blockPosition()), EntitySpawnReason.CONVERSION, null);
            }
            villager.setVillagerXp(villager.getVillagerXp());
            if (witch.hasCustomName()) {
                villager.setCustomName(witch.getCustomName());
                villager.setCustomNameVisible(witch.isCustomNameVisible());
            }
            villager.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(witch, villager);
        });
    }

    protected void transfusePigMan(ZombifiedPiglin piglin) {
        piglin.convertTo(EntityType.PIG, ConversionParams.single(piglin, false, false), (pig) -> {
            if (this.level() instanceof ServerLevel serverLevel) {
                pig.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(pig.blockPosition()), EntitySpawnReason.CONVERSION, null);
            }
            if (piglin.hasCustomName()) {
                pig.setCustomName(piglin.getCustomName());
                pig.setCustomNameVisible(piglin.isCustomNameVisible());
            }
            pig.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(piglin, pig);
        });
    }

    protected void transfuseSkeleton(Skeleton skeleton) {
        skeleton.convertTo(EntityType.ZOMBIE, ConversionParams.single(skeleton, false, false), (zombie) -> {
            if (this.level() instanceof ServerLevel serverLevel) {
                zombie.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(zombie.blockPosition()), EntitySpawnReason.CONVERSION, null);
            }
            if (skeleton.hasCustomName()) {
                zombie.setCustomName(skeleton.getCustomName());
                zombie.setCustomNameVisible(skeleton.isCustomNameVisible());
            }
            zombie.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(skeleton, zombie);
        });
    }

    // Sheep: no entity type change — just randomise the wool colour in place
    protected void transfuseSheep(Sheep sheep) {
        DyeColor[] colors = DyeColor.values();
        sheep.setColor(colors[this.random.nextInt(colors.length)]);
    }

    // Slime → MagmaCube (size is assigned by finalizeSpawn — setSize is protected in Slime/MagmaCube)
    protected void transfuseSlime(Slime slime) {
        slime.convertTo(EntityType.MAGMA_CUBE, ConversionParams.single(slime, false, false), (magmaCube) -> {
            if (this.level() instanceof ServerLevel serverLevel) {
                magmaCube.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(magmaCube.blockPosition()), EntitySpawnReason.CONVERSION, null);
            }
            if (slime.hasCustomName()) {
                magmaCube.setCustomName(slime.getCustomName());
                magmaCube.setCustomNameVisible(slime.isCustomNameVisible());
            }
            magmaCube.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(slime, magmaCube);
        });
    }

    // MagmaCube → Slime
    protected void transfuseMagmaCube(MagmaCube magmaCube) {
        magmaCube.convertTo(EntityType.SLIME, ConversionParams.single(magmaCube, false, false), (slime) -> {
            if (this.level() instanceof ServerLevel serverLevel) {
                slime.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(slime.blockPosition()), EntitySpawnReason.CONVERSION, null);
            }
            if (magmaCube.hasCustomName()) {
                slime.setCustomName(magmaCube.getCustomName());
                slime.setCustomNameVisible(magmaCube.isCustomNameVisible());
            }
            slime.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(magmaCube, slime);
        });
    }

    // ZombieHorse → Horse
    protected void transfuseZombieHorse(ZombieHorse zombieHorse) {
        zombieHorse.convertTo(EntityType.HORSE, ConversionParams.single(zombieHorse, false, false), (horse) -> {
            if (this.level() instanceof ServerLevel serverLevel) {
                horse.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(horse.blockPosition()), EntitySpawnReason.CONVERSION, null);
            }
            if (zombieHorse.hasCustomName()) {
                horse.setCustomName(zombieHorse.getCustomName());
                horse.setCustomNameVisible(zombieHorse.isCustomNameVisible());
            }
            horse.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(zombieHorse, horse);
        });
    }

    // SkeletonHorse → ZombieHorse
    protected void transfuseSkeletonHorse(SkeletonHorse skeletonHorse) {
        skeletonHorse.convertTo(EntityType.ZOMBIE_HORSE, ConversionParams.single(skeletonHorse, false, false), (zombieHorse) -> {
            if (this.level() instanceof ServerLevel serverLevel) {
                zombieHorse.finalizeSpawn(serverLevel, serverLevel.getCurrentDifficultyAt(zombieHorse.blockPosition()), EntitySpawnReason.CONVERSION, null);
            }
            if (skeletonHorse.hasCustomName()) {
                zombieHorse.setCustomName(skeletonHorse.getCustomName());
                zombieHorse.setCustomNameVisible(skeletonHorse.isCustomNameVisible());
            }
            zombieHorse.setPersistenceRequired();
            ForgeEventFactory.onLivingConvert(skeletonHorse, zombieHorse);
        });
    }

    @Override
    public ItemStack getItem() {
        ItemStack itemstack = new ItemStack(this.getDefaultItem());
        return itemstack;
    }

    @Override
    protected Item getDefaultItem() {
        return modItem("shot_leaf");
    }

    // Uses ServerLevel.sendParticles() because this is always called from server-side code;
    // level.addParticle() on the server has no effect — only the client renders particles.
    protected void particleSpawn() {
        if (this.level() instanceof ServerLevel serverLevel) {
            serverLevel.sendParticles(ParticleTypes.POOF,
                    this.getX(), this.getY(), this.getZ(),
                    8, 0.4, 0.4, 0.4, 0.05);
        }
    }

    // Spawns a scattered burst around the block center.
    protected void particleSpawn(BlockPos pos) {
        if (this.level() instanceof ServerLevel serverLevel) {
            double cx = pos.getX() + 0.5;
            double cy = pos.getY() + 0.5;
            double cz = pos.getZ() + 0.5;
            serverLevel.sendParticles(ParticleTypes.POOF, cx, cy, cz, 12, 0.6, 0.6, 0.6, 0.05);
        }
    }


    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if (!this.level().isClientSide() && !this.isRemoved()) {
            Entity entity = this.getOwner();
            BlockPos blockPos = new BlockPos(pResult.getBlockPos());
            BlockState blockstate = level().getBlockState(blockPos);
            if (entity instanceof ServerPlayer serverplayer) {

                if (serverplayer.level() == this.level()) {
                    if (pResult.getType().equals(BlockHitResult.Type.BLOCK)) {

                        if (!blockstate.is(Blocks.TALL_GRASS) && !blockstate.is(Blocks.SHORT_GRASS)) {

                            BlockState newState = null;

                            if (blockstate.is(BlockTags.SMALL_FLOWERS)) {
                                int choice = this.random.nextInt(3);
                                Block cropBlock = choice == 0
                                        ? modBlock("hemp_crop")
                                        : choice == 1
                                        ? modBlock("sativa_crop")
                                        : modBlock("indica_crop");
                                CropBlock crop = (CropBlock) cropBlock;
                                newState = crop.defaultBlockState()
                                        .setValue(CropBlock.AGE, crop.getMaxAge());
                                level().setBlockAndUpdate(blockPos.below(), Blocks.FARMLAND.defaultBlockState());
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
                            } else if (blockstate.is(modBlock("oily_dirt"))) {
                                newState = Blocks.GLOWSTONE.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(modBlock("violet_dirt"))) {
                                newState = Blocks.IRON_ORE.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(modBlock("lime_dirt"))) {
                                newState = Blocks.EMERALD_ORE.defaultBlockState();
                                particleSpawn(blockPos);
                            } else if (blockstate.is(modBlock("resin_dirt"))) {
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
        if (!level().isClientSide() && !this.isRemoved()) {
            BlockPos currentPos = this.blockPosition();
            BlockState currentState = level().getBlockState(currentPos);
            if (currentState.is(BlockTags.SMALL_FLOWERS)) {
                onHitBlock(new BlockHitResult(this.position(), Direction.UP, currentPos, false));
                return;
            }
        }

        super.tick();
    }
}
