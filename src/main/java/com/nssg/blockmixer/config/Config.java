package com.nssg.blockmixer.config;

public class Config {
    private String settingMixMode = "Default";
    private boolean settingChatNotifications = false;

    //settingMixMode
    public String getMixMode() { return settingMixMode; }
    public void setMixMode(String settingMixMode) { this.settingMixMode = settingMixMode; }

    public boolean getChatNotifications() { return settingChatNotifications; }
    public void setChatNotifications(Boolean settingChatNotifications) { this.settingChatNotifications = settingChatNotifications; }
}
