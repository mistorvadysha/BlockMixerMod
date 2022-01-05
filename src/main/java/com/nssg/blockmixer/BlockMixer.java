package com.nssg.blockmixer;

import java.util.ArrayList;
import java.util.Random;

import com.nssg.blockmixer.command.BmAddCommand;
import com.nssg.blockmixer.command.BmClearCommand;
import com.nssg.blockmixer.config.JsonManager;

import net.fabricmc.api.ModInitializer;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class BlockMixer implements ModInitializer {

    public static final String MOD_ID = "nssgs-blockmixer";

    public static boolean toggleMod = false;

    public static boolean[] hotbarSlots = {false, false, false, false, false, false, false, false, false};
    public static int lastSlot = -1;
    public static int lastSlot1 = -1;
    public static ArrayList<Integer> hotbarSlotsInt = new ArrayList<>();
    static Random random = new Random();

    @Override
    public void onInitialize() {
        BmAddCommand.register(null, false);
        BmClearCommand.register(null, false);
    }

    public static boolean SlotsSettings(int slot, boolean isEnabled) {
        if (hotbarSlots[slot] != isEnabled) {
            hotbarSlots[slot] = isEnabled;
            hotbarSlotsInt.add(slot);
            
            toggleMod = true;
            return true;
        }
        return false;
    }

    public static void SlotRemove(int slot) {
        hotbarSlots[slot] = false;
        // hotbarSlotsInt.remove(slot);
        for (int i = 0; i < hotbarSlotsInt.size(); i++) {
            //System.out.printf("\n[i: %d] [%d] [slot: %d]\n", i, hotbarSlotsInt.get(i), slot);
            if (hotbarSlotsInt.get(i) == slot) { hotbarSlotsInt.remove(Integer.valueOf(slot)); }
        }
        if (hotbarSlotsInt.isEmpty()) { toggleMod = false; }
    }
    

    public static void SwtichSlot(LivingEntity placer) {
        PlayerEntity self = (PlayerEntity) (Object) placer;
        if ((self.getWorld().toString() == "ClientLevel") && (toggleMod == true))
        {
            int index = random.nextInt(hotbarSlotsInt.size());
            if (JsonManager.configJSON.getSettingMixMode() == "Non-repeating" && hotbarSlotsInt.size() > 1) { while (index == lastSlot) { index = random.nextInt(hotbarSlotsInt.size()); } }
            else if (JsonManager.configJSON.getSettingMixMode() == "Non-repeating [2]" && hotbarSlotsInt.size() > 1) { while (index == lastSlot && index == lastSlot1) { index = random.nextInt(hotbarSlotsInt.size()); } }
            self.getInventory().selectedSlot = hotbarSlotsInt.get(index);
            lastSlot1 = lastSlot;
            lastSlot = index;
            //
            //System.out.println("Selected hotbar slot number " + (index+1)); 
        }
    }
}