package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Blocks;

public class HempFarmerLootTables extends BaseLootTableProvider {


    public HempFarmerLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(Registration.POWER_BATTERY.get(), createStandardTable("powerbattery", Registration.POWER_BATTERY.get(),
                Registration.POWER_BATTERY_BE.get()));
        lootTables.put(Registration.GRINDER.get(), createStandardTable("grinder", Registration.GRINDER.get(), Registration.GRINDER_BE.get()));
        lootTables.put(Registration.RESIN_DIRT.get(), createSimpleTable("resin_dirt", Registration.RESIN_DIRT.get()));
        lootTables.put(Registration.RESIN_CARPET_BLOCK.get(), createSimpleTable("resin_burlap_carpet", Registration.RESIN_CARPET_BLOCK.get()));
        lootTables.put(Registration.BURLAP_CARPET_BLOCK.get(), createSimpleTable("burlap_carpet", Registration.BURLAP_CARPET_BLOCK.get()));
        lootTables.put(Registration.LIME_BURLAP_CARPET_BLOCK.get(), createSimpleTable("lime_burlap_carpet", Registration.LIME_BURLAP_CARPET_BLOCK.get()));
        lootTables.put(Registration.LIME_DIRT.get(), createSimpleTable("lime_dirt", Registration.LIME_DIRT.get()));
        lootTables.put(Registration.VIOLET_BURLAP_CARPET_BLOCK.get(), createSimpleTable("violet_burlap_carpet", Registration.VIOLET_BURLAP_CARPET_BLOCK.get()));
        lootTables.put(Registration.VIOLET_DIRT.get(), createSimpleTable("violet_dirt", Registration.VIOLET_DIRT.get()));
        lootTables.put(Registration.OILY_BURLAP_CARPET_BLOCK.get(), createSimpleTable("oily_burlap_carpet", Registration.OILY_BURLAP_CARPET_BLOCK.get()));
        lootTables.put(Registration.OILY_DIRT.get(), createSimpleTable("oily_dirt", Registration.OILY_DIRT.get()));

        //rarity is set to wheat seed standards. bonus multipler is 1 default.
        lootTables.put(Blocks.GRASS, createItemBlockLootTable("grass", Registration.SEEDS_HEMP.get(), Registration.SEEDS_INDICA.get(), Registration.SEEDS_SATIVA.get(), 0.025f, 1));
    }


}
