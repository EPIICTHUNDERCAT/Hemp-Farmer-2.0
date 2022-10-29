package com.github.epiicthundercat.hempfarmer;

import com.github.epiicthundercat.hempfarmer.setup.ClientSetup;
import com.github.epiicthundercat.hempfarmer.setup.HFConfig;
import com.github.epiicthundercat.hempfarmer.setup.ModSetup;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(HempFarmer.MODID)
public class HempFarmer {


    public static final Logger LOGGER = LogManager.getLogger();

    public static final String MODID = "hempfarmer";


    public HempFarmer() {

        Registration.init();
        ModSetup.setup();
        HFConfig.register();

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(ModSetup::init);
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));


    }

}
