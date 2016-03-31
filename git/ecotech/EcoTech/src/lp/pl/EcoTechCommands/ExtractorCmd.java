package lp.pl.EcoTechCommands;

import lp.pl.EcoTech.Chat;
import lp.pl.EcoTech.Database;
import lp.pl.EcoTech.Extractor;
import lp.pl.EcoTech.Inventories;
import lp.pl.EcoTech.Listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.block.Furnace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ExtractorCmd {

	public static boolean Execute(CommandSender sender, Command command,
            String label, String[] args){
		Player player = (Player) sender;
		if(args.length == 1){
			Chat.sendError(player, "Format is /tech extractor <add/remove/info> (fuel/material/product) (amount)");
			return true;
		}
		if(!Listeners.inCmd.containsKey(player.getName())){
			Chat.sendError(player, "Please select an Extractor first.");
			return true;
		}
		  Location l = Listeners.inCmd.get(player.getName());
		  int x = l.getBlockX();
		  int y = l.getBlockY();
		  int z = l.getBlockZ();
		  String world = l.getWorld().getName();
		  Material material = Extractor.getMaterialType(l);
		  Material product = Extractor.getProductType(l);
		  int fuelAmt = Extractor.getFuelAmt(l);
		  int productAmt = Extractor.getProductAmt(l);
		  int materialAmt = Extractor.getMaterialAmt(l);
		  int amt = 0;
		  ItemStack fuel = new ItemStack(Material.BLAZE_ROD);
		  ItemMeta fuelMeta = fuel.getItemMeta();
		  fuelMeta.setDisplayName(ChatColor.AQUA + "Fuel Rod");
		  fuel.setItemMeta(fuelMeta);
		  ItemStack materialItemStack = new ItemStack(Extractor.getMaterialType(l));
		  ItemStack productItemStack = new ItemStack(Extractor.getProductType(l));
		  Furnace f = (Furnace) l.getBlock().getState();
		  FurnaceInventory inv = f.getInventory();
		  OfflinePlayer owner = Extractor.getOwner(l);
		if(!player.getName().equals(owner.getName())){
			  Chat.sendError(player, "You don't own this Extractor");
			  return true;
		}
		if(args.length == 2){
			if(args[1].equalsIgnoreCase("info")){
				Chat.sendExtractorInfo(player, Listeners.inCmd.get(player.getName()));
				return true;
			}else{
				Chat.sendError(player, "Format is /tech extractor <add/remove/info> (fuel/material/product) (amount)");
				return true;
			}
		}
		if(args.length == 3){
			Chat.sendError(player, "Format is /tech extractor <add/remove/info> (fuel/material/product) (amount)");
			return true;
		}
		if(args.length == 4){
			  try{
				  amt = Integer.parseInt(args[3]);
			  }catch(Exception e){
				  Chat.sendError(player, amt + " is not a valid number.");
				  return true;
			  }
			if(args[1].equalsIgnoreCase("add")){
				if(args[2].equalsIgnoreCase("fuel")){
					  if(player.getInventory().containsAtLeast(fuel, amt)){
					  fuelAmt += amt;
					  Inventories.removeItems(player, fuel, amt);
					  inv.setSmelting(new ItemStack(Material.IRON_ORE,64));
					  inv.setFuel(new ItemStack(Material.COAL, 64));
					  Database.saveExtractor(x, y, z, world, material.name(), product.name(), fuelAmt, productAmt, materialAmt, owner.getName());
					  return true;
					  }else{
							Chat.sendError(player, "You do not have the required items.");
							return true;
						}
				}
				if(args[2].equalsIgnoreCase("material")){
					  if(player.getInventory().containsAtLeast(materialItemStack, amt)){
					  materialAmt += amt;
					  Inventories.removeItems(player, materialItemStack, amt);
					  inv.setSmelting(new ItemStack(Material.IRON_ORE,64));
					  inv.setFuel(new ItemStack(Material.COAL, 64));
					  Database.saveExtractor(x, y, z, world, material.name(), product.name(), fuelAmt, productAmt, materialAmt, owner.getName());
					  return true;
					  }else{
							Chat.sendError(player, "You do not have the required items.");
							return true;
						}
				}
			}
			if(args[1].equalsIgnoreCase("remove")){
				if(args[2].equalsIgnoreCase("fuel")){
					if(Inventories.hasSpaceFor(player, fuel, amt)){
						if(Extractor.getProductAmt(l) < amt){
							Chat.sendError(player, "Not enough items in the Extractor.");
							return true;
						}
					  fuelAmt -= amt;
					  Inventories.giveItems(player, fuel, amt);
					  Database.saveExtractor(x, y, z, world, material.name(), product.name(), fuelAmt, productAmt, materialAmt, owner.getName());
					  return true;
					}else{
						Chat.sendError(player, "You do not have the required space.");
						return true;
					}
					}
				if(args[2].equalsIgnoreCase("material")){
					if(Inventories.hasSpaceFor(player, materialItemStack, amt)){
						if(Extractor.getProductAmt(l) < amt){
							Chat.sendError(player, "Not enough items in the Extractor.");
							return true;
						}
					  materialAmt -= amt;
					  Inventories.giveItems(player, materialItemStack, amt);
					  Database.saveExtractor(x, y, z, world, material.name(), product.name(), fuelAmt, productAmt, materialAmt, owner.getName());
					  return true;
					}else{
						Chat.sendError(player, "You do not have the required space.");
						return true;
					}
				}
				if(args[2].equalsIgnoreCase("product")){
					if(Inventories.hasSpaceFor(player, productItemStack, amt)){
						if(Extractor.getProductAmt(l) < amt){
							Chat.sendError(player, "Not enough items in the Extractor.");
							return true;
						}
					  productAmt -= amt;
					  Inventories.giveItems(player, productItemStack, amt);
					  Database.saveExtractor(x, y, z, world, material.name(), product.name(), fuelAmt, productAmt, materialAmt, owner.getName());
					  return true;
					}else{
						Chat.sendError(player, "You do not have the required space.");
						return true;
					}
				}else{
					Chat.sendError(player, "Format is /tech extractor <add/remove/info> (fuel/material/product) (amount)");
					return true;
				}
				}else{
					Chat.sendError(player, "Format is /tech extractor <add/remove/info> (fuel/material/product) (amount)");
					return true;
				}
		}else{
			Chat.sendError(player, "Format is /tech extractor <add/remove/info> (fuel/material/product) (amount)");
			return true;
		}
	}
}
