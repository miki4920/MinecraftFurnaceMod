package com.miki4920.furnacemod.integration;

import com.miki4920.furnacemod.FurnaceMod;
import com.miki4920.furnacemod.block.ModBlocks;
import com.miki4920.furnacemod.recipe.LavaPoweredFurnaceRecipe;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class LavaPoweredFurnaceRecipeCategory implements IRecipeCategory<LavaPoweredFurnaceRecipe> {
    public final static ResourceLocation UID = new ResourceLocation(FurnaceMod.MOD_ID, "gem_cutting");
    public final static ResourceLocation TEXTURE =
            new ResourceLocation(FurnaceMod.MOD_ID, "textures/gui/lava_powered_furnace_gui.png");

    private final IDrawable background;
    private final IDrawable icon;

    public LavaPoweredFurnaceRecipeCategory(IGuiHelper helper) {
        this.background = helper.createDrawable(TEXTURE, 0, 0, 176, 85);
        ItemStack furnace = new ItemStack(ModBlocks.LAVA_POWERED_FURNACE.get());
        this.icon = helper.createDrawableIngredient(VanillaTypes.ITEM_STACK, furnace);
    }

    @Override
    @SuppressWarnings("removal")
    public @NotNull ResourceLocation getUid() {
        return UID;
    }

    @Override
    @SuppressWarnings("removal")
    public @NotNull Class<? extends LavaPoweredFurnaceRecipe> getRecipeClass() {
        return LavaPoweredFurnaceRecipe.class;
    }

    @Override
    public @NotNull Component getTitle() {
        return new TextComponent("Lava-Powered Furnace");
    }

    @Override
    public @NotNull IDrawable getBackground() {
        return this.background;
    }

    @Override
    public @NotNull IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(@Nonnull IRecipeLayoutBuilder builder, @Nonnull LavaPoweredFurnaceRecipe recipe, @Nonnull IFocusGroup focusGroup) {
        builder.addSlot(RecipeIngredientRole.CATALYST, 48, 18).addIngredients(Ingredient.of(Items.LAVA_BUCKET));
        builder.addSlot(RecipeIngredientRole.INPUT, 88, 18).addIngredients(recipe.getIngredients().get(0));
        builder.addSlot(RecipeIngredientRole.INPUT, 134, 18).addIngredients(recipe.getIngredients().get(1));
        builder.addSlot(RecipeIngredientRole.OUTPUT, 111, 51).addItemStack(recipe.getResultItem());
    }
}
