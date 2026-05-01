package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.setup.HFPaintings;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

@Mod.EventBusSubscriber(modid = HempFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var generator = event.getGenerator();
        var output = generator.getPackOutput();
        var lookupProvider = event.getLookupProvider();
        var existingFileHelper = event.getExistingFileHelper();

        if (event.includeServer()) {
            generator.addProvider(true, new HempFarmerRecipes(output, lookupProvider));
            generator.addProvider(true, new HempFarmerLootTables(output, lookupProvider));
            generator.addProvider(true, new HempFarmerGlobalLootModifiers(output, lookupProvider));
            HempFarmerBlockTags blockTags = new HempFarmerBlockTags(output, lookupProvider, existingFileHelper);
            generator.addProvider(true, blockTags);
            generator.addProvider(true, new HempFarmerItemTags(output, lookupProvider, existingFileHelper));

            // Painting variants (data-driven registry)
            RegistrySetBuilder registryBuilder = new RegistrySetBuilder()
                    .add(Registries.PAINTING_VARIANT, HFPaintings::bootstrap);
            DatapackBuiltinEntriesProvider paintingProvider = new DatapackBuiltinEntriesProvider(
                    output, lookupProvider, registryBuilder, Set.of(HempFarmer.MODID));
            generator.addProvider(true, paintingProvider);
            generator.addProvider(true, new HFPaintingTagsProvider(
                    output, paintingProvider.getFullRegistries(), existingFileHelper));
        }
        if (event.includeClient()) {
            // HempFarmerBlockStates and HempFarmerItemModels removed: BlockStateProvider and
            // ItemModelProvider were removed in Forge 64. Static JSONs are in src/main/resources.
            generator.addProvider(true, new HempFarmerLanguageProvider(output, "en_us"));
        }
    }
}
