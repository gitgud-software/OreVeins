package com.icloud.kevinmendoza.OreVeins;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;

public class ChunkFinder 
{
	public int x;
	public int z;
	private Random rand;
	public ChunkFinder(Chunk chunk, Random rand)
	{
		this.rand = rand;
		this.x = chunk.getX();
		this.z = chunk.getZ();
	}
	public ChunkFinder(TwoPoint point, Random rand)
	{
		this.rand = rand;
		this.x = point.x;
		this.z = point.z;
	}
	public TwoPoint findchunk(int end) 
	{
		int it=0;
		double theta;
		int x,z,radius;
		while(true)
		{
			radius = end;
			theta = ((double)this.rand.nextInt(628)-314.0)/100.0;	
			x = (int)(radius*Math.cos(theta));
			z = (int)(radius*Math.sin(theta));
			if(!Bukkit.getWorlds().get(0).isChunkLoaded(this.x + x, this.z+ z)) //Not currently loaded
			{
				if(Bukkit.getWorlds().get(0).loadChunk(this.x + x, this.z+ z,false))
				{
					Bukkit.getWorlds().get(0).unloadChunk(this.x + x, this.z+ z);
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
