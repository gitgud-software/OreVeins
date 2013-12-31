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
			zoffset=zneg*(int)(.5*end*rand.nextDouble() +end*.5);
			xoffset =xneg*(int)Math.sqrt((end*end) - (zoffset*zoffset));
			if(!world.isChunkLoaded(this.x + zoffset, this.z+ xoffset)) //Not currently loaded
			{
				if(world.loadChunk(this.x + zoffset, this.z+ xoffset,false))
				{
					world.unloadChunk(this.x + zoffset, this.z+ xoffset);
				}
				else
				{
					break;
				}
			}
			it++;
			//DebugLogger.console("tried" + it + " to find good chunk");
			if(it > 50)
			{
				return null;
			}
		}
		TwoPoint freechunk = new TwoPoint(x+xoffset, z +zoffset);
		return freechunk;
	}
}
