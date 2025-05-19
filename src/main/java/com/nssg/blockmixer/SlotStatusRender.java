// package com.nssg.blockmixer;

// import java.util.function.Function;

// import com.mojang.blaze3d.systems.RenderSystem;

// import net.fabricmc.api.ClientModInitializer;
// import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
// import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
// import net.minecraft.client.MinecraftClient;
// import net.minecraft.client.gui.DrawContext;
// import net.minecraft.client.render.RenderLayer;
// import net.minecraft.client.render.RenderTickCounter;
// import net.minecraft.util.Identifier;
// import net.minecraft.util.Util;
// import net.minecraft.util.math.ColorHelper;
// import net.minecraft.util.math.MathHelper;

// public class SlotStatusRender implements ClientModInitializer {
//  private static final Identifier TEXTURE_INDICATOR = Identifier.of(BlockMixerClient.MOD_ID, "textures/indicators.png");
// 	private static final Identifier TEXTURE_COUNTINDICATOR = Identifier.of(BlockMixerClient.MOD_ID, "textures/countindicators.png");
	
// 	private static int windowX;
// 	private static int windowY;

// 	private static final int res = 8;
	
// 	private static int countindX;
// 	private static final int countindY = 8;
// 	private static final int countindOffset[] = {6, 12, 18, 24, 30, 36, 42, 48, 54, 60};

// 	private static int offset = 0;
// 	private static final int slotPos[] = {-80, -60, -40, -20, 0, 20, 40, 60, 80};

// 	public static void CheckToggle() {
// 		if (HotbarManager.toggleMod == false) { offset = res; }
// 		else { offset = 0; }
// 	}

// 	public static boolean isZeroOffset() {
// 		if (offset == 0) { return true; }
// 		else { return false; }
// 	}
	
// 	public static void RenderIndicators(DrawContext drawContext, RenderTickCounter tickCounter) {
// 		windowX = MinecraftClient.getInstance().getWindow().getScaledWidth();
// 		windowY = MinecraftClient.getInstance().getWindow().getScaledHeight();

// 		int color = 0xFFFF0000; // Red
// 		int targetColor = 0xFF00FF00; // Green

// 		// You can use the Util.getMeasuringTimeMs() function to get the current time in milliseconds.
// 		// Divide by 1000 to get seconds.
// 		double currentTime = Util.getMeasuringTimeMs() / 1000.0;

// 		// "lerp" simply means "linear interpolation", which is a fancy way of saying "blend".
// 		float lerpedAmount = MathHelper.abs(MathHelper.sin((float) currentTime));
// 		int lerpedColor = ColorHelper.lerp(lerpedAmount, color, targetColor);

// 		// Draw a square with the lerped color.
// 		// x1, x2, y1, y2, z, color
// 		drawContext.fill(0, 0, 10, 10, 0, lerpedColor);

// 		for (int slotId=0; slotId<9; slotId++){

// 			if (HotbarManager.hotbar[slotId].getState()) {

// 				// RenderSystem.enableBlend();
// 				// RenderSystem.disableDepthTest();
				
// 				drawContext.drawTexture(
// 					RenderLayer::getGuiTextured, TEXTURE_INDICATOR,
// 					windowX/2-res/2-3+slotPos[slotId], windowY-18,
// 					res, res,
// 					offset, 0,
// 					res, res,
// 					16, 8
// 				);

// 				if (HotbarManager.hasCountIncreased() && !HotbarManager.isOnlyOneInPool()) {
// 					if (HotbarManager.hotbar[slotId].getCount() != 10) { countindX = 6; }
// 					else { countindX = 12; }

// 					drawContext.drawTexture(
// 						RenderLayer::getGuiTextured, TEXTURE_COUNTINDICATOR,
// 						windowX/2-8+slotPos[slotId], windowY-19,
// 						countindX, countindY,
// 						countindOffset[HotbarManager.hotbar[slotId].getCount()-1], 0,
// 						countindX, countindY,
// 						72, 8
// 					);
// 				}
// 				// RenderSystem.disableBlend();
// 				// RenderSystem.enableDepthTest();
// 			}			
// 		}
// 	}

// 	@Override
// 	public void onInitializeClient() {
// 		// Attach our rendering code to before the chat hud layer. Our layer will render right before the chat. The API will take care of z spacing and automatically add 200 after every layer.
// 		HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> layeredDrawer.attachLayerBefore(IdentifiedLayer.HOTBAR_AND_BARS, TEXTURE_INDICATOR, SlotStatusRender::RenderIndicators));
// 	}
// }