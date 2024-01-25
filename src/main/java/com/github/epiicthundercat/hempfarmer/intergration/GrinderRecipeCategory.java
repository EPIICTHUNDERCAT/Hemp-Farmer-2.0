package com.github.epiicthundercat.hempfarmer.intergration;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderRecipeHandler;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import com.mojang.blaze3d.vertex.PoseStack;
import mcjty.theoneprobe.rendering.RenderHelper;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.IRecipeLayout;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.builder.IRecipeSlotBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IGuiItemStackGroup;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.ingredients.IIngredients;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GrinderRecipeCategory implements IRecipeCategory<GrinderRecipeHandler> {
    public final static ResourceLocation UID = new ResourceLocation(HempFarmer.MODID, "grinder");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(HempFarmer.MODID, "textures/gui/grinder_gui.png");

    private final IDrawable background;
    private final IDrawable slot;
    private final IDrawable icon;

    public GrinderRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 179, 67);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM, new ItemStack(Registration.GRINDER.get()));
        this.slot = helper.getSlotDrawable();
    }



    @Override
    public ResourceLocation getUid() {
        return UID;
    }

    @Override
    public Class<? extends GrinderRecipeHandler> getRecipeClass() {
        return GrinderRecipeHandler.class;
    }

    @Override
    public Component getTitle() {
        return new TextComponent("Grinder");
    }

    @Override
    public IDrawable getBackground() {
        return this.background;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }


    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull GrinderRecipeHandler recipe, @Nonnull IFocusGroup focusGroup) {

        builder.addSlot(RecipeIngredientRole.INPUT, 46, 31)
                .setBackground(this.slot, -1, -1)
                .addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 118, 31)
                .setBackground(this.slot, -1, -1)
                .addItemStack(recipe.getResultItem());
//
//        builder.addSlot(RecipeIngredientRole.INPUT, 46, 31).addIngredients(recipe.getIngredients().get(0));
//        builder.addSlot(RecipeIngredientRole.OUTPUT, 118, 31).addItemStack(recipe.getResultItem());


    }





}
