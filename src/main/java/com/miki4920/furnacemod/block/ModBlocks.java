package com.miki4920.furnacemod.block;

import com.miki4920.furnacemod.FurnaceMod;
import com.miki4920.furnacemod.block.custom.ObsidianIngotLamp;
import com.miki4920.furnacemod.item.ModCreativeModeTab;
import com.miki4920.furnacemod.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;
import java.util.function.ToIntBiFunction;
import java.util.function.ToIntFunction;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FurnaceMod.MOD_ID);

    public static final int OBSIDIAN_LIGHT_LEVEL = 5;

    public static final RegistryObject<Block> OBSIDIAN_ORE = registerBlock("obsidian_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(50).requiresCorrectToolForDrops().lightLevel((state) -> OBSIDIAN_LIGHT_LEVEL)), ModCreativeModeTab.FURNACE_MOD_TAB);

    // Obsidian Ingot Block
    public static final RegistryObject<Block> OBSIDIAN_INGOT_BLOCK = registerBlock("obsidian_ingot_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(50).requiresCorrectToolForDrops().lightLevel((state) -> OBSIDIAN_LIGHT_LEVEL)), ModCreativeModeTab.FURNACE_MOD_TAB);

    // Obsidian Ingot fences, stairs etc.
    public static final RegistryObject<Block> OBSIDIAN_INGOT_BLOCK_STAIRS = registerBlock("obsidian_ingot_block_stairs",
            () -> new StairBlock(() -> ModBlocks.OBSIDIAN_INGOT_BLOCK.get().defaultBlockState(), BlockBehaviour.Properties.of(Material.STONE).strength(50).requiresCorrectToolForDrops().lightLevel((state) -> OBSIDIAN_LIGHT_LEVEL)), ModCreativeModeTab.FURNACE_MOD_TAB);
    public static final RegistryObject<Block> OBSIDIAN_INGOT_BLOCK_SLAB = registerBlock("obsidian_ingot_block_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of(Material.STONE).strength(50).requiresCorrectToolForDrops().lightLevel((state) -> OBSIDIAN_LIGHT_LEVEL)), ModCreativeModeTab.FURNACE_MOD_TAB);
    public static final RegistryObject<Block> OBSIDIAN_INGOT_BLOCK_FENCE = registerBlock("obsidian_ingot_block_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(50).requiresCorrectToolForDrops().lightLevel((state) -> OBSIDIAN_LIGHT_LEVEL)), ModCreativeModeTab.FURNACE_MOD_TAB);
    public static final RegistryObject<Block> OBSIDIAN_INGOT_BLOCK_FENCE_GATE = registerBlock("obsidian_ingot_block_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.of(Material.STONE).strength(50).requiresCorrectToolForDrops().lightLevel((state) -> OBSIDIAN_LIGHT_LEVEL)), ModCreativeModeTab.FURNACE_MOD_TAB);
    public static final RegistryObject<Block> OBSIDIAN_INGOT_BLOCK_WALL = registerBlock("obsidian_ingot_block_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of(Material.STONE).strength(50).requiresCorrectToolForDrops().lightLevel((state) -> OBSIDIAN_LIGHT_LEVEL)), ModCreativeModeTab.FURNACE_MOD_TAB);
    // Obsidian Ingot Glass
    public static final RegistryObject<Block> OBSIDIAN_INGOT_GLASS = registerBlock("obsidian_ingot_glass",
            () -> new GlassBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(50).requiresCorrectToolForDrops().noOcclusion().sound(SoundType.GLASS).lightLevel((state) -> OBSIDIAN_LIGHT_LEVEL)), ModCreativeModeTab.FURNACE_MOD_TAB);

    public static final RegistryObject<Block> OBSIDIAN_INGOT_LAMP = registerBlock("obsidian_ingot_lamp",
            () -> new ObsidianIngotLamp(BlockBehaviour.Properties.of(Material.GLASS).strength(50).requiresCorrectToolForDrops().noOcclusion().lightLevel((state) -> state.getValue(ObsidianIngotLamp.FULL_POWER) ? 15 : OBSIDIAN_LIGHT_LEVEL).sound(SoundType.GLASS)), ModCreativeModeTab.FURNACE_MOD_TAB);


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
