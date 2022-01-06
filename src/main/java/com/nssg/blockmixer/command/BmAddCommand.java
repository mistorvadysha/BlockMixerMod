package com.nssg.blockmixer.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.nssg.blockmixer.SlotSwitcher;
import com.nssg.blockmixer.config.ConfigManager;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.text.TranslatableText;

public class BmAddCommand 
{
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, boolean dedicated)
    {
        ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("bm")
            .then(ClientCommandManager.literal("add").executes(BmAddCommand::run)));
    }

    public static int run(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException
    {
        int slot = context.getSource().getPlayer().getInventory().selectedSlot;
        
        if (SlotSwitcher.isSlotEnabled(slot) == false) {
            SlotSwitcher.AddSlot(slot);
            
            if (ConfigManager.configJSON.getChatNotifications()) {
                context.getSource().sendFeedback(new TranslatableText("commands.blockmixer.addslot", (slot+1)));
            }
        }
        else if (ConfigManager.configJSON.getChatNotifications()) {
            context.getSource().sendFeedback(new TranslatableText((slot+1) + " " + "commands.blockmixer.alreadyaddedslot"));
        }

        return 1;
    }
}