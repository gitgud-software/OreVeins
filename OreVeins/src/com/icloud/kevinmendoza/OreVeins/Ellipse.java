package com.icloud.kevinmendoza.OreVeins;

import java.io.Serializable;

//this class creates a little chunk of blocks according to the ellipse formula
//it also uses rotation matrix rules to rotate the formed blocks on three different coordinate axes

public class Ellipse extends Shape implements Serializable
{
	public Ellipse(int a, int b)
	{
		if(a<1)
			a=1;
		if(b<1)
			b=1;
		this.a = a;
		this.b = b;
		if(b>a)
			this.points = new ThreePoint[4*b*b*b+1];
		else
			this.points = new ThreePoint[4*a*a*a+1];
		int bb = b*b, aa=a*a,count=0; double bigX,bigY;
		for(int x=-a;x<=a;x++)
		{
			for(int y=-b;y<=b;y++)
			{
				bigX = ((double)(x*x) / (double)aa);
				bigY = ((double)(y*y) / (double)bb);
				if(1 >= (bigX+bigY))
				{
					ThreePoint point = new ThreePoint(x,y,1);
					this.points[count] = point;
					count++;
				}
			}
		}	
	}
	
	
}