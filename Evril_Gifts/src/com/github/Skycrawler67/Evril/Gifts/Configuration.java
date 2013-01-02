package com.github.Skycrawler67.Evril.Gifts;

import java.io.File;
import java.util.Arrays;

public class Configuration
{
	public Gifts plugin;
	
	public Configuration(Gifts plugin)
	{
		this.plugin = plugin;
	}
	
	public void loadConfig()
	{
		File config = new File(plugin.getDataFolder(), "config.yml");
		
		if(!config.exists())
		{
			plugin.getConfig().options().header("Configuration of Evril Gifts");
			plugin.getConfig().set("Activate" , true);
			plugin.getConfig().set("Gift Item", 339);
			plugin.getConfig().set("Gift Name", "Cadeau");
			plugin.getConfig().set("Gift Data", 1111);
			String[] idList = {"267,1,Iron_Sword", "276,1,Diamond_Sword", "283,1,Gold_Sword", "54,10,Chest"};
			plugin.getConfig().set("List of gifts", Arrays.asList(idList));
			plugin.saveConfig();
		}
		else
		{
			plugin.saveConfig();
		}
	}
	
	public void reloadConfig()
	{
		plugin.reloadConfig();
	}
}
