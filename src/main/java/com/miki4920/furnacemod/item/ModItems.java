package com.miki4920.furnacemod.item;

import com.miki4920.furnacemod.FurnaceMod;
import com.miki4920.furnacemod.item.custom.EffectSword;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FurnaceMod.MOD_ID);

    public static final RegistryObject<Item> OBSIDIAN_INGOT = ITEMS.register("obsidian_ingot",
            () -> new Item(new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    // Custom Sword with Swappable Effects
    public static final RegistryObject<Item> OBSIDIAN_INGOT_SWORD = ITEMS.register("obsidian_ingot_sword",
            () -> new EffectSword(ModTiers.OBSIDIAN_INGOT, 0, -2.4f,
                    new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB), new MobEffect[]{MobEffects.WITHER, MobEffects.MOVEMENT_SLOWDOWN, MobEffects.WEAKNESS}));

    // Tools
    public static final RegistryObject<Item> OBSIDIAN_INGOT_PICKAXE = ITEMS.register("obsidian_ingot_pickaxe",
            () -> new PickaxeItem(ModTiers.OBSIDIAN_INGOT, -4, -2.4f, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_INGOT_AXE = ITEMS.register("obsidian_ingot_axe",
            () -> new AxeItem(ModTiers.OBSIDIAN_INGOT, 4, -3.2f, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_INGOT_HOE = ITEMS.register("obsidian_ingot_hoe",
            () -> new HoeItem(ModTiers.OBSIDIAN_INGOT, -4, -2.4f, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    public static final RegistryObject<Item> OBSIDIAN_INGOT_SHOVEL = ITEMS.register("obsidian_ingot_shovel",
            () -> new ShovelItem(ModTiers.OBSIDIAN_INGOT, -4, -2.4f, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    // Armour
    public static final RegistryObject<Item> OBSIDIAN_INGOT_HELMET = ITEMS.register("obsidian_ingot_helmet",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN_INGOT, EquipmentSlot.HEAD, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));
    public static final RegistryObject<Item> OBSIDIAN_INGOT_CHESTPLATE = ITEMS.register("obsidian_ingot_chestplate",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN_INGOT, EquipmentSlot.CHEST, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));
    public static final RegistryObject<Item> OBSIDIAN_INGOT_LEGGINGS = ITEMS.register("obsidian_ingot_leggings",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN_INGOT, EquipmentSlot.LEGS, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));
    public static final RegistryObject<Item> OBSIDIAN_INGOT_BOOTS = ITEMS.register("obsidian_ingot_boots",
            () -> new ArmorItem(ModArmorMaterials.OBSIDIAN_INGOT, EquipmentSlot.FEET, new Item.Properties().fireResistant().tab(ModCreativeModeTab.FURNACE_MOD_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
