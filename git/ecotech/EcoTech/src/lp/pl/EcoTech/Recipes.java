package lp.pl.EcoTech;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;

public class Recipes {

	 public static ShapelessRecipe fuelRodRecipe;
	 public static ShapelessRecipe energyCellRecipe;
	public static void setRecipes(){
		ItemStack fuelRod = new ItemStack(Material.BLAZE_ROD);
		ItemMeta meta = fuelRod.getItemMeta();
		meta.setDisplayName(ChatColor.AQUA + "Fuel Rod");
		fuelRod.setItemMeta(meta);
		fuelRodRecipe = new ShapelessRecipe(fuelRod);
		fuelRodRecipe.addIngredient(3, Material.SULPHUR);
		fuelRodRecipe.addIngredient(1, Material.BLAZE_ROD);
		fuelRodRecipe.addIngredient(5, Material.GOLD_NUGGET);
		Main.instance.getServer().addRecipe(fuelRodRecipe);
		ItemStack energyCell = new ItemStack(Material.getMaterial(2259));
        ItemMeta meta2 = energyCell.getItemMeta();
        meta2.setDisplayName(ChatColor.AQUA + "Energy Cell");
        energyCell.setItemMeta(meta2);
        energyCellRecipe = new ShapelessRecipe(energyCell);
        energyCellRecipe.addIngredient(6, Material.REDSTONE_BLOCK);
        energyCellRecipe.addIngredient(2, Material.GOLD_INGOT);
        energyCellRecipe.addIngredient(Material.IRON_INGOT);
        Main.instance.getServer().addRecipe(energyCellRecipe);
        
	}
}
