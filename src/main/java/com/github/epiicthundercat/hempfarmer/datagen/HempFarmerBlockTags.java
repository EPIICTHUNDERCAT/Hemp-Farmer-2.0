package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class HempFarmerBlockTags extends BlockTagsProvider {

    public HempFarmerBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, HempFarmer.MODID, helper);
    }

    @Override
    protected void addTags() {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
//                .add(Registration.GENERATOR.get())
                .add(Registration.POWER_BATTERY.get());
//                .add(Registration.MYSTERIOUS_ORE_OVERWORLD.get())
//                .add(Registration.MYSTERIOUS_ORE_NETHER.get())
//                .add(Registration.MYSTERIOUS_ORE_END.get())
//                .add(Registration.MYSTERIOUS_ORE_DEEPSLATE.get());
      tag(BlockTags.NEEDS_IRON_TOOL)
             //   .add(Registration.GEN.get())
                .add(Registration.POWER_BATTERY.get());
//                .add(Registration.MYSTERIOUS_ORE_OVERWORLD.get())
//                .add(Registration.MYSTERIOUS_ORE_NETHER.get())
//                .add(Registration.MYSTERIOUS_ORE_END.get())
//                .add(Registration.MYSTERIOUS_ORE_DEEPSLATE.get());
//        tag(Tags.Blocks.ORES)
//                .add(Registration.MYSTERIOUS_ORE_OVERWORLD.get())
//                .add(Registration.MYSTERIOUS_ORE_NETHER.get())
//                .add(Registration.MYSTERIOUS_ORE_END.get())
//                .add(Registration.MYSTERIOUS_ORE_DEEPSLATE.get());
//
//        tag(Registration.MYSTERIOUS_ORE)
//                .add(Registration.MYSTERIOUS_ORE_OVERWORLD.get())
//                .add(Registration.MYSTERIOUS_ORE_NETHER.get())
//                .add(Registration.MYSTERIOUS_ORE_END.get())
//                .add(Registration.MYSTERIOUS_ORE_DEEPSLATE.get());
    }

    @Override
    public String getName() {
        return "Hemp Farmer Tags";
    }
}