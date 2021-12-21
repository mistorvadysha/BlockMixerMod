package com.nssg.blockmixer;

import java.util.ArrayList;
import java.util.Random;

import com.nssg.blockmixer.command.BmAddCommand;
import com.nssg.blockmixer.command.BmClearCommand;

// import com.nssg.blockmixer.util.ModCommandRegister;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class BlockMixer implements ModInitializer {

    public static final String MOD_ID = "nssgs-blockmixer";

    public static boolean toggleMod = false;

    public static boolean[] hotbarSlots = {false, false, false, false, false, false, false, false, false};
    public static ArrayList<Integer> hotbarSlotsInt = new ArrayList<>();
    static Random random = new Random();

    @Override
    public void onInitialize() {
        // ModCommandRegister.RegisterCommands();
        BmAddCommand.register(null, false);
        BmClearCommand.register(null, false);
    }

    public static boolean SlotsSettings(int slot, boolean isEnabled) {
        if (hotbarSlots[slot] != isEnabled){
            hotbarSlots[slot] = isEnabled;
            hotbarSlotsInt.add(slot);
            
            toggleMod = true;
            return true;
        }
        return false;
    }

    public static void SwtichSlot(LivingEntity placer) {
        PlayerEntity self = (PlayerEntity) (Object) placer;
        if ((self.getWorld().toString() == "ClientLevel") && (toggleMod == true))
        {
            int index = random.nextInt(hotbarSlotsInt.size());
            self.getInventory().selectedSlot = hotbarSlotsInt.get(index);
            //
            System.out.println("Selected hotbar slot number " + (index+1)); 
        }
    }

}