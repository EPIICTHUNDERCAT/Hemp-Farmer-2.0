package com.github.epiicthundercat.hempfarmer.datagen;


import com.github.epiicthundercat.hempfarmer.HempFarmer;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = HempFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(true, new HempFarmerRecipes(output));
            generator.addProvider(true, new HempFarmerLootTables(output));
            generator.addProvider(true, new HempFarmerGlobalLootModifiers(output));
            HempFarmerBlockTags blockTags = new HempFarmerBlockTags(output, lookupProvider, existingFileHelper);
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new HempFarmerItemTags(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        }
        if (event.includeClient()) {
            generator.addProvider(true, new HempFarmerBlockStates(output, existingFileHelper));
            generator.addProvider(true, new HempFarmerItemModels(output, existingFileHelper));
            generator.addProvider(true, new HempFarmerLanguageProvider(output, "en_us"));
        }
    }
}
