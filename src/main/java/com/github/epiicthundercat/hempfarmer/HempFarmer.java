package com.github.epiicthundercat.hempfarmer;

import com.github.epiicthundercat.hempfarmer.setup.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.bus.BusGroup;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HempFarmer.MODID)
public class HempFarmer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "hempfarmer";

    public HempFarmer(FMLJavaModLoadingContext context) {
        BusGroup modBusGroup = context.getModBusGroup();
        Registration.init(modBusGroup);
        HFConfig.register(context.getContainer());
        FMLCommonSetupEvent.getBus(modBusGroup).addListener(ModSetup::init);
        BuildCreativeModeTabContentsEvent.BUS.addListener(ModSetup::buildCreativeTab);
        if (FMLEnvironment.dist == Dist.CLIENT) {
            FMLClientSetupEvent.getBus(modBusGroup).addListener(ClientSetup::init);
        }
    }
}
