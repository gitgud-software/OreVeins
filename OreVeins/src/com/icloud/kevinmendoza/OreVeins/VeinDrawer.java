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
	public void drawVein(String[][][] vein) 
	{
		Block block;
		for(int x=0;x<16;x++)
		{
			for(int z=0;z<16;z++)
			{
				for(int y=0;y<16;y++)
				{
					if(vein[x][y][z]!=null)
					{
						block = this.chunk.getBlock(x, y, z);
						if(block.getType().compareTo(Material.STONE)==0)
						{
							if(vein[x][y][z].contains("GOLD"))
							{

								block.setType(Material.GOLD_ORE);

							}
							else if (vein[x][y][z].contains("IRON"))
							{

								chunk.getBlock(x, y, z).setType(Material.IRON_ORE);

							}
							else if (vein[x][y][z].contains("COAL"))
							{

								chunk.getBlock(x, y, z).setType(Material.COAL_ORE);

							}
							else if (vein[x][y][z].contains("LAPIZ"))
							{
								chunk.getBlock(x, y, z).setType(Material.LAPIS_ORE);

							}
							else if (vein[x][y][z].contains("REDSTONE"))
							{

								chunk.getBlock(x, y, z).setType(Material.REDSTONE_ORE);

							}
							else if (vein[x][y][z].contains("EMERALD"))
							{

								chunk.getBlock(x, y, z).setType(Material.EMERALD_ORE);

							}
							else if (vein[x][y][z].contains("DIAMOND"))
							{

								chunk.getBlock(x, y, z).setType(Material.DIAMOND_ORE);

							}
						}
					}
				}
			}
		}
	}
}



