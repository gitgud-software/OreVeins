package com.icloud.kevinmendoza.OreVeins;

import org.bukkit.Chunk;
import org.bukkit.Material;

public class VeinDrawer {
	public Chunk chunk;
	public VeinDrawer(Chunk chunk)
	{
		this.chunk = chunk;
	}
	public void drawVein(VeinClass vein) 
	{
		TwoPoint chunkPoint = new TwoPoint(this.chunk.getX(),this.chunk.getZ());
		if(vein.contains(chunkPoint))
		{
			DebugLogger.console("vein is in current chunk");
			ChunkParametric paramet = vein.returnChunkInfo(chunkPoint);
			double t = paramet.p1;
			double tf = paramet.p2;
			int x, y, z;
			DebugLogger.console("drawing vein with parameter1 "+ t +" parameter2 "+tf+" and dt "+ vein.dt);
			//System.out.println("parameter 1 "+ t + " parameter2 " + tf + " dt "+ vein.dt);
			while(t<=tf)
			{
				if(vein.dt == 0)
				{
					chunk.getBlock(0, 100, 1).setType(Material.GOLD_ORE);
					break;
				}
				x = vein.p1x + (int)(t*vein.vx) - 16*chunk.getX();
				y = vein.p1y + (int)(t*vein.vy);
				z = vein.p1z + (int)(t*vein.vz)- 16*chunk.getZ();
				if(vein.ore == "GOLD")
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.GOLD_ORE);
					}
				else if (vein.ore == "IRON")
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.IRON_ORE);
					}
				else if (vein.ore == "COAL")
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.COAL_ORE);
					}
				else if (vein.ore == "LAPIZ")
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.LAPIS_ORE);
					}
				else if (vein.ore == "REDSTONE")
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.REDSTONE_ORE);
					}
				else if (vein.ore == "EMERALD")
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.EMERALD_ORE);
					}
				else if (vein.ore == "DIAMOND")
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.DIAMOND_ORE);
					}
				}
				t = t +vein.dt;
			}
		}
	}
	


