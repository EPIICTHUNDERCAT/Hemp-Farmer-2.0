package com.github.epiicthundercat.hempfarmer.datagen;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyCustomDataFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.nbt.ContextNbtProvider;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public abstract class BaseLootTableProvider implements net.minecraft.data.loot.LootTableSubProvider {

    protected final Map<Block, LootTable.Builder> lootTables = new HashMap<>();

    protected abstract void addTables();

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> writer) {
        addTables();
        for (Map.Entry<Block, LootTable.Builder> entry : lootTables.entrySet()) {
            writer.accept(entry.getKey().getLootTable(), entry.getValue().setParamSet(LootContextParamSets.BLOCK));
        }
    }

    protected LootTable.Builder createStandardTable(String name, Block block, BlockEntityType<?> type) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block)
                        .apply(CopyCustomDataFunction.copyData(ContextNbtProvider.BLOCK_ENTITY)
                                .copy("Inventory", "Inventory", CopyCustomDataFunction.MergeStrategy.REPLACE)
                                .copy("Energy", "Energy", CopyCustomDataFunction.MergeStrategy.REPLACE)
                                .copy("Info", "Info", CopyCustomDataFunction.MergeStrategy.REPLACE)
                                .copy("CookTime", "CookTime", CopyCustomDataFunction.MergeStrategy.REPLACE)
                                .copy("CookLength", "CookLength", CopyCustomDataFunction.MergeStrategy.REPLACE)
                                .copy("XP", "XP", CopyCustomDataFunction.MergeStrategy.REPLACE)));
        return LootTable.lootTable().withPool(builder);
    }

    protected LootTable.Builder createSimpleTable(String name, Block block) {
        LootPool.Builder builder = LootPool.lootPool()
                .name(name)
                .setRolls(ConstantValue.exactly(1))
                .add(LootItem.lootTableItem(block));
        return LootTable.lootTable().withPool(builder);
    }
}
