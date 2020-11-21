package com.alegemaate.takemehome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class TakeMeHome extends JavaPlugin {
  @Override
  public void onEnable() {
    getServer().getPluginManager().addPermission(new Permission("takemehome.home"));
    getLogger().info("Take me home enabled! Enjoy your home!");
  }

  @Override
  public void onDisable() {
    getLogger().info("Bye!");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("home")) {
      // Ensure sender is a player
      if (sender instanceof Player) {
        // Cast sender to player
        Player player = (Player) sender;

        // Bed
        if (player.getBedSpawnLocation() != null) {
          sender.sendMessage(ChatColor.DARK_PURPLE + "Teleporting home");
          player.teleport(player.getBedSpawnLocation());
        }
        // Spawn
        else {
          sender.sendMessage(ChatColor.DARK_PURPLE + "Teleporting to spawn");
          player.teleport(getServer().getWorld("world").getSpawnLocation());
        }
      } else {
        sender.sendMessage(ChatColor.RED + "This command can only be run by a player.");
      }
      return true;
    }
    return false;
  }
}
