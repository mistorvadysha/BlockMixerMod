package com.nssg.blockmixer.util;

import com.nssg.blockmixer.config.ConfigManager;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

public class ChatNotification {
    public static void Send(Text message) {
        if (ConfigManager.config.getChatNotifications()) {
            MinecraftClient.getInstance().player.sendMessage(message, false);
        }
    }
}