package com.nssg.blockmixer.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.nssg.blockmixer.HotbarManager;
import com.nssg.blockmixer.util.ChatNotification;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.text.TranslatableText;

public class BmClearCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, boolean dedicated)
    {
        ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("bm")
            .then(ClientCommandManager.literal("clear").executes(BmClearCommand::run)));
    }

    public static int run(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException
    {
        HotbarManager.ResetHotbar();
        ChatNotification.Send(new TranslatableText("chat.blockmixer.clearslot"));
        return 1;
    }
}
