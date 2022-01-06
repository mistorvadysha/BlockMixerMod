package com.nssg.blockmixer.config;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nssg.blockmixer.BlockMixerClient;

import net.fabricmc.loader.api.FabricLoader;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Path configPath = FabricLoader.getInstance().getConfigDir().resolve(BlockMixerClient.MOD_ID + ".json");
    
    public static Config configJSON;
    
    public static void Load() throws IOException {
        Config config = new Config();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(configPath.toString()))) {
            String configString = reader.lines().collect(Collectors.joining());
            reader.close();
            configJSON = GSON.fromJson(configString, Config.class);
        }

        catch (IOException e) {
            SetDefaults(config);
            Files.createFile(configPath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(configPath.toFile()));
            String configString = GSON.toJson(config);
            writer.write(configString);
            writer.close();
        }   
    }

    public static void SetDefaults(Config config) {
        config.setMixMode("Default");
        config.setChatNotifications(false);
    }

    public static void RewriteConfig() {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(configPath.toFile()));
            String configString = GSON.toJson(configJSON);
            writer.write(configString);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
