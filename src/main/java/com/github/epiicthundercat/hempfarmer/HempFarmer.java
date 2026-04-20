package com.github.epiicthundercat.hempfarmer;

import com.github.epiicthundercat.hempfarmer.setup.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(HempFarmer.MODID)
public class HempFarmer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "hempfarmer";

    public HempFarmer(FMLJavaModLoadingContext context) {
        IEventBus modEventBus = context.getModEventBus();
        Registration.init(modEventBus);
        ModSetup.setup();
        HFConfig.register(context.getContainer());

        modEventBus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modEventBus.addListener(ClientSetup::init));
    }
}
