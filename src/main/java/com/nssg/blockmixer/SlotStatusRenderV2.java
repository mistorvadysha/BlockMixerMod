package com.nssg.blockmixer;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.util.Identifier;
import net.minecraft.client.MinecraftClient;

public class SlotStatusRenderV2 {
    
    private static final Identifier TEXTURE_INDICATOR = Identifier.of(BlockMixerClient.MOD_ID, "textures/indicators.png");
    private static final Identifier TEXTURE_COUNTINDICATOR = Identifier.of(BlockMixerClient.MOD_ID, "textures/countindicators.png");

	private static int windowX;
	private static int windowY;

	private static final int res = 8;
	
	private static int countindX;
	private static final int countindY = 8;
	private static final int countindOffset[] = {6, 12, 18, 24, 30, 36, 42, 48, 54, 60};

	private static int offset = 0;
	private static final int slotPos[] = {-80, -60, -40, -20, 0, 20, 40, 60, 80};

	public static void CheckToggle() {
		if (HotbarManager.toggleMod == false) { offset = res; }
		else { offset = 0; }
	}

	public static boolean isZeroOffset() {
		if (offset == 0) { return true; }
		else { return false; }
	}

    public static void RenderIndicators(DrawContext drawContext, RenderTickCounter tickCounter) {
        windowX = MinecraftClient.getInstance().getWindow().getScaledWidth();
		windowY = MinecraftClient.getInstance().getWindow().getScaledHeight();

		for (int slotId=0; slotId<9; slotId++){

			if (HotbarManager.hotbar[slotId].getState()) {
				
                drawContext.drawTexture(
					RenderLayer::getGuiTextured, TEXTURE_INDICATOR,
                    windowX/2-res/2-3+slotPos[slotId], windowY-18,
					offset, 0,
					res, res,
					16, 8
				);

				// drawContext.drawTexture(
				// 	RenderLayer::getGuiTextured, TEXTURE_INDICATOR,
				// 	windowX/2-res/2-3+slotPos[slotId], windowY-18,
				// 	res, res,
				// 	offset, 0,
				// 	res, res,
				// 	16, 8
				// );

				if (HotbarManager.hasCountIncreased() && !HotbarManager.isOnlyOneInPool()) {
					if (HotbarManager.hotbar[slotId].getCount() != 10) { countindX = 6; }
					else { countindX = 12; }

					drawContext.drawTexture(
						RenderLayer::getGuiTextured, TEXTURE_COUNTINDICATOR,
						windowX/2-8+slotPos[slotId], windowY-19,
						// countindX, countindY,
						countindOffset[HotbarManager.hotbar[slotId].getCount()-1], 0,
						countindX, countindY,
						72, 8
					);
				}
            }
        }
    }
}