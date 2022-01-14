package com.nssg.blockmixer.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.nssg.blockmixer.HotbarManager;
import com.nssg.blockmixer.util.ChatNotification;

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
        int slotId = context.getSource().getPlayer().getInventory().selectedSlot;
        
        if (!HotbarManager.hotbar[slotId].getState()) {
            HotbarManager.EditSlot(slotId, true, 1, true);
            ChatNotification.Send(new TranslatableText("chat.blockmixer.addslot", (slotId+1)));
        }
        else {
            ChatNotification.Send(new TranslatableText((slotId+1) + " " + "chat.blockmixer.alreadyaddedslot"));
        }
        return 1;
    }
}