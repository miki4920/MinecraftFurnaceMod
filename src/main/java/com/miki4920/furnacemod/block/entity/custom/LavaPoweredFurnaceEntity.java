package com.miki4920.furnacemod.block.entity.custom;

import com.miki4920.furnacemod.block.entity.ModBlockEntities;
import com.miki4920.furnacemod.recipe.LavaPoweredFurnaceRecipe;
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
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;
import java.util.Optional;

public class LavaPoweredFurnaceEntity extends BlockEntity implements MenuProvider {
    public static final int ITEM_SLOTS = 5;
    public static final int LIQUID_CONTAINER_INPUT = 0;
    public static final int LIQUID_CONTAINER_OUTPUT = 1;
    public static final int SLOT_ONE = 2;
    public static final int SLOT_TWO = 3;
    public static final int OUTPUT_SLOT = 4;

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

    protected final ContainerData data;
    private int progress = 0;
    private int maxProgress = 100;

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IFluidHandler> lazyFluidHandler = LazyOptional.empty();

    public LavaPoweredFurnaceEntity(BlockPos pWorldPosition, BlockState pBlockState) {
        super(ModBlockEntities.LAVA_POWERED_FURNACE_ENTITY.get(), pWorldPosition, pBlockState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> LavaPoweredFurnaceEntity.this.progress;
                    case 1 -> LavaPoweredFurnaceEntity.this.maxProgress;
                    case 2 -> LavaPoweredFurnaceEntity.this.fluidTank.getFluidAmount();
                    case 3 -> LavaPoweredFurnaceEntity.this.fluidTank.getCapacity();
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> LavaPoweredFurnaceEntity.this.progress = value;
                    case 1 -> LavaPoweredFurnaceEntity.this.maxProgress = value;
                    case 2 -> LavaPoweredFurnaceEntity.this.fluidTank.setFluid(new FluidStack(Fluids.LAVA, value));
                    case 3 -> LavaPoweredFurnaceEntity.this.fluidTank.setCapacity(value);
                }
            }

            public int getCount() {
                return 4;
            }
        };
    }

    @Override
    public @NotNull Component getDisplayName() {
        return new TextComponent("Lava-Powered Furnace");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, @NotNull Inventory pInventory, @NotNull Player pPlayer) {
        return new LavaPoweredFurnaceMenu(pContainerId, pInventory, this, this.data);
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
        tag.putInt("lava_powered_station.progress", progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(@NotNull CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        fluidTank.readFromNBT(nbt.getCompound("fluid"));
        progress = nbt.getInt("lava_powered_station.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public static SimpleContainer getInventory(LavaPoweredFurnaceEntity entity) {
        SimpleContainer inventory = new SimpleContainer(entity.itemHandler.getSlots());
        for (int i = 0; i < entity.itemHandler.getSlots(); i++) {
            inventory.setItem(i, entity.itemHandler.getStackInSlot(i));
        }
        return inventory;
    }

    public static void tick(Level pLevel, BlockPos pPos, BlockState pState, LavaPoweredFurnaceEntity entity) {
        if(!pLevel.isClientSide()) {
            if(hasRecipe(entity) && hasEnoughLava(entity)) {
                entity.progress++;
                removeLava(entity);
                setChanged(pLevel, pPos, pState);
                if(entity.progress > entity.maxProgress) {
                    craftItem(entity);

                }
            }
            else {
                entity.resetProgress();
                setChanged(pLevel, pPos, pState);
            }
            if(canInsertLava(entity)) {
                insertLava(entity);
            }
        }
    }

    private static boolean canInsertLava(LavaPoweredFurnaceEntity entity) {
        SimpleContainer inventory = getInventory(entity);
        ItemStack inputItemStack = inventory.getItem(LavaPoweredFurnaceEntity.LIQUID_CONTAINER_INPUT);
        if(inputItemStack == ItemStack.EMPTY || !(inputItemStack.getItem() instanceof BucketItem)) {
            return false;
        }
        else if(!canInsertItemIntoSlot(inventory, new ItemStack(Items.BUCKET, 1), LavaPoweredFurnaceEntity.LIQUID_CONTAINER_OUTPUT) || !canInsertAmountIntoSlot(inventory, LIQUID_CONTAINER_OUTPUT)) {
            return false;
        }
        else if(!(entity.fluidTank.getFluidAmount() + FluidAttributes.BUCKET_VOLUME <= entity.fluidTank.getCapacity())) {
            return false;
        }
        return true;
    }

    private static void insertLava(LavaPoweredFurnaceEntity entity) {
        SimpleContainer inventory = getInventory(entity);
        ItemStack inputItemStack = inventory.getItem(LavaPoweredFurnaceEntity.LIQUID_CONTAINER_INPUT);
        BucketItem bucket = (BucketItem) inputItemStack.getItem();
        FluidStack extracted = new FluidStack(bucket.getFluid(), FluidAttributes.BUCKET_VOLUME);
        entity.fluidTank.fill(extracted, IFluidHandler.FluidAction.EXECUTE);
        inventory.removeItem(LavaPoweredFurnaceEntity.LIQUID_CONTAINER_INPUT, 1);
        if(canInsertItemIntoSlot(inventory, new ItemStack(Items.BUCKET, 1), LavaPoweredFurnaceEntity.LIQUID_CONTAINER_OUTPUT) || !canInsertAmountIntoSlot(inventory, LIQUID_CONTAINER_OUTPUT)) {
            entity.itemHandler.insertItem(LavaPoweredFurnaceEntity.LIQUID_CONTAINER_OUTPUT, new ItemStack(Items.BUCKET, 1), false);
        }
    }

    private static void removeLava(LavaPoweredFurnaceEntity entity) {
        entity.fluidTank.drain(1, IFluidHandler.FluidAction.EXECUTE);
    }

    private static boolean hasRecipe(LavaPoweredFurnaceEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = getInventory(entity);
        assert level != null;
        Optional<LavaPoweredFurnaceRecipe> match = level.getRecipeManager()
                .getRecipeFor(LavaPoweredFurnaceRecipe.Type.INSTANCE, inventory, level);

        return match.isPresent() && canInsertAmountIntoSlot(inventory, OUTPUT_SLOT) && canInsertItemIntoSlot(inventory, match.get().getResultItem(), OUTPUT_SLOT);
    }

    private static boolean hasEnoughLava(LavaPoweredFurnaceEntity entity) {
        return entity.fluidTank.getFluidAmount() >= 1;
    }

    private static void craftItem(LavaPoweredFurnaceEntity entity) {
        Level level = entity.level;
        SimpleContainer inventory = getInventory(entity);

        assert level != null;
        Optional<LavaPoweredFurnaceRecipe> match = level.getRecipeManager()
                .getRecipeFor(LavaPoweredFurnaceRecipe.Type.INSTANCE, inventory, level);

        if(match.isPresent()) {
            entity.itemHandler.extractItem(SLOT_ONE,1, false);
            entity.itemHandler.extractItem(SLOT_TWO,1, false);

            entity.itemHandler.setStackInSlot(OUTPUT_SLOT, new ItemStack(match.get().getResultItem().getItem(),
                    entity.itemHandler.getStackInSlot(OUTPUT_SLOT).getCount() + 1));

            entity.resetProgress();
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private static boolean canInsertItemIntoSlot(SimpleContainer inventory, ItemStack output, int slot) {
        return inventory.getItem(slot).getItem() == output.getItem() || inventory.getItem(slot).isEmpty();
    }

    private static boolean canInsertAmountIntoSlot(SimpleContainer inventory, int slot) {
        return inventory.getItem(slot).getMaxStackSize() > inventory.getItem(slot).getCount();
    }

}
