package com.alegemaate.creepstop;

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

  @EventHandler
  public void onEntityExplode(EntityExplodeEvent event) {
    // Stop it
    if (!event.isCancelled() && event.getEntity() instanceof Creeper) {
      event.setCancelled(true);
    }
  }
}
