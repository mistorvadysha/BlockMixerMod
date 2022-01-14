package com.nssg.blockmixer.util;

public class Slot {
    private boolean state = false;
    private int count = 1;

    public boolean getState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}