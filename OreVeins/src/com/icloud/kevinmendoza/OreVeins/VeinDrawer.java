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
			ChunkParametric paramet = vein.returnChunkInfo(chunkPoint);
			double t = paramet.p1;
			double tf = paramet.p2;
			int x, y, z;
			while(t<=tf)
			{
				x = vein.p1x + (int)(t*vein.vx) - 16*chunk.getX();
				y = vein.p1y + (int)(t*vein.vy);
				z = vein.p1z + (int)(t*vein.vz)- 16*chunk.getZ();
				switch(vein.ore)
				{
				case "GOLD": if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.GOLD_ORE);
					}
				case "IRON":
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.GOLD_ORE);
					}
				case "COAL":
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.GOLD_ORE);
					}
				case "REDSTONE":
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.GOLD_ORE);
					}
				case "LAPIZ":
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.GOLD_ORE);
					}
				case "EMERALD":
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.GOLD_ORE);
					}
				case "DIAMOND":
					if(this.chunk.getBlock(x, y, z).getType().equals(Material.STONE))
					{
						chunk.getBlock(x, y, z).setType(Material.GOLD_ORE);
					}
				}
				t = t +vein.dt;
			}
		}
	}
	

}
