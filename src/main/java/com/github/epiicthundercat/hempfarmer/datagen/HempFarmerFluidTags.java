package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
/*
Stanby on the Delete of class unless not needed before release (assuming it will not be!)
 */
public class HempFarmerFluidTags extends FluidTagsProvider {


    public HempFarmerFluidTags(DataGenerator generator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(generator, HempFarmer.MODID, existingFileHelper);


    }
//
//    @Override
//    protected void addTags() {
//        tag(Tags.Fluids.MILK).add(Registration.MILK_ITEM);
//    }
//

    @Override
    public String getName() {
        return "Hemp Farmer Tags";
    }
}
