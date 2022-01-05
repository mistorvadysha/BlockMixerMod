package com.nssg.blockmixer.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.text.TranslatableText;

public class ModMenuIntegration implements ModMenuApi{

    public String[] modeList = {"Default", "Non-repeating", "Non-repeating [2]"};

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> {
            ConfigBuilder builder = ConfigBuilder
                .create()
                .setParentScreen(null)
                .setTitle(new TranslatableText("title.blockmixer.config"));

            builder.setSavingRunnable(() -> {
                JsonManager.RewriteConfig();
            });

            ConfigEntryBuilder entryBuilder = builder.entryBuilder();

            ConfigCategory general = builder
                .getOrCreateCategory(new TranslatableText("category.blockmixer.general"))
        
                .addEntry(entryBuilder.startSelector(new TranslatableText("option.blockmixer.mixmode"), modeList, JsonManager.configJSON.getSettingMixMode())
                .setDefaultValue("Default")
                .setTooltip(new TranslatableText("option.blockmixer.mixmode.tooltip"))
                .setSaveConsumer(newValue -> JsonManager.configJSON.setSettingMixMode(newValue))
                .build());
                
            return builder.build();
        };
    }
}