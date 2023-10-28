package me.covetplugins;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.DisplaySlot;

import java.util.List;

public class Main extends JavaPlugin {

    private ScoreboardManager scoreboardManager;

    @Override
    public void onEnable() {
        // Load or create the configuration
        getConfig().options().copyDefaults(true);
        saveConfig();

        // Initialize the scoreboard manager with configuration
        String title = getConfig().getString("scoreboard.title", "My Scoreboard");
        String displaySlotStr = getConfig().getString("scoreboard.displaySlot", "SIDEBAR");
        DisplaySlot displaySlot = DisplaySlot.valueOf(displaySlotStr);
        List<String> lines = getConfig().getStringList("scoreboard.lines");

        scoreboardManager = new ScoreboardManager(this, title, displaySlot, lines);

        // Register event listeners
        getServer().getPluginManager().registerEvents(scoreboardManager, this);
    }

    @Override
    public void onDisable() {
        // Disable and clean up any resources if needed
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }
}
