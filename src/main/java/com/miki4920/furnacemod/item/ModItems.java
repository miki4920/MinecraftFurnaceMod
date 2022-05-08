package com.miki4920.furnacemod.item;

import com.miki4920.furnacemod.FurnaceMod;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FurnaceMod.MOD_ID);

    public static final RegistryObject<Item> OBSIDIAN_INGOT = ITEMS.register("obsidian_ingot",
            () -> new Item(new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_INGOT_SWORD = ITEMS.register("obsidian_ingot_sword",
            () -> new SwordItem(ModTiers.OBSIDIAN_INGOT, 6, 1.6f, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_INGOT_PICKAXE = ITEMS.register("obsidian_ingot_pickaxe",
            () -> new PickaxeItem(ModTiers.OBSIDIAN_INGOT, 1, 1f, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_INGOT_AXE = ITEMS.register("obsidian_ingot_axe",
            () -> new AxeItem(ModTiers.OBSIDIAN_INGOT, 1, 1f, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_INGOT_HOE = ITEMS.register("obsidian_ingot_hoe",
            () -> new HoeItem(ModTiers.OBSIDIAN_INGOT, 1, 1f, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_INGOT_SHOVEL = ITEMS.register("obsidian_ingot_shovel",
            () -> new ShovelItem(ModTiers.OBSIDIAN_INGOT, 1, 1f, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
