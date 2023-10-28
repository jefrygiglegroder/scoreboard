package me.covetplugins;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import static org.apache.logging.log4j.LogManager.getLogger;

public class board implements Runnable {

    private final static board instance = new board();

    private board() {}

    @Override
    public void run() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getScoreboard() != null) {
                // Check if the player already has a sidebar
                if (player.getScoreboard().getObjective("scoreboard") != null) {
                    updateScoreboard(player);
                } else if (player.getScoreboard().getObjective("belowName") != null) {
                    updateBelowName(player);
                }
            } else {
                createNewScoreboard(player);
            }
        }
    }

    private void createNewScoreboard(Player player) {
        getLogger().info("Creating a new scoreboard for " + player.getName());
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("scoreboard", "dummy");

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName(ChatColor.GOLD + "" + ChatColor.BOLD + "Scoreboard");

        objective.getScore("concept").setScore(1);

        Team team1 = scoreboard.registerNewTeam("team1");
        String teamKey = ChatColor.WHITE.toString();

        team1.addEntry(teamKey);

        String placeholder = getConfigPlaceholder();

        team1.setPrefix(placeholder);


        String manaValue = calculateManaForPlayer(player);
        team1.setSuffix("Mana: " + manaValue);

        objective.getScore(teamKey).setScore(0);

        player.setScoreboard(scoreboard);
        getLogger().info("New scoreboard created for " + player.getName());
    }

    private void updateScoreboard(Player player) {
        getLogger().info("Updating scoreboard for " + player.getName());
        Scoreboard scoreboard = player.getScoreboard();
        Team team = scoreboard.getTeam("team1");


        String placeholder = getConfigPlaceholder();


        String updatedPrefix = placeholder;


        String manaValue = calculateManaForPlayer(player);
        String updatedSuffix = "Mana: " + manaValue;

        team.setPrefix(updatedPrefix);
        team.setSuffix(updatedSuffix);
        getLogger().info("Scoreboard updated for " + player.getName());
    }

    private void updateBelowName(Player player) {
        getLogger().info("Updating below-name display for " + player.getName());
        Scoreboard scoreboard = player.getScoreboard();
        Team team = scoreboard.getTeam("team1");


        String placeholder = getConfigPlaceholder();


        String updatedPrefix = placeholder;


        String manaValue = calculateManaForPlayer(player);
        String updatedSuffix = "Mana: " + manaValue;


        Objective belowNameObjective = scoreboard.getObjective("belowName");
        if (belowNameObjective != null) {
            belowNameObjective.setDisplayName(updatedPrefix + " - " + updatedSuffix);
        }

        getLogger().info("Below-name display updated for " + player.getName());
    }

    private String getConfigPlaceholder() {
        return null;
    }

    private String calculateManaForPlayer(Player player) {
        return null;
    }

    public static board getInstance() {
        return instance;
    }
}
