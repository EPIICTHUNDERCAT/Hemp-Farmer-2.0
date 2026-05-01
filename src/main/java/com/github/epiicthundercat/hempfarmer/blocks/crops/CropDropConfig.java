package com.github.epiicthundercat.hempfarmer.blocks.crops;

import net.minecraftforge.common.ForgeConfigSpec;

public class CropDropConfig {

    // Hemp crop drops
    public static ForgeConfigSpec.IntValue HEMP_SEED_MIN;
    public static ForgeConfigSpec.IntValue HEMP_SEED_MAX;
    public static ForgeConfigSpec.DoubleValue HEMP_SEED_BONUS_CHANCE;
    public static ForgeConfigSpec.IntValue HEMP_BUD_MIN;
    public static ForgeConfigSpec.IntValue HEMP_BUD_MAX;
    public static ForgeConfigSpec.IntValue HEMP_HEMP_MIN;
    public static ForgeConfigSpec.IntValue HEMP_HEMP_MAX;
    public static ForgeConfigSpec.DoubleValue HEMP_LEAF_CHANCE;

    // Indica crop drops
    public static ForgeConfigSpec.IntValue INDICA_SEED_MIN;
    public static ForgeConfigSpec.IntValue INDICA_SEED_MAX;
    public static ForgeConfigSpec.DoubleValue INDICA_SEED_BONUS_CHANCE;
    public static ForgeConfigSpec.IntValue INDICA_BUD_MIN;
    public static ForgeConfigSpec.IntValue INDICA_BUD_MAX;
    public static ForgeConfigSpec.IntValue INDICA_HEMP_MIN;
    public static ForgeConfigSpec.IntValue INDICA_HEMP_MAX;
    public static ForgeConfigSpec.DoubleValue INDICA_LEAF_CHANCE;

    // Sativa crop drops
    public static ForgeConfigSpec.IntValue SATIVA_SEED_MIN;
    public static ForgeConfigSpec.IntValue SATIVA_SEED_MAX;
    public static ForgeConfigSpec.DoubleValue SATIVA_SEED_BONUS_CHANCE;
    public static ForgeConfigSpec.IntValue SATIVA_BUD_MIN;
    public static ForgeConfigSpec.IntValue SATIVA_BUD_MAX;
    public static ForgeConfigSpec.IntValue SATIVA_HEMP_MIN;
    public static ForgeConfigSpec.IntValue SATIVA_HEMP_MAX;
    public static ForgeConfigSpec.DoubleValue SATIVA_LEAF_CHANCE;

    public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {

        SERVER_BUILDER.comment("Drop rates for hemp_crop when harvested at full growth (age 7)").push("hemp_crop");

        HEMP_SEED_MIN = SERVER_BUILDER
                .comment("Guaranteed seed drops (Default: 1, Min: 0, Max: 64)")
                .defineInRange("seed_min", 1, 0, 64);
        HEMP_SEED_MAX = SERVER_BUILDER
                .comment("Cap on total seeds — each seed above seed_min has seed_bonus_chance to drop (Default: 2, Min: 0, Max: 64)")
                .defineInRange("seed_max", 2, 0, 64);
        HEMP_SEED_BONUS_CHANCE = SERVER_BUILDER
                .comment("Per-seed roll chance for seeds above seed_min (Default: 0.25, Min: 0.0, Max: 1.0)")
                .defineInRange("seed_bonus_chance", 0.25, 0.0, 1.0);
        HEMP_BUD_MIN = SERVER_BUILDER
                .comment("Minimum bud (regular) dropped (Default: 1, Min: 0, Max: 64)")
                .defineInRange("bud_min", 1, 0, 64);
        HEMP_BUD_MAX = SERVER_BUILDER
                .comment("Maximum bud (regular) dropped (Default: 2, Min: 0, Max: 64)")
                .defineInRange("bud_max", 2, 0, 64);
        HEMP_HEMP_MIN = SERVER_BUILDER
                .comment("Minimum raw hemp dropped (Default: 1, Min: 0, Max: 64)")
                .defineInRange("hemp_min", 1, 0, 64);
        HEMP_HEMP_MAX = SERVER_BUILDER
                .comment("Maximum raw hemp dropped (Default: 2, Min: 0, Max: 64)")
                .defineInRange("hemp_max", 2, 0, 64);
        HEMP_LEAF_CHANCE = SERVER_BUILDER
                .comment("Chance of dropping one leaf (Default: 0.05, Min: 0.0, Max: 1.0)")
                .defineInRange("leaf_chance", 0.05, 0.0, 1.0);

        SERVER_BUILDER.pop();

        SERVER_BUILDER.comment("Drop rates for indica_crop when harvested at full growth (age 7)").push("indica_crop");

        INDICA_SEED_MIN = SERVER_BUILDER
                .comment("Guaranteed seed drops (Default: 1, Min: 0, Max: 64)")
                .defineInRange("seed_min", 1, 0, 64);
        INDICA_SEED_MAX = SERVER_BUILDER
                .comment("Cap on total seeds — each seed above seed_min has seed_bonus_chance to drop (Default: 2, Min: 0, Max: 64)")
                .defineInRange("seed_max", 2, 0, 64);
        INDICA_SEED_BONUS_CHANCE = SERVER_BUILDER
                .comment("Per-seed roll chance for seeds above seed_min (Default: 0.25, Min: 0.0, Max: 1.0)")
                .defineInRange("seed_bonus_chance", 0.25, 0.0, 1.0);
        INDICA_BUD_MIN = SERVER_BUILDER
                .comment("Minimum indica bud dropped (Default: 1, Min: 0, Max: 64)")
                .defineInRange("bud_min", 1, 0, 64);
        INDICA_BUD_MAX = SERVER_BUILDER
                .comment("Maximum indica bud dropped (Default: 2, Min: 0, Max: 64)")
                .defineInRange("bud_max", 2, 0, 64);
        INDICA_HEMP_MIN = SERVER_BUILDER
                .comment("Minimum violet raw hemp dropped (Default: 1, Min: 0, Max: 64)")
                .defineInRange("hemp_min", 1, 0, 64);
        INDICA_HEMP_MAX = SERVER_BUILDER
                .comment("Maximum violet raw hemp dropped (Default: 2, Min: 0, Max: 64)")
                .defineInRange("hemp_max", 2, 0, 64);
        INDICA_LEAF_CHANCE = SERVER_BUILDER
                .comment("Chance of dropping one leaf (Default: 0.05, Min: 0.0, Max: 1.0)")
                .defineInRange("leaf_chance", 0.05, 0.0, 1.0);

        SERVER_BUILDER.pop();

        SERVER_BUILDER.comment("Drop rates for sativa_crop when harvested at full growth (age 7)").push("sativa_crop");

        SATIVA_SEED_MIN = SERVER_BUILDER
                .comment("Guaranteed seed drops (Default: 1, Min: 0, Max: 64)")
                .defineInRange("seed_min", 1, 0, 64);
        SATIVA_SEED_MAX = SERVER_BUILDER
                .comment("Cap on total seeds — each seed above seed_min has seed_bonus_chance to drop (Default: 2, Min: 0, Max: 64)")
                .defineInRange("seed_max", 2, 0, 64);
        SATIVA_SEED_BONUS_CHANCE = SERVER_BUILDER
                .comment("Per-seed roll chance for seeds above seed_min (Default: 0.25, Min: 0.0, Max: 1.0)")
                .defineInRange("seed_bonus_chance", 0.25, 0.0, 1.0);
        SATIVA_BUD_MIN = SERVER_BUILDER
                .comment("Minimum sativa bud dropped (Default: 1, Min: 0, Max: 64)")
                .defineInRange("bud_min", 1, 0, 64);
        SATIVA_BUD_MAX = SERVER_BUILDER
                .comment("Maximum sativa bud dropped (Default: 2, Min: 0, Max: 64)")
                .defineInRange("bud_max", 2, 0, 64);
        SATIVA_HEMP_MIN = SERVER_BUILDER
                .comment("Minimum lime raw hemp dropped (Default: 1, Min: 0, Max: 64)")
                .defineInRange("hemp_min", 1, 0, 64);
        SATIVA_HEMP_MAX = SERVER_BUILDER
                .comment("Maximum lime raw hemp dropped (Default: 2, Min: 0, Max: 64)")
                .defineInRange("hemp_max", 2, 0, 64);
        SATIVA_LEAF_CHANCE = SERVER_BUILDER
                .comment("Chance of dropping one leaf (Default: 0.05, Min: 0.0, Max: 1.0)")
                .defineInRange("leaf_chance", 0.05, 0.0, 1.0);

        SERVER_BUILDER.pop();
    }
}
