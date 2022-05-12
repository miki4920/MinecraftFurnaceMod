package com.miki4920.furnacemod.block.entity;

import com.miki4920.furnacemod.FurnaceMod;
import com.miki4920.furnacemod.block.ModBlocks;
import com.miki4920.furnacemod.block.custom.LavaPoweredFurnace;
import com.miki4920.furnacemod.block.entity.custom.LavaPoweredFurnaceEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, FurnaceMod.MOD_ID);
    public static final RegistryObject<BlockEntityType<LavaPoweredFurnaceEntity>> LAVA_POWERED_FURNACE_ENTITY = BLOCK_ENTITIES.register("lava_powered_furnace_entity", () ->
    BlockEntityType.Builder.of(LavaPoweredFurnaceEntity::new, ModBlocks.LAVA_POWERED_FURNACE.get()).build(null));
    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
