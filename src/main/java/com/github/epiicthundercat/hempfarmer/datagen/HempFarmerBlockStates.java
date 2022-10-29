package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.blocks.crops.HempCrop;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;

public class HempFarmerBlockStates extends BlockStateProvider {


    public HempFarmerBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, HempFarmer.MODID, exFileHelper);
    }


    @Override
    protected void registerStatesAndModels() {


    }



}
