package com.icloud.kevinmendoza.OreVeins;
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
			//removeStone(chunk);//removes all the stone from this chunk for debugging purposes
			//event.getChunk().getWorld().refreshChunk(event.getChunk().getX(),event.getChunk().getZ());
		}
	}
	
	private void addOres(Chunk chunk)
	{
		//DebugLogger.console("adding veins");
		String[][][] chunkveins = addVeins(chunk);//adds a 3x3 array String object containing info
		// on which ores go where
		if(chunkveins!=null)//if chunkveins is empty, well then fuck that
		{
		//chunkveins is not null! there's info in here!
			DebugLogger.console("drawing veins");
			VeinDrawer draw = new VeinDrawer(chunk);//make a veindrawer object at the current chunk
			draw.drawVein(chunkveins);//draw the vein 
		}
	}
	
	private String[][][] addVeins(Chunk chunk) 
	{ 
		
		Random rand = new Random();//create new random object..
		VeinChunkReadWrite RWObj = new VeinChunkReadWrite();//create the read,write object to read and write
		//vein info from file
		String xval = new Integer(chunk.getX()).toString();
		String zval = new Integer(chunk.getZ()).toString();
		String key = xval + ":" + zval;
		//^^ create the string key used to retrieve pertinent chunk information
		String[][][] theVeins = RWObj.readChunks(key);//<<-this returns any information 
		//probability percentage that it will generate a vein in this chunk
		//DebugLogger.console("probability dictates that I will have my vein");
		String[][][] vein = addNewVein(chunk,rand);//calls the function to create a new vein!
		//returns the 3x3 pertinent array
		if(vein!=null)//only if the vein object was successfully created will it be added
		{				//to the current vein object
			if(theVeins!=null)//if there are veins from file, add to them
			{
				//DebugLogger.console("uhoh.. its not reading the objects back");
				for(int x=0;x<16;x++)//loop through all possible chunk coordinates
				{
					for(int y=1;y<128;y++)
					{
						for(int z=0;z<16;z++)
						{
							if(theVeins[x][y][z]==null || theVeins[x][y][z].contains("COAL"))
							{
								theVeins[x][y][z] = vein[x][y][z];//combine the ores into the array
								//but make sure any coal veins are crosscut, as is tradition 8D
							}
						}
					}
				}
			}
		}
		RWObj.deleteChunkInfo(key);
		//DebugLogger.console("adding old veins");
	return theVeins;//return veins, null or not
	}

	private String[][][] addNewVein(Chunk chunk, Random rand)
	{
		int goldProb = 5;
		ChunkFinder finder = new ChunkFinder(chunk,rand);//find a nice empty chunk to settle down in 
		//and have kids (aka for the endpoint of the line)
		int end = 10;//the ten is the max chunk length a vein can be
		//later on 10 will be replaced by something a little more meaningful
		TwoPoint startpoint = new TwoPoint(chunk.getX(),chunk.getZ());//create a twopoint start point
		TwoPoint endpoint =  finder.findchunk(end);//get endpoint from the
		//chunk finder
		if(endpoint!=null)//if an endpoint could not be found, terminate trying to make a vein
		{
			String[][][] gold = null,iron,redstone,lapiz,coal,diamond,emerald,bif;
			if(rand.nextInt(goldProb)==0)
			{	//veins have the fieldsVein startChunk, endChunk,oretype, (emerald only secondary grade)
				//random object, primary grade, branch frequency, bonanza frequency, cross sectional area)
				String ore = new String("GOLD");//make a gold vein! GOOOOLLLDDDD
				Vein vein = new Vein(startpoint,endpoint,ore,null,rand, rand.nextInt(1)+1, rand.nextInt(20)+40, rand.nextInt(5)+10,rand.nextInt(10)+1);//give it the stuff it needs
				gold = vein.returnAndPartitionBlocks(chunk);//this is a powerhouse method
			}
			/*if(rand.nextInt(coalProb)==0)
			{
				String ore = new String("COAL");//make a gold vein! GOOOOLLLDDDD
				Vein vein = new Vein(startpoint,endpoint,ore,null,rand, grade, null, bonanza);//give it the stuff it needs
				 coal = vein.returnAndPartitionBlocks(chunk);//this is a powerhouse method
			}
			if(rand.nextInt(ironProb)==0)
			{
				String ore = new String("IRON");//make a gold vein! GOOOOLLLDDDD
				Vein vein = new Vein(startpoint,endpoint,ore,null,rand, grade, branch, bonanza);//give it the stuff it needs
				iron = vein.returnAndPartitionBlocks(chunk);//this is a powerhouse method
			}
			if(rand.nextInt(ironProb)==0)
			{
				String ore = new String("BIF");//make a gold vein! GOOOOLLLDDDD
				Vein vein = new Vein(startpoint,endpoint,ore,null,rand, grade, null, bonanza);//give it the stuff it needs
				bif = vein.returnAndPartitionBlocks(chunk);//this is a powerhouse method
			}
			if(rand.nextInt(redProb)==0)
			{
				String ore = new String("REDSTONE");//make a gold vein! GOOOOLLLDDDD
				Vein vein = new Vein(startpoint,endpoint,ore,null,rand, grade, branch, bonanza);//give it the stuff it needs
				redstone = vein.returnAndPartitionBlocks(chunk);//this is a powerhouse method
			}
			if(rand.nextInt(lapProb)==0)
			{
				String ore = new String("LAPIZ");//make a gold vein! GOOOOLLLDDDD
				Vein vein = new Vein(startpoint,endpoint,ore,null,rand, grade, null, bonanza);//give it the stuff it needs
				lapiz = vein.returnAndPartitionBlocks(chunk);//this is a powerhouse method
			}
			if(rand.nextInt(emProb)==0)
			{
				String ore = new String("EMERALD");//make a gold vein! GOOOOLLLDDDD
				Vein vein = new Vein(startpoint,endpoint,ore,null,rand, grade, branch, bonanza);//give it the stuff it needs
				emerald = vein.returnAndPartitionBlocks(chunk);//this is a powerhouse method
			}
			if(rand.nextInt(diaProb)==0)
			{
				String ore = new String("DIAMOND");//make a gold vein! GOOOOLLLDDDD
				Vein vein = new Vein(startpoint,endpoint,ore,null,rand, grade, null, bonanza);//give it the stuff it needs
				diamond = vein.returnAndPartitionBlocks(chunk);
			}*/
			/*for(int x=0;x<16;x++)
			{
				for(int y=2;y<128;y++)
				{
					for(int z=0;z<16;z++)
					{
						//heirarchy here is Iron>Gold>Redstone>Emerald>Diamond>Coal>BIF
						
						
					}
				}
			}*/
				return gold;
		}
		else
		{
			return null;
		}
	}
	
	/*private void removeStone(Chunk chunk)
	{
		Block block;
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				for (int y = 1; y < 60; y++)
				{//getBlock(x, y, z).getType().compareTo(Material.STONE)==0
					block = chunk.getBlock(x, y, z);
					if (block.getType().compareTo(Material.STONE)==0 
							|| block.getType().compareTo(Material.GRAVEL)==0 
							|| block.getType().compareTo(Material.SAND)==0 
							|| block.getType().compareTo(Material.WATER)==0 
							|| block.getType().compareTo(Material.LAVA)==0
							|| block.getType().compareTo(Material.DIRT)==0)
					{
						block.setType(Material.AIR);
					}
				}
			}
		}
	}*/
	
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
