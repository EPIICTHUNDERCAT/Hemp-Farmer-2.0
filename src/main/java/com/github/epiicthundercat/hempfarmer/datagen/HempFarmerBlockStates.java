package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class HempFarmerBlockStates extends BlockStateProvider {


    public HempFarmerBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, HempFarmer.MODID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {
        simpleBlock(Registration.LIME_DIRT.get());
        simpleBlock(Registration.OILY_DIRT.get());
        simpleBlock(Registration.VIOLET_DIRT.get());
        simpleBlock(Registration.RESIN_DIRT.get());

    }


}
