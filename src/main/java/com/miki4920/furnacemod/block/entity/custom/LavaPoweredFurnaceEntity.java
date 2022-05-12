package com.miki4920.furnacemod.block.entity.custom;

import com.miki4920.furnacemod.block.entity.ModBlockEntities;
import com.miki4920.furnacemod.item.ModItems;
import com.miki4920.furnacemod.screen.LavaPoweredFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStack;
import net.minecraftforge.fluids.capability.templates.FluidHandlerItemStackSimple;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.system.CallbackI;

import javax.annotation.Nonnull;

public class LavaPoweredFurnaceEntity extends BlockEntity implements MenuProvider {
    public static final int ITEM_SLOTS = 5;
    private final ItemStackHandler itemHandler = new ItemStackHandler(ITEM_SLOTS) {
        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
        }
    };
    private final FluidTank fluidTank = new FluidTank(10000) {
        @Override
        protected void onContentsChanged() {
            super.onContentsChanged();
        }
    };
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    public LavaPoweredFurnaceEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.LAVA_POWERED_FURNACE_ENTITY.get(), pWorldPosition, pBlockState);
    }

    @Override
    public Component getDisplayName() {
        return new TextComponent("Lava-Powered Furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pInventory, Player pPlayer) {
        return new LavaPoweredFurnaceMenu(pContainerId, pInventory, this);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return lazyItemHandler.cast();
        }
        else if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return lazyFluidHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyFluidHandler = LazyOptional.of(() -> fluidTank);
    }

    @Override
    public void invalidateCaps()  {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyFluidHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        CompoundTag fluidTag = new CompoundTag();
        tag.put("fluid", fluidTank.writeToNBT(fluidTag));
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        fluidTank.readFromNBT(nbt.getCompound("fluid"));
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, LavaPoweredFurnaceEntity pBlockEntity) {
        if(hasRecipe(pBlockEntity) && hasNotReachedStackLimit(pBlockEntity)) {
            craftItem(pBlockEntity);
        }
        if(pBlockEntity.itemHandler.getStackInSlot(0).getItem() == Items.LAVA_BUCKET && pBlockEntity.fluidTank.getFluidAmount() < pBlockEntity.fluidTank.getCapacity()) {
            pBlockEntity.fluidTank.fill(new FluidStack(Fluids.LAVA, 1000), IFluidHandler.FluidAction.EXECUTE);
            pBlockEntity.itemHandler.extractItem(0, 1, false);
            pBlockEntity.itemHandler.setStackInSlot(1, new ItemStack(Items.BUCKET,
                    pBlockEntity.itemHandler.getStackInSlot(1).getCount() + 1));
        }
    }

    private static void craftItem(LavaPoweredFurnaceEntity entity) {
        entity.fluidTank.drain(100, IFluidHandler.FluidAction.EXECUTE);
        entity.itemHandler.extractItem(2, 1, false);
        entity.itemHandler.extractItem(3, 1, false);
        entity.itemHandler.setStackInSlot(4, new ItemStack(ModItems.OBSIDIAN_INGOT.get(),
                entity.itemHandler.getStackInSlot(4).getCount() + 1));
    }

    private static boolean hasRecipe(LavaPoweredFurnaceEntity entity) {
        boolean hasEnoughLava = entity.fluidTank.getFluid().getAmount() >= 100;
        boolean hasItemInFirstSlot = entity.itemHandler.getStackInSlot(2).getItem() == Items.OBSIDIAN;
        boolean hasItemInSecondSlot = entity.itemHandler.getStackInSlot(3).getItem() == Items.DIAMOND;
        return hasEnoughLava && hasItemInFirstSlot && hasItemInSecondSlot;
    }

    private static boolean hasNotReachedStackLimit(LavaPoweredFurnaceEntity entity) {
        return entity.itemHandler.getStackInSlot(4).getCount() < entity.itemHandler.getStackInSlot(4).getMaxStackSize();
    }

}
