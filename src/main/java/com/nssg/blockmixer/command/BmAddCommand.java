package com.nssg.blockmixer.command;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.nssg.blockmixer.BlockMixer;

import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;
import net.fabricmc.fabric.api.client.command.v1.FabricClientCommandSource;
import net.minecraft.text.LiteralText;

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
        System.out.println(slot);
        if (BlockMixer.SlotsSettings(slot, true)) { context.getSource().sendFeedback(new LiteralText("Succsessfully added slot " + (slot+1))); }
        else { context.getSource().sendFeedback(new LiteralText((slot+1) + " is already added!")); }
        return 1;
    }

}