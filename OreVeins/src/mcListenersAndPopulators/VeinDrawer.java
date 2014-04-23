/*******************************************************************************
 * OreVeins realistic ore distribution plugin
 * Copyright (C) 2014  Kevin Mendoza
 * kevinmendoza@mac.com
 * Major Contributors: Kevin Song, Alex Lin, Darren Chang, Drew Parliament, Zeno Hao
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package mcListenersAndPopulators;

import geometryClasses.ThreePoint;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

/*this vein drawer class draws any ores within a 3d string object array
 * such simple, so block, much craft, shine daimond!
 * 
 */
public class VeinDrawer 
{
	private static Chunk thechunk;
	private static Random rand = new Random();
	public static void drawVein(String[][][] vein, Chunk chunk) 
	{
		//DebugLogger.console("drawingOres");
		thechunk = chunk;
		Block block;
		ThreePoint point;
		for(int x=0;x<16;x++)
		{
			for(int z=0;z<16;z++)
			{
				for(int y=2;y<128;y++)
				{
					if(vein[x][y][z]!=null)
					{
						point = new ThreePoint(x,y,z);
						point.x+=chunk.getX()*16;
						point.z+=chunk.getZ()*16;
						block = chunk.getBlock(x, y, z);
						if(isType("STONE",block))
						{
							setBlock(vein[x][y][z],point);
						}
						else if(isType("GRASS",block))
						{ 
							if(rand.nextInt(10)==1)
								setBlock(vein[x][y][z],point);	
						}
						else if(isType("DIRT",block))
						{
							if(rand.nextInt(3)==1)
							setBlock(vein[x][y][z],point);
						}
						else if(isType("GRAVEL",block))
						{
					
							setBlock(vein[x][y][z],point);
						}
						else if(isType("SAND",block))
						{
							setBlock(vein[x][y][z],point);
						}
						/*else if(isType("SANDSTONE",block))
						{
							point.x+=chunk.getX()*16;
							point.z+=chunk.getZ()*16;
							SupergeneEnrichment.RegisterPoints(vein[x][y][z], point);
						}
						else if(isType("AIR",block))
						{
							
						}*/
					}
				}
			}
		}
		chunk.getWorld().refreshChunk(chunk.getX(), chunk.getZ());
	}
	
	private static boolean isType(String type, Block block)
	{
		if(block.getType().compareTo(materialReturn(type))==0)
			return true;
		else
			return false;
	}
	
	private static Material materialReturn(String ore) 
	{
		if(ore.contains("GOLD"))
		{
			return Material.GOLD_ORE;
		}
		else if (ore.contains("IRON"))
		{
			return Material.IRON_ORE;
		}
		else if (ore.contains("COAL"))
		{
			return Material.COAL_ORE;
		}
		else if (ore.contains("LAPIZ"))
		{
			return Material.LAPIS_ORE;
		}
		else if (ore.contains("REDSTONE"))
		{
			return Material.REDSTONE_ORE;
		}
		else if (ore.contains("EMERALD"))
		{
			return Material.EMERALD_ORE;
		}
		else if (ore.contains("DIAMOND"))
		{
			return Material.DIAMOND_ORE;
		}
		else if (ore.contains("AIR"))
		{
			return Material.AIR;
		}
		else if (ore.contains("GRASS"))
		{
			return Material.GRASS;
		}
		else if (ore.contains("DIRT"))
		{
			return Material.DIRT;
		}
		else if (ore.contains("GRAVEL"))
		{
			return Material.GRAVEL;
		}
		else if (ore.contains("SANDSTONE"))
		{
			return Material.SANDSTONE;
		}
		else if (ore.contains("SAND"))
		{
			return Material.SAND;
		}
		else if (ore.contains("STONE"))
		{
			return Material.STONE;
		}
		else
		{
			DebugLogger.console("SHIT, tried to print" + ore);
			return null;
		}
	}
	
	private static void setBlock(String ore, ThreePoint point)
	{
		int x = point.x;
		int y = point.y;
		int z = point.z;
		Material mat = materialReturn(ore);
		if(mat==null)
			DebugLogger.console("at point"+ point.toString() + "x:"+thechunk.getX() + "z:"+thechunk.getZ());
		thechunk.getBlock(x, y, z).setType(mat);
	}
}

