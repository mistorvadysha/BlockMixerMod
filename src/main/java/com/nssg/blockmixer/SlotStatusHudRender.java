package com.nssg.blockmixer;

import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.client.gui.hud.InGameHud;

public class SlotStatusHudRender {
    private static final Identifier TEXTURE_ID = new Identifier(BlockMixerClient.MOD_ID, "textures/indicators.png");
	
	private static int windowX;
	private static int windowY;
	private static final int res = 8;
	private static int offset = 0;
	private static final int slotPos[] = {-80, -60, -40, -20, 0, 20, 40, 60, 80};

	public static void CheckToggle() {
		if (SlotSwitcher.toggleMod == false) { offset = res; }
		else { offset = 0; }
	}

	public static boolean isZeroOffset() {
		if (offset == 0) { return true; }
		else { return false; }
	}

	public static void RenderIndicators(MatrixStack matrixStack, float tickDelta) {
        RenderSystem.setShaderTexture(0, TEXTURE_ID);

		windowX = MinecraftClient.getInstance().getWindow().getScaledWidth();
		windowY = MinecraftClient.getInstance().getWindow().getScaledHeight();

		for (int i=0; i<SlotSwitcher.hotbarSlotsState.length; i++){
			if (SlotSwitcher.hotbarSlotsState[i]) { InGameHud.drawTexture(matrixStack, windowX/2-res/2-3+slotPos[i], windowY-18, 0, offset, 0, res, res, 16, 8); }
			
		}
	}
}