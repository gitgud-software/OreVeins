package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class VeinSystem 
{
	public String ore;
	public ArrayList<ThreePoint> orePoints;
	private ArrayList<ThreePoint> otherPoints;
	private int averageGrade;
	private int bonanza;
	private int branch;
	private int x,z;
	public VeinSystem(String ore, ThreePoint start,int strike, int averageGrade, int bonanza, int branch)
	{
		DebugLogger.console("making New Vein System");
		this.x = start.x >>4;
		this.z = start.z >>4;
		this.orePoints = new ArrayList<ThreePoint>();
		this.otherPoints = new ArrayList<ThreePoint>();
		this.ore = ore;
		this.averageGrade = averageGrade;
		this.bonanza = bonanza;
		this.branch = branch;
		Random rand = new Random();
		createAndAddVein(strike,start,rand);
		if(otherPoints.size()>1)
		{
			saveSystemToFile();
		}
	}

	private void createAndAddVein(int newStrike,ThreePoint start,Random rand) 
	{
		int A,B,C;
		A = (int) ((double)this.branch*0.5*Math.pow(rand.nextGaussian(),2));
		B = (int) ((double)(this.averageGrade)*0.5*Math.pow(rand.nextGaussian(),2));
		C = (int) ((double)this.bonanza*0.5*Math.pow(rand.nextGaussian(),2));
		int randomBranch  =  A + this.branch;
		int randomGrade   =  B + this.averageGrade/2+2;
		int randomBonanza =  C + this.bonanza;
		Vein vein = new Vein(start, newStrike, randomBranch, randomGrade, randomBonanza, rand, rand.nextInt(this.averageGrade/7 + 4)+2);
		DebugLogger.console("making new vein with strike, bonanza, grade" +newStrike+" "+randomBonanza +" "+ randomGrade);
		ArrayList<ThreePoint> nodes = new ArrayList<ThreePoint>();
		if(vein.centers!=null)
		{
			int prob = vein.branch;
			for(int i=0;i<vein.centers.size();i++)
			{
				
				//bonanza adding code
				if(rand.nextInt(vein.bonanza)==0)
				{
					Shape shapeObj = new Shape();
					shapeObj.ellipsoid(rand.nextInt(this.averageGrade/7 + 1)+1,
					rand.nextInt(this.averageGrade/7 + 1)+1, rand.nextInt(this.averageGrade/7 + 1)+1);
					shapeObj.rotateRandom(rand);
					for(int j = 0;j<shapeObj.points.length;j++)
					{
						addInPoints(shapeObj.points[j],vein.centers.get(i));
					}
				}
				else//add in points like normal
				{
					for(int j = 0;j<vein.cross.length;j++)
					{
						if(rand.nextInt(vein.grade)==0)
						{
							addInPoints(vein.cross[j],vein.centers.get(i));
						}
					}
				}
				//branching vein code
				if(rand.nextInt(prob)==0)
				{
					nodes.add(vein.centers.get(i));
				}
			}
			//if more than one node was added, make another vein
			if(nodes.size()>1)
			{
				for(int i=0;i<nodes.size();i++)
				{
					int randomStrike = rand.nextInt((int)((double)(newStrike)*0.75))+2;
					createAndAddVein( randomStrike,nodes.get(i),rand);
				}
			}	
		}
	}
	
	private void addInPoints(ThreePoint cross, ThreePoint offset)
	{
		if(cross!=null && offset != null)
		{
			cross.x+=offset.x;cross.y+=offset.y;cross.z+=offset.z;
			if(cross.x>>4 == this.x && cross.z >>4 == this.z)
			{
				this.orePoints.add(cross);
			}
			else
			{
				this.otherPoints.add(cross);
			}
		}
	}
	
	private void saveSystemToFile()
	{
		DebugLogger.console("saveToFile");
		//three steps. 1.add points to hashmap. 2. get relevant points and add them to hash values 3. add all points to hashmap. 3. save
		//values to disk
		HashMap<String,String[][][]> oreMap = new HashMap<String,String[][][]>();
		for(int i=0;i<this.otherPoints.size();i++)
		{
			String chx = new Integer(this.otherPoints.get(i).x >>4).toString();
			String chz = new Integer(this.otherPoints.get(i).z >>4).toString();
			String key = chx +":" + chz;
			if(oreMap.containsKey(key))//2
			{
				 String[][][] check =oreMap.get(key);
				 ThreePoint checkPoint = this.otherPoints.get(i);
				 checkPoint.x = checkPoint.x - 16*(int)(checkPoint.x/16);
				 checkPoint.z = checkPoint.z - 16*(int)(checkPoint.z/16);
				 if(check[checkPoint.x][checkPoint.y][checkPoint.z]==null || check[checkPoint.x][checkPoint.y][checkPoint.z].contains("COAL") )
				 {
					 check[checkPoint.x][checkPoint.y][checkPoint.z] = this.ore;
					 oreMap.put(key, check);
				 }
					 
			}
			else//1
			{
				String[][][] check = new String[16][128][16];
				ThreePoint checkPoint = this.otherPoints.get(i);
				checkPoint.x = checkPoint.x - 16*(int)(checkPoint.x/16);
				checkPoint.z = checkPoint.z - 16*(int)(checkPoint.z /16);
				check[checkPoint.x][checkPoint.y][checkPoint.z] = this.ore;
				oreMap.put(key, check);
			}
			
		}
		for(String entry: oreMap.keySet())
		{
			if(oreMap.containsKey(entry))
			{
				VeinChunkReadWrite.writeChunkInfo(entry, oreMap.get(entry));
			}
		}
		this.otherPoints = null;
	}

}
