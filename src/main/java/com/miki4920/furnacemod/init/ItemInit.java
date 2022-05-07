package com.miki4920.furnacemod.init;

import com.google.common.base.Supplier;
import com.miki4920.furnacemod.FurnaceMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.extensions.IForgePackResources;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FurnaceMod.MOD_ID);
    private static <T extends Item> RegistryObject<T> register(final String name, final Supplier<T> item) {
        return ITEMS.register(name, item);
    }
}
