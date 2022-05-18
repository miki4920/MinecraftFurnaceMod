package com.miki4920.furnacemod.recipe;

import com.google.gson.JsonElement;
import mekanism.api.annotations.NonNull;
import mekanism.api.recipes.ingredients.InputIngredient;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FluidStackIngredient implements InputIngredient<@NonNull FluidStack> {
    @Override
    public boolean testType(@NotNull @NonNull FluidStack fluidStack) {
        return false;
    }

    @Override
    public @NonNull FluidStack getMatchingInstance(@NonNull FluidStack fluidStack) {
        return null;
    }

    @Override
    public long getNeededAmount(@NonNull FluidStack fluidStack) {
        return 0;
    }

    @Override
    public List<@NonNull FluidStack> getRepresentations() {
        return null;
    }

    @Override
    public void write(FriendlyByteBuf buffer) {

    }

    @Override
    public JsonElement serialize() {
        return null;
    }

    @Override
    public boolean test(@NonNull FluidStack fluidStack) {
        return false;
    }
}
