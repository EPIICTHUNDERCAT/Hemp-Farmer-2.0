package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class HempFarmerItemTags extends ItemTagsProvider {


    public HempFarmerItemTags(DataGenerator generator, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
        super(generator, blockTags, HempFarmer.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
//        tag(Tags.Items.CROPS)
//                .add(Registration.BLOCK.get());
//

        tag(Tags.Items.SEEDS)
                .add(Registration.SEEDS_SATIVA.get())
                .add(Registration.SEEDS_INDICA.get())
                .add(Registration.SEEDS_HEMP.get());

//        tag(Tags.Items.)

    }

    @Override
    public String getName() {
        return "Hemp Farmer Tags";
    }
}
