package com.nssg.blockmixer.mixin;

import java.util.ArrayList;

import com.nssg.blockmixer.BlockMixer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.gui.screen.TitleScreen;

@Mixin(TitleScreen.class)
public class TitleScreenMixin{
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        BlockMixer.toggleMod = false;
        BlockMixer.hotbarSlots = new boolean[] {false, false, false, false, false, false, false, false, false};
        BlockMixer.hotbarSlotsInt = new ArrayList<>();
    }
}