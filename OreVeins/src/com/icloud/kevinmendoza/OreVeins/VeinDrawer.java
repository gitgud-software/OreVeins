package com.icloud.kevinmendoza.OreVeins;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;

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
			//DebugLogger.console("vein is in current chunk");
			ChunkParametric paramet = vein.returnChunkInfo(chunkPoint);
			double t = paramet.p1;
			double tf = paramet.p2;
			int x, y, z;
			if(vein.dt != 0)
			{
				//DebugLogger.console("drawing vein with p1 "+ t +" p2 "+tf+" dt "+ vein.dt+" & mat "+vein.ore);
				Block block;
				while(t<=tf)
				{
					x = vein.p1x + (int)(t*vein.vx) - 16*chunk.getX();
					y = vein.p1y + (int)(t*vein.vy);
					z = vein.p1z + (int)(t*vein.vz)- 16*chunk.getZ();
					if(vein.ore.contains("GOLD"))
					{
						block = this.chunk.getBlock(x, y, z);
						//DebugLogger.console("String matches");
						if(block.getType().compareTo(Material.STONE)==0)
						{
							//DebugLogger.console("making gold at x"+x + " y"+ y + " z"+ z);
							block.setType(Material.GOLD_ORE);
						}
					}
					else if (vein.ore == "IRON")
					{
						if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
						{
							chunk.getBlock(x, y, z).setType(Material.IRON_ORE);
						}
					}
					else if (vein.ore == "COAL")
					{
						if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
						{
							chunk.getBlock(x, y, z).setType(Material.COAL_ORE);
						}
					}
					else if (vein.ore == "LAPIZ")
					{
						if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
						{
							chunk.getBlock(x, y, z).setType(Material.LAPIS_ORE);
						}
					}
					else if (vein.ore == "REDSTONE")
					{
						if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
						{
							chunk.getBlock(x, y, z).setType(Material.REDSTONE_ORE);
						}
					}
					else if (vein.ore == "EMERALD")
					{
						if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
						{
							chunk.getBlock(x, y, z).setType(Material.EMERALD_ORE);
						}
					}
					else if (vein.ore == "DIAMOND")
					{
						if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
						{
							chunk.getBlock(x, y, z).setType(Material.DIAMOND_ORE);
						}
					}
					t = t +vein.dt;
				}
				vein.clearChunk(chunkPoint);
			}
			else
			{
				DebugLogger.console("vein drawing failed. current chunk not in vein chunk list");
			}
		}
		else
		{
			chunk.getBlock(0, 100, 1).setType(Material.GOLD_ORE);
		}
	}
}
	


