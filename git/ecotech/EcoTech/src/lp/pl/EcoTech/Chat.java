package lp.pl.EcoTech;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;

public class Chat {

	public static void noPermission(Player p){
		p.sendMessage(ChatColor.RED + "You do not have permission to use EcoTech.");
	}
	public static void giveExtractorFormat(Player p){
		p.sendMessage(ChatColor.RED + "Use the format /tech giveextractor <material> <product> <player> <amount> .");
	}
	public static void sendMessage(Player p, String s){
		p.sendMessage(ChatColor.GREEN + s);
	}
	public static void sendError(Player p, String s){
		p.sendMessage(ChatColor.RED + s);
	}
	public static void sendAllFormat(Player p){
		p.sendMessage(ChatColor.YELLOW + "===============" + ChatColor.AQUA + "[" + ChatColor.GOLD + "EcoTech" + ChatColor.AQUA + "]" + ChatColor.YELLOW + "===============");
		p.sendMessage(ChatColor.AQUA + "/tech extractor,ext <add/remove/info> (fuel/material/product) (amount)" + ChatColor.YELLOW + " Extractor Commands");
	}
	public static void sendExtractorInfo(Player p, Location l){
		String materialType = Extractor.getMaterialType(l).name();
		String productType = Extractor.getProductType(l).name();
		int fuelAmt = Extractor.getFuelAmt(l);
		int materialAmt = Extractor.getMaterialAmt(l);
		int productAmt = Extractor.getProductAmt(l);
		p.sendMessage(ChatColor.GREEN + "Material Type: " + materialType);
		p.sendMessage(ChatColor.GREEN + "Product Type: " + productType);
		p.sendMessage(ChatColor.GREEN + "Fuel Amount: " + fuelAmt);
		p.sendMessage(ChatColor.GREEN + "Material Amount: " + materialAmt);
		p.sendMessage(ChatColor.GREEN + "Product Amount: " + productAmt);
	}
}
