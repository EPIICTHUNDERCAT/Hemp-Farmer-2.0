package com.github.epiicthundercat.hempfarmer.datagen;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.event.loot.SeedDropModifier;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.InvertedLootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class HempFarmerGlobalLootModifiers extends GlobalLootModifierProvider {

    public HempFarmerGlobalLootModifiers(PackOutput output) {
        super(output, HempFarmer.MODID);
    }

    @Override
    protected void start() {
        LootItemCondition notShears = InvertedLootItemCondition.invert(
                MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS))
        ).build();

        addSeed("hemp_seeds_from_grass",      Blocks.GRASS,       Registration.SEEDS_HEMP.get(),   notShears);
        addSeed("hemp_seeds_from_fern",        Blocks.FERN,        Registration.SEEDS_HEMP.get(),   notShears);
        addSeed("hemp_seeds_from_large_fern",  Blocks.LARGE_FERN,  Registration.SEEDS_HEMP.get(),   notShears);
        addSeed("hemp_seeds_from_tall_grass",  Blocks.TALL_GRASS,  Registration.SEEDS_HEMP.get(),   notShears);

        addSeed("indica_seeds_from_grass",     Blocks.GRASS,       Registration.SEEDS_INDICA.get(), notShears);
        addSeed("indica_seeds_from_fern",      Blocks.FERN,        Registration.SEEDS_INDICA.get(), notShears);
        addSeed("indica_seeds_from_large_fern",Blocks.LARGE_FERN,  Registration.SEEDS_INDICA.get(), notShears);
        addSeed("indica_seeds_from_tall_grass",Blocks.TALL_GRASS,  Registration.SEEDS_INDICA.get(), notShears);

        addSeed("sativa_seeds_from_grass",     Blocks.GRASS,       Registration.SEEDS_SATIVA.get(), notShears);
        addSeed("sativa_seeds_from_fern",      Blocks.FERN,        Registration.SEEDS_SATIVA.get(), notShears);
        addSeed("sativa_seeds_from_large_fern",Blocks.LARGE_FERN,  Registration.SEEDS_SATIVA.get(), notShears);
        addSeed("sativa_seeds_from_tall_grass",Blocks.TALL_GRASS,  Registration.SEEDS_SATIVA.get(), notShears);

        // Nelly Song music disc — 5% chance in various structure chests
        addDisc("nelly_song_disc_from_dungeon",          "minecraft:chests/simple_dungeon");
        addDisc("nelly_song_disc_from_desert_pyramid",   "minecraft:chests/desert_pyramid");
        addDisc("nelly_song_disc_from_nether_fortress",  "minecraft:chests/nether_bridge");
        addDisc("nelly_song_disc_from_jungle_temple",    "minecraft:chests/jungle_temple");
        addDisc("nelly_song_disc_from_woodland_mansion", "minecraft:chests/woodland_mansion");
        addDisc("nelly_song_disc_from_stronghold",       "minecraft:chests/stronghold_library");
        addDisc("nelly_song_disc_from_end_city",         "minecraft:chests/end_city_treasure");
        addDisc("nelly_song_disc_from_bastion",          "minecraft:chests/bastion_treasure");
        addDisc("nelly_song_disc_from_pillager_outpost", "minecraft:chests/pillager_outpost");
    }

    private void addDisc(String name, String lootTableId) {
        add(name, new SeedDropModifier(
                new LootItemCondition[] {
                        LootTableIdCondition.builder(ResourceLocation.parse(lootTableId)).build(),
                        LootItemRandomChanceCondition.randomChance(0.03f).build()
                },
                Registration.NELLY_SONG_MUSIC_DISC.get(),
                1
        ));
    }

    private void addSeed(String name, Block block, Item item, LootItemCondition notShears) {
        add(name, new SeedDropModifier(
                new LootItemCondition[] {
                        LootItemRandomChanceCondition.randomChance(0.1f).build(),
                        LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).build(),
                        notShears
                },
                item,
                1
        ));
    }
}
