package com.nssg.blockmixer;

import java.util.ArrayList;
import java.util.Random;

import com.nssg.blockmixer.config.ConfigManager;
import com.nssg.blockmixer.util.Slot;

import net.minecraft.entity.player.PlayerEntity;

public class HotbarManager {

    static Random random = new Random();
    
    public static Slot[] hotbar = new Slot[9];
    private static ArrayList<Integer> slotsPool = new ArrayList<>();
    private static int lastSlots[] = {-1, -1};

    public static boolean toggleMod = false;
    
    public static void Load() {
        for (int slotId=0; slotId<9; slotId++) {
            hotbar[slotId] = new Slot();
        }
    }

    public static void EditSlot (int slotId, Boolean state, int count, boolean doToggleMod) {
        hotbar[slotId].setState(state);
        hotbar[slotId].setCount(count);
        SetSlotsPool();
        
        if (doToggleMod && state) { toggleMod = state; }
        if (!hasActiveSlots()) { toggleMod = false; }
    }

    public static boolean hasActiveSlots() {      
        boolean hasActiveSlots = false;
        for (int slotId=0; slotId<9; slotId++) {
            if (hotbar[slotId].getState() == true) {
                hasActiveSlots = true;
            }
        }
        return hasActiveSlots;
    }

    public static boolean hasCountIncreased() {
        boolean hasCountIncreased = false;
        for (int slotId=0; slotId<9; slotId++) {
            if (hotbar[slotId].getCount() > 1) {
                hasCountIncreased = true;
            }
        }
        return hasCountIncreased; 
    }

    public static boolean isOnlyOneInPool() {
        boolean isOnlyOneInPool = true;
        if (toggleMod) {
            int tmp = slotsPool.get(0);
            for (int i=0; i<slotsPool.size(); i++) {
                if (slotsPool.get(i) != tmp) { isOnlyOneInPool = false;}
            }
        }
        return isOnlyOneInPool;
    }

    public static void ResetHotbar() {
        hotbar = new Slot[9];
        toggleMod = false;
        Load();
    }

    public static void SetSlotsPool() {
        slotsPool.clear();
        for (int slotId=0; slotId<9; slotId++) {
            if (hotbar[slotId].getState() == true) {
                for (int i=0; i<hotbar[slotId].getCount(); i++) {
                    slotsPool.add(slotId);
                }
            }
        }
    }

    public static void SwitchSlot(PlayerEntity player) {
        if (toggleMod == true && player.method_48926().toString() == "ClientLevel") {
            String settingMixMode = ConfigManager.config.getMixMode();

            int slotsPoolSize = slotsPool.size();
            int index = random.nextInt(slotsPoolSize);
            int slotId = slotsPool.get(index);

            switch (settingMixMode) {
                case "Non-repeating":
                    if (slotsPool.size() > 1) {
                        while (lastSlots[0] == slotId) { 
                            index = random.nextInt(slotsPoolSize);
                            slotId = slotsPool.get(index);
                        }
                    }
                    break;
                case "Non-repeating [2]":
                    if (slotsPool.size() > 1) {
                        while (lastSlots[0] == slotId && lastSlots[1] == slotId) {
                            index = random.nextInt(slotsPoolSize);
                            slotId = slotsPool.get(index);
                        }
                    }
                    break;
                default:
                    break;
            }
            player.getInventory().selectedSlot = slotId;
            
            lastSlots[1] = lastSlots[0];
            lastSlots[0] = slotId;
        }
    }
}
