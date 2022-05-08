package com.miki4920.furnacemod.item;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab FURNACE_MOD_TAB = new CreativeModeTab("furnacetab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.OBSIDIAN_INGOT.get());
        }
    };
}
