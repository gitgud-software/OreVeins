package com.icloud.kevinmendoza.OreVeins;

public class Bonanza extends Shape
{
	public Bonanza(int a, int b, int c)
	{
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
		
	}
}
