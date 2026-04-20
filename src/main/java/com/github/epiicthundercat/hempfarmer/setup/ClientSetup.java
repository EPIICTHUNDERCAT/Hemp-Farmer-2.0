package com.github.epiicthundercat.hempfarmer.setup;

import com.github.epiicthundercat.hempfarmer.client.PowerBatteryScreen;
import com.github.epiicthundercat.hempfarmer.client.grinder.GrinderScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

// NOTE (1.21.1): ItemBlockRenderTypes.setRenderLayer() was removed.
// Render types are now declared in block model JSON via "render_type": "minecraft:cutout"
// or "minecraft:translucent". Update the block state / model JSONs for:
//   - HEMP_CROP, INDICA_CROP, SATIVA_CROP  → "render_type": "minecraft:cutout"
//   - POWER_BATTERY                        → "render_type": "minecraft:translucent"
public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.POWER_BATTERY_CONTAINER.get(), PowerBatteryScreen::new);
            MenuScreens.register(Registration.GRINDER_CONTAINER.get(), GrinderScreen::new);
        });
    }
}
