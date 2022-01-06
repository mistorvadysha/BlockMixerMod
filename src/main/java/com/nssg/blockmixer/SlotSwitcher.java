package com.nssg.blockmixer;

import java.util.ArrayList;
import java.util.Random;

import com.nssg.blockmixer.config.ConfigManager;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class SlotSwitcher {

    static Random random = new Random();

    public static boolean[] hotbarSlotsState = {false, false, false, false, false, false, false, false, false};
    public static ArrayList<Integer> hotbarSlotsIDs = new ArrayList<>();

    private static boolean toggleMod = false;

    private static int lastSlot1 = -1;
    private static int lastSlot = -1;

    public static void AddSlot(int slot) {
        hotbarSlotsState[slot] = true;
        hotbarSlotsIDs.add(slot);

        toggleMod = true;
    }

    public static void RemoveSlot(int slot) {
        hotbarSlotsState[slot] = false;
        for (int i = 0; i < hotbarSlotsIDs.size(); i++) {
            if (hotbarSlotsIDs.get(i) == slot) { hotbarSlotsIDs.remove(Integer.valueOf(slot)); }
        }
        if (hotbarSlotsIDs.isEmpty()) {
            toggleMod = false;
        }
    }

    public static boolean isSlotEnabled(int slot) {
        return hotbarSlotsState[slot];
    }

    public static void ClearSlots() {
        toggleMod = false;
        hotbarSlotsState = new boolean[] {false, false, false, false, false, false, false, false, false};
        hotbarSlotsIDs = new ArrayList<>();
    }

    public static void SwtichSlot(LivingEntity placer) {        
        if (toggleMod == true && placer.getWorld().toString() == "ClientLevel") {
            PlayerEntity self = (PlayerEntity) (Object) placer;

            String settingMixMode = ConfigManager.configJSON.getMixMode();
            int index = random.nextInt(hotbarSlotsIDs.size());

            switch (settingMixMode) {
                case "Non-repeating":

                    if (hotbarSlotsIDs.size() > 1) {
                        while (index == lastSlot) { 
                            index = random.nextInt(hotbarSlotsIDs.size()); 
                        }
                    }
                    break;

                case "Non-repeating [2]":

                    if (hotbarSlotsIDs.size() > 1) {
                        while (lastSlot == index && lastSlot1 == index) {
                            index = random.nextInt(hotbarSlotsIDs.size()); 
                        }
                    }
                    break;

                default:
                    break;
            }

            self.getInventory().selectedSlot = hotbarSlotsIDs.get(index);

            lastSlot1 = lastSlot;
            lastSlot = index;
        }
    }
}