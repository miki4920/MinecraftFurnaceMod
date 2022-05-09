package com.miki4920.furnacemod;

import com.miki4920.furnacemod.block.ModBlocks;
import com.miki4920.furnacemod.item.ModItems;
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

        eventBus.addListener(this::clientSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.OBSIDIAN_INGOT_GLASS.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.OBSIDIAN_INGOT_LAMP.get(), RenderType.translucent());
    }
}
