package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;

public class Shape 

{
	int a;
	int b;
	int c;
	public double Xrot,Yrot,Zrot;
	public ThreePoint points[];
	public void rotateX(double theta)
	{
		
		double tau = Math.toRadians(theta);
		this.Xrot = tau;
		double yprime; double zprime;
		for(int i=0;i<this.points.length;i++)
		{
			if(this.points[i]!=null)
			{
				ThreePoint point = points[i];
				yprime = (double)(point.y)*Math.cos(tau)- (double)(point.z)*Math.sin(tau);
				zprime = (double)(point.y)*Math.sin(tau) + (double)(point.z)*Math.cos(tau);
				points[i].y = (int)yprime;
				points[i].z = (int)zprime;
				//DebugLogger.console("X the point is "+points[i].x +" "+ points[i].y + " "+ points[i].z);
			}
		}
	}

	public void rotateY(double theta)
	{
		double tau = Math.toRadians(theta);
		this.Yrot = tau;
		double xprime; double zprime;
		for(int i=0;i<points.length;i++)
		{
			if(points[i]!=null)
			{
				ThreePoint point = points[i];
				xprime = (double)(point.x)*Math.cos(tau)+ (double)(point.x)*Math.sin(tau);
				zprime = -(double)(point.x)*Math.sin(tau) + (double)(point.z)*Math.cos(tau);
				points[i].x = (int)xprime;
				points[i].z = (int)zprime;
				//DebugLogger.console("Y the point is "+points[i].x +" "+ points[i].y + " "+ points[i].z);
			}
		}
	}

	public void rotateZ(double theta)
	{
		double tau = Math.toRadians(theta);
		this.Zrot = tau;
		double xprime; double yprime;
		for(int i=0;i<points.length;i++)
		{
			if(points[i]!=null)
			{
				ThreePoint point = points[i];
				xprime = (double)(point.x)*Math.cos(tau)- (double)(point.y)*Math.sin(tau);
				yprime = (double)(point.x)*Math.sin(tau) + (double)(point.y)*Math.cos(tau);
				points[i].x = (int)xprime;
				points[i].y = (int)yprime;
				//DebugLogger.console("Z the point is "+points[i].x +" "+ points[i].y + " "+ points[i].z);
			}
		}
	}

}
