package com.github.epiicthundercat.hempfarmer.setup;

import com.github.epiicthundercat.hempfarmer.blocks.crops.CropDropConfig;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderConfig;
import com.github.epiicthundercat.hempfarmer.blocks.powerbattery.PowerBatteryConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.config.ModConfig;

public class HFConfig {

    public static void register(ModContainer container) {
        registerServerConfigs(container);
        registerClientConfigs(container);
    }

    private static void registerClientConfigs(ModContainer container) {
        ForgeConfigSpec.Builder CLIENT_BUILDER = new ForgeConfigSpec.Builder();
        PowerBatteryConfig.registerClientConfig(CLIENT_BUILDER);
        container.addConfig(new ModConfig(ModConfig.Type.CLIENT, CLIENT_BUILDER.build(), container));
    }

    private static void registerServerConfigs(ModContainer container) {
        ForgeConfigSpec.Builder SERVER_BUILDER = new ForgeConfigSpec.Builder();
        GrinderConfig.registerServerConfig(SERVER_BUILDER);
        PowerBatteryConfig.registerServerConfig(SERVER_BUILDER);
        CropDropConfig.registerServerConfig(SERVER_BUILDER);
        container.addConfig(new ModConfig(ModConfig.Type.SERVER, SERVER_BUILDER.build(), container));
    }

}
