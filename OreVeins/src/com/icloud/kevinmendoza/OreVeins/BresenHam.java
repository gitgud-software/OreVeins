package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;

public class BresenHam 
{
	public ArrayList<ThreePoint> returnPoints(ThreePoint start, ThreePoint end)
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
}
