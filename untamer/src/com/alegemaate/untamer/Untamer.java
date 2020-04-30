package com.alegemaate.untamer;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class Untamer extends JavaPlugin implements Listener {
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    getLogger().info("Untamer enabled! Right click animal with shears to untame");
  }

  @Override
  public void onDisable() {
    getLogger().info("Bye!");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("untamer")) {
      sender.sendMessage("Right click animal with shears to untame");
      return true;
    }
    return false;
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onEntityClick(PlayerInteractEntityEvent event) {
    // Precondition check
    if (event.isCancelled()) {
      return;
    }

    // Get player
    Player player = event.getPlayer();
    ItemStack handItem = player.getInventory().getItemInMainHand();

    // Right click must be with shears
    if (handItem.getType() == Material.SHEARS) {
      if (event.getRightClicked() instanceof Tameable) {
        Tameable creature = (Tameable) event.getRightClicked();
        AnimalTamer tamer = creature.getOwner();
        
        // Creature has no owner
        if (tamer == null) {
          return;
        }
        
        // You must own creature
        if (creature.getOwner().getUniqueId() != player.getUniqueId()) {
          player.sendMessage("§5Can not untame! Creature belongs to " + tamer.getName() + "§f");
          return;
        }

        // Untame it
        creature.setTamed(false);
        player.sendMessage("§5Creature untamed§f");
      }
    }
  }
}
