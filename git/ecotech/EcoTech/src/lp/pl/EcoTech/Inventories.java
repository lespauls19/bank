package lp.pl.EcoTech;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Inventories {
	public static boolean hasSpaceFor(Player p, ItemStack i, int amt){
	    int invSpaceRequired = (int)Math.ceil(amt / i.getMaxStackSize());
	    int invSpace = 0;
	    for (ItemStack s : p.getInventory()) {
	      if (s == null) {
	        invSpace++;
	      }
	    }
		if(invSpaceRequired < invSpace){
			return true;
		}else{
			return false;
		}
	}
	public static void removeItems(Player player, ItemStack type, int toRemove)
	  {
	    Inventory inv = player.getInventory();
	  
	    int slot = 0;
	    
	    ItemStack retVal = null;
	    while (toRemove > 0)
	    {
	      while ((inv.getItem(slot) == null) || (!inv.getItem(slot).isSimilar(type))) {
	        slot++;
	      }
	      ItemStack stack = inv.getItem(slot);
	      
	      retVal = stack.clone();
	      

	      retVal.setAmount(toRemove);
	      if (stack.getAmount() > toRemove)
	      {
	        stack.setAmount(stack.getAmount() - toRemove);
	        

	        inv.setItem(slot, stack);
	        

	        toRemove = 0;
	      }
	      else
	      {
	        toRemove -= stack.getAmount();
	        

	        inv.removeItem(new ItemStack[] { stack });
	      }
	    }
	    return;
	  }
	public static void giveItems(Player p, ItemStack i, int amtToGive){
		Inventory inv = p.getInventory();
        int slot = 0;
	    while (amtToGive > 0)
	    {
	      while (!(inv.getItem(slot) == null)) {
	  	        slot++;
	      }
    	  if(amtToGive > 63){
    		  i.setAmount(64);
    	  }else{ 
    		  i.setAmount(amtToGive);
    	  }
	        inv.setItem(slot, i);
	        amtToGive -= i.getAmount();
	    }
	    return;
	}
	public static void dropItemsInLocation(Location loc, ItemStack i, int amtToDrop){
	    while (amtToDrop > 0)
	    {
    	  if(amtToDrop > 63){
    		  i.setAmount(64);
    	  }else{ 
    		  i.setAmount(amtToDrop);
    	  }
    	    loc.getWorld().dropItem(loc, i);
	        amtToDrop -= i.getAmount();
	    }
	    return;
	}
}
