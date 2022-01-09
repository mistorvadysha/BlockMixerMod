package com.nssg.blockmixer.mixin;

import com.nssg.blockmixer.SlotStatusHudRender;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// @Mixin(HudRenderCallback.class)
@Mixin(InGameHud.class)
public class IndicatorRenderMixin {

    @Inject(at = @At ("TAIL"), method = "render")
    private void render(MatrixStack matrices, float tickDelta, CallbackInfo info) {
        SlotStatusHudRender.RenderIndicators(matrices, tickDelta);
    }
}
