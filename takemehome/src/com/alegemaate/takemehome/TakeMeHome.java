package com.alegemaate.takemehome;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class TakeMeHome extends JavaPlugin {
	@Override
	public void onEnable() {
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
					sender.sendMessage("§5Teleporting home§f");
					player.teleport(player.getBedSpawnLocation());
				}
				// Spawn
				else {
					sender.sendMessage("§5Teleporting to spawn§f");
					player.teleport(getServer().getWorld("world").getSpawnLocation());
				}
			}
			else {
				sender.sendMessage("This command can only be run by a player.");
			}
            return true;
		}
		return false;
	}
}
