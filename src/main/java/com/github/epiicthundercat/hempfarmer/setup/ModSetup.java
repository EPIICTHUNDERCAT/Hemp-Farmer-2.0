package com.github.epiicthundercat.hempfarmer.setup;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = HempFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModSetup {

    public static final String TAB_NAME = "hempfarmer";

    public static final CreativeModeTab ITEM_GROUP = new CreativeModeTab(TAB_NAME) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Registration.SHOT_LEAF.get());
        }
    };

    public static void setup() {
        IEventBus bus = MinecraftForge.EVENT_BUS;
//        bus.addListener(Ores::onBiomeLoadingEvent);
//        bus.addGenericListener(Entity.class, ManaEvents::onAttachCapabilitiesPlayer);
//        bus.addListener(ManaEvents::onPlayerCloned);
//        bus.addListener(ManaEvents::onRegisterCapabilities);
//        bus.addListener(ManaEvents::onWorldTick);
    }

    public static void init(FMLCommonSetupEvent event) {
//        event.enqueueWork(() -> {
//            Ores.registerConfiguredFeatures();
//            Dimensions.register();
//        });
//        Messages.register();

    }

//    @SubscribeEvent
//    public static void onAttributeCreate(EntityAttributeCreationEvent event) {
//        event.put(Registration.THIEF.get(), ThiefEntity.prepareAttributes().build());
//    }
}
