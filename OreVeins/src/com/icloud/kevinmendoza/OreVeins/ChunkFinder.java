package com.icloud.kevinmendoza.OreVeins;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.World;

public class ChunkFinder 
{
	public int x;
	public int z;
	public ChunkFinder(Chunk chunk)
	{
		this.x = chunk.getX();
		this.z = chunk.getZ();
	}
	public TwoPoint findchunk(World world, int end) 
	{
		int xoffset;
		int zoffset;
		int xneg;
		int zneg;
		Random rand = new Random();
		int it=0;
		while(true)
		{
			if(rand.nextBoolean())
			{
				xneg = 1;
			}
			else
			{
				xneg = -1;
			}
			if(rand.nextBoolean())
			{
				zneg = 1;
			}
			else
			{
				zneg = -1;
			}
			zoffset=zneg*(int)(end*rand.nextDouble());
			xoffset =xneg*(int)Math.sqrt((end*end) - (zoffset*zoffset));
			if(!world.isChunkLoaded(x + zoffset, z+ xoffset)) //Not currently loaded
			{
				Boolean exists = world.loadChunk(x + zoffset, z+ xoffset,false);
				if(exists)
				{
					world.unloadChunk(x + zoffset, z+ xoffset);
				}
				else
				{
					break;
				}
			}
			it++;
			//DebugLogger.console("tried" + it + " to find good chunk");
			if(it > 100)
			{
				return null;
			}
		}
		TwoPoint freechunk = new TwoPoint(x+xoffset, z +zoffset);
		return freechunk;
	}
}
