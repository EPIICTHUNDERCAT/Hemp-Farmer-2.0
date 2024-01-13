package com.github.epiicthundercat.hempfarmer.blocks.grinder;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import com.mojang.realmsclient.util.JsonUtils;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.RecipeMatcher;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class GrinderRecipeHandler implements Recipe<CraftingContainer> {

    public static final RecipeType<GrinderRecipeHandler> TYPE = RecipeType.register("grinder_recipe");
    public static final Serializer SERIALIZER = new Serializer();
    // protected final Level level;
    private final ResourceLocation ID;
    private final NonNullList<Ingredient> INPUTS;
    //private final Ingredient INPUTS;
    private final ItemStack OUTPUT;
    private final float XP;
    protected final int CRAFTTIME;
    private final boolean ISSIMPLE;

    public GrinderRecipeHandler(ResourceLocation id, /*Ingredient inputStacks*/NonNullList<Ingredient> inputStacks, ItemStack outputStack, float xp, int craftTime) {

        this.ID = id;
        this.INPUTS = inputStacks;
        this.OUTPUT = outputStack;
        this.XP = xp;
        this.CRAFTTIME = craftTime;
        this.ISSIMPLE = inputStacks.stream().allMatch(Ingredient::isSimple);
    }


    @Override
    public boolean matches(CraftingContainer inv, Level world) {
        StackedContents recipeItemHelper = new StackedContents();
        java.util.List<ItemStack> inputs = new java.util.ArrayList<>();
        int i = 0;

        for (int j = 0; j < 1; ++j) {
            ItemStack itemstack = inv.getItem(j);
            if (!itemstack.isEmpty()) {
                ++i;
                if (ISSIMPLE) {
                    recipeItemHelper.accountStack(itemstack, 1);
                } else {
                    inputs.add(itemstack);
                }
            }
        }

        return i == this.INPUTS.size() && (ISSIMPLE ? recipeItemHelper.canCraft(this, null) : RecipeMatcher.findMatches(inputs, this.INPUTS) != null);
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer) {
        return this.OUTPUT.copy();
    }

    public boolean matches(ItemStackHandler inv) {
        StackedContents recipeHelper = new StackedContents();
        java.util.List<ItemStack> inputStacks = new java.util.ArrayList<>();
        int count = 0;
        //how many slots? changed from 3-1 for 2 slots 0,1
        for (int i = 0; i < 1; ++i) {
            ItemStack itemstack = inv.getStackInSlot(i);
            if (!itemstack.isEmpty()) {
                ++count;
                if (ISSIMPLE) {
                    recipeHelper.accountStack(itemstack, 1);
                } else {
                    inputStacks.add(itemstack);
                }
            }
        }
        return count == this.INPUTS.size() && (ISSIMPLE ? recipeHelper.canCraft(this, null) : RecipeMatcher.findMatches(inputStacks, this.INPUTS) != null);
    }

    public ItemStack getOutput() {
        return this.OUTPUT.copy();
    }

    public float getXP() {
        return this.XP;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return this.INPUTS;
    }

    @Override
    public boolean isSpecial() {
        return this.ISSIMPLE;
    }


    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return this.OUTPUT;
    }

    @Override
    public ResourceLocation getId() {
        return this.ID;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    @Override
    public RecipeType<?> getType() {
        return TYPE;
    }

    public int getCraftTime() {
        return this.CRAFTTIME;
    }

    public static class Serializer extends ForgeRegistryEntry<RecipeSerializer<?>> implements RecipeSerializer<GrinderRecipeHandler> {

        @Override
        public GrinderRecipeHandler fromJson(ResourceLocation recipeId, JsonObject json) {
            try {
                NonNullList<Ingredient> inputStacks = NonNullList.create();
                for (int i = 0; i < GsonHelper.getAsJsonArray(json, "ingredients").size(); ++i) {
                    Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonArray(json, "ingredients").get(i));
                    if (!ingredient.isEmpty()) {
                        inputStacks.add(ingredient);
                    }
                }

                if (inputStacks.isEmpty()) {
                    throw new JsonParseException("No ingredients for Grinder recipe.");
                } else {
                    if (inputStacks.size() > 3) {
                        throw new JsonParseException("Too many ingredients for Grinder recipe, the max is 1.");
                    } else {
                        ItemStack outputStack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
                        float xp = GsonHelper.getAsFloat(json, "xp");
                        int craftTime = GsonHelper.getAsInt(json, "craft_time");
                        return new GrinderRecipeHandler(recipeId, inputStacks, outputStack, xp, craftTime);
                    }
                }
            } catch (JsonSyntaxException e) {
                return null;
            }
        }


        @Override
        public GrinderRecipeHandler fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            int inputSize = buffer.readVarInt();
            NonNullList<Ingredient> inputStacks = NonNullList.withSize(inputSize, Ingredient.EMPTY);
            for (int i = 0; i < inputStacks.size(); ++i) {
                inputStacks.set(i, Ingredient.fromNetwork(buffer));
            }
            float xp = buffer.readFloat();
            int craftTime = buffer.readInt();
            ItemStack outputStack = buffer.readItem();
            return new GrinderRecipeHandler(recipeId, inputStacks, outputStack, xp, craftTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, GrinderRecipeHandler recipe) {
            buffer.writeVarInt(recipe.INPUTS.size());
            for (Ingredient ingredient : recipe.INPUTS) {
                ingredient.toNetwork(buffer);
            }
            buffer.writeFloat(recipe.XP);
            buffer.writeInt(recipe.CRAFTTIME);
            buffer.writeItem(recipe.OUTPUT);
        }
    }
}
