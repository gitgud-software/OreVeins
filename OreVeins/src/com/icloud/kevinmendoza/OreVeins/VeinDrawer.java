package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
/*this vein drawer class draws any ores within a 3d string object array
 * such simple, so block, much craft, shine daimond!
 * 
 */
public class VeinDrawer {
	public Chunk chunk;
	private Random rand;
	public VeinDrawer(Chunk chunk)
	{
		this.chunk = chunk;
		this.rand = new Random();
	}
	public void drawVein(String[][][] vein) 
	{
		Block block;
		for(int x=0;x<16;x++)
		{
			for(int z=0;z<16;z++)
			{
				for(int y=2;y<128;y++)
				{
					if(vein[x][y][z]!=null)
					{
						block = this.chunk.getBlock(x, y, z);
						//if(block.getType().compareTo(Material.STONE)==0)
						//{
							if(vein[x][y][z].contains("GOLD"))
							{

								block.setType(Material.GOLD_ORE);
								//DebugLogger.console("makin gold");
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
						//}
					}
				}
			}
		}
		drawStringers(chunk);
	}
	private void drawStringers(Chunk chunk)
	{
		VeinChunkReadWrite RWObj = new VeinChunkReadWrite();
		String xval = new Integer(chunk.getX()).toString();//after this is all done, write all the chunks 
		String zval = new Integer(chunk.getZ()).toString();
		String key = xval + ":" + zval;
		ArrayList<Stringer> stringers;
		stringers = RWObj.readStringer(key);
		RWObj.deleteStringer(key);
		stringerLoop(chunk,stringers);
		stringers = RWObj.readStringer(key);
		stringerLoop(chunk,stringers);
		RWObj.deleteStringer(key);
	}
	private void stringerLoop(Chunk chunk, ArrayList<Stringer> stringers)
	{
		ThreePoint[] crossSectionPoints;
		ThreePoint offset;
		if(stringers!=null)
		{
			for(int i=0;i<stringers.size();i++)
			{//iterate through list of all stringers
				Stringer stringer = stringers.get(i);
				for(int j=0;j<stringer.theOres.size();j++)
				{//iterate through all points
					offset = stringer.theOres.get(j);
					if(rand.nextInt(stringer.branch)==0 && stringer.max >0)
					{
						Stringer st = new Stringer(rand, stringer.theOres.get(j),stringer.ore,
								stringer.grade,stringer.bonanza, stringer.branch, stringer.cross,stringer.max-1);
					}
					if(rand.nextInt(stringer.bonanza)!=0)
					{
						crossSectionPoints = stringer.section;
					}
					else if(!stringer.ore.contains("EMERALD"))
					{
						Bonanza hooray = new Bonanza(rand.nextInt(3)+1,rand.nextInt(3)+1,rand.nextInt(3)+1);
						hooray.rotateX(rand.nextInt(180)-90);
						hooray.rotateY(rand.nextInt(180)-90);
						hooray.rotateZ(rand.nextInt(180)-90);
						crossSectionPoints = hooray.points;
					}
					else
					{//This needs to be changed to type geode
						Bonanza hooray = new Bonanza(rand.nextInt(3)+1,rand.nextInt(3)+1,rand.nextInt(3)+1);
						hooray.rotateX(rand.nextInt(180)-90);
						hooray.rotateY(rand.nextInt(180)-90);
						hooray.rotateZ(rand.nextInt(180)-90);
						crossSectionPoints = hooray.points;
					}
					for(int k=0;k<crossSectionPoints.length;k++)
					{//iterate through the cross section given;
						//DebugLogger.console("the ore is "+stringer.ore+" the grade is"+ stringer.grade+"the iteration is" +k);
						if(rand.nextInt(stringer.grade)==0)
						{
							Block block = Bukkit.getWorlds().get(0).getBlockAt(offset.x+crossSectionPoints[k].x, offset.y 
									+crossSectionPoints[k].y, offset.z +crossSectionPoints[k].z);
							//DebugLogger.console("the ore is "+stringer.ore);
							if(stringer.ore.contains("GOLD"))
							{
								block.setType(Material.GOLD_ORE);
							}
							else if(stringer.ore.contains("IRON"))
							{
								block.setType(Material.IRON_ORE);
							}
							else if	(stringer.ore.contains("REDSTONE"))
							{
								//DebugLogger.console("drawing the stone");
								block.setType(Material.REDSTONE_ORE);
							}
							else if	(stringer.ore.contains("EMERALD"))
							{
								block.setType(Material.EMERALD_ORE);
							}
						}
					}
				}
			}
			//this is the code that allows the stringers to branchVV
		}
	}
}



