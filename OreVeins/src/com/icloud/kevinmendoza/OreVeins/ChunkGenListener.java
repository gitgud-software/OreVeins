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
		removeOres(event.getChunk());
		//checkForVeins();
		addVeins(event.getChunk());
	}
	private void addVeins(Chunk chunk) 
	{ /*Goal: make a linear vein from this chunk to another chunk, only generating vein 
		that is in this chunk. Save info about vein for generation in another chunk*/
		Random rand = new Random();
		int end = (int)(100*rand.nextDouble());
		int probability = 1; //probaby percentage that it will generate a vein in this chunkilit
		if(end <= probability)
		{
			ChunkFinder finder = new ChunkFinder(chunk);
			end = (int)(30*rand.nextDouble());//the thirty is the max chunk length a vein can be'
			TwoPoint startpoint = new TwoPoint(chunk.getX(),chunk.getZ());
			TwoPoint endpoint = finder.findchunk(chunk.getWorld(), end);
			String ore = new String("GOlD");
			VeinClass vein = new VeinClass(startpoint,endpoint,ore);
			VeinDrawer draw = new VeinDrawer(chunk);
			draw.drawVein(vein);
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
				{
					block = chunk.getBlock(x, y, z);
					if (block.getType() == Material.IRON_ORE || block.getType() == Material.COAL_ORE || block.getType() == Material.GOLD_ORE || block.getType() == Material.LAPIS_ORE || block.getType() == Material.REDSTONE_ORE || block.getType() == Material.DIAMOND_ORE || block.getType() == Material.EMERALD_ORE)
					{
						block.setType(Material.STONE);
					}
				}
			}
		}
	}
	
}
