package com.miki4920.furnacemod.datagen;

import com.miki4920.furnacemod.block.ModBlocks;
import com.miki4920.furnacemod.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(DataGenerator pGenerator) {
        super(pGenerator);
    }
    protected static RecipeBuilder modFenceBuilder(ItemLike pFence, Ingredient pMaterial, Ingredient pStick) {
        return ShapedRecipeBuilder.shaped(pFence, 6).define('W', pMaterial).define('#', pStick).pattern("W#W").pattern("W#W");
    }
    protected static RecipeBuilder modFenceGateBuilder(ItemLike pFenceGate, Ingredient pMaterial, Ingredient pStick) {
        return ShapedRecipeBuilder.shaped(pFenceGate).define('#', pStick).define('W', pMaterial).pattern("#W#").pattern("#W#");
    }
    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> pFinishedRecipeConsumer) {
        // Blocks
        ShapedRecipeBuilder.shaped(ModBlocks.OBSIDIAN_INGOT_BLOCK.get())
                .define('O', ModItems.OBSIDIAN_INGOT.get())
                .pattern("OOO")
                .pattern("OOO")
                .pattern("OOO")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        stairBuilder(ModBlocks.OBSIDIAN_INGOT_BLOCK_STAIRS.get(), Ingredient.of(ModBlocks.OBSIDIAN_INGOT_BLOCK.get()))
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        modFenceBuilder(ModBlocks.OBSIDIAN_INGOT_BLOCK_FENCE.get(), Ingredient.of(ModBlocks.OBSIDIAN_INGOT_BLOCK.get()), Ingredient.of(ModItems.OBSIDIAN_INGOT.get()))
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        modFenceGateBuilder(ModBlocks.OBSIDIAN_INGOT_BLOCK_FENCE_GATE.get(), Ingredient.of(ModBlocks.OBSIDIAN_INGOT_BLOCK.get()), Ingredient.of(ModItems.OBSIDIAN_INGOT.get()))
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        slabBuilder(ModBlocks.OBSIDIAN_INGOT_BLOCK_SLAB.get(), Ingredient.of(ModBlocks.OBSIDIAN_INGOT_BLOCK.get()))
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        wallBuilder(ModBlocks.OBSIDIAN_INGOT_BLOCK_WALL.get(), Ingredient.of(ModBlocks.OBSIDIAN_INGOT_BLOCK.get()))
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(ModBlocks.OBSIDIAN_INGOT_GLASS.get(), 8)
                .define('O', ModBlocks.OBSIDIAN_INGOT_BLOCK.get())
                .define('G', Tags.Items.GLASS)
                .pattern("OOO")
                .pattern("OGO")
                .pattern("OOO")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(ModBlocks.OBSIDIAN_INGOT_LAMP.get(), 8)
                .define('O', ModBlocks.OBSIDIAN_INGOT_GLASS.get())
                .define('G', Items.GLOWSTONE)
                .pattern("OOO")
                .pattern("OGO")
                .pattern("OOO")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        // Machines
        ShapedRecipeBuilder.shaped(ModBlocks.LAVA_POWERED_FURNACE.get())
                .define('O', Tags.Items.OBSIDIAN)
                .define('G', Items.FURNACE)
                .pattern("OOO")
                .pattern("OGO")
                .pattern("OOO")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        // Items
        ShapelessRecipeBuilder.shapeless(ModItems.OBSIDIAN_INGOT.get(), 9).requires(ModBlocks.OBSIDIAN_INGOT_BLOCK.get())
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        // Tools
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_INGOT_AXE.get())
                .define('O', ModItems.OBSIDIAN_INGOT.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("OO ")
                .pattern("OS ")
                .pattern(" S ")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_INGOT_HOE.get())
                .define('O', ModItems.OBSIDIAN_INGOT.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("OO ")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_INGOT_PICKAXE.get())
                .define('O', ModItems.OBSIDIAN_INGOT.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern("OOO")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_INGOT_SHOVEL.get())
                .define('O', ModItems.OBSIDIAN_INGOT.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern(" O ")
                .pattern(" S ")
                .pattern(" S ")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_INGOT_SWORD.get())
                .define('O', ModItems.OBSIDIAN_INGOT.get())
                .define('S', Tags.Items.RODS_WOODEN)
                .pattern(" O ")
                .pattern(" O ")
                .pattern(" S ")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        // Armour
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_INGOT_BOOTS.get())
                .define('O', ModItems.OBSIDIAN_INGOT.get())
                .pattern("   ")
                .pattern("O O")
                .pattern("O O")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_INGOT_LEGGINGS.get())
                .define('O', ModItems.OBSIDIAN_INGOT.get())
                .pattern("OOO")
                .pattern("O O")
                .pattern("O O")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_INGOT_CHESTPLATE.get())
                .define('O', ModItems.OBSIDIAN_INGOT.get())
                .pattern("O O")
                .pattern("OOO")
                .pattern("OOO")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
        ShapedRecipeBuilder.shaped(ModItems.OBSIDIAN_INGOT_HELMET.get())
                .define('O', ModItems.OBSIDIAN_INGOT.get())
                .pattern("   ")
                .pattern("OOO")
                .pattern("O O")
                .unlockedBy("has_obsidian", inventoryTrigger(ItemPredicate.Builder.item()
                        .of(Items.OBSIDIAN).build()))
                .save(pFinishedRecipeConsumer);
    }
}
