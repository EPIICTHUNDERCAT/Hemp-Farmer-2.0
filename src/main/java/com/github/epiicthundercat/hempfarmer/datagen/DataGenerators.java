package com.github.epiicthundercat.hempfarmer.datagen;


import com.github.epiicthundercat.hempfarmer.HempFarmer;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = HempFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {


        DataGenerator generator = event.getGenerator();
        if (event.includeServer()) {
            generator.addProvider(new HempFarmerRecipes(generator));
            generator.addProvider(new HempFarmerLootTables(generator));
            HempFarmerBlockTags blockTags = new HempFarmerBlockTags(generator, event.getExistingFileHelper());
            generator.addProvider(blockTags);
            generator.addProvider(new HempFarmerItemTags(generator, blockTags, event.getExistingFileHelper()));
            //generator.addProvider(new TutBiomeTags(generator, event.getExistingFileHelper()));
            //    generator.addProvider(new TutStructureSetTags(generator, event.getExistingFileHelper()));
        }
        if (event.includeClient()) {
            // generator.addProvider(new HempFarmerBlockStates(generator, event.getExistingFileHelper()));
            generator.addProvider(new HempFarmerItemModels(generator, event.getExistingFileHelper()));
            generator.addProvider(new HempFarmerLanguageProvider(generator, "en_us"));


        }


    }
}