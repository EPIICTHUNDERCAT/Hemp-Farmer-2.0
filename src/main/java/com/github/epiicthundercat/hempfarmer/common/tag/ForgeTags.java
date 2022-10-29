package com.github.epiicthundercat.hempfarmer.common.tag;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ForgeTags {

    public static final TagKey<Item> CROPS = forgeItemTag("crops");
    public static final TagKey<Item> CROPS_HEMP = forgeItemTag("crops/hemp");


    private static TagKey<Block> forgeBlockTag(String path) {
        return TagKey.create(Registry.BLOCK_REGISTRY, new ResourceLocation("forge", path));
    }

    private static TagKey<Item> forgeItemTag(String path) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", path));
    }
}
