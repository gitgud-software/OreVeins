package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;
import java.util.Random;

public class Bonanza extends Shape
{
	public Bonanza(int a, int b, int c)
	{
		Random rand = new Random();
		//bonanza's are ellipsoids
		this.a = a;
		this.b = b;
		this.c = c;
		int aa =a*a, bb=b*b,cc=c*c, count=0;
		double XX,YY,ZZ;
		this.points = new ThreePoint[(a*b*c*8)];
		//DebugLogger.console("the dimensions are "+a+" "+b+ " "+c+ " "+" the size is then "+(a*b*c)*8);
		for(int x=-a;x<a;x++)
		{
			for(int y=-b;y<b;y++)
			{
				for(int z=-c;z<c;z++)
				{
					XX = x*x / aa;
					YY = y*y/bb;
					ZZ = z*z/cc;
					if(1>= XX+YY+ZZ)
					{
						ThreePoint thepoint = new ThreePoint(x,y,z);
						//DebugLogger.console(" "+count);
						this.points[count] = thepoint;
						count++;
					}
				}
			}
		}
		rotateX(rand.nextInt(180)-90);
		rotateY(rand.nextInt(180)-90);
		rotateZ(rand.nextInt(180)-90);
		this.points = returnList();
	}
	private ThreePoint[] returnList()
	{
		ArrayList<ThreePoint> lePoints = new ArrayList<ThreePoint>();
		for(int i=0;i<this.points.length;i++)
		{
			if(this.points[i]!=null)
			{
				ThreePoint newPoint = new ThreePoint(this.points[i].x,
						this.points[i].y,this.points[i].z);
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
}
