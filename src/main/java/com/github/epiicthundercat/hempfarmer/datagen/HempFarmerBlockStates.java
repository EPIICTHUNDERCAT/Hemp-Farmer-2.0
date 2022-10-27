package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class HempFarmerBlockStates extends BlockStateProvider {


    public HempFarmerBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, HempFarmer.MODID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {


    }

}
