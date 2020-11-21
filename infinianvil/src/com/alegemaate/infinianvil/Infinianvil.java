package com.alegemaate.infinianvil;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Infinianvil extends JavaPlugin implements Listener {
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    getServer().getPluginManager().addPermission(new Permission("infinianvil.use"));
    getLogger().info("Infianvil enabled! Enjoy your anvils!");
  }

  @Override
  public void onDisable() {
    getLogger().info("Bye!");
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onInventoryClick(InventoryClickEvent event) {
    // Precondition check
    if (event.isCancelled() || !(event.getInventory() instanceof AnvilInventory) || !(event.getWhoClicked() instanceof Player)) {
      return;
    }

    // Check permissions
    Player player = (Player) event.getWhoClicked();
    if (!player.hasPermission("infinianvil.use")) {
      return;
    }

    // Get the entity
    Block targetBlock = player.getTargetBlock(null, 7);

    // Update material
    if (targetBlock != null
        && (targetBlock.getType() == Material.CHIPPED_ANVIL || targetBlock.getType() == Material.DAMAGED_ANVIL)) {
      BlockFace facing = ((Directional) targetBlock.getBlockData()).getFacing();
      targetBlock.setType(Material.ANVIL);
      ((Directional) targetBlock.getBlockData()).setFacing(facing);
      player.sendMessage(ChatColor.GREEN + "Anvil repaired");
    }
  }
}
