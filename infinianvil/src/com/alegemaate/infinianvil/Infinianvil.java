package com.alegemaate.infinianvil;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Directional;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Infinianvil extends JavaPlugin implements Listener {
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    getLogger().info("Infianvil enabled! Enjoy your anvils!");
  }

  @Override
  public void onDisable() {
    getLogger().info("Bye!");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("infinianvil")) {
      sender.sendMessage("Infinianvil is here to save your anvils.");
      return true;
    }
    return false;
  }

  @EventHandler(priority = EventPriority.MONITOR)
  public void onInventoryClick(InventoryClickEvent e) {
    // Precondition check
    if (e.isCancelled() || e.getInventory().equals(null) || !(e.getInventory() instanceof AnvilInventory)
        || e.getWhoClicked().equals(null)) {
      return;
    }

    // Get the entity
    Block a = e.getWhoClicked().getTargetBlock(null, 7);

    // Update material
    if (a != null && (a.getType() == Material.CHIPPED_ANVIL || a.getType() == Material.DAMAGED_ANVIL)) {
      BlockFace facing = ((Directional) a.getBlockData()).getFacing();
      a.setType(Material.ANVIL);
      ((Directional) a.getBlockData()).setFacing(facing);
      getLogger().info("Infianvil: Anvil repaired!");
    }
  }
}
