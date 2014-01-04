package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;

public class LineDrawingUtilityClass 
{
	
	public static ArrayList<ThreePoint> bresenHamAlgo(ThreePoint start, ThreePoint end)
	{
		ArrayList<ThreePoint> thePoints = new ArrayList<ThreePoint>();
		int xmult =1,ymult=1,zmult=1,x,y,z;
		int Dz = end.z -start.z;
		int Dy = end.y -start.y;
		int Dx = end.x -start.x;
		x = start.x;
		y = start.y;
		z = start.z;
		if(Dz < 0)
		{
			Dz=-Dz;
			zmult=-1;
		}
		if(Dy < 0)
		{
			Dy=-Dy;
			ymult=-1;
		}
		if(Dx < 0)
		{
			Dx=-Dx;
			zmult=-1;
		}
		int dx2 = Dx*2;
		int dy2 = Dy*2;
		int dz2 = Dz*2;
		if(Dx >= Dy && Dx >= Dz)
		{
			int ERRXY = dy2 - Dx;
			int ERRXZ = dz2 - Dx;
			for(int i =0;i<Dx;i++)
			{
				if(ERRXY >0)
				{
					y +=ymult;
					ERRXY -=dx2;
				}
				if(ERRXZ >0)
				{
					z +=zmult;
					ERRXZ -=dx2;
				}
				ERRXY +=dy2;
				ERRXZ +=dz2;
				x+=xmult;
				ThreePoint point = new ThreePoint(x,y,z);
				thePoints.add(point);
			}
		}
		else if (Dy > Dx && Dy >= Dz)
		{
			int errxy = dx2-Dy;
			int errzy = dz2-Dy;
			for(int i =0;i<Dy;i++)
			{
				if(errxy > 0)
				{
					x+=xmult;
					errxy-=dy2;
				}
				if(errzy > 0)
				{
					z+=zmult;
					errzy-=dy2;
				}
				errxy+=dx2;
				errzy+=dz2;
				y+=ymult;
				ThreePoint point = new ThreePoint(x,y,z);
				thePoints.add(point);
			}
		}
		else
		{
			int erryz = dy2-Dz;
			int errxz = dx2-Dz;
			for(int i =0;i<Dz;i++)
			{
				if(erryz > 0)
				{
					y+=ymult;
					erryz-=dz2;
				}
				if(errxz > 0)
				{
					x+=xmult;
					errxz-=dz2;
				}
				erryz+=dy2;
				errxz+=dx2;
				z+=ymult;
				ThreePoint point = new ThreePoint(x,y,z);
				thePoints.add(point);
			}
		}
		thePoints.add(start);
		return thePoints;
	}
	
	public static ArrayList<ThreePoint> bezierCurve(ThreePoint start, ThreePoint end,Random rand)
	{
		int vx = end.x - start.x;
		int vy = end.y - start.y;
		int vz = end.z - start.z;
		int dist = (int)Math.sqrt(vx*vx + vy*vy + vz*vz);
		int qui = dist / 30;
		if(qui <1)
		{
			qui =1;
		}
		int vrx = vx/qui, vry = vy/qui, vrz = vz/qui;
		int x = start.x;
		int y = start.y;
		int z = start.z;
		ArrayList<ThreePoint> centers = new ArrayList<ThreePoint>();
		while (true)//create nodes to offset
		{
			ThreePoint first = new ThreePoint(x,y,z);
			centers.add(first);
			x+=vrx;
			y+=vry;
			z+=vrz;
			vx = x - start.x;
			vy = y - start.y;
			vz = z - start.z;
			int r = (int)Math.sqrt(vx*vx + vy*vy + vz*vz);
			if(r>=dist)
			{
				break;
			}
		}
		ArrayList<ThreePoint> curvePoints = new ArrayList<ThreePoint>();
		for(int i=1;i<centers.size()-1;i++)//offset those points
		{
			curvePoints.add(getEndPoint( centers.get(i) , rand.nextInt((dist/(qui*2))) , rand ) );
		}
		x=0;y=0;z=0;
		int n = centers.size()-1;
		double t=0;
		//DebugLogger.console("centers & qui" + centers.size() + " "+ qui);
		double step = 1.0/20.0;
		ArrayList<ThreePoint> points = new ArrayList<ThreePoint>();
		while(true)
		{
			x=0;y=0;z=0;
			for(int i=0;i<=n;i++)
			{
				x+=binomialCoeff(n,i)*(int)Math.pow(1-t, n-i)*Math.pow(t, i)*centers.get(i).x;
				y+=binomialCoeff(n,i)*(int)Math.pow(1-t, n-i)*Math.pow(t, i)*centers.get(i).y;
				z+=binomialCoeff(n,i)*(int)Math.pow(1-t, n-i)*Math.pow(t, i)*centers.get(i).z;
			}
			ThreePoint thePoint = new ThreePoint(x,y,z);
			points.add(thePoint);
			if(t >= 1)
			{
				break;
			}
			t = t + step;
		}
		ArrayList<ThreePoint> veinPoints = new ArrayList<ThreePoint>();
		for(int i=1;i<points.size();i++)
		{
			veinPoints.addAll(bresenHamAlgo(points.get(i-1),points.get(i)));
		}
		return veinPoints;
	}

	public static int binomialCoeff(int n, int k)
	{
		int coeff = 1;
		for (int i = n - k + 1; i <= n; i++) 
		{
			coeff *= i;
		}
		for (int i = 1; i <= k; i++) 
		{
			coeff /= i;
		}
		return coeff;
	}

	public static ThreePoint getEndPoint(ThreePoint start, int strike, Random rand) 
	{
		if(strike==0)
			return null;
		int it=0;
		double theta;
		int x,z,y;
		while(true)
		{
			while(true)
			{
				//phi = ((double)rand.nextInt(314))/100.0;
				y = rand.nextInt(256)-128 + start.y;
				if(y<128 && y>2)
					break;
			}
			theta = ((double)rand.nextInt(628))/100.0;
			x = strike*(int)(Math.cos(theta)) + start.x;
			z = strike*(int)(Math.sin(theta)) + start.z;
			if(!Bukkit.getWorlds().get(0).isChunkLoaded(x >>4, z>>4)) //Not currently loaded
			{	
				if(Bukkit.getWorlds().get(0).loadChunk(x >>4,  z>>4,false))
				{
					Bukkit.getWorlds().get(0).unloadChunk(x >>4,  z>>4);
				}
				else
				{
					break;
				}
			}
			it++;
			//DebugLogger.console("tried" + it + " to find good chunk");
			if(it > 100)
			{
				return null;
			}
		}
		ThreePoint freepoint = new ThreePoint(x,y,z);
		return freepoint;
	}
	
	
}
