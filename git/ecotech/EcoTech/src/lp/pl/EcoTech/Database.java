package lp.pl.EcoTech;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class Database {

	public static FileConfiguration config = Main.instance.getConfig();
	
	public static ArrayList<String> getExtractors(){
		ArrayList<String> list = (ArrayList<String>) config.getConfigurationSection("extractor").getKeys(false);
		return list;
	}
	public static void saveExtractor(int x, int y, int z, String world, 
			String materialTypes, String product, int fuelAmt, int productAmt, int materialAmt, String owner){
		    String data = materialTypes + ";" + product + ";" + fuelAmt + ";" + productAmt + ";" + materialAmt + ";" + owner;
		    String key = x + "," + y + "," + z + "," + world;
		config.set("extractor." + key, data);	
		Main.instance.saveConfig();
	}
	public static void removeExtractor(int x, int y, int z, String world){
		    String key = x + "," + y + "," + z + "," + world;
		if(config.contains("extractor." + key)){
			config.set("extractor." + key, null);
			Main.instance.saveConfig();
		}
	}
	public static boolean contains(int x, int y, int z, String world){
	    String key = x + "," + y + "," + z + "," + world;
	if(config.contains("extractor." + key) && !(config.getString("extractor." + key).equals(null))){
		return true;
	}
	return false;
}
	
}
