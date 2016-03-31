package lp.pl.EcoTech;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Extractor {

	public static FileConfiguration config = Main.instance.getConfig();
	public static boolean isExtractor(Location l){
		for(String s : config.getConfigurationSection("extractor").getKeys(false)){
			int x = Integer.parseInt(s.split(",")[0]);
			int y = Integer.parseInt(s.split(",")[1]);
			int z = Integer.parseInt(s.split(",")[2]);
			World world = Bukkit.getWorld(s.split(",")[3]);
			Location loc = new Location(world,x,y,z);
			if(loc.equals(l)){
				return true;
			}
		}
		return false;
	}
	public static void remove(Location l){
		int x = l.getBlockX();
		int y = l.getBlockY();
		int z = l.getBlockZ();
		String world = l.getWorld().getName();
		Database.removeExtractor(x, y, z, world);
	}
    public static boolean isExtractorItem(ItemStack i){
    	if(i.hasItemMeta()){
    	if(i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Iron Extractor") ||
    			i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Gold Extractor") ||
    			i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Diamond Extractor") ||
    			i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Dirt/Exp Extractor") ||
    			i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Netherrack/Quartz Extractor") ||
    			i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Coal Extractor") ||
    			i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Emerald Extractor") ||
    			i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Redstone Extractor")){
    		return true;
    	}
    	return false;
    }
    	return false;
    }
    public static Material getExtractorItemData(ItemStack i, String materialOrProduct){
    	Material m = Material.AIR;
    	if(i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Iron Extractor")){
    		if(materialOrProduct.equals("material")){
    			m = Material.COBBLESTONE;
    		}else{
    			m = Material.IRON_INGOT;
    		}
    	}
    	if(i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Gold Extractor")){
    		if(materialOrProduct.equals("material")){
    			m = Material.COBBLESTONE;
    		}else{
    			m = Material.GOLD_INGOT;
    		}
    	}
    	if(i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Diamond Extractor")){
    		if(materialOrProduct.equals("material")){
    			m = Material.COBBLESTONE;
    		}else{
    			m = Material.DIAMOND;
    		}
    	}
    	if(i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Dirt/Exp Extractor")){
    		if(materialOrProduct.equals("material")){
    			m = Material.DIRT;
    		}else{
    			m = Material.EXP_BOTTLE;
    		}
    	}
    	if(i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Netherrack/Quartz Extractor")){
    		if(materialOrProduct.equals("material")){
    			m = Material.NETHERRACK;
    		}else{
    			m = Material.QUARTZ;
    		}
    	}
    	if(i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Coal Extractor")){
    		if(materialOrProduct.equals("material")){
    			m = Material.COBBLESTONE;
    		}else{
    			m = Material.COAL;
    		}
    	}
    	if(i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Redstone Extractor")){
    		if(materialOrProduct.equals("material")){
    			m = Material.COBBLESTONE;
    		}else{
    			m = Material.REDSTONE;
    		}
    	}
    	if(i.getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Cobble/Emerald Extractor")){
    		if(materialOrProduct.equals("material")){
    			m = Material.COBBLESTONE;
    		}else{
    			m = Material.EMERALD;
    		}
    	}
    	return m;
    }
	public static int getFuelAmt(Location l){
		int amt = 0;
		String unParsedLoc = l.getBlockX() + "," + l.getBlockY() + "," +  l.getBlockZ() + "," + l.getWorld().getName();
		String data = config.getString("extractor." + unParsedLoc);
		amt = Integer.parseInt(data.split(";")[2]);
		return amt;
	}
	public static int getMaterialAmt(Location l){
		int amt = 0;
		String unParsedLoc = l.getBlockX() + "," + l.getBlockY() + "," +  l.getBlockZ() + "," + l.getWorld().getName();
		String data = config.getString("extractor." + unParsedLoc);
		amt = Integer.parseInt(data.split(";")[4]);
		return amt;
	}
	public static OfflinePlayer getOwner(Location l){
		String unParsedLoc = l.getBlockX() + "," + l.getBlockY() + "," +  l.getBlockZ() + "," + l.getWorld().getName();
		String data = config.getString("extractor." + unParsedLoc);
		OfflinePlayer p = Bukkit.getOfflinePlayer(data.split(";")[5]);
		return p;
	}
	public static int getProductAmt(Location l){
		int amt = 0;
		String unParsedLoc = l.getBlockX() + "," + l.getBlockY() + "," +  l.getBlockZ() + "," + l.getWorld().getName();
		String data = config.getString("extractor." + unParsedLoc);
		amt = Integer.parseInt(data.split(";")[3]);
		return amt;
	}
	public static Material getMaterialType(Location l){
		Material type = null;
		String unParsedLoc = l.getBlockX() + "," + l.getBlockY() + "," +  l.getBlockZ() + "," + l.getWorld().getName();
		String data = config.getString("extractor." + unParsedLoc);
		type = Material.getMaterial(data.split(";")[0]);
		return type;
	}
	public static Material getProductType(Location l){
		Material type = null;
		String unParsedLoc = l.getBlockX() + "," + l.getBlockY() + "," +  l.getBlockZ() + "," + l.getWorld().getName();
		String data = config.getString("extractor." + unParsedLoc);
		type = Material.getMaterial(data.split(";")[1]);
		return type;
	}
	public static ItemStack getExtractorItem(Material material, Material product){
		ItemStack type = new ItemStack(Material.FURNACE);
		ItemMeta meta = type.getItemMeta();
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.IRON_INGOT)){
			meta.setDisplayName(ChatColor.AQUA + "Cobble/Iron Extractor");
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.GOLD_INGOT)){
			meta.setDisplayName(ChatColor.AQUA + "Cobble/Gold Extractor");
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.DIAMOND)){
			meta.setDisplayName(ChatColor.AQUA + "Cobble/Diamond Extractor");
		}
		if(material.equals(Material.DIRT) && product.equals(Material.EXP_BOTTLE)){
			meta.setDisplayName(ChatColor.AQUA + "Dirt/Exp Extractor");
		}
		if(material.equals(Material.NETHERRACK) && product.equals(Material.QUARTZ)){
			meta.setDisplayName(ChatColor.AQUA + "Netherrack/Quartz Extractor");
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.COAL)){
			meta.setDisplayName(ChatColor.AQUA + "Cobble/Coal Extractor");
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.REDSTONE)){
			meta.setDisplayName(ChatColor.AQUA + "Cobble/Redstone Extractor");
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.EMERALD)){
			meta.setDisplayName(ChatColor.AQUA + "Cobble/Emerald Extractor");
		}
		type.setItemMeta(meta);
		return type;
	}
	public static int getAmtIterations(Material material, Material product){
		int iterations = 0;
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.IRON_INGOT)){
			iterations = 6;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.GOLD_INGOT)){
			iterations = 8;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.DIAMOND)){
			iterations = 48;
		}
		if(material.equals(Material.DIRT) && product.equals(Material.EXP_BOTTLE)){
			iterations = 8;
		}
		if(material.equals(Material.NETHERRACK) && product.equals(Material.QUARTZ)){
			iterations = 4;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.COAL)){
			iterations = 6;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.REDSTONE)){
			iterations = 6;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.EMERALD)){
			iterations = 24;
		}
		return iterations;
	}
	public static int getMaterialCost(Material material, Material product){
		int cost = 0;
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.IRON_INGOT)){
			cost = 8;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.GOLD_INGOT)){
			cost = 8;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.DIAMOND)){
			cost = 64;
		}
		if(material.equals(Material.DIRT) && product.equals(Material.EXP_BOTTLE)){
			cost = 10;
		}
		if(material.equals(Material.NETHERRACK) && product.equals(Material.QUARTZ)){
			cost = 6;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.COAL)){
			cost = 8;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.REDSTONE)){
			cost = 12;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.EMERALD)){
			cost = 32;
		}
		return cost;
	}
	public static int getProductOutput(Material material, Material product){
		int output = 0;
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.IRON_INGOT)){
			output = 2;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.GOLD_INGOT)){
			output = 2;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.DIAMOND)){
			output = 1;
		}
		if(material.equals(Material.DIRT) && product.equals(Material.EXP_BOTTLE)){
			output = 6;
		}
		if(material.equals(Material.NETHERRACK) && product.equals(Material.QUARTZ)){
			output = 4;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.COAL)){
			output = 5;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.REDSTONE)){
			output = 14;
		}
		if(material.equals(Material.COBBLESTONE) && product.equals(Material.EMERALD)){
			output = 1;
		}
		return output;
	}
}
