package lp.pl.EcoTech;


import java.awt.List;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Detector {
	
	public static ItemStack getDetectorItem(Material type, int range){
		ItemStack i = new ItemStack(Material.COMPASS);
		ItemMeta meta = i.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		if(type.equals(Material.IRON_ORE)){
			meta.setDisplayName(ChatColor.AQUA + "Iron Ore Detector");
			lore.add(ChatColor.RED + "Charge: 0%");
			lore.add(ChatColor.RED + "Range: " + range + "m");
			meta.setLore(lore);
			i.setItemMeta(meta);
			return i;
		}
		if(type.equals(Material.GOLD_ORE)){
			meta.setDisplayName(ChatColor.AQUA + "Gold Ore Detector");
			lore.add(ChatColor.RED + "Charge: 0%");
			lore.add(ChatColor.RED + "Range: " + range + "m");
			meta.setLore(lore);
			i.setItemMeta(meta);
			return i;
		}
		if(type.equals(Material.LAPIS_ORE)){
			meta.setDisplayName(ChatColor.AQUA + "Lapis Ore Detector");
			lore.add(ChatColor.RED + "Charge: 0%");
			meta.setLore(lore);
			i.setItemMeta(meta);
			return i;
		}
		if(type.equals(Material.DIAMOND_ORE)){
			meta.setDisplayName(ChatColor.AQUA + "Diamond Ore Detector");
			lore.add(ChatColor.RED + "Charge: 0%");
			lore.add(ChatColor.RED + "Range: " + range + "m");
			meta.setLore(lore);
			i.setItemMeta(meta);
			return i;
		}
		if(type.equals(Material.MOB_SPAWNER)){
			meta.setDisplayName(ChatColor.AQUA + "Spawner Detector");
			lore.add(ChatColor.RED + "Charge: 0%");
			lore.add(ChatColor.RED + "Range: " + range + "m");
			meta.setLore(lore);
			i.setItemMeta(meta);
			return i;
		}
	return null;
	}
	public static int getCharge(ItemStack i){
		ItemMeta meta = i.getItemMeta();
		int charge = Integer.parseInt(meta.getLore().get(2).split(" ")[1].replace("%", ""));
		return charge;
	}
}
