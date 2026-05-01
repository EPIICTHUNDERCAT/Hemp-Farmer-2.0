package com.github.epiicthundercat.hempfarmer.blocks.grinder;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class GrinderRecipeHandler implements Recipe<SingleRecipeInput> {

    // Lightweight output reference — avoids calling item.components() during recipe JSON decode,
    // which fails for modded items because components are not yet initialized at that point.
    private record OutputRef(Identifier id, int count) {}

    private static final com.mojang.serialization.Codec<OutputRef> OUTPUT_CODEC = RecordCodecBuilder.create(inst -> inst.group(
            Identifier.CODEC.fieldOf("id").forGetter(OutputRef::id),
            com.mojang.serialization.Codec.INT.optionalFieldOf("count", 1).forGetter(OutputRef::count)
    ).apply(inst, OutputRef::new));

    public static final MapCodec<GrinderRecipeHandler> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC.listOf()
                    .xmap(list -> {
                        NonNullList<Ingredient> r = NonNullList.create();
                        r.addAll(list);
                        return r;
                    }, list -> list)
                    .fieldOf("ingredients")
                    .forGetter(r -> r.inputs),
            OUTPUT_CODEC.fieldOf("output").forGetter(r -> new OutputRef(r.outputId, r.outputCount)),
            com.mojang.serialization.Codec.FLOAT.fieldOf("xp").forGetter(r -> r.xp),
            com.mojang.serialization.Codec.INT.fieldOf("craft_time").forGetter(r -> r.craftTime)
    ).apply(inst, (inputs, output, xp, craftTime) ->
            new GrinderRecipeHandler(inputs, output.id(), output.count(), xp, craftTime)));

    // Stream codec sends id + count directly — ItemStack construction happens on receive, when components are ready.
    public static final StreamCodec<RegistryFriendlyByteBuf, GrinderRecipeHandler> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
            r -> (List<Ingredient>) (List<?>) r.inputs,
            Identifier.STREAM_CODEC,
            r -> r.outputId,
            ByteBufCodecs.INT,
            r -> r.outputCount,
            ByteBufCodecs.FLOAT,
            r -> r.xp,
            ByteBufCodecs.INT,
            r -> r.craftTime,
            (inputs, outputId, outputCount, xp, craftTime) -> {
                NonNullList<Ingredient> list = NonNullList.create();
                list.addAll(inputs);
                return new GrinderRecipeHandler(list, outputId, outputCount, xp, craftTime);
            }
    );

    public static final RecipeSerializer<GrinderRecipeHandler> SERIALIZER = new RecipeSerializer<>(CODEC, STREAM_CODEC);

    private final NonNullList<Ingredient> inputs;
    private final Identifier outputId;
    private final int outputCount;
    private final float xp;
    protected final int craftTime;

    public GrinderRecipeHandler(NonNullList<Ingredient> inputs, Identifier outputId, int outputCount, float xp, int craftTime) {
        this.inputs = inputs;
        this.outputId = outputId;
        this.outputCount = outputCount;
        this.xp = xp;
        this.craftTime = craftTime;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return !inputs.isEmpty() && inputs.get(0).test(input.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input) {
        return getOutput();
    }

    public boolean matches(ItemStackHandler inv) {
        ItemStack stack = inv.getStackInSlot(0);
        return !stack.isEmpty() && !inputs.isEmpty() && inputs.get(0).test(stack);
    }

    // Creates the ItemStack on demand — safe to call after item registration is complete.
    public ItemStack getOutput() {
        Item item = ForgeRegistries.ITEMS.getValue(outputId);
        if (item == null || item == Items.AIR) return ItemStack.EMPTY;
        return new ItemStack(item, outputCount);
    }

    public float getXP() {
        return xp;
    }

    public NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public RecipeSerializer<GrinderRecipeHandler> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<GrinderRecipeHandler> getType() {
        return Registration.GRINDER_RECIPE_TYPE.get();
    }

    public int getCraftTime() {
        return craftTime;
    }

    @Override
    public boolean showNotification() {
        return false;
    }

    @Override
    public String group() {
        return "";
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }
}
