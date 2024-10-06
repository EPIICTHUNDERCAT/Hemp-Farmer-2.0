package com.github.epiicthundercat.hempfarmer.setup;

import com.github.epiicthundercat.hempfarmer.client.PowerBatteryScreen;
import com.github.epiicthundercat.hempfarmer.client.grinder.GrinderScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {


        event.enqueueWork(() -> {
            MenuScreens.register(Registration.POWER_BATTERY_CONTAINER.get(), PowerBatteryScreen::new);
            ItemBlockRenderTypes.setRenderLayer(Registration.POWER_BATTERY.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(Registration.HEMP_CROP.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(Registration.INDICA_CROP.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(Registration.SATIVA_CROP.get(), RenderType.cutout());

            MenuScreens.register(Registration.GRINDER_CONTAINER.get(), GrinderScreen::new);


        });
    }


}
