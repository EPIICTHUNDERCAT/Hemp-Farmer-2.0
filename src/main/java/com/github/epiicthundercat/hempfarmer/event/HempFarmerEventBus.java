package com.github.epiicthundercat.hempfarmer.event;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.event.loot.SeedDropModifier;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;

@Mod.EventBusSubscriber(modid = HempFarmer.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HempFarmerEventBus {

    @SubscribeEvent
    public static void registerModifierSerializers(@Nonnull final RegistryEvent.Register<GlobalLootModifierSerializer<?>>
                                                           event) {
        event.getRegistry().registerAll(

                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "hemp_seeds_from_grass")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "indica_seeds_from_grass")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "sativa_seeds_from_grass")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "hemp_seeds_from_fern")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "indica_seeds_from_fern")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "sativa_seeds_from_fern")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "hemp_seeds_from_large_fern")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "indica_seeds_from_large_fern")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "sativa_seeds_from_large_fern")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "hemp_seeds_from_tall_grass")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "indica_seeds_from_tall_grass")),
                new SeedDropModifier.Serializer().setRegistryName
                        (new ResourceLocation(HempFarmer.MODID, "sativa_seeds_from_tall_grass"))
//                new FoodDropModifiers.Serializer().setRegistryName
//                        (new ResourceLocation(HempFarmer.MODID, "raw_horse_meat_from_horse"))

        );
    }
}
