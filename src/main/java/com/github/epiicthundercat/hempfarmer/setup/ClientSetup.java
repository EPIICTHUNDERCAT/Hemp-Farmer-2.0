package com.github.epiicthundercat.hempfarmer.setup;

import com.github.epiicthundercat.hempfarmer.client.PowerBatteryRenderer;
import com.github.epiicthundercat.hempfarmer.client.PowerBatteryScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.POWER_BATTERY_CONTAINER.get(), PowerBatteryScreen::new);
            ItemBlockRenderTypes.setRenderLayer(Registration.POWER_BATTERY.get(), RenderType.translucent());
            PowerBatteryRenderer.register();
        });
//        MinecraftForge.EVENT_BUS.addListener(KeyInputHandler::onKeyInput);
//        KeyBindings.init();
//        OverlayRegistry.registerOverlayAbove(HOTBAR_ELEMENT, "name", ManaOverlay.HUD_MANA);
    }


//    @SubscribeEvent
//    public static void onModelRegistryEvent(ModelRegistryEvent event) {
//        ModelLoaderRegistry.registerLoader(GeneratorModelLoader.GENERATOR_LOADER, new GeneratorModelLoader());
//    }
//
//    @SubscribeEvent
//    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
//        event.registerLayerDefinition(ThiefModel.THIEF_LAYER, ThiefModel::createBodyLayer);
//    }
//
//    @SubscribeEvent
//    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
//        event.registerEntityRenderer(Registration.THIEF.get(), ThiefRenderer::new);
//    }
//
    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
            return;
        }
        event.addSprite(PowerBatteryRenderer.POWER_SMOKE);
    }

}
