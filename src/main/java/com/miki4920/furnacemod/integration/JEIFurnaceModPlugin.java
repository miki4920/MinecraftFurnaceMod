package com.miki4920.furnacemod.integration;

import com.miki4920.furnacemod.FurnaceMod;
import com.miki4920.furnacemod.recipe.LiquidMachineRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;


@JeiPlugin
public class JEIFurnaceModPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(FurnaceMod.MOD_ID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                LavaPoweredFurnaceRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<LiquidMachineRecipe> recipes = rm.getAllRecipesFor(LiquidMachineRecipe.Type.INSTANCE);
        registration.addRecipes(new RecipeType<>(LavaPoweredFurnaceRecipeCategory.UID, LiquidMachineRecipe.class), recipes);
    }
}
