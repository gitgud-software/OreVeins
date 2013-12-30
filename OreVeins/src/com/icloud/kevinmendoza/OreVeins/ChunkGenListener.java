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
		addOres(chunk);
		removeStone(chunk);
		//event.getChunk().getWorld().refreshChunk(event.getChunk().getX(),event.getChunk().getZ());
	}
	
	private void addOres(Chunk chunk)
	{
		DebugLogger.console("adding veins");
		String[][][] chunkveins = addVeins(chunk);
		if(chunkveins==null)
		{
			DebugLogger.console("No cigar :(");
		}
		else
		{
			DebugLogger.console("drawing veins");
			draw(chunkveins, chunk);
		}
	}
	
	private void draw(String[][][] theveins, Chunk chunk)
	{
		VeinDrawer draw = new VeinDrawer(chunk);
		draw.drawVein(theveins);
		
	}
	
	private String[][][] addVeins(Chunk chunk) 
	{ /*Goal: make a linear vein from this chunk to another chunk, only generating vein 
		that is in this chunk. Save info about vein for generation in another chunk*/
		Random rand = new Random();
		VeinChunkReadWrite RWObj = new VeinChunkReadWrite();
		String xval = new Integer(chunk.getX()).toString();
		String zval = new Integer(chunk.getZ()).toString();
		String key = xval + ":" + zval;
		String[][][] theVeins = RWObj.readChunks(key);
		//DebugLogger.console("making new veins");//probability percentage that it will generate a vein in this chunk
		if(rand.nextInt(100) <= 50)
		{
			//DebugLogger.console("probability dictates that I will have my vein");
			String[][][] vein = addNewVein(chunk,rand);
			if(vein!=null)
			{
				for(int x=0;x<16;x++)
				{
					for(int y=1;y<128;y++)
					{
						for(int z=0;z<16;z++)
						{
							if(!theVeins[x][y][z].contains("COAL"))
							{
								theVeins[x][y][z] = vein[x][y][z];
							}
						}
					}
				}
			}
		}
		//DebugLogger.console("adding old veins");
	return theVeins;
	}

	private String[][][] addNewVein(Chunk chunk, Random rand)
	{
		ChunkFinder finder = new ChunkFinder(chunk);
		int end = (int)(10*rand.nextDouble());//the thirty is the max chunk length a vein can be'
		TwoPoint startpoint = new TwoPoint(chunk.getX(),chunk.getZ());
		TwoPoint endpoint =  finder.findchunk(chunk.getWorld(), end);
		if(endpoint!=null)
		{
			String ore = new String("GOLD");
			Vein vein = new Vein(startpoint,endpoint,ore);
			String[][][] list = vein.returnAndPartitionBlocks(chunk);
			if(list==null)
				return null;
			else
				return list;
		}
		else
		{
			return null;
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
