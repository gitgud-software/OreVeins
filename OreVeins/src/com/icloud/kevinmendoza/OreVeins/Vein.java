package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Chunk;

public class Vein 
{
	public HashMap<String,String[][][]> chunkMap;
	public String ore;
	ArrayList<ThreePoint> theOres;
	public Vein(TwoPoint startChunk, TwoPoint endChunk, String ore)
	{
		this.theOres = new ArrayList<ThreePoint>();
		this.ore = ore;
		ThreePoint start = new ThreePoint();
		ThreePoint end = new ThreePoint();
		start.x = start.x + startChunk.x*16;
		start.z = start.z + startChunk.z*16;
		end.x = end.x + startChunk.x*16;
		end.z = end.z + startChunk.z*16;
		BresenHam hammy = new BresenHam();
		ArrayList<ThreePoint> nodal = hammy.returnPoints(start,end);
		Random rand = new Random();
		Ellipse crossSection = new Ellipse(rand.nextInt(5)+1,rand.nextInt(5)+1);
		crossSection.rotateX(rand.nextInt(180)-90);
		crossSection.rotateY(rand.nextInt(180)-90);
		crossSection.rotateZ(rand.nextInt(180)-90);
		ThreePoint[] littleEllipses = crossSection.ellipsePoints;
		ThreePoint point;
		ThreePoint offset;
		for(int i = 0;i<nodal.size();i++)
		{
			for(int j =0;j<littleEllipses.length;j++)
			{
				if(littleEllipses[j]!=null)
				{
					point = littleEllipses[j];
					offset = nodal.get(i);
					point.y = offset.y + point.y;
					if(point.y>2 && point.y < 128)
					{
						point.x = offset.x + point.x;
						point.z = offset.z + point.z;
						this.theOres.add(point);
					}
				}
			}
		}
	}

	public String[][][] returnAndPartitionBlocks(Chunk chunk) 
	{

		int chx; int chz;
		String key;
		String[][][] theOtherArray;
		ThreePoint aPoint;
		if(this.theOres==null)
		{
			for(int i=0;i<=this.theOres.size();i++)
			{
				aPoint = this.theOres.get(i);
				chx = aPoint.x >> 4;
				chz = aPoint.z >> 4;
				String xval = new Integer(chx).toString();
				String zval = new Integer(chz).toString();
				key = xval + ":" + zval;
				if(chunkMap.containsKey(key))
				{
					theOtherArray = chunkMap.get(key);
					if(this.ore.contains("COAL") || theOtherArray[aPoint.x - (aPoint.x >> 4)][aPoint.y][aPoint.z- (aPoint.z >> 4)]!=null)
					{
					theOtherArray[aPoint.x - (aPoint.x >> 4)][aPoint.y][aPoint.z- (aPoint.z >> 4)] = ore;
					chunkMap.put(key, theOtherArray);
				}
			}
			else
			{
				String[][][] freshBool = new String[16][128][16];
				freshBool[aPoint.x - (aPoint.x >> 4)][aPoint.y][aPoint.z - (aPoint.z >> 4)] = this.ore;
				chunkMap.put(key, freshBool);
			}
			}
			VeinChunkReadWrite RWObj = new VeinChunkReadWrite();
			String xval = new Integer(chunk.getX()).toString();
			String zval = new Integer(chunk.getZ()).toString();
			key = xval + ":" + zval;
			for(String entry : chunkMap.keySet())
			{
				if(chunkMap.get(entry) !=null && !entry.contains(key))
				{
					theOtherArray = RWObj.readChunks(entry);
					for(int x =0;x<16;x++)
					{
						for(int y=1;y<128;y++)
						{
							for(int z=0;z<16;z++)
							{
								if(theOtherArray[x][y][z]==null || theOtherArray[x][y][z].contains("COAL"))
								{
									theOtherArray[x][y][z] = ore;
								}
							}
						}
					}
					RWObj.writeChunkInfo(key,theOtherArray);
				}
			}
			return chunkMap.get(key);
		}
		else
			return null;
	}

}