package listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.event.world.WorldLoadEvent;
import populatorClasses.OrePopulator;


public final class WorldListener implements Listener
{

	@EventHandler
	public void onInit(WorldInitEvent event) 
	{
		OrePopulator pop = new OrePopulator();
		if(!Bukkit.getWorlds().get(0).getPopulators().contains(pop)) 
			Bukkit.getWorlds().get(0).getPopulators().add(pop);
	}
	
	@EventHandler
	public void onLoad(WorldLoadEvent event)
	{
		OrePopulator pop = new OrePopulator();
		if(!Bukkit.getWorlds().get(0).getPopulators().contains(pop)) 
			Bukkit.getWorlds().get(0).getPopulators().add(pop);
	}
}
