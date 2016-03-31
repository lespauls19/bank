package lp.pl.EcoTech;



import lp.pl.EcoTechCommands.ExtractorCmd;
import lp.pl.EcoTechCommands.GiveExtractor;
import lp.pl.EcoTechCommands.Recharge;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin{
	public static Main instance;
	public void onEnable(){
		instance = this;
		getServer().getPluginManager().registerEvents(new Listeners(this), this);
		Recipes.setRecipes();
	}
	
    public void onDisable(){
    	
    }
    
    public boolean onCommand(CommandSender sender, Command command,
            String label, String[] args) {
		if((sender instanceof ConsoleCommandSender)){
			if(args[0].equalsIgnoreCase("giveextractor")){
			return GiveExtractor.Execute(sender, command, label, args);
		}
		}
    Player player = (Player) sender;
    if(label.equalsIgnoreCase("tech")){
    	if(player.hasPermission("ecotech.use")){
    		if(args.length > 0){
    			if(args[0].equalsIgnoreCase("recharge")){
    			return Recharge.Execute(sender, command, label, args);
    			}
    			if(args[0].equalsIgnoreCase("extractor") || args[0].equalsIgnoreCase("ext")){
    		    return ExtractorCmd.Execute(sender, command, label, args);
    			}
    }
			Chat.sendAllFormat(player);
    	}
    	}else{
    		Chat.noPermission(player);
    	}
    	return true;
    }
    
}
