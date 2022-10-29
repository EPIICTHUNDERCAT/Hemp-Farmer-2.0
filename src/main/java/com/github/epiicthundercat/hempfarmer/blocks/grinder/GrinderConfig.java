package com.github.epiicthundercat.hempfarmer.blocks.grinder;

import net.minecraftforge.common.ForgeConfigSpec;

public class GrinderConfig {


    public static ForgeConfigSpec.IntValue COLLECTING_DELAY;
    public static ForgeConfigSpec.IntValue HEMP_PER;
    public static ForgeConfigSpec.IntValue ENERGY_CAPACITY;
    public static ForgeConfigSpec.IntValue ENERGY_RECEIVE;
    public static ForgeConfigSpec.IntValue ENERGY_GENERATE;

    public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
        SERVER_BUILDER.comment("Settings for the grinder").push("grinder");

        COLLECTING_DELAY = SERVER_BUILDER
                .comment("Delay (in ticks) before collecting items")
                .defineInRange("collectingDelay", 10, 1, Integer.MAX_VALUE);
        HEMP_PER = SERVER_BUILDER
                .comment("How much hemp you get from one ore")
                .defineInRange("groundHempPerRaw", 2, 1, Integer.MAX_VALUE);
        ENERGY_CAPACITY = SERVER_BUILDER
                .comment("How much energy fits into the grinder")
                .defineInRange("capacity", 100000, 1, Integer.MAX_VALUE);
        ENERGY_RECEIVE = SERVER_BUILDER
                .comment("How much energy the grinder can receive per side")
                .defineInRange("receive", 1000, 1, Integer.MAX_VALUE);
        ENERGY_GENERATE = SERVER_BUILDER
                .comment("How much energy is needed to process one hemp")
                .defineInRange("grind", 250, 1, Integer.MAX_VALUE);

        SERVER_BUILDER.pop();
    }



}
