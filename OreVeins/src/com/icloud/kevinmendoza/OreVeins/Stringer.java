package com.icloud.kevinmendoza.OreVeins;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;

public class Stringer implements Serializable
{
	public int max;
	public String ore;
	public int grade;
	public int bonanza;
	public int branch;
	public ArrayList<ThreePoint> theOres;
	public ThreePoint[] section;
	public int cross;
	public Stringer()
	{
	}
	
	public Stringer(Random rand, ThreePoint start,String ore,int grade,int bonanza, int branch,int cross, int max)
	{
		this.max = max;
		this.cross = cross;
		this.ore = ore;
		this.grade = grade;
		this.bonanza = bonanza;
		this.branch = branch;
		ChunkFinder find = new ChunkFinder(returnChunkCoords(start),rand);
		TwoPoint endChunk = find.findchunk(5);
		if(endChunk!=null)
		{	//max length of next chunk
			ThreePoint end = new ThreePoint();
			end.x+=16*endChunk.x;
			end.z+=16*endChunk.z;
			ThreePoint[] nodes = bezier(start,end,rand);
			BresenHam hammy = new BresenHam();
			ArrayList<ThreePoint> points;
			HashMap<String,ArrayList<ThreePoint>> theStringPoints = new HashMap<String,ArrayList<ThreePoint>>();
			TwoPoint chcs; String key,xval,zval;
			for(int i=1;i<nodes.length;i++)
			{
				points =  hammy.returnPoints(nodes[i-1], nodes[i]);
				for(int j=0;j<points.size();j++)
				{
					
					chcs = returnChunkCoords(points.get(j));
					xval = new Integer(chcs.x).toString();//after this is all done, write all the chunks 
					zval = new Integer(chcs.z).toString();;//that arent the current one to file
					key = xval + ":" + zval;
					if (theStringPoints.get(key)==null)
					{
						ArrayList<ThreePoint> newArray = new ArrayList<ThreePoint>();
						newArray.add(points.get(j));
						theStringPoints.put(key, newArray);
					}
					else
					{
						theStringPoints.get(key).add(points.get(j));
					}
				}
			}
			xval = new Integer(start.x).toString();//after this is all done, write all the chunks 
			zval = new Integer(start.z).toString();;//that arent the current one to file
			key = xval + ":" + zval;
			int a,b,A;
			VeinChunkReadWrite RWObj = new VeinChunkReadWrite();
			int vx = end.x - start.x;
			int vy = end.y - start.y;
			int vz = end.z - start.z;
			double phi,theta,r = Math.sqrt(vx^2 + vy^2 +vz^2);
			int rotateZ = rand.nextInt(90)-45;
			phi = Math.acos(vy/r);
			theta = Math.atan(vz/((double)vx+0.001));
			ThreePoint[] crossPoints; 
			for(String entry : theStringPoints.keySet())
			{
				
				if(theStringPoints.get(entry) !=null)
				{
					Stringer stringer = new Stringer();
					stringer.theOres = theStringPoints.get(entry);
					stringer.ore =this.ore;
					stringer.max = this.max;
					stringer.cross = (int) ((double)this.cross *2*rand.nextDouble())+1;
					stringer.grade = (int) ((double)this.grade *2*rand.nextDouble());
					if(stringer.grade ==0)
						stringer.grade =1;
					stringer.bonanza = (int) ((double)this.bonanza *2*rand.nextDouble());
					if(stringer.bonanza <3)
						stringer.bonanza =60;
					stringer.branch = (int) ((double)this.branch *2*rand.nextDouble());
					if(stringer.branch <5)
						stringer.branch =70;
					stringer.section = returnCrossPoints(rand, theta,phi,rotateZ);
					RWObj.writeStringer(stringer, entry);
				}
			}
		}
	}
	private ThreePoint[] returnCrossPoints(Random rand,double theta, double phi, int rotateZ)
	{
		int A,b,a;
		A = rand.nextInt(this.cross)+2;
		b = rand.nextInt(A)-1;
		if(b<1)
			b=1;
		a = A-b;
		Ellipse crossSection = new Ellipse(a,b);
		crossSection.rotateX((int)Math.toDegrees(theta));//pitch
		//and i even have rotation arguments!
		crossSection.rotateY((int)Math.toDegrees(phi)+90);//rotation around the Y axis//We CAN SPIN IN ALL THE RIGHT
		crossSection.rotateZ(rotateZ);
		ArrayList<ThreePoint> lePoints = new ArrayList<ThreePoint>();
		for(int i=0;i<crossSection.points.length;i++)
		{
			if(crossSection.points[i]!=null)
			{
				ThreePoint newPoint = new ThreePoint(crossSection.points[i].x,
						crossSection.points[i].y,crossSection.points[i].z);
				lePoints.add(newPoint);
			}
		}
		ThreePoint[] finalReturn = new ThreePoint[lePoints.size()];
		for(int i=0;i<lePoints.size();i++)
		{
			finalReturn[i] = lePoints.get(i);
		}
		return  finalReturn;
	}
	
	private TwoPoint returnChunkCoords(ThreePoint point)
	{
		TwoPoint ch= new TwoPoint(point.x >> 4,point.z >>4);
		return ch;
	}
	private ThreePoint[] bezier(ThreePoint start, ThreePoint end,Random rand) 
	{
		int vx = end.x - start.x;
		int vy = end.y - start.y;
		int vz = end.z - start.z;
		int divisions = (int) Math.sqrt(vx*vx + vy*vy + vz*vz);
		divisions = divisions/10;
		int x = start.x, y = start.y, z = start.z;
		int vxr = vx/divisions;
		int vyr = vy/divisions;
		int vzr = vz/divisions;
		ThreePoint[] Bzs = new ThreePoint[divisions];
		Bzs[0]=start;
		for(int i=1;i<divisions;i++)
		{
			x+=vxr;
			y+=vyr;
			z+=vzr;
			Bzs[i] = new ThreePoint(x,y,z);
		}
		ThreePoint[] centers = new ThreePoint[Bzs.length];
		centers[0]= Bzs[0];
		centers[Bzs.length-1]= Bzs[Bzs.length-1];
		int radius;
		x=0;y=0;z=0;
		double phi=0.0, theta=0.0;
		 radius = rand.nextInt(20);
		for(int i =1;i<Bzs.length;i++)
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
		int count=0;double t=0;
		int n = centers.length-1;
		
		ThreePoint[] nodes = new ThreePoint[16];
		while(t<=1)
		{
			x=0;
			y=0;
			z=0;
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
}
