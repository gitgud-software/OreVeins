package com.icloud.kevinmendoza.OreVeins;
import java.util.ArrayList;
import java.util.HashMap;
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
		if(event.getWorld().getName().equals(Bukkit.getWorlds().get(0).getName()))
		{
			Chunk chunk = event.getChunk();//gets the current chunk info from the event
			removeOres(chunk);//first loops through the chunk gotten and removes all the default ores
			addOres(chunk);//adds in the custom defined ores, the point of this plugin
		}
		if(VeinChunkReadWrite.loadedMap!=null)
		{
			DebugLogger.console("Drawing veins in loaded chunks");
			drawOtherOres(VeinChunkReadWrite.loadedMap);
			VeinChunkReadWrite.loadedMap=null;
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
		VeinDrawer.drawVein(addedOres,chunk);
	}

	}
	
	
	private String[][][] getOres(Chunk chunk)
	{
		String currentChunkKey = LineDrawingUtilityClass.convertToKey(chunk.getX(), chunk.getZ());
		String[][][] oldOres = VeinChunkReadWrite.readChunks(currentChunkKey,true);

		String[][][] newVeins = getNewVeins(chunk);
		VeinChunkReadWrite.deleteChunkInfo(currentChunkKey,true);
		if(newVeins!=null)
		{
			if(oldOres!=null)
			{
				for(int x=0;x<16;x++)
				{
					for(int z=0;z<16;z++)
					{
						for(int y=2;y<128;y++)
						{
							if(oldOres[x][y][z]==null 
									|| oldOres[x][y][z].contains("COAL"))
							{
								if(newVeins[x][y][z]!=null)
								{//DebugLogger.console(" put at "+x+ " "+z);
									oldOres[x][y][z] = "GOLD";
								}
							}
						}
					}
				}
				return oldOres;
			}
			else
			{
				return newVeins;
			}
		}
		else
		{
			return oldOres;
		}
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

	private void removeOres(Chunk chunk)
	{
		Block block;
		for (int x = 0; x < 16; x++)
		{
			for (int y = 1; y < 128; y++)
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
