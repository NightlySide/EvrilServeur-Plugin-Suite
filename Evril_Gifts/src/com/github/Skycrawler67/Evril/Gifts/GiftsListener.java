package com.github.Skycrawler67.Evril.Gifts;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class GiftsListener implements Listener{	
	
	public Gifts plugin;
	
	public GiftsListener(Gifts plugin)
	{
		this.plugin = plugin;
	}
	
	@SuppressWarnings({ "deprecation"})
	@EventHandler(priority = EventPriority.NORMAL)
	public void onUseGift(PlayerInteractEvent e)
	{
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) 
		{
			if(plugin.getConfig().getBoolean("Activate") == true)
			{
				if (e.getPlayer().hasPermission("evril.gift.use"))
				{
					List<String> itemname = new ArrayList<String>();
					List<Integer> itemamount =  new ArrayList<Integer>();;
					Inventory inv = e.getPlayer().getInventory();
					ItemStack[] items1 = inv.getContents();
					boolean clear = false;
					for (ItemStack isc : items1) {
						if (isc == null) {
							clear = true;
						}
					}
					List<String> items = plugin.getConfig().getStringList("List of gifts");
					List<ItemStack> itemStacks = new ArrayList<ItemStack>();
					int Max = items.size();
					int Min = 0;
						
					ItemStack item = e.getPlayer().getItemInHand();
					if (item.getTypeId() == plugin.getConfig().getInt("Gift Item"))
					{
						if (clear == true)
				        {
							int numItem = (int) (Min+(Math.random()*(Max-Min)));
							numItem = (int) Gifts.arrondir(numItem, 1);						
							
							int amount = e.getPlayer().getItemInHand().getAmount();
							e.getPlayer().getItemInHand().setAmount(amount-1);
							if (amount == 1) 
							{
								e.getPlayer().setItemInHand(null);
							}
							e.getPlayer().sendMessage(ChatColor.GOLD + "You've just use one " + plugin.getConfig().getString("Gift Name") + " !");
							
							for (String item1 : items)
							{
							item1.replace(" ", "");
							String[] args = item1.split(",");
							 
							if (args.length == 4)
							itemStacks.add(new ItemStack(Integer.parseInt(args[0]), Integer.parseInt(args[1]), Short.parseShort(args[3])));
							 
							else if (args.length == 3)
							itemStacks.add(new ItemStack(Integer.parseInt(args[0]), Integer.parseInt(args[1])));
							
							itemname.add(args[2].replace("_", " "));
							itemamount.add(Integer.parseInt(args[1]));
							}
							
							e.getPlayer().sendMessage(ChatColor.GREEN + "[Evril-Gift] You've receive : " + itemamount.get(numItem) + " " + itemname.get(numItem));
							e.getPlayer().getInventory().addItem(itemStacks.get(numItem));
							
							e.getPlayer().updateInventory();
				        }
						else
						{
							e.getPlayer().sendMessage(ChatColor.RED + "[Evril-Gift] You inventory is full !");
						}
					}
				}
				else if (!e.getPlayer().hasPermission("evril.gift.use"))
				{
					e.getPlayer().sendMessage(ChatColor.RED + "[Evril-Gift] You don't have the permssion to do that");
					e.getPlayer().sendMessage(ChatColor.RED + "Permissions required : " + ChatColor.GREEN + "evril.gift.use");
				}
			}
		}
	}
}
