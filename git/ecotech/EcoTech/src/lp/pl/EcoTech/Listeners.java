package lp.pl.EcoTech;




import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.event.DocumentEvent.EventType;

import net.md_5.bungee.api.ChatColor;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceSmeltEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.FurnaceInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Listeners implements Listener{
	public static HashMap<String, Location> inCmd = new HashMap<String, Location>();
	public static Main plugin;
	  public Listeners(Main instance)
	  {
	    plugin = instance;
	  }

	  @EventHandler(priority=EventPriority.HIGH)
	  public void onBreak(BlockBreakEvent e){
		  // Handles Extractors
		  Location loc = e.getBlock().getLocation();
		  ItemStack type = null;
		  if(Extractor.isExtractor(loc)){
			  Furnace furnace = (Furnace) loc.getBlock().getState();
			  furnace.getInventory().clear();
			  type = Extractor.getExtractorItem(Extractor.getMaterialType(loc), Extractor.getProductType(loc));
			  loc.getWorld().getBlockAt(loc).setType(Material.AIR);
              ItemStack stackToBeDropped = null;
              int amtToBeDropped = 0;
              if(Extractor.getFuelAmt(loc) > 0){
            	  stackToBeDropped = Recipes.fuelRodRecipe.getResult();
            	  amtToBeDropped = Extractor.getFuelAmt(loc);
                  Inventories.dropItemsInLocation(loc, stackToBeDropped, amtToBeDropped);
              }
              if(Extractor.getMaterialAmt(loc) > 0){
            	  stackToBeDropped = new ItemStack(Extractor.getMaterialType(loc));
            	  amtToBeDropped = Extractor.getMaterialAmt(loc);
                  Inventories.dropItemsInLocation(loc, stackToBeDropped, amtToBeDropped);
              }
              if(Extractor.getProductAmt(loc) > 0){
            	  stackToBeDropped = new ItemStack(Extractor.getProductType(loc));
            	  amtToBeDropped = Extractor.getProductAmt(loc);
                  Inventories.dropItemsInLocation(loc, stackToBeDropped, amtToBeDropped);
              }
              Inventories.dropItemsInLocation(loc, type, 1);
              Extractor.remove(loc);
              for(Entry entry : inCmd.entrySet()){
            	  String key = (String) entry.getKey();
            	  Location value = (Location) entry.getValue();
            	  if(loc == value){
            		  inCmd.remove(key);
            	  }
              }
			  e.setCancelled(true);
		  }
	  }
	  @EventHandler(priority=EventPriority.HIGH)
	  public void onPlace(BlockPlaceEvent e){
		  if(Extractor.isExtractorItem(e.getItemInHand())){
			  int x = e.getBlock().getX();
			  int y = e.getBlock().getY();
			  int z = e.getBlock().getZ();
			  String world = e.getBlock().getWorld().getName();
			  String materialTypes = Extractor.getExtractorItemData(e.getItemInHand(), "material").name();
			  String product = Extractor.getExtractorItemData(e.getItemInHand(), "product").name();
			  int fuelAmt = 0;
			  int productAmt = 0;
			  int materialAmt = 0;
			  Database.saveExtractor(x, y, z, world, materialTypes, product, fuelAmt, productAmt, materialAmt, e.getPlayer().getName());
		  }
	  }
	  @EventHandler(priority=EventPriority.HIGH)
	  public void onClick(PlayerInteractEvent e){
		  Player p = e.getPlayer();
		  if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK) && e.getClickedBlock().getState() instanceof Furnace){
			  if(Extractor.isExtractor(e.getClickedBlock().getLocation())){
				  Location l = e.getClickedBlock().getLocation();
				  inCmd.put(p.getName(), l);
				  Chat.sendMessage(p, "Extractor Selected.");
				  e.setCancelled(true);
			  }
		  }
	  }
	  @EventHandler(priority=EventPriority.HIGH)
	  public void onTransfer(InventoryMoveItemEvent e){
		  Inventory source = e.getSource();
		  Inventory dest = e.getDestination();
		  if(source.getName().contains("Extractor")){
			  e.setCancelled(true);
		  }
		  if(dest.getName().contains("Extractor")){
			  e.setCancelled(true);
		  }
	  }
	  @EventHandler(priority=EventPriority.HIGHEST)
	  public void onSmelt(FurnaceSmeltEvent e){
		  Location l = e.getBlock().getLocation();
		  if(Extractor.isExtractor(l)){
			  Material material = Extractor.getMaterialType(l);
			  Material product = Extractor.getProductType(l);
			  Furnace f = (Furnace) e.getBlock().getState();
			  FurnaceInventory inv = f.getInventory();
			  if(Extractor.getMaterialCost(material, product) > Extractor.getMaterialAmt(l)){
				  inv.setSmelting(null);
				  e.setCancelled(true);
				  return;
			  }
			  if(Extractor.getFuelAmt(l) <= 0){
				  inv.setSmelting(null);
				  e.setCancelled(true);
				  return;
			  }
			  int x = e.getBlock().getX();
			  int y = e.getBlock().getY();
			  int z = e.getBlock().getZ();
			  String world = e.getBlock().getWorld().getName();
			  int iterations = inv.getResult().getAmount();
			  if(Extractor.getAmtIterations(Extractor.getMaterialType(l), Extractor.getProductType(l)) <= inv.getResult().getAmount()){
				  inv.setResult(null);
				  inv.setSmelting(new ItemStack(Material.IRON_ORE, 64));
				  inv.setFuel(new ItemStack(Material.COAL, 64));
				  int fuelAmt = Extractor.getFuelAmt(l) - 1;
				  int productAmt = Extractor.getProductAmt(l) + Extractor.getProductOutput(material, product);
				  int materialAmt = Extractor.getMaterialAmt(l) - Extractor.getMaterialCost(material, product);
				  Database.saveExtractor(x, y, z, world, material.name(), product.name(), fuelAmt, productAmt, materialAmt, Extractor.getOwner(l).getName());
			  }
		  }
	  }
}
