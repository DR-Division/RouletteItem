package com.division.file;


import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class GuiConfig {

    private final File file;
    private final YamlConfiguration config;

    public GuiConfig(String path) {
        this.file = new File(path, "config.yml");
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void loadConfig() {
        try {
            config.load(file);
        }
        catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
