package com.nssg.blockmixer.keybindings;

import com.nssg.blockmixer.SlotStatusHudRender;
import com.nssg.blockmixer.SlotSwitcher;
import com.nssg.blockmixer.util.ChatNotification;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.TranslatableText;

public class KeyBindings {
    private static KeyBinding kbToggleSlot;
    // private static KeyBinding kbToggleBM;

    public static void register() {
        kbToggleSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.blockmixer.toggleslot", 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_B, 
            "BlockMixer"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (kbToggleSlot.wasPressed()) {
                int slot =  client.player.getInventory().selectedSlot;

                if (client.player.isSneaking()) {
                    SlotSwitcher.toggleMod = !SlotSwitcher.toggleMod;
                    SlotStatusHudRender.CheckToggle();
                    ChatNotification.Send(new TranslatableText("commands.blockmixer.togglebm"));
                }

                else if (SlotSwitcher.isSlotEnabled(slot) == false) {
                    SlotSwitcher.AddSlot(slot);
                    ChatNotification.Send(new TranslatableText("commands.blockmixer.addslot", (slot+1)));
                    
                }
                else {
                    SlotSwitcher.RemoveSlot(slot);
                    ChatNotification.Send(new TranslatableText("commands.blockmixer.removeslot", (slot+1)));
                }
            }
        });
    }
}