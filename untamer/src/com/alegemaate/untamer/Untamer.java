package com.alegemaate.untamer;

import org.bukkit.Material;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Untamer extends JavaPlugin implements Listener {
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    getServer().getPluginManager().addPermission(new Permission("untamer.untame"));
    getLogger().info("Untamer enabled! Right click animal with shears to untame");
  }

  @Override
  public void onDisable() {
    getLogger().info("Bye!");
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

    // Check permissions
    if (!player.hasPermission("untamer.untame")) {
      player.sendMessage(ChatColor.RED + "You do not have permission untame animals");
      return;
    }
    
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
          event.setCancelled(true);
          player.sendMessage(ChatColor.RED + "Can not untame, creature belongs to " + tamer.getName());
          return;
        }

        // Untame it
        creature.setTamed(false);
        event.setCancelled(true);
        player.sendMessage(ChatColor.GOLD + "Creature untamed!");
      }
    }
  }
}
