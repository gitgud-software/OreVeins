package com.icloud.kevinmendoza.OreVeins;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public final class ChunkGenListener implements Listener 
{
	@EventHandler
	public void onGenerate(ChunkPopulateEvent event) 
	{
		if(event.getWorld().getName().equals(Bukkit.getWorlds().get(0).getName()))
		{
			Chunk chunk = event.getChunk();//gets the current chunk info from the event
			//removeOres(chunk);//first loops through the chunk gotten and removes all the default ores
			addOres(chunk);//adds in the custom defined ores, the point of this plugin

			if(VeinChunkReadWrite.loadedMap!=null)
			{
				drawOtherOres(VeinChunkReadWrite.loadedMap);
				VeinChunkReadWrite.loadedMap=null;
			}
		}
	}
	
	private void drawOtherOres(HashMap<String, String[][][]> loadedMap) 
	{
		String delims = "[:]";
		int x , z;
		Chunk chunk;
		for(String entry: loadedMap.keySet())
		{
			String[] tokens = entry.split(delims);
			x = Integer.parseInt(tokens[0]);
			z = Integer.parseInt(tokens[1]);
			if(loadedMap.get(entry)!=null)
			{
				DebugLogger.console("Drawing veins in loaded chunks " + entry);
				chunk = Bukkit.getWorlds().get(0).getChunkAt(x, z);
				VeinDrawer.drawVein(loadedMap.get(entry), chunk);
			}
		}
	}

	private void addOres(Chunk chunk)
	{
		//DebugLogger.console("addOres");
		//first, get already added veins
	String[][][] addedOres =getOres(chunk);
	if(addedOres!=null)
	{
		DebugLogger.console("Drawing old and new ores " + chunk.getX() + ":" + chunk.getZ());
		VeinDrawer.drawVein(addedOres,chunk);
	}

	}
	
	private String[][][] getOres(Chunk chunk)
	{
		String[][][] newVeins = getNewVeins(chunk);
				return newVeins;
	}
	
	private String[][][] getNewVeins(Chunk chunk) 
	{
		Random rand = new Random();
		if(rand.nextInt(10)==0)
		{
			ThreePoint start = new ThreePoint();
			start.x+=16*chunk.getX();
			start.z+=16*chunk.getZ();
			start.y = 1;
						
			Vein newVeinSystem = new Vein(start, 40);
			return newVeinSystem.currentstuff;
		}
		else
		{
			return null;
		}
	}

}
