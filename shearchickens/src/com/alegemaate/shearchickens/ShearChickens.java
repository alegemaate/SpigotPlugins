package com.alegemaate.shearchickens;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class ShearChickens extends JavaPlugin implements Listener {
  private int NUM_PARTICLES_SHEAR = 20;
  
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    getLogger().info("Shear chickens enabled! Shear your chickens like sheep");
  }

  @Override
  public void onDisable() {
    getLogger().info("Bye!");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("shearchickens")) {
      sender.sendMessage("Right click chicken with shears to shear like a chicken");
      return true;
    }
    return false;
  }
  
  private Location randomLocationOffset(Location loc) {
    double offX = Math.random() * 2.0f - 1.0f;
    double offY = Math.random() * 2.0f - 1.0f;
    double offZ = Math.random() * 2.0f - 1.0f;
    return loc.add(offX, offY, offZ);
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
      if (event.getRightClicked() instanceof Chicken) {
        // Chicken
        Chicken chicken = (Chicken) event.getRightClicked();
        
        // Check age
        if (!chicken.isAdult()) {
          // Already a baby
          player.sendMessage("�5I am too young to die!�f");
          player.playSound(chicken.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.6f, 1.6f);
        } else {
          // Make a baby
          chicken.setBaby();
          
          // Add feather
          ItemStack feather = new ItemStack(Material.FEATHER);
          player.getWorld().dropItemNaturally(chicken.getLocation(), feather);
          
          // Disturb user
          player.sendMessage("�5The chicken screams in pain�f");
          player.sendMessage("�4AHHHHHH�f");
          player.getWorld().playSound(chicken.getLocation(), Sound.ENTITY_GHAST_SCREAM, 0.6f, 1.4f);
          
          for (int i = 0; i < NUM_PARTICLES_SHEAR; i++) {
            player.getWorld().spawnParticle(Particle.VILLAGER_ANGRY, randomLocationOffset(chicken.getLocation()), 1);
          }
        }
        
        // Cancel event
        event.setCancelled(true);
      }
    }
  }
}
