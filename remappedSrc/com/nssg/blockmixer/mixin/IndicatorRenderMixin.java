package com.nssg.blockmixer.mixin;

import com.nssg.blockmixer.SlotStatusRender;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class IndicatorRenderMixin {

    @Inject(at = @At ("TAIL"), method = "renderHotbar")
    private void renderHotbar(float tickDelta, MatrixStack matrices, CallbackInfo info) {
        SlotStatusRender.RenderIndicators(matrices);
    }
}
