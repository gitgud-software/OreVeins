package com.icloud.kevinmendoza.OreVeins;
//this class creates a little chunk of blocks according to the ellipse formula
//it also uses rotation matrix rules to rotate the formed blocks on three different coordinate axes

public class Ellipse 
{
	ThreePoint[] ellipsePoints;
	public Ellipse(int a, int b)
	{
		if(b>a)
			this.ellipsePoints = new ThreePoint[4*b*b*b+1];
		else
			this.ellipsePoints = new ThreePoint[4*a*a*a+1];
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
					this.ellipsePoints[count] = point;
					count++;
				}
			}
		}	
	}

	public void rotateX(double theta)
	{
		double tau = Math.toRadians(theta);
		double yprime; double zprime;
		for(int i=0;i<this.ellipsePoints.length;i++)
		{
			if(this.ellipsePoints[i]!=null)
			{
				ThreePoint point = ellipsePoints[i];
				yprime = (double)(point.y)*Math.cos(tau)- (double)(point.z)*Math.sin(tau);
				zprime = (double)(point.y)*Math.sin(tau) + (double)(point.z)*Math.cos(tau);
				ellipsePoints[i].y = (int)yprime;
				ellipsePoints[i].z = (int)zprime;
				//DebugLogger.console("X the point is "+ellipsePoints[i].x +" "+ ellipsePoints[i].y + " "+ ellipsePoints[i].z);
			}
		}
	}


	public void rotateY(double theta)
	{
		double tau = Math.toRadians(theta);
		double xprime; double zprime;
		for(int i=0;i<ellipsePoints.length;i++)
		{
			if(ellipsePoints[i]!=null)
			{
				ThreePoint point = ellipsePoints[i];
				xprime = (double)(point.x)*Math.cos(tau)+ (double)(point.x)*Math.sin(tau);
				zprime = -(double)(point.x)*Math.sin(tau) + (double)(point.z)*Math.cos(tau);
				ellipsePoints[i].x = (int)xprime;
				ellipsePoints[i].z = (int)zprime;
				//DebugLogger.console("Y the point is "+ellipsePoints[i].x +" "+ ellipsePoints[i].y + " "+ ellipsePoints[i].z);
			}
		}
	}

	public void rotateZ(double theta)
	{
		double tau = Math.toRadians(theta);
		double xprime; double yprime;
		for(int i=0;i<ellipsePoints.length;i++)
		{
			if(ellipsePoints[i]!=null)
			{
				ThreePoint point = ellipsePoints[i];
				xprime = (double)(point.x)*Math.cos(tau)- (double)(point.y)*Math.sin(tau);
				yprime = (double)(point.x)*Math.sin(tau) + (double)(point.y)*Math.cos(tau);
				ellipsePoints[i].x = (int)xprime;
				ellipsePoints[i].y = (int)yprime;
				//DebugLogger.console("Z the point is "+ellipsePoints[i].x +" "+ ellipsePoints[i].y + " "+ ellipsePoints[i].z);
			}
		}
	}

}
