package me.covetplugins;

import me.covetplugins.handler.BoardCommand;
import me.covetplugins.handler.eventListener;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public final class Main extends JavaPlugin {

    private BukkitTask task;

    @Override
    public void onEnable() {
        getCommand("board").setExecutor(new BoardCommand(this));
        saveDefaultConfig(); 

        FileConfiguration config = getConfig(); 
        
        Scoreboard customScoreboard = new Scoreboard(this);

        List<String> scoreboardLines = config.getStringList("scoreboard.lines");

        if (config != null) {

        } else {
            getLogger().severe("Config file not found or couldn't be loaded. Unable to create scoreboard.");
        }
    }

    @Override
    public void onDisable() {
        if (task != null && !task.isCancelled())
            task.cancel();
    }
}
