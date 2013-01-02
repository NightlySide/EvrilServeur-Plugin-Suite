package com.github.Skycrawler67.Evril.Gifts;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Gifts extends JavaPlugin
{
	public final Logger log = Logger.getLogger("Minecraft");
	public static Gifts plugin;
	private static ItemStack gift;
	protected Configuration configuration = new Configuration(this);
	public ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();


	@Override
	public void onEnable()
	{
		PluginDescriptionFile pdf = this.getDescription();
		configuration.loadConfig();
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvents(new GiftsListener(this), this);
		console.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + pdf.getName() + ChatColor.DARK_GRAY + "]" + ChatColor.GREEN + " Version " + pdf.getVersion() + " has been Enabled !");
	}
	
	@Override
	public void onDisable()
	{
		PluginDescriptionFile pdf = this.getDescription();
		console.sendMessage(ChatColor.DARK_GRAY + "[" + ChatColor.AQUA + pdf.getName() + ChatColor.DARK_GRAY + "]" + ChatColor.RED + " has been Disabled !");
	}

	public ItemStack constructGift(String amount) {
		int amountInt = Integer.parseInt(amount);
        ItemStack item = new ItemStack(this.getConfig().getInt("Gift Item"), amountInt);
        ItemMeta meta = (ItemMeta) item.getItemMeta();
        
        meta.setDisplayName(ChatColor.GOLD + this.getConfig().getString("Gift Name"));
        item.setDurability((short) this.getConfig().getInt("Gift Data"));
        item.setItemMeta(meta);
        return item;
    }
	
    public void giveGift(Player player, String amount) {
    	gift = this.constructGift(amount);
        player.getInventory().addItem(gift);
    }
    
    static public double arrondir(double value, int n) {
    	double r = (Math.round(value * Math.pow(10, n))) / (Math.pow(10, n));
    	return r;
    } 
    
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player player = (Player) sender;
		if(commandLabel.equalsIgnoreCase("EvrilGift"))
		{
			if(this.getConfig().getBoolean("Activate") == true)
			{
				if (args.length == 0)
				{
					this.giveGift(player,"1");
					player.sendMessage(ChatColor.GOLD + "EvrilGift gived !");
				}
				if (args.length == 1)
				{
					if (args[0].equalsIgnoreCase("reload"))
					{
						configuration.reloadConfig();
						player.sendMessage(ChatColor.GREEN + "[Evril-Gift] Configuration Reloaded");
					}
					else
					{
						this.giveGift(player,args[0]);
						player.sendMessage(ChatColor.GOLD + args[0] + " EvrilGift gived !");
					}
				}
			}
		}
		return false;
	}
}
