package com.alegemaate.usefulflesh;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.FurnaceRecipe;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmokingRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class UsefulFlesh extends JavaPlugin {
  @Override
  public void onEnable() {
    // Add recipes to server
    getServer().addRecipe(createFurnaceRecipe());
    getServer().addRecipe(createSmokingRecipe());

    // Log enabled
    getLogger().info("Useful flesh enabled!");
  }
  
  @Override
  public void onDisable() {
    getLogger().info("Bye!");
  }
  
  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (command.getName().equalsIgnoreCase("usefulflesh")) {
      sender.sendMessage("Smelt or smoke rotten flesh into leather");
      return true;
    }
    return false;
  }

  // Create furnace recipe
  private FurnaceRecipe createFurnaceRecipe() {
    NamespacedKey key = new NamespacedKey(this, "rotten_flesh");
    ItemStack ouputMaterial = new ItemStack(Material.LEATHER);
    Material inputMaterial = Material.ROTTEN_FLESH;
    float experience = 0.1f;
    int cookTime = 200;

    return new FurnaceRecipe(key, ouputMaterial, inputMaterial, experience, cookTime);
  }

  // Create smoker recipe
  private SmokingRecipe createSmokingRecipe() {
    NamespacedKey key = new NamespacedKey(this, "rotten_flesh");
    ItemStack ouputMaterial = new ItemStack(Material.LEATHER);
    Material inputMaterial = Material.ROTTEN_FLESH;
    float experience = 0.1f;
    int cookTime = 200;

    return new SmokingRecipe(key, ouputMaterial, inputMaterial, experience, cookTime);
  }
}
