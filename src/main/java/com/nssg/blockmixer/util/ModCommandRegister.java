// package com.nssg.blockmixer.util;

// import javax.naming.Context;

// import com.nssg.blockmixer.command.BmAddCommand;
// import com.nssg.blockmixer.command.BmClearCommand;

// import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
// import net.fabricmc.api.ClientModInitializer;
// import net.fabricmc.fabric.api.client.command.v1.ClientCommandManager;

// public class ModCommandRegister implements ClientModInitializer {
//     // @Override
//     // public static void RegisterCommands(){
//     //     ClientCommandManager.DISPATCHER.register(BmAddCommand::run);
//     //     CommandRegistrationCallback.EVENT.register(BmClearCommand::register);
        
//     // }

//     @Override
//     public void onInitializeClient() {
//         CommandRegistrationCallback.EVENT.register(BmAddCommand::run);
        
//     }
// }