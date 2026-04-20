package com.github.epiicthundercat.hempfarmer.setup;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.decoration.PaintingVariant;

public class HFPaintings {

    // 1×1 block (16×16 px) — texture: assets/hempfarmer/textures/painting/cannabis_field.png
    public static final ResourceKey<PaintingVariant> CANNABIS_FIELD = create("cannabis_field");

    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, CANNABIS_FIELD, 1, 1);
    }

    private static void register(BootstrapContext<PaintingVariant> context, ResourceKey<PaintingVariant> key, int width, int height) {
        context.register(key, new PaintingVariant(width, height, key.location()));
    }

    private static ResourceKey<PaintingVariant> create(String name) {
        return ResourceKey.create(Registries.PAINTING_VARIANT,
                ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID, name));
    }
}
