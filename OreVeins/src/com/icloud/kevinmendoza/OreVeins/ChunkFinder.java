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
		Random rand = new Random();
		int it=0;
		double theta;
		int x,z,radius;
		while(true)
		{
			radius = rand.nextInt(end+1);
			theta = ((double)rand.nextInt(628)-314)/100;	
			x = (int)(radius*Math.cos(theta));
			z = (int)(radius*Math.sin(theta));
			if(!world.isChunkLoaded(this.x + x, this.z+ z)) //Not currently loaded
			{
				if(world.loadChunk(this.x + x, this.z+ z,false))
				{
					world.unloadChunk(this.x + x, this.z+ z);
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
		TwoPoint freechunk = new TwoPoint(this.x+x, this.z +z);
		return freechunk;
	}
}
