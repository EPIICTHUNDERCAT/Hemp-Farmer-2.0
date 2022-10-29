package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;

public class HempFarmerLootTables extends BaseLootTableProvider {


    public HempFarmerLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.POWER_BATTERY.get(), createStandardTable("powerbattery", Registration.POWER_BATTERY.get(),
                Registration.POWER_BATTERY_BE.get()));
        lootTables.put(Registration.GRINDER.get(), createStandardTable("grinder", Registration.GRINDER.get(), Registration.GRINDER_BE.get()));

    }


}
