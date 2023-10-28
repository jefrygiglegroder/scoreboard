package me.covetplugins;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.List;

public class ScoreboardManager implements Listener {

    private final Main plugin;
    private String title;
    private DisplaySlot displaySlot;
    private List<String> lines;
    private boolean scoreboardEnabled = true; // Add a flag to track scoreboard state

    public ScoreboardManager(Main plugin, String title, DisplaySlot displaySlot, List<String> lines) {
        this.plugin = plugin;
        this.title = title;
        this.displaySlot = displaySlot;
        this.lines = lines;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        if (scoreboardEnabled) {
            setScoreboard(player);
        }
    }

    public void setScoreboard(Player player) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("YourObjective", "dummy", title);
        objective.setDisplaySlot(displaySlot);

        // Set scores for your scoreboard lines
        int score = lines.size();
        for (String line : lines) {
            objective.getScore(line).setScore(score);
            score--;
        }

        player.setScoreboard(scoreboard);
    }

    public void setScoreboardEnabled(Player player, boolean enabled) {
        scoreboardEnabled = enabled;
    }
}
