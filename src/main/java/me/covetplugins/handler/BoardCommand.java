package me.covetplugins.handler;

import me.covetplugins.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BoardCommand implements CommandExecutor {
    private final Main plugin;

    public BoardCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("This command can only be run by a player.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("on")) {
                // Turn on the scoreboard
                // Implement the logic to enable the scoreboard here
                player.sendMessage("Scoreboard enabled.");
                return true;
            } else if (args[0].equalsIgnoreCase("off")) {
                // Turn off the scoreboard
                // Implement the logic to disable the scoreboard here
                player.sendMessage("Scoreboard disabled.");
                return true;
            } else if (args[0].equalsIgnoreCase("reload")) {
                // Reload the configuration
                plugin.reloadConfig();
                player.sendMessage("Configuration reloaded.");
                return true;
            }
        }

        player.sendMessage("Usage: /board <on/off/reload>");
        return true;
    }
}
