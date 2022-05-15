package com.miki4920.furnacemod.event;

import com.miki4920.furnacemod.FurnaceMod;
import com.miki4920.furnacemod.recipe.LiquidMachineRecipe;
import net.minecraft.core.Registry;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nonnull;


@Mod.EventBusSubscriber(modid = FurnaceMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerRecipeTypes(@Nonnull final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, LiquidMachineRecipe.Type.ID, LiquidMachineRecipe.Type.INSTANCE);
    }
}
