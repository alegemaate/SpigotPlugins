package com.alegemaate.creepstop;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CreepStop extends JavaPlugin implements Listener {
  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(this, this);
    getLogger().info("Creep Stop Enabled! Creepers will be stopped!");
  }

  @Override
  public void onDisable() {
    getLogger().info("Bye!");
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("creepstop")) {
      sender.sendMessage("Disables creepers without using mobgreifing");
      return true;
    }
    return false;
  }

  @EventHandler
  public void onEntityExplode(EntityExplodeEvent event) {
    // Stop it
    if (event.getEntity() instanceof Creeper) {
      event.setCancelled(true);
    }
  }
}
