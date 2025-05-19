package com.nssg.blockmixer;
import java.io.IOException;
import com.nssg.blockmixer.config.ConfigManager;
import com.nssg.blockmixer.keybindings.KeyBindings;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback;
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer;
import net.minecraft.util.Identifier;

public class BlockMixerClient implements ClientModInitializer {

    public static final String MOD_ID = "nssgs-blockmixer";
    private static final Identifier MY_HUD_LAYER = Identifier.of("mymod", "my_hud_layer");


    @Override
    public void onInitializeClient() {
        // Mod settings loading
        try { ConfigManager.Load(); } catch (IOException e) { e.printStackTrace(); }

        // Key bindings loading
        KeyBindings.register();

        //
        HotbarManager.Load();

        // HudRenderCallback.EVENT.register((drawContext, tickDelta) -> {
        //     SlotStatusRender.RenderIndicators(drawContext);
        // });
        HudLayerRegistrationCallback.EVENT.register(layeredDrawer -> {
            layeredDrawer.attachLayerAfter(
                IdentifiedLayer.HOTBAR_AND_BARS,
                MY_HUD_LAYER,
                SlotStatusRenderV2::RenderIndicators
            );
        });
    }
}