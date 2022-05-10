package com.miki4920.furnacemod.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModPlacedFeatures {
    public static final Holder<PlacedFeature> OBSIDIAN_ORE_PLACED = PlacementUtils.register("obsidian_ore_placed",
            ModConfiguredFeatures.OBSIDIAN_ORE, ModOrePlacement.commonOrePlacement(20, // VeinsPerChunk
                    HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(3))));
}
