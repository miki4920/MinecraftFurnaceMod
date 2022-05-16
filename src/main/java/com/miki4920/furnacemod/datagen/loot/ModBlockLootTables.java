package com.miki4920.furnacemod.datagen.loot;

import com.miki4920.furnacemod.block.ModBlocks;
import com.miki4920.furnacemod.item.ModItems;
import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.IntRange;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.LimitCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.NotNull;


public class ModBlockLootTables extends BlockLoot {
    @Override
    protected void addTables() {
        this.dropSelf(ModBlocks.OBSIDIAN_INGOT_BLOCK.get());

        this.dropSelf(ModBlocks.OBSIDIAN_INGOT_BLOCK_FENCE.get());
        this.dropSelf(ModBlocks.OBSIDIAN_INGOT_BLOCK_FENCE_GATE.get());
        this.dropSelf(ModBlocks.OBSIDIAN_INGOT_BLOCK_WALL.get());
        this.dropSelf(ModBlocks.OBSIDIAN_INGOT_BLOCK_SLAB.get());
        this.dropSelf(ModBlocks.OBSIDIAN_INGOT_BLOCK_STAIRS.get());


        this.dropSelf(ModBlocks.OBSIDIAN_INGOT_GLASS.get());
        this.dropSelf(ModBlocks.OBSIDIAN_INGOT_LAMP.get());
        this.dropSelf(ModBlocks.LAVA_POWERED_FURNACE.get());


        this.add(ModBlocks.OBSIDIAN_ORE.get(), (block) -> {
            return createSilkTouchDispatchTable(block, applyExplosionDecay(block, LootItem.lootTableItem(Items.OBSIDIAN).apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 9.0F))).apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE)).apply(LimitCount.limitCount(IntRange.range(4, 9)))));
        });
    }

    @Override
    protected @NotNull Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}

