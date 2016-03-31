package lp.pl.EcoTechCommands;

import lp.pl.EcoTech.Chat;
import lp.pl.EcoTech.Extractor;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class GiveExtractor {

	public static boolean Execute(CommandSender sender, Command command,
            String label, String[] args){
		if(args.length <= 1){
			return false;
		}
		Material material = Material.getMaterial(args[1]);
		Material product = Material.getMaterial(args[2]);
		Player recipient = Bukkit.getPlayer(args[3]);
		int qty = Integer.parseInt(args[4]);
		ItemStack item = Extractor.getExtractorItem(material, product); 
		item.setAmount(qty);
		recipient.getInventory().addItem(item);
		return true;
	}
}
