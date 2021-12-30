package com.nssg.blockmixer;

import net.minecraft.client.option.KeyBinding;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;

public class BlockMixerClient implements ClientModInitializer {

    private static KeyBinding keyBinding;

    @Override
    public void onInitializeClient() {
        keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.blockmixer.addslot", 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_B, 
            "BlockMixer"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (keyBinding.wasPressed()) {
                int slot =  client.player.getInventory().selectedSlot;
                
                if (BlockMixer.SlotsSettings(slot, true)) {
                    client.player.sendMessage(new TranslatableText("commands.blockmixer.addslot", (slot+1)), false);
                }
                else { 
                    BlockMixer.SlotRemove(slot);
                    client.player.sendMessage(new TranslatableText("commands.blockmixer.removeslot", (slot+1)), false);
                }
            }
        });
    }
}
