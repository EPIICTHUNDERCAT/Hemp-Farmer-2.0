package com.github.epiicthundercat.hempfarmer.blocks.grinder;

import net.minecraftforge.common.ForgeConfigSpec;

public class GrinderConfig {


    public static ForgeConfigSpec.IntValue HEMP_PER;
    public static ForgeConfigSpec.IntValue ENERGY_CAPACITY;
    public static ForgeConfigSpec.IntValue ENERGY_RECEIVE;
    public static ForgeConfigSpec.IntValue ENERGY_NEED;

    public static void registerServerConfig(ForgeConfigSpec.Builder SERVER_BUILDER) {
        SERVER_BUILDER.comment("Settings for the grinder").push("grinder");


        HEMP_PER = SERVER_BUILDER
                .comment("How much hemp you get from one ore")
                .defineInRange("groundHempPerRaw", 2, 1, Integer.MAX_VALUE);
        ENERGY_CAPACITY = SERVER_BUILDER
                .comment("How much energy fits into the grinder")
                .defineInRange("capacity", 100000, 1, Integer.MAX_VALUE);
        ENERGY_RECEIVE = SERVER_BUILDER
                .comment("How much energy the grinder can receive per side")
                .defineInRange("receive", 1000, 1, Integer.MAX_VALUE);
        ENERGY_NEED = SERVER_BUILDER
                .comment("How much energy is needed to process one hemp")
                .defineInRange("grind", 250, 1, Integer.MAX_VALUE);

        SERVER_BUILDER.pop();
    }



}
