package com.github.epiicthundercat.hempfarmer.util;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;


public class UtilTools {


    /**
     *
     * @param level
     * @param pos
     * @param remaining
     * Spawns in Item on location of block.
     */
    public static void spawnInWorld(Level level, BlockPos pos, ItemStack remaining) {
        if (!remaining.isEmpty()) {
            ItemEntity entityitem = new ItemEntity(level, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, remaining);
            entityitem.setPickUpDelay(40);
            entityitem.setDeltaMovement(entityitem.getDeltaMovement().multiply(0, 1, 0));
            level.addFreshEntity(entityitem);
        }
    }

//
//    public static void teleport(ServerPlayer entity, ServerLevel destination, BlockPos pos, boolean findTop) {
//        entity.changeDimension(destination, new ITeleporter() {
//            @Override
//            public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
//                entity = repositionEntity.apply(false);
//                int y = pos.getY();
//                if (findTop) {
//                    y = destination.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
//                }
//                entity.teleportTo(pos.getX(), y, pos.getZ());
//                return entity;
//            }
//        });
//    }

    public static MutableComponent translate(String key, Object... args) {
        return Component.translatable(/*HempFarmer.MODID + "." + */key, args);
    }



}
