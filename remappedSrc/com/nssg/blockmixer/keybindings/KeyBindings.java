package com.nssg.blockmixer.keybindings;

import com.nssg.blockmixer.HotbarManager;
import com.nssg.blockmixer.SlotStatusRender;
import com.nssg.blockmixer.util.ChatNotification;

import org.lwjgl.glfw.GLFW;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;

public class KeyBindings {
    private static KeyBinding bindToggleSlot;
    private static KeyBinding bindIncreaseRatio;
    private static KeyBinding bindDecreaseRatio;

    public static void register() {
        bindToggleSlot = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.blockmixer.toggleslot", 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_B, 
            "BlockMixer"
        ));

        bindIncreaseRatio = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.blockmixer.increaseratio", 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_RIGHT_BRACKET, 
            "BlockMixer"
        ));

        bindDecreaseRatio = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.blockmixer.decreaseratio", 
            InputUtil.Type.KEYSYM, 
            GLFW.GLFW_KEY_LEFT_BRACKET, 
            "BlockMixer"
        ));
        
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (bindToggleSlot.wasPressed()) {
                int slotId =  client.player.getInventory().selectedSlot;

                if (client.player.isSneaking()) {
                    if (HotbarManager.hasActiveSlots()) {
                            HotbarManager.toggleMod = !HotbarManager.toggleMod;
                            SlotStatusRender.CheckToggle();
                            ChatNotification.Send(Text.translatable("chat.blockmixer.togglebm"));
                    }
                }

                else if (!HotbarManager.hotbar[slotId].getState()) {
                    HotbarManager.EditSlot(slotId, true, 1, true);
                    SlotStatusRender.CheckToggle();
                    ChatNotification.Send(Text.translatable("chat.blockmixer.addslot", slotId+1));
                }

                else {
                    HotbarManager.EditSlot(slotId, false, 1, true);
                    ChatNotification.Send(Text.translatable("chat.blockmixer.removeslot", slotId+1));
                }
            }

            while (bindIncreaseRatio.wasPressed()) {
                int slotId =  client.player.getInventory().selectedSlot;
                int count = HotbarManager.hotbar[slotId].getCount();
                if (HotbarManager.hotbar[slotId].getState() && count < 10 && !HotbarManager.isOnlyOneInPool()) {
                    HotbarManager.EditSlot(slotId, true, count+1, false);
                    ChatNotification.Send(Text.translatable("chat.blockmixer.increase", slotId+1));
                }
                
            }

            while (bindDecreaseRatio.wasPressed()) {
                int slotId =  client.player.getInventory().selectedSlot;
                int count = HotbarManager.hotbar[slotId].getCount();
                if (HotbarManager.hotbar[slotId].getState() && count > 1) {
                    HotbarManager.EditSlot(slotId, true, count-1, false);
                    ChatNotification.Send(Text.translatable("chat.blockmixer.decrease", slotId+1));
                }

                
            }
        });
    }
}
