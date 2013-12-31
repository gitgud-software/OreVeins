package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;

public class BresenHam 
{
	public ArrayList<ThreePoint> returnPoints(ThreePoint start, ThreePoint end)
	{
		ArrayList<ThreePoint> thePoints = new ArrayList<ThreePoint>();
		int dz = end.z -start.z;
		int dx = end.y -start.y;
		int dy = end.x -start.x;
		int x=start.x;
		int y=start.y;
		int z=start.z;
		int xmult =1,ymult=1,zmult=1;
		if(dz<0)
		{
			dz=-dz;
			zmult=-1;
		}
		if(dy<0)
		{
			dy=-dy;
			ymult=-1;
		}
		if(dx<0)
		{
			dx=-dx;
			xmult=-1;
		}
		while(dx+dy+dz >=0)
		{
			ThreePoint point = new ThreePoint(xmult*x,ymult*y,zmult*z);
			DebugLogger.console("iterating through point "+ point.x +" "+ point.y +" "+ point.z + " with starting points " + start.x+ " "+start.y+" "+ start.z + " " + end.x+ " "+end.y+" "+ end.z);
			thePoints.add(point);
			if(dy>dz && dy>dx)
			{
				y++;
				dy--;
			}
			else if(dz>dy && dz>dx)
			{
				z++;
				dz--;
			}
			else
			{
				x++;
				dx--;
			}
		}	
		return thePoints;
	}		
}
