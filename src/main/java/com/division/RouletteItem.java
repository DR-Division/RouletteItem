package com.division;

import com.division.command.RouletteCommand;
import com.division.data.RouletteData;
import com.division.file.GuiConfig;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class RouletteItem extends JavaPlugin {

    private GuiConfig config;
    private RouletteData data;

    @Override
    public void onEnable() {
        saveDefaultConfig(); //파일 없을경우 새로 생성
        config = new GuiConfig(getDataFolder().getAbsolutePath()); //config directory
        data = new RouletteData(config.getConfig()); //data Class
        RouletteCommand command = new RouletteCommand(config, data);
        config.loadConfig(); //load config from file
        data.loadData(true); //load data from config
        data.setActive(true);
        Objects.requireNonNull(getCommand("roulette")).setExecutor(command);
        Objects.requireNonNull(getCommand("roulette")).setTabCompleter(command);



    }

    @Override
    public void onDisable() {
        data.saveData();
        config.saveConfig();
    }


}
