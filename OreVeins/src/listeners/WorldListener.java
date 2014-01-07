package listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import populatorClasses.OreReplacer;


public final class WorldListener implements Listener
{

	@EventHandler
	public void onInit(WorldInitEvent event) 
	{
		OreReplacer pop = new OreReplacer();
		if(!Bukkit.getWorlds().get(0).getPopulators().contains(pop))
		{
			Bukkit.getWorlds().get(0).getPopulators().add(pop);
		}
	}
	
}
