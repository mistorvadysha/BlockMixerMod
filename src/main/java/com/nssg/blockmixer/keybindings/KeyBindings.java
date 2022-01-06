package com.nssg.blockmixer.keybindings;

import com.nssg.blockmixer.SlotSwitcher;
import com.nssg.blockmixer.config.ConfigManager;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;

public class KeyBindings {
    private static KeyBinding kbAddSlot;

    public static void register() {
        kbAddSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.blockmixer.addslot", 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_B, 
            "BlockMixer"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (kbAddSlot.wasPressed()) {
                int slot =  client.player.getInventory().selectedSlot;

                if (SlotSwitcher.isSlotEnabled(slot) == false) {
                    SlotSwitcher.AddSlot(slot);

                    if (ConfigManager.configJSON.getChatNotifications()) {
                        client.player.sendMessage(new TranslatableText("commands.blockmixer.addslot", (slot+1)), false);
                    }
                    
                }
                else {
                    SlotSwitcher.RemoveSlot(slot);

                    if (ConfigManager.configJSON.getChatNotifications()) {
                        client.player.sendMessage(new TranslatableText("commands.blockmixer.removeslot", (slot+1)), false);
                    }
                }
            }
        });
    }
}