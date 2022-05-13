package com.miki4920.furnacemod.recipe;

import com.miki4920.furnacemod.FurnaceMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FurnaceMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<LavaPoweredFurnaceRecipe>> LAVA_POWERED_FURNACE_SERIALIZER =
            SERIALIZERS.register("lava_powered_furnace_recipe", () -> LavaPoweredFurnaceRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus) {
        SERIALIZERS.register(eventBus);
    }
}
