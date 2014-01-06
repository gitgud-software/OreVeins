package com.icloud.kevinmendoza.OreVeins;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;

public final class WorldListener implements Listener
{

	@EventHandler
	public void onInit(WorldInitEvent event) 
	{
		Bukkit.getWorlds().get(0).getPopulators().add(new ReplaceOresWithStone());
	}
	
	@EventHandler
	public void onLoad(WorldLoadEvent event)
	{
		Bukkit.getWorlds().get(0).getPopulators().add(new ReplaceOresWithStone());
	}
}
