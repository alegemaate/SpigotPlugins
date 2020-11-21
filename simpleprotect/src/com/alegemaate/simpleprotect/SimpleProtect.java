package com.alegemaate.simpleprotect;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

public class SimpleProtect extends JavaPlugin implements Listener {
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    getServer().getPluginManager().addPermission(new Permission("simpleprotect.allowed"));
    getLogger().info("Simple Protect Enabled!");
  }

  @Override 
  public void onDisable() {
    getLogger().info("Bye!");
  }

  private boolean playerIsDisabled(Player player) {
    return player instanceof Player && !player.hasPermission("simpleprotect.allowed");
  }

  @EventHandler
  public void onInventoryOpen(InventoryOpenEvent event) {
    final Player player = (Player) event.getPlayer();
    if (playerIsDisabled(player)) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onBlockBreak(BlockBreakEvent event) {
    final Player player = (Player) event.getPlayer();
    if (playerIsDisabled(player)) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onBlockPlace(BlockPlaceEvent event) {
    final Player player = (Player) event.getPlayer();
    if (playerIsDisabled(player)) {
      event.setCancelled(true);
    }
  }

  @EventHandler
  public void onPlayerDropItem(PlayerDropItemEvent event) {
    final Player player = (Player) event.getPlayer();
    if (playerIsDisabled(player)) {
      event.setCancelled(true);
    }
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onPlayerInteract(PlayerInteractEvent event) {
    final Player player = (Player) event.getPlayer();
    if (playerIsDisabled(player)) {
      event.setCancelled(true);
    }
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
    final Player player = (Player) event.getPlayer();
    if (playerIsDisabled(player)) {
      event.setCancelled(true);
    }
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onFoodLevelChange(FoodLevelChangeEvent event) {
    if (!(event.getEntity() instanceof Player)) {
      return;
    }
    
    final Player player = (Player) event.getEntity();
    if (playerIsDisabled(player)) {
      event.setCancelled(true);
    }
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onDamageEntity(EntityDamageEvent event) {
    if (!(event.getEntity() instanceof Player)) {
      return;
    }
    
    final Player player = (Player) event.getEntity();
    if (playerIsDisabled(player)) {
      event.setCancelled(true);
    }
  }

  @EventHandler(priority = EventPriority.LOWEST)
  public void onTarget(EntityTargetEvent event) {
    if (!(event.getTarget() instanceof Player)) {
      return;
    }
    
    final Player player = (Player) event.getTarget();
    if (playerIsDisabled(player)) {
      event.setCancelled(true);
    }
  }
}
