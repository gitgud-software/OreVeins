package com.icloud.kevinmendoza.OreVeins;

import java.util.Random;

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
		Chunk chunk = event.getChunk();
		removeOres(chunk);
		addVeins(chunk);
		removeStone(chunk);
		//event.getChunk().getWorld().refreshChunk(event.getChunk().getX(),event.getChunk().getZ());
	}
	private void addVeins(Chunk chunk) 
	{ /*Goal: make a linear vein from this chunk to another chunk, only generating vein 
		that is in this chunk. Save info about vein for generation in another chunk*/
		Random rand = new Random();
		int end = (int)(100*rand.nextDouble());
		int probability = 50; //probability percentage that it will generate a vein in this chunk
		if(end <= probability)
		{
			//DebugLogger.console("lets make a vein");
			ChunkFinder finder = new ChunkFinder(chunk);
			//DebugLogger.console("made chunk Finder");
			end = (int)(10*rand.nextDouble());//the thirty is the max chunk length a vein can be'
			TwoPoint startpoint = new TwoPoint(chunk.getX(),chunk.getZ());
			//DebugLogger.console("starting chunk is X"+chunk.getX()+" Z"+chunk.getZ());
			TwoPoint endpoint =  finder.findchunk(chunk.getWorld(), end);
			if(endpoint != null)
			{
				//DebugLogger.console("ending chunk is X"+endpoint.x+" Z"+endpoint.z);
				String ore = new String("GOLD");
				VeinClass vein = new VeinClass(startpoint,endpoint,ore);
				//DebugLogger.console("vein object created");
				VeinDrawer draw = new VeinDrawer(chunk);
				//DebugLogger.console("vein drawer initialized");
				draw.drawVein(vein);
				DebugLogger.console("drawing vein");
				
			}
		}
	}
	private void removeStone(Chunk chunk)
	{
		Block block;
		for (int x = 0; x < 16; x++)
		{
			for (int y = 1; y < 128; y++)
			{
				for (int z = 0; z < 16; z++)
				{//getBlock(x, y, z).getType().compareTo(Material.STONE)==0
					block = chunk.getBlock(x, y, z);
					if (block.getType().compareTo(Material.STONE)==0 
							|| block.getType().compareTo(Material.GRAVEL)==0 
							|| block.getType().compareTo(Material.SAND)==0 
							|| block.getType().compareTo(Material.WATER)==0 
							|| block.getType().compareTo(Material.LAVA)==0)
					{
						block.setType(Material.AIR);
					}
				}
			}
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
