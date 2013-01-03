package com.github.Skycrawler67.Evril.Gifts;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiftsCommands implements CommandExecutor{

	private Gifts plugin;
	protected Configuration configuration = new Configuration(plugin);
	
	public GiftsCommands(Gifts plugin)
	{
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	{
		Player player = (Player) sender;
		
		if(commandLabel.equalsIgnoreCase("EvrilGift"))
		{
			if(plugin.getConfig().getBoolean("Activate") == true)
			{
				if (player.hasPermission("evril.gift.help"))
				{
					if (args.length == 0)
					{
						player.sendMessage(ChatColor.DARK_GREEN +"/----------++Evril-Gift++----------/");
						player.sendMessage(ChatColor.GOLD + "/EvrilGift " + ChatColor.DARK_GRAY + " - Show this page");
						player.sendMessage(ChatColor.GOLD + "/EvrilGift reload " + ChatColor.DARK_GRAY + " - Reload the Configuration");
						player.sendMessage(ChatColor.GOLD + "/EvrilGift give [amount]" + ChatColor.DARK_GRAY + " - Give you [amount] Gift(s)");
						player.sendMessage(ChatColor.DARK_GREEN +"/----------------------------------/");
					}
					
					if (args.length == 1)
					{
						if (args[0].equalsIgnoreCase("reload"))
						{
							configuration.reloadConfig();
							player.sendMessage(ChatColor.GREEN + "[Evril-Gift] Configuration Reloaded");
						}
						
						else if (args[0].equalsIgnoreCase("help"))
						{
							player.sendMessage(ChatColor.DARK_GREEN +"/----------++Evril-Gift++----------/");
							player.sendMessage(ChatColor.GOLD + "/EvrilGift (help)" + ChatColor.DARK_GRAY + " - Show this page");
							player.sendMessage(ChatColor.GOLD + "/EvrilGift reload " + ChatColor.DARK_GRAY + " - Reload the Configuration");
							player.sendMessage(ChatColor.GOLD + "/EvrilGift give [amount]" + ChatColor.DARK_GRAY + " - Give you [amount] Gift(s)");
							player.sendMessage(ChatColor.DARK_GREEN +"/----------------------------------/");
						}
						
						else if (args[0].equalsIgnoreCase("give"))
						{
							if (player.hasPermission("evril.gift.create"))
							{
								player.sendMessage(ChatColor.GOLD + args[0] + "[Evril-Gift] " + ChatColor.DARK_GRAY + "Useage : " + ChatColor.RED + "/EvrilGift give [amount]");
							}
						}
					}
					
					if (args.length == 2)
					{
						if (args[0].equalsIgnoreCase("give"))
						{
							if (player.hasPermission("evril.gift.create"))
							{
								plugin.giveGift(player,args[1]);
								player.sendMessage(ChatColor.GOLD + args[1] + " EvrilGift gived !");
							}
						}
					}
				}
				else if (!player.hasPermission("evril.gift.create"))
				{
					player.sendMessage(ChatColor.RED + "[Evril-Gift] You don't have the permssion to do that");
					player.sendMessage(ChatColor.RED + "Permissions required : " + ChatColor.GREEN + "evril.gift.help");
				}
			}
			return true;
		}
		return false;
	}

}
