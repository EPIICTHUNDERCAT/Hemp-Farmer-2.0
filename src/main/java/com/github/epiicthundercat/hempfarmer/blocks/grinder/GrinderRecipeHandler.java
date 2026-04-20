package com.github.epiicthundercat.hempfarmer.blocks.grinder;

import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

public class GrinderRecipeHandler implements Recipe<SingleRecipeInput> {

    public static final Serializer SERIALIZER = new Serializer();

    private final NonNullList<Ingredient> INPUTS;
    private final ItemStack OUTPUT;
    private final float XP;
    protected final int CRAFTTIME;

    public GrinderRecipeHandler(NonNullList<Ingredient> inputStacks, ItemStack outputStack, float xp, int craftTime) {
        this.INPUTS = inputStacks;
        this.OUTPUT = outputStack;
        this.XP = xp;
        this.CRAFTTIME = craftTime;
    }

    @Override
    public boolean matches(SingleRecipeInput input, Level level) {
        return !INPUTS.isEmpty() && INPUTS.get(0).test(input.item());
    }

    @Override
    public ItemStack assemble(SingleRecipeInput input, HolderLookup.Provider registries) {
        return OUTPUT.copy();
    }

    public boolean matches(ItemStackHandler inv) {
        ItemStack stack = inv.getStackInSlot(0);
        return !stack.isEmpty() && !INPUTS.isEmpty() && INPUTS.get(0).test(stack);
    }

    public ItemStack getOutput() {
        return OUTPUT.copy();
    }

    public float getXP() {
        return XP;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return INPUTS;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return OUTPUT;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return Registration.GRINDER_RECIPE_TYPE.get();
    }

    public int getCraftTime() {
        return CRAFTTIME;
    }

    public static class Serializer implements RecipeSerializer<GrinderRecipeHandler> {

        public static final MapCodec<GrinderRecipeHandler> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.listOf()
                        .xmap(list -> {
                            NonNullList<Ingredient> r = NonNullList.create();
                            r.addAll(list);
                            return r;
                        }, list -> list)
                        .fieldOf("ingredients")
                        .forGetter(r -> r.INPUTS),
                ItemStack.STRICT_CODEC.fieldOf("output").forGetter(r -> r.OUTPUT),
                com.mojang.serialization.Codec.FLOAT.fieldOf("xp").forGetter(r -> r.XP),
                com.mojang.serialization.Codec.INT.fieldOf("craft_time").forGetter(r -> r.CRAFTTIME)
        ).apply(inst, GrinderRecipeHandler::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, GrinderRecipeHandler> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()),
                r -> (List<Ingredient>) (List<?>) r.INPUTS,
                ItemStack.STREAM_CODEC,
                r -> r.OUTPUT,
                ByteBufCodecs.FLOAT,
                r -> r.XP,
                ByteBufCodecs.INT,
                r -> r.CRAFTTIME,
                (inputs, output, xp, craftTime) -> {
                    NonNullList<Ingredient> list = NonNullList.create();
                    list.addAll(inputs);
                    return new GrinderRecipeHandler(list, output, xp, craftTime);
                }
        );

        @Override
        public MapCodec<GrinderRecipeHandler> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, GrinderRecipeHandler> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
