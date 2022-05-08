package com.miki4920.furnacemod.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTiers {
    public static final ForgeTier OBSIDIAN_INGOT = new ForgeTier(4, 5000, 16f,
            7f, 30, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItems.OBSIDIAN_INGOT.get()));
}
