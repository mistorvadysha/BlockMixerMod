package com.nssg.blockmixer.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.Text;

public class ModMenuIntegration implements ModMenuApi{

    private String[] modeList = {"Default", "Non-repeating", "Non-repeating [2]"};

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder
                .create()
                .setParentScreen(null)
                .setTitle(Text.translatable("title.blockmixer.config"));

            builder.setSavingRunnable(() -> {
                ConfigManager.Save();
            });

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            ConfigCategory general = builder
                .getOrCreateCategory(Text.translatable("category.blockmixer.general"))

                .addEntry(entryBuilder.startSelector(Text.translatable("option.blockmixer.mixmode"), modeList, ConfigManager.config.getMixMode())
                .setDefaultValue("Default")
                .setTooltip(Text.translatable("option.blockmixer.mixmode.tooltip"))
                .setSaveConsumer(newValue -> ConfigManager.config.setMixMode(newValue))
                .build())

                .addEntry(entryBuilder.startBooleanToggle(Text.translatable("option.blockmixer.chatnotifications"), ConfigManager.config.getChatNotifications())
                .setDefaultValue(false)
                .setSaveConsumer(newValue -> ConfigManager.config.setChatNotifications(newValue))
                .build());
   
            return builder.build();
        };
    }
}