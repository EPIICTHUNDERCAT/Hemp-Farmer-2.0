package com.github.epiicthundercat.hempfarmer.event.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class SeedDropModifier extends LootModifier {

    public static final Supplier<MapCodec<SeedDropModifier>> CODEC = Suppliers.memoize(() ->
            RecordCodecBuilder.<SeedDropModifier>mapCodec(inst ->
                    LootModifier.codecStart(inst)
                            .and(ForgeRegistries.ITEMS.getCodec().fieldOf("addition").forGetter(m -> m.addition))
                            .and(Codec.INT.optionalFieldOf("count", 1).forGetter(m -> m.count))
                            .apply(inst, SeedDropModifier::new)
            )
    );

    private final Item addition;
    private final int count;

    public SeedDropModifier(LootItemCondition[] conditionsIn, Item addition, int count) {
        super(conditionsIn);
        this.addition = addition;
        this.count = count;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(LootTable lootTable, ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(new ItemStack(addition, count));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}