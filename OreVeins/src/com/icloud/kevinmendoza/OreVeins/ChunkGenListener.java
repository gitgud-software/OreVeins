package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;
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
		ArrayList<VeinClass> theveins = addVeins(chunk);
		if(theveins.isEmpty())
			DebugLogger.console("No cigar :(");
		else
			DebugLogger.console("Veins isn't empty!");
		draw(theveins, chunk);
		
		saveVeins(theveins);
	}
	
	private void draw(ArrayList<VeinClass> theveins, Chunk chunk)
	{
		VeinDrawer draw = new VeinDrawer(chunk);
		for(int i =0;i<theveins.size();i++)
		{
			draw.drawVein(theveins.get(i));
		}
	}
	
	private ArrayList<VeinClass> addVeins(Chunk chunk) 
	{ /*Goal: make a linear vein from this chunk to another chunk, only generating vein 
		that is in this chunk. Save info about vein for generation in another chunk*/
		Random rand = new Random();
		ArrayList<VeinClass> theVeins = new ArrayList<VeinClass>();
		//DebugLogger.console("making new veins");//probability percentage that it will generate a vein in this chunk
		if(rand.nextInt(100) <= 50)
		{
			//DebugLogger.console("probability dictates that I will have my vein");
			VeinClass vein = addNewVein(chunk,rand);
			if(vein!=null)
			{
				theVeins.add(vein);
			}
		}
		//DebugLogger.console("adding old veins");
		VeinChunkReadWrite RWObj = new VeinChunkReadWrite();
		TwoPoint theChunk = new TwoPoint(chunk.getX(),chunk.getZ());
		ArrayList<Integer> veinIDList = RWObj.readChunks(theChunk);
		if(veinIDList!=null)
		{
			for(int i =0; i<veinIDList.size();i++)
			{
				VeinClass othervein = new VeinClass();
				othervein = RWObj.readVein(veinIDList.get(i));
				if(othervein!=null)
				{
					theVeins.add(othervein);
				}
			}
		}
		return theVeins;
	}

	private VeinClass addNewVein(Chunk chunk, Random rand)
	{
		ChunkFinder finder = new ChunkFinder(chunk);
		int end = (int)(10*rand.nextDouble());//the thirty is the max chunk length a vein can be'
		TwoPoint startpoint = new TwoPoint(chunk.getX(),chunk.getZ());
		TwoPoint endpoint =  finder.findchunk(chunk.getWorld(), end);
		VeinChunkReadWrite RWObj = new VeinChunkReadWrite();
		int id=1;
		//DebugLogger.console("fetching vein ID");
		while(true)
		{
			if(RWObj.readVein(id)==null)
			{
				break;
			}
			id++;
		}
		//DebugLogger.console("fetched ID, making new Vein Object");
		if(endpoint != null)
		{
			String ore = new String("GOLD");
			VeinClass vein = new VeinClass(startpoint,endpoint,ore,id);
		//	DebugLogger.console("Vein Object created, passing it up");
			return vein;
		}
		else
		{
			//DebugLogger.console("Vein Object Failed, passing null");
			return null;
		}
	}

	private void saveVeins(ArrayList<VeinClass> vein)
	{
		if(vein!=null && !vein.isEmpty())
		{
			DebugLogger.console("vein is being saved?");
			VeinChunkReadWrite RWObject = new VeinChunkReadWrite();
			for(int i =0;i<vein.size();i++)
			{
				DebugLogger.console("vein chunk span?" + vein.get(i).chunkInfo.size());
				RWObject.writeVein(vein.get(i));
			}
		}
		DebugLogger.console("arrayList was empty");
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
