package com.github.epiicthundercat.hempfarmer.util;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.mojang.blaze3d.vertex.VertexFormatElement;
import com.mojang.math.Transformation;
import com.mojang.math.Vector3f;
import com.mojang.math.Vector4f;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.model.pipeline.BakedQuadBuilder;
import net.minecraftforge.common.util.ITeleporter;

import java.util.function.Function;

public class UtilTools {

    public static void spawnInWorld(Level level, BlockPos pos, ItemStack remaining) {
        if (!remaining.isEmpty()) {
            ItemEntity entityitem = new ItemEntity(level, pos.getX() + .5, pos.getY() + .5, pos.getZ() + .5, remaining);
            entityitem.setPickUpDelay(40);
            entityitem.setDeltaMovement(entityitem.getDeltaMovement().multiply(0, 1, 0));
            level.addFreshEntity(entityitem);
        }
    }

    public static void teleport(ServerPlayer entity, ServerLevel destination, BlockPos pos, boolean findTop) {
        entity.changeDimension(destination, new ITeleporter() {
            @Override
            public Entity placeEntity(Entity entity, ServerLevel currentWorld, ServerLevel destWorld, float yaw, Function<Boolean, Entity> repositionEntity) {
                entity = repositionEntity.apply(false);
                int y = pos.getY();
                if (findTop) {
                    y = destination.getHeight(Heightmap.Types.WORLD_SURFACE_WG, pos.getX(), pos.getZ());
                }
                entity.teleportTo(pos.getX(), y, pos.getZ());
                return entity;
            }
        });
    }

    public static MutableComponent translate(String key, Object... args) {
        return new TranslatableComponent(HempFarmer.MODID + "." + key, args);
    }
    private static void putVertex(BakedQuadBuilder builder, Vector3f normal, Vector4f vector,
                                  float u, float v, TextureAtlasSprite sprite) {

        var elements = builder.getVertexFormat().getElements().asList();
        for (int j = 0 ; j < elements.size() ; j++) {
            var e = elements.get(j);
            switch (e.getUsage()) {
                case POSITION -> builder.put(j, vector.x(), vector.y(), vector.z(), 1.0f);
                case COLOR    -> builder.put(j, 1.0f, 1.0f, 1.0f, 1.0f);
                case UV       -> putVertexUV(builder, u, v, sprite, j, e);
                case NORMAL   -> builder.put(j, normal.x(), normal.y(), normal.z());
                default       -> builder.put(j);
            }
        }
    }

    private static void putVertexUV(BakedQuadBuilder builder, float u, float v, TextureAtlasSprite sprite, int j, VertexFormatElement e) {
        switch (e.getIndex()) {
            case 0  -> builder.put(j, sprite.getU(u), sprite.getV(v));
            case 2  -> builder.put(j, (short) 0, (short) 0);
            default -> builder.put(j);
        }
    }

    public static BakedQuad createQuad(Vector3f v1, Vector3f v2, Vector3f v3, Vector3f v4, Transformation rotation, TextureAtlasSprite sprite) {
        Vector3f normal = v3.copy();
        normal.sub(v2);
        Vector3f temp = v1.copy();
        temp.sub(v2);
        normal.cross(temp);
        normal.normalize();

        int tw = sprite.getWidth();
        int th = sprite.getHeight();

        rotation = rotation.blockCenterToCorner();
        rotation.transformNormal(normal);

        Vector4f vv1 = new Vector4f(v1); rotation.transformPosition(vv1);
        Vector4f vv2 = new Vector4f(v2); rotation.transformPosition(vv2);
        Vector4f vv3 = new Vector4f(v3); rotation.transformPosition(vv3);
        Vector4f vv4 = new Vector4f(v4); rotation.transformPosition(vv4);

        var builder = new BakedQuadBuilder(sprite);
        builder.setQuadOrientation(Direction.getNearest(normal.x(), normal.y(), normal.z()));
        putVertex(builder, normal, vv1, 0, 0, sprite);
        putVertex(builder, normal, vv2, 0, th, sprite);
        putVertex(builder, normal, vv3, tw, th, sprite);
        putVertex(builder, normal, vv4, tw, 0, sprite);
        return builder.build();
    }

    public static Vector3f v(float x, float y, float z) {
        return new Vector3f(x, y, z);
    }
}
