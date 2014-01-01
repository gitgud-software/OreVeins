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

import org.bukkit.Bukkit;
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
	public int contin;
	public ThreePoint startpoint;
	public ThreePoint endpoint;
	//what ore the vein is made of
	private ArrayList<ThreePoint> theOres;// an initial list of all the points that need to be placed of the vein,
	//even the ones not in the current chunk
	//this constructor is for all ores who's base is a line
	public Vein(TwoPoint startChunk, TwoPoint endChunk, String ore, String second, Random rand)
	{
		this.contin = 30;
		this.grade = 10;
		this.bonanza = 30;
		this.chunkMap = new HashMap<String,String[][][]>();
		this.theOres = new ArrayList<ThreePoint>();
		this.ore = ore;
		ThreePoint start = new ThreePoint();
		ThreePoint end = new ThreePoint();
		while(true)
		{
			if(start.y < 128 && end.y < 128)
				break;
			start = new ThreePoint();
			end = new ThreePoint();
		}
		start.x = start.x + startChunk.x*16;
		start.z = start.z + startChunk.z*16;
		end.x = end.x + endChunk.x*16;
		end.z = end.z + endChunk.z*16;
		if(this.ore.contains("GOLD") || this.ore.contains("Redstone") || this.ore.contains("Iron"))
		{
			hydroVein(rand, start, end);
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
	//this constructor is for conical sections.
	public Vein(TwoPoint startChunk, Random rand)
	{
		this.ore = "DIAMOND";
	}
	//this constructor is for stringer veins
	private void hydroStringer(Random rand, ThreePoint start)
	{
		
		
	}
	
	private void metaSedBed()
	{
		
	}
	
	private void hydroVein(Random rand, ThreePoint start, ThreePoint end)
	{
		ThreePoint[] nodal = bezier(start,end,rand);
		int x=0, y=0, z=0,a=1, b=1,A;
		int vx = end.x - start.x;
		int vy = end.y - start.y;
		int vz = end.z - start.z;
		int rotateZ = rand.nextInt(90)-45;
		double r = Math.sqrt(vx^2 + vy^2 +vz^2),phi,theta;
		BresenHam hammy = new BresenHam();
		for(int k=1;k<nodal.length;k++)
		{
			if(rand.nextInt(200)==0)
			{
				ThreePoint offset = offset(rand,nodal[k]);
				x+=offset.x;
				y+=offset.y;
				z+=offset.z;//move the vein sideways
			}
			A = 2*rand.nextInt(this.grade)+2;
			b = rand.nextInt(A)-1;
			if(b<1)
				b=1;
			a = A-b;
			ArrayList<ThreePoint> centers = hammy.returnPoints(nodal[k-1],nodal[k]);
			Ellipse crossSection = new Ellipse(a,b);
			phi = Math.acos(vy/r);
			theta = Math.atan(vz/((double)vx+0.001));
			crossSection.rotateX((int)Math.toDegrees(theta));//pitch
			//and i even have rotation arguments!
			crossSection.rotateY((int)Math.toDegrees(phi)+90);//rotation around the Y axis//We CAN SPIN IN ALL THE RIGHT
			crossSection.rotateZ(rotateZ);//rotation about the axis//DIRECTIONS you spin me right round baby right round!
			ThreePoint[] littleEllipses = crossSection.points;//now with the
			//ellipse all spun about, lets get the points it corresponds to
			for(int i = 0;i<centers.size();i++)//for all the points along the line
			{
				centers.get(i).x+=x;
				centers.get(i).y+=y;
				centers.get(i).z+=z;
				if(rand.nextInt(this.bonanza)==0)//a little bonanza surprise for the miners
				{
					bonanza(rand, centers.get(i), i);
				}//then place the normal grade in the ellipse shape
				crossSectionPlace(littleEllipses,centers.get(i),rand);
			}
		}
	}
	
	private ThreePoint offset(Random rand, ThreePoint nodal)
	{
		String biome = Bukkit.getWorlds().get(0).getBiome(nodal.x, nodal.z).name();
		ThreePoint offset = new ThreePoint(0,0,0);
		if(biome.contains("MOUNTAINS"))
		{
			if(rand.nextInt(150)==0)
			{
				offset.x=rand.nextInt(70)-35;
				offset.y=rand.nextInt(70)-35;
				offset.z=rand.nextInt(70)-35;
			}	
			return offset;
		}
		else if (biome.contains("HILLS"))
		{
			if(rand.nextInt(100)==0)
			{
				offset.x=rand.nextInt(50)-25;
				offset.y=rand.nextInt(50)-25;
				offset.z=rand.nextInt(50)-25;
			}
			return offset;
		}
		else
		{
			if(rand.nextInt(200)==0)
			{
				offset.x=rand.nextInt(30)-15;
				offset.y=rand.nextInt(30)-15;
				offset.z=rand.nextInt(30)-15;
			}
			return offset;
		}
	}
	
	private int biomeRadius(ThreePoint nodal, Random rand)
	{
		String biome = Bukkit.getWorlds().get(0).getBiome(nodal.x, nodal.z).name();
		int r;
		if(biome.contains("MOUNTAINS"))
		{
			r = rand.nextInt(70);
		}
		else if (biome.contains("HILLS"))
		{
			r = rand.nextInt(50);
		}
		else
		{
			r = rand.nextInt(30);
		}
		return r;
	}
	
	private ThreePoint[] bezier(ThreePoint start, ThreePoint end,Random rand) 
	{
		int vx = end.x - start.x;
		int vy = end.y - start.y;
		int vz = end.z - start.z;
		int divisions = (int)Math.sqrt((vx*vx) + (vy*vy) + (vz*vz)); 
		divisions =	(int)(((double)divisions)/(2.5*Math.sqrt(divisions)));
		int x = start.x, y = start.y, z = start.z;
		int vxr = vx/divisions;
		int vyr = vy/divisions;
		int vzr = vz/divisions;
		ThreePoint[] Bzs = new ThreePoint[divisions+1];
		Bzs[0]=start;
		for(int i=1;i<divisions;i++)
		{
			x+=vxr;
			y+=vyr;
			z+=vzr;
			Bzs[i] = new ThreePoint(x,y,z);
		}
		Bzs[Bzs.length-1]=end;
		
		ThreePoint[] centers = new ThreePoint[Bzs.length];
		centers[0]= Bzs[0];
		centers[Bzs.length-1]= Bzs[Bzs.length-1];
		int radius;
		x=0;y=0;z=0;
		double phi=0.0, theta=0.0;
		 radius = rand.nextInt(20);
		for(int i =1;i<Bzs.length-1;i++)
		{
			//DebugLogger.console("r is" + r);
			 while(y>128)
			 {
				 phi = ((double)(rand.nextInt(628)))/100.0;
				 theta = ((double)(rand.nextInt(314)))/100.0;
				 y = (int)(radius*Math.cos(theta)) + Bzs[i].y;
			 }
			 x = (int)(radius*Math.sin(theta)*Math.cos(phi));
			 z = (int)(radius*Math.sin(theta)*Math.sin(phi));
			//DebugLogger.console("bzs "+ bzs[i].x +" "+ bzs.length + "bzs"+ i);
			centers[i]= new ThreePoint(x+Bzs[i].x,y +Bzs[i].y,z+Bzs[i].z);
			radius = biomeRadius(centers[i], rand);
		}
		int n,count=0;double t=0;
		ThreePoint[] nodes = new ThreePoint[16];
		while(t<=1)
		{
			x=0;
			y=0;
			z=0;
			n = centers.length-1;
			for(int i=0;i<=n;i++)
			{
				x+=binomialCoefficient(n,i)*centers[i].x*Math.pow((1-t), n-i) * Math.pow(t, i);
				y+=binomialCoefficient(n,i)*centers[i].y*Math.pow((1-t), n-i) * Math.pow(t, i);
				z+=binomialCoefficient(n,i)*centers[i].z*Math.pow((1-t), n-i) * Math.pow(t, i);
			}
			//DebugLogger.console(" count "+count + " t "+ t + " iterate by"+ 1/15);
			nodes[count] = new ThreePoint((int)x,(int)y,(int)z);
			count++;
			t=t+1.0/15.0;
		}
		return nodes;
	}
	
	private int binomialCoefficient(int n, int k) 
	{
	    // take the lowest possible k to reduce computing using: n over k = n over (n-k)
		int top=1,bottom=1;
	    for(int i=1;i<=k;i++)
	    {
	    	 top = top*(n-(k-i));
	    	 bottom = i*bottom;
	    }
	    int result = top/bottom;
	    return result;
	}
	 
	private void crossSectionPlace(ThreePoint[] crossSection, ThreePoint nodal,Random rand)
	{
		ThreePoint point;
		ThreePoint offset;
		for(int j =0;j<crossSection.length;j++)//and for all the points in the ellipse object
		{
			if(crossSection[j]!=null)//as long as the point of the ellipse object exists
			{
				point = null;
				point = crossSection[j];
				offset = null;
				offset = nodal;
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
	
	private void igVein()
	{
		
	}
	
	private void bonanza(Random rand,ThreePoint nodal, int i)
	{
		Bonanza hooray = new Bonanza(rand.nextInt(3)+1,rand.nextInt(3)+1,rand.nextInt(3)+1);
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
				offset = nodal;
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