package com.icloud.kevinmendoza.OreVeins;
/*
 * this is the powerhouse vein class. All veins will be borne out of this class
 * various cross sections will be possible from this class as well as vein strike types
 * It also stores parts of itself in chunks
 * 
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Chunk;

public class Vein 
{
	public HashMap<String,String[][][]> chunkMap;//yeah i had to hashmap it. really ingenious solution really
	//i make a key anytime i need to and then add to it. Its a hashmap of string per chunks! :D
	public String ore;//what ore the vein is made of
	ArrayList<ThreePoint> theOres;// an initial list of all the points that need to be placed of the vein,
	//even the ones not in the current chunk
	public Vein(TwoPoint startChunk, TwoPoint endChunk, String ore)
	{
		this.theOres = new ArrayList<ThreePoint>();
		this.ore = ore;
		ThreePoint start = new ThreePoint();
		ThreePoint end = new ThreePoint();
		start.x = start.x + startChunk.x*16;
		start.z = start.z + startChunk.z*16;
		end.x = end.x + startChunk.x*16;
		end.z = end.z + startChunk.z*16;//what are the absolute coordinates of the vein line, these are! :D
		BresenHam hammy = new BresenHam();//whos moons are over my hammy? Bresen's are!
		//but in all seriousness, this bresenham function is an integer-operation-only line drawing function
		//which returns all blocks that lie along this line.
		ArrayList<ThreePoint> nodal = hammy.returnPoints(start,end);
		Random rand = new Random();//a new random object NUUUUUU
		
		Ellipse crossSection = new Ellipse(rand.nextInt(5)+1,rand.nextInt(5)+1);
		//This ellipse class is pretty cool to. Creates an ellipse in the x,y plane with major/minor
		//axes arguments that are random. 
		crossSection.rotateX(rand.nextInt(180)-90);//and i even have rotation arguments!
		crossSection.rotateY(rand.nextInt(180)-90);//We CAN SPIN IN ALL THE RIGHT
		crossSection.rotateZ(rand.nextInt(180)-90);//DIRECTIONS
		ThreePoint[] littleEllipses = crossSection.ellipsePoints;//now with the
		//ellipse all spun about, lets get the points it corresponds to
		ThreePoint point;
		ThreePoint offset;
		for(int i = 0;i<nodal.size();i++)//for all the points along the line
		{
			for(int j =0;j<littleEllipses.length;j++)//and for all the points in the ellipse object
			{
				if(littleEllipses[j]!=null)//as long as the point of the ellipse object exists
				{
					point = littleEllipses[j];
					offset = nodal.get(i);
					point.y = offset.y + point.y; //get the point from the ellipse object and add the offset
					//from the line object
					if(point.y>2 && point.y < 128)
					{
						point.x = offset.x + point.x;
						point.z = offset.z + point.z;
						this.theOres.add(point);//and finally add in the point object to the ores array
					}
				}
			}
		}
	}

	public String[][][] returnAndPartitionBlocks(Chunk chunk) 
	{

		int chx; int chz;
		String key;
		String[][][] theOtherArray;// an array to save the portion of the vein to the hashmap based on
		//its chunk coordinates
		ThreePoint aPoint;
		if(this.theOres!=null || this.theOres.size()==0)//this should not be null!! but it is so i put this here
		{
			for(int i=0;i<=this.theOres.size();i++)//for every point in the vein, iterate through it
			{
				aPoint = this.theOres.get(i);//get the first point in the ores object
				chx = aPoint.x >> 4;//get the chunk coordinate of the first point, and make a key based on it
				chz = aPoint.z >> 4;
				String xval = new Integer(chx).toString();
				String zval = new Integer(chz).toString();
				key = xval + ":" + zval;
				if(chunkMap.containsKey(key))//if the hashmap has that chunk point, place it in the map
				{
					theOtherArray = chunkMap.get(key);//there is that coal thing. only place it because of COAL :)
					if(this.ore.contains("COAL") || theOtherArray[aPoint.x - (aPoint.x >> 4)][aPoint.y][aPoint.z- (aPoint.z >> 4)]!=null)
					{
						theOtherArray[aPoint.x - (aPoint.x >> 4)][aPoint.y][aPoint.z- (aPoint.z >> 4)] = ore;
						chunkMap.put(key, theOtherArray);
					}
				}
				else//if not, place it at a new key
				{
					String[][][] freshBool = new String[16][128][16];//size of the chunk
					freshBool[aPoint.x - (aPoint.x >> 4)][aPoint.y][aPoint.z - (aPoint.z >> 4)] = this.ore;
					chunkMap.put(key, freshBool);
				}
			}
			VeinChunkReadWrite RWObj = new VeinChunkReadWrite();
			String xval = new Integer(chunk.getX()).toString();//after this is all done, write all the chunks 
			String zval = new Integer(chunk.getZ()).toString();//that arent the current one to file
			key = xval + ":" + zval;
			for(String entry : chunkMap.keySet())
			{
				if(chunkMap.get(entry) !=null && !entry.contains(key))
				{
					theOtherArray = RWObj.readChunks(entry);//load up current ores from the chunks in the vein
					for(int x =0;x<16;x++)
					{
						for(int y=1;y<128;y++)
						{
							for(int z=0;z<16;z++)
							{
								if(theOtherArray[x][y][z]==null || theOtherArray[x][y][z].contains("COAL"))
								{
									theOtherArray[x][y][z] = ore;//add this ore to the chunk if its empty or coal
								}
							}
						}
					}
					RWObj.writeChunkInfo(key,theOtherArray);//store the modified chunk data back to file
				}
			}
			return chunkMap.get(key);//aand return the data for the current chunk
		}
		else
			return null;
	}

}