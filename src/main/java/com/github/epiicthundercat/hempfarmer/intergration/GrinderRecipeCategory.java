package com.github.epiicthundercat.hempfarmer.intergration;

import com.github.epiicthundercat.hempfarmer.HempFarmer;
import com.github.epiicthundercat.hempfarmer.blocks.grinder.GrinderRecipeHandler;
import com.github.epiicthundercat.hempfarmer.setup.Registration;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

import javax.annotation.Nonnull;

public class GrinderRecipeCategory implements IRecipeCategory<GrinderRecipeHandler> {

    public static final RecipeType<GrinderRecipeHandler> RECIPE_TYPE =
            RecipeType.create(HempFarmer.MODID, "grinder", GrinderRecipeHandler.class);

    public final static ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(HempFarmer.MODID, "textures/gui/grinder_gui.png");

    private static final int WIDTH = 179;
    private static final int HEIGHT = 67;

    private final IDrawable background;
    private final IDrawable slot;
    private final IDrawable icon;

    public GrinderRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, WIDTH, HEIGHT);
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, new ItemStack(Registration.GRINDER.get()));
        this.slot = helper.getSlotDrawable();
    }

    @Override
    public RecipeType<GrinderRecipeHandler> getRecipeType() {
        return RECIPE_TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.literal("Grinder");
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    @Override
    public IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void draw(GrinderRecipeHandler recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics, 0, 0);
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull GrinderRecipeHandler recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.INPUT, 46, 31)
                .setBackground(this.slot, -1, -1)
                .addIngredients(recipe.getIngredients().get(0));

        builder.addSlot(RecipeIngredientRole.OUTPUT, 118, 31)
                .setBackground(this.slot, -1, -1)
                .addItemStack(recipe.getOutput());
    }
}
