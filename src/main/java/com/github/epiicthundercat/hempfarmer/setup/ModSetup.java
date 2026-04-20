package com.github.epiicthundercat.hempfarmer.setup;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = HempFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static final String TAB_NAME = "hempfarmer";

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
    }

    public static void init(FMLCommonSetupEvent event) {
        HFMessages.register();
    }

    @SubscribeEvent
    public static void buildCreativeTab(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey() == Registration.HEMP_FARMER_TAB.getKey()) {
            event.accept(Registration.SHOT_LEAF);
            event.accept(Registration.LEAF);
            event.accept(Registration.CANNABIS_TEA);
            event.accept(Registration.LIGHTER);
            event.accept(Registration.NELLY_SONG_MUSIC_DISC);
            event.accept(Registration.SATIVA_JOINT);
            event.accept(Registration.REGS_JOINT);
            event.accept(Registration.INDICA_JOINT);
            event.accept(Registration.SATIVA_BUD);
            event.accept(Registration.INDICA_BUD);
            event.accept(Registration.BUD);
            event.accept(Registration.GROUND_SATIVA_BUD);
            event.accept(Registration.GROUND_INDICA_BUD);
            event.accept(Registration.GROUND_BUD);
            event.accept(Registration.RAW_HEMP);
            event.accept(Registration.LIME_RAW_HEMP);
            event.accept(Registration.VIOLET_RAW_HEMP);
            event.accept(Registration.DRY_HEMP);
            event.accept(Registration.LIME_DRY_HEMP);
            event.accept(Registration.VIOLET_DRY_HEMP);
            event.accept(Registration.HEMP_HEARTS);
            event.accept(Registration.LIME_HEMP_HEARTS);
            event.accept(Registration.VIOLET_HEMP_HEARTS);
            event.accept(Registration.BOWL_HEMP_HEARTS);
            event.accept(Registration.BOWL_LIME_HEMP_HEARTS);
            event.accept(Registration.BOWL_VIOLET_HEMP_HEARTS);
            event.accept(Registration.HEMP_MILK_BUCKET);
            event.accept(Registration.POT_BROWNIE);
            event.accept(Registration.SEEDS_HEMP);
            event.accept(Registration.SEEDS_SATIVA);
            event.accept(Registration.SEEDS_INDICA);
            event.accept(Registration.SEEDS_HEMP_CRUSHED);
            event.accept(Registration.SEEDS_SATIVA_CRUSHED);
            event.accept(Registration.SEEDS_INDICA_CRUSHED);
            event.accept(Registration.SEEDS_HEMP_TOASTED);
            event.accept(Registration.SEEDS_SATIVA_TOASTED);
            event.accept(Registration.SEEDS_INDICA_TOASTED);
            event.accept(Registration.BURLAP_ITEM);
            event.accept(Registration.OILY_BURLAP_ITEM);
            event.accept(Registration.RESIN_BURLAP_ITEM);
            event.accept(Registration.LIME_BURLAP_ITEM);
            event.accept(Registration.VIOLET_BURLAP_ITEM);
            event.accept(Registration.BURLAP_HELMET);
            event.accept(Registration.BURLAP_CHESTPLATE);
            event.accept(Registration.BURLAP_LEGGINGS);
            event.accept(Registration.BURLAP_BOOTS);
            event.accept(Registration.HEMP_OIL);
            event.accept(Registration.LIME_OIL);
            event.accept(Registration.VIOLET_OIL);
            event.accept(Registration.RESIN);
            event.accept(Registration.HEMP_PAPER);
            event.accept(Registration.ROLLING_PAPER);
            event.accept(Registration.LEAF_WAND);
            event.accept(Registration.SUPERIOR_LEAF_WAND);
            event.accept(Registration.BROKEN_SUPERIOR_LEAF_WAND);
            event.accept(Registration.SHOT_LEAF);
            event.accept(Registration.LIME_DIRT_ITEM);
            event.accept(Registration.OILY_DIRT_ITEM);
            event.accept(Registration.RESIN_DIRT_ITEM);
            event.accept(Registration.VIOLET_DIRT_ITEM);
            event.accept(Registration.BURLAP_BLOCK_ITEM);
            event.accept(Registration.OILY_BURLAP_BLOCK_ITEM);
            event.accept(Registration.LIME_BURLAP_BLOCK_ITEM);
            event.accept(Registration.VIOLET_BURLAP_BLOCK_ITEM);
            event.accept(Registration.RESIN_BURLAP_BLOCK_ITEM);
            event.accept(Registration.POWER_BATTERY_ITEM);
            event.accept(Registration.GRINDER_ITEM);
        }
    }
}
