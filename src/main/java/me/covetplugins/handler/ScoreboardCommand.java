package me.covetplugins.handler;

import me.covetplugins.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.command.TabCompleter;
import java.util.ArrayList;
import java.util.List;

public class ScoreboardCommand implements CommandExecutor, TabCompleter {
    private final Main plugin;

    public ScoreboardCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length != 1) {
                player.sendMessage("Usage: /scoreboard <on|off|reload>");
            } else {
                switch (args[0].toLowerCase()) {
                    case "on":
                        plugin.getScoreboardManager().setScoreboardEnabled(player, true);
                        player.sendMessage("Scoreboard turned on.");
                        break;
                    case "off":
                        plugin.getScoreboardManager().setScoreboardEnabled(player, false);
                        player.sendMessage("Scoreboard turned off.");
                        break;
                    case "reload":
                        plugin.reloadConfig();
                        player.sendMessage("Configuration reloaded.");
                        break;
                    default:
                        player.sendMessage("Usage: /scoreboard <on|off|reload>");
                        break;
                }
            }
        } else {
            sender.sendMessage("This command can only be used by a player.");
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            completions.add("on");
            completions.add("off");
            completions.add("reload");
        }

        return completions;
    }
}
