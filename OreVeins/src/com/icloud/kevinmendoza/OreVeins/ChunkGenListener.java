package com.icloud.kevinmendoza.OreVeins;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;

public final class ChunkGenListener implements Listener 
{
	@EventHandler
	public void onGenerate(ChunkPopulateEvent event) 
	{
		DebugLogger.console("onGenerate");
		if(event.getWorld().getName().equals(Bukkit.getWorlds().get(0).getName()))
		{
			Chunk chunk = event.getChunk();//gets the current chunk info from the event
			removeOres(chunk);//first loops through the chunk gotten and removes all the default ores
			addOres(chunk);//adds in the custom defined ores, the point of this plugin
		}
	}
	
	private void addOres(Chunk chunk)
	{
		DebugLogger.console("addOres");
		//first, get already added veins
	String[][][] addedOres =getOres(chunk);
	if(addedOres!=null)
	{
		DebugLogger.console("Drawing Veins");
		VeinDrawer.drawVein(addedOres,chunk);
	}
	else
		DebugLogger.console("isNull");
	}
	
	
	private String[][][] getOres(Chunk chunk)
	{
		DebugLogger.console("getOres");
		String chx = new Integer(chunk.getX()).toString();
		String chz = new Integer(chunk.getZ()).toString();
		String currentChunkKey = chx+":"+chz;
		String[][][] oldOres = VeinChunkReadWrite.readChunks(currentChunkKey);
		ArrayList<ThreePoint> newVeins = getNewVeins(chunk);
		if(newVeins!=null)
		{
			if(oldOres!=null)
			{
				for(int i=0;i<newVeins.size();i++)
				{
					ThreePoint testPoint = newVeins.get(i);
					if(oldOres[testPoint.x][testPoint.y][testPoint.z]==null 
							|| oldOres[testPoint.x][testPoint.y][testPoint.z].contains("COAL"))
					{
						oldOres[testPoint.x][testPoint.y][testPoint.z] = "GOLD";
					}
			
				}
				return oldOres;
			}
			else
			{
				return null;
			}
		}
		else
		{
			return oldOres;
		}
	}
	
	private ArrayList<ThreePoint> getNewVeins(Chunk chunk) 
	{
		DebugLogger.console("getNewVeins");
		Random rand = new Random();
		if(rand.nextInt(10)==0)
		{
			ThreePoint start = new ThreePoint();
			start.x+=16*chunk.getX();
			start.z+=16*chunk.getZ();
			VeinSystem newVeinSystem = new VeinSystem("GOLD", start,400/*length of vein*/, 
					100/* branch*/, 30 /*average grade*/, 100 /*average bonanza*/);
			return newVeinSystem.orePoints;
		}
		else
		{
			return null;
		}
	}

	private void removeOres(Chunk chunk)
	{
		Block block;
		for (int x = 0; x < 16; x++)
		{
			for (int y = 0; y < 128; y++)
			{
				for (int z = 0; z < 16; z++)
				{//getBlock(x, y, z).getType().compareTo(Material.STONE)==0
					block = chunk.getBlock(x, y, z);
					if (block.getType().compareTo(Material.COAL_ORE)==0 
							|| block.getType().compareTo(Material.IRON_ORE)==0 
							|| block.getType().compareTo(Material.GOLD_ORE)==0 
							|| block.getType().compareTo(Material.LAPIS_ORE)==0 
							|| block.getType().compareTo(Material.REDSTONE_ORE)==0 
							|| block.getType().compareTo(Material.DIAMOND_ORE)==0 
							|| block.getType().compareTo(Material.EMERALD_ORE)==0)
					{
						block.setType(Material.STONE);
					}
				}
			}
		}
	}
}
