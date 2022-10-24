package com.nssg.blockmixer;
import java.io.IOException;
import com.nssg.blockmixer.config.ConfigManager;
import com.nssg.blockmixer.keybindings.KeyBindings;

import net.fabricmc.api.ClientModInitializer;

public class BlockMixerClient implements ClientModInitializer {

    public static final String MOD_ID = "nssgs-blockmixer";

    @Override
    public void onInitializeClient() {
        // Mod settings loading
        try { ConfigManager.Load(); } catch (IOException e) { e.printStackTrace(); }

        // Key bindings loading
        KeyBindings.register();

        //
        HotbarManager.Load();
    }
}