package com.miki4920.furnacemod.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.GlassBlock;
import net.minecraft.world.level.block.RedstoneTorchBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;

public class ObsidianIngotLamp extends GlassBlock {
    public static final BooleanProperty FULL_POWER = BooleanProperty.create("full_power");

    public ObsidianIngotLamp(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(FULL_POWER, Boolean.FALSE));
    }

    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide && pHand == InteractionHand.MAIN_HAND) {
            pLevel.setBlock(pPos, pState.setValue(FULL_POWER, !pState.getValue(FULL_POWER)), 3);
        }
        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(FULL_POWER);
    }
}
