package com.nssg.blockmixer;

import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.hud.InGameHud;

public class SlotStatusHudRender implements ClientModInitializer, HudRenderCallback {
    private static final Identifier TEXTURE_ID = new Identifier(BlockMixerClient.MOD_ID, "textures/indicators.png");
	
	private static int windowX;
	private static int windowY;
	private static final int res = 10;
	public static int offset = 0;
	private static final int slotPos[] = {-80, -60, -40, -20, 0, 20, 40, 60, 80};

	@Override
	public void onInitializeClient() {
		HudRenderCallback.EVENT.register(this);
	}

	@Override
	public void onHudRender(MatrixStack matrixStack, float tickDelta) {
        RenderSystem.setShaderTexture(0, TEXTURE_ID);
		windowX = MinecraftClient.getInstance().getWindow().getScaledWidth();
		windowY = MinecraftClient.getInstance().getWindow().getScaledHeight();
		
		for (int i=0; i<SlotSwitcher.hotbarSlotsState.length; i++){
			if (SlotSwitcher.hotbarSlotsState[i]) { InGameHud.drawTexture(matrixStack, windowX/2-res/2-3+slotPos[i], windowY-19, 0, offset, 0, res, res, 20, 10); }
		}
	}
}