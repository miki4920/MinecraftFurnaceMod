package com.miki4920.furnacemod.screen;

import com.miki4920.furnacemod.FurnaceMod;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

public class LavaPoweredFurnaceScreen extends AbstractContainerScreen<LavaPoweredFurnaceMenu> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(FurnaceMod.MOD_ID, "textures/gui/lava_powered_furnace_gui.png");

    public LavaPoweredFurnaceScreen(LavaPoweredFurnaceMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(pPoseStack, x, y, 0, 0, imageWidth, imageHeight);

        if(menu.isCrafting()) {
            blit(pPoseStack, x + 137, y + 41, 176, 0, 10, menu.getScaledProgress());
        }


        blit(pPoseStack, x + 8, y + 69 - menu.getContainerCapacity(), 0, 219 - menu.getContainerCapacity(), 34, menu.getContainerCapacity());

    }

    @Override
    public void render(PoseStack pPoseStack, int mouseX, int mouseY, float delta) {
        renderBackground(pPoseStack);
        super.render(pPoseStack, mouseX, mouseY, delta);
        renderTooltip(pPoseStack, mouseX, mouseY);
    }
}
