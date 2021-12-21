package com.nssg.blockmixer.command;

import java.util.ArrayList;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.nssg.blockmixer.BlockMixer;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.text.LiteralText;

public class BmClearCommand {
    public static void register(CommandDispatcher<FabricClientCommandSource> dispatcher, boolean dedicated)
    {
        ClientCommandManager.DISPATCHER.register(ClientCommandManager.literal("bm")
            .then(ClientCommandManager.literal("clear").executes(BmClearCommand::run)));
    }

    public static int run(CommandContext<FabricClientCommandSource> context) throws CommandSyntaxException
    {
        BlockMixer.toggleMod = false;
        BlockMixer.hotbarSlots = new boolean[] {false, false, false, false, false, false, false, false, false};
        BlockMixer.hotbarSlotsInt = new ArrayList<>();
        context.getSource().sendFeedback(new LiteralText("BlockMixer now is empty"));
        return 1;
    }
}