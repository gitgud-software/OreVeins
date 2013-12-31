package com.icloud.kevinmendoza.OreVeins;
/*
 * this is the powerhouse vein class. All veins will be borne out of this class
 * various cross sections will be possible from this class as well as vein strike types
 * It also stores parts of itself in chunks
 * 
 * 
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Chunk;

public class Vein implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HashMap<String,String[][][]> chunkMap;//yeah i had to hashmap it. really ingenious solution really
	//i make a key anytime i need to and then add to it. Its a hashmap of string per chunks! :D
	public String ore;
	public int grade;
	public int bonanza;
	public int branch;
	public int strike;
	public int contin;
	public ThreePoint startpoint;
	public ThreePoint endpoint;
	//what ore the vein is made of
	private ArrayList<ThreePoint> theOres;// an initial list of all the points that need to be placed of the vein,
	//even the ones not in the current chunk
	public Vein(TwoPoint startChunk, TwoPoint endChunk, String ore, Random rand)
	{
		this.grade = 10;
		this.bonanza = 30;
		this.chunkMap = new HashMap<String,String[][][]>();
		this.theOres = new ArrayList<ThreePoint>();
		this.ore = ore;
		ThreePoint start = new ThreePoint();
		ThreePoint end = new ThreePoint();
		while(start.y > 128 && end.y > 128)
		{
			start = new ThreePoint();
			end = new ThreePoint();
		}
		start.x = start.x + startChunk.x*16;
		start.z = start.z + startChunk.z*16;
		end.x = end.x + endChunk.x*16;
		end.z = end.z + endChunk.z*16;//what are the absolute coordinates of the vein line, these are! :D
		BresenHam hammy = new BresenHam();//whos moons are over my hammy? Bresen's are!
		//but in all seriousness, this bresenham function is an integer-operation-only line drawing function
		//which returns all blocks that lie along this line.
		if(this.ore.contains("GOLD") || this.ore.contains("Redstone") || this.ore.contains("Iron"))
		{
			hydroVein(rand, hammy, start, end);
		}
		else if (this.ore.contains("COAL") || this.ore.contains("BIF") || this.ore.contains("LAPIZ"))
		{
			if(this.ore.contains("BIF"))
			{
				this.ore = "IRON";
			}
			metaSedBed();
		}
		else if(this.ore.contains("EMERALD"))
		{
			igVein();
		}
		
	}
	
	public Vein(TwoPoint startChunk, Random rand)
	{
		this.ore = "DIAMOND";
	}
	
	private void veinType()
	{
		
		
		
		
	}
	
	private void hydroStringer()
	{
		
		
	}
	
	private void metaSedBed()
	{
		
	}
	
	private void hydroVein(Random rand, BresenHam hammy, ThreePoint start, ThreePoint end)
	{
		ArrayList<ThreePoint> nodal = hammy.returnPoints(start,end);
		Ellipse crossSection = new Ellipse(3,6);
		//This ellipse class is pretty cool to. Creates an ellipse in the x,y plane with major/minor
		//axes arguments that are random. 
		int vx = end.x - start.x;
		int vy = end.y - start.y;
		int vz = end.z - start.z;
		double r = Math.sqrt(vx^2 + vy^2 +vz^2);
		double phi, theta;
		phi = Math.acos(vy/r);
		theta = Math.atan(vz/vx);
		crossSection.rotateX((int)Math.toDegrees(theta));//pitch
		//and i even have rotation arguments!
		crossSection.rotateY((int)Math.toDegrees(phi)+90);//rotation around the Y axis//We CAN SPIN IN ALL THE RIGHT
		//crossSection.rotateZ(rand.nextInt(180)-90);//rotation about the axis//DIRECTIONS you spin me right round baby right round!
		ThreePoint[] littleEllipses = crossSection.points;//now with the
		//ellipse all spun about, lets get the points it corresponds to
		ThreePoint point;
		ThreePoint offset;
		for(int i = 0;i<nodal.size();i++)//for all the points along the line
		{
			if(rand.nextInt(this.bonanza)==0)//a little bonanza surprise for the miners
			{
				bonanza(rand, nodal, i);
			}//then place the normal grade in the ellipse shape
			for(int j =0;j<littleEllipses.length;j++)//and for all the points in the ellipse object
			{
				if(littleEllipses[j]!=null)//as long as the point of the ellipse object exists
				{
					point = null;
					point = littleEllipses[j];
					offset = null;
					offset = nodal.get(i);
					//DebugLogger.console("size of little ellipses is" + littleEllipses.length+ "current iteration is "+ j);
					//DebugLogger.console("Ellipse is not null, so heres x y and z" + point.x + " " + point.y + " " + point.z);
					//DebugLogger.console("offset is : "+ offset.x + " "+offset.y + " "+ offset.z);
					ThreePoint point2 = new ThreePoint(offset.x + point.x,offset.y + point.y,offset.z + point.z);
					//get the point from the ellipse object and add the offset
					//from the line object
					if(point2.y>2 && point2.y < 128)
					{
						if(!this.theOres.contains(point2) && rand.nextInt(this.grade)==0)
						{
							this.theOres.add(point2);
						}
					}
				}
			}

		}
	}
		
	private void igVein()
	{
		
	}
	
	private void bonanza(Random rand,ArrayList<ThreePoint> nodal, int i)
	{
		Bonanza hooray = new Bonanza(rand.nextInt(6)+1,rand.nextInt(6)+1,rand.nextInt(6)+1);
		hooray.rotateX(rand.nextInt(180)-90);
		hooray.rotateY(rand.nextInt(180)-90);
		hooray.rotateZ(rand.nextInt(180)-90);
		ThreePoint[] ellipsoid = hooray.points;
		ThreePoint point,offset;
		for(int j =0;j<ellipsoid.length;j++)//and for all the points in the ellipse object
		{
			if(ellipsoid[j]!=null)//as long as the point of the ellipse object exists
			{
				point = null;
				point = ellipsoid[j];
				offset = null;
				offset = nodal.get(i);
				//DebugLogger.console("size of little ellipses is" + littleEllipses.length+ "current iteration is "+ j);
				//DebugLogger.console("Ellipse is not null, so heres x y and z" + point.x + " " + point.y + " " + point.z);
				//DebugLogger.console("offset is : "+ offset.x + " "+offset.y + " "+ offset.z);
				ThreePoint point2 = new ThreePoint(offset.x + point.x,offset.y + point.y,offset.z + point.z);
				//get the point from the ellipse object and add the offset
				//from the line object
				if(point2.y>2 && point2.y < 128)
				{
					if(!this.theOres.contains(point2))
					{
						this.theOres.add(point2);
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
		if(this.theOres!=null && this.theOres.size()!=0)//this should not be null!! but it is so i put this here
		{
			for(int i=0;i<this.theOres.size();i++)//for every point in the vein, iterate through it
			{
				aPoint = this.theOres.get(i);//get the first point in the ores object
				chx = aPoint.x >> 4;//get the chunk coordinate of the first point, and make a key based on it
				chz = aPoint.z >> 4;
				//DebugLogger.console("point is "+ aPoint.x + " "+ aPoint.y +" "+ aPoint.z);
				String xval = new Integer(chx).toString();
				String zval = new Integer(chz).toString();
				key = xval + ":" + zval;
				if(aPoint.y<128)
				{
					if(chunkMap.containsKey(key))//if the hashmap has that chunk point, place it in the map
					{
						theOtherArray = chunkMap.get(key);//there is that coal thing. only place it because of COAL :)
						if(this.ore.contains("COAL") || theOtherArray[aPoint.x - (16*(aPoint.x >> 4))][aPoint.y][aPoint.z - (16*(aPoint.z >> 4))]==null)
						{
							//DebugLogger.console("old chunk, placing point"+(aPoint.x - (16*(aPoint.x >> 4)))+" "+aPoint.y+ " "+(aPoint.z - (16*(aPoint.z >> 4))));
							theOtherArray[aPoint.x - (16*(aPoint.x >> 4))][aPoint.y][aPoint.z - (16*(aPoint.z >> 4))] = ore;
							chunkMap.put(key, theOtherArray);
						}
					}
					else//if not, place it at a new key
					{
						String[][][] freshBool = new String[16][128][16];//size of the chunk
						//DebugLogger.console("new chunk, placed point "+(aPoint.x - (16*(aPoint.x >> 4)))+" "+aPoint.y+ " "+(aPoint.z - (16*(aPoint.z >> 4))));
						freshBool[aPoint.x - (16*(aPoint.x >> 4))][aPoint.y][aPoint.z - (16*(aPoint.z >> 4))] = this.ore;
						chunkMap.put(key, freshBool);
					}
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
					String[][][] readInChunks = new String[16][128][16];
					readInChunks = RWObj.readChunks(entry);
					if(readInChunks != null)
					{//load up current ores from the chunks in the vein
						for(int x =0;x<16;x++)
						{
							for(int y=2;y<128;y++)
							{
								for(int z=0;z<16;z++)
								{
									if(chunkMap.get(entry)[x][y][z]!=null)
									{
										readInChunks[x][y][z] = ore;//add this ore to the chunk if its empty or coal
									}
								}
							}
						}
						RWObj.writeChunkInfo(entry,readInChunks);//store the info to file
					}
					else
					{
						String[][][] Array = new String[16][128][16];
						for(int x =0;x<16;x++)
						{
							for(int y=2;y<128;y++)
							{
								for(int z=0;z<16;z++)
								{
									if(chunkMap.get(entry)[x][y][z]!=null)
									{
										Array[x][y][z] = ore;//add this ore to the chunk if its empty or coal
									}
									else
									{
										Array[x][y][z] = null;
									}
								}
							}
						}
						RWObj.writeChunkInfo(entry,Array);
					}//store the modified chunk data back to file
				}
			}
			return chunkMap.get(key);//aand return the data for the current chunk
		}
		else
		{
			return null;
		}
	}

}