package com.miki4920.furnacemod;

import com.miki4920.furnacemod.block.ModBlocks;
import com.miki4920.furnacemod.block.entity.ModBlockEntities;
import com.miki4920.furnacemod.item.ModItems;
import com.miki4920.furnacemod.recipe.ModRecipes;
import com.miki4920.furnacemod.screen.LavaPoweredFurnaceScreen;
import com.miki4920.furnacemod.screen.ModMenuTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;



@Mod("furnacemod")
public class FurnaceMod
{
    public static final String MOD_ID = "furnacemod";

    public FurnaceMod()
    {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModBlockEntities.register(eventBus);
        ModMenuTypes.register(eventBus);
        ModRecipes.register(eventBus);
        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.OBSIDIAN_INGOT_GLASS.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.OBSIDIAN_INGOT_LAMP.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.LAVA_POWERED_FURNACE.get(), RenderType.translucent());
        MenuScreens.register(ModMenuTypes.LAVA_POWERED_FURNACE_MENU.get(), LavaPoweredFurnaceScreen::new);
    }
}
