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
    
    public static Config config;

    public static void Load() throws IOException {
        config = new Config();
        
        // Read config file and save data to Config "config"
        try {
            BufferedReader reader = new BufferedReader(new FileReader(configPath.toString()));
            String configString = reader.lines().collect(Collectors.joining());
            reader.close();
            config = GSON.fromJson(configString, Config.class);
        }

        // Create a new "nssgs-blockmixer.json file if it doesn't exist"
        catch (IOException e) {
            CreateConfigFile(config);

            Load();
        }   
    }

    private static void CreateConfigFile(Config config) throws IOException {
        Files.createFile(configPath);
        Save();
    }

    public static void Save() {
        try {
            String configString = GSON.toJson(config);

            BufferedWriter writer = new BufferedWriter(new FileWriter(configPath.toFile()));
            writer.write(configString);
            writer.close();
        }
        catch (IOException e) { e.printStackTrace(); }
    }
}