package geometryClasses;

import java.util.Random;



public class Shape

{
	public ThreePoint[] points;
	
	public Shape(int a, int b)
	{
		if(a<1)
			a=1;
		if(b<1)
			b=1;
		ThreePoint[] temp;
		if(b>a)
			temp = new ThreePoint[4*b*b*b+1];
		else
			temp = new ThreePoint[4*a*a*a+1];
		
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
					temp[count] = point;
					count++;
				}
			}
		}
	concantenate(temp);
	}
	
	public Shape(int a, int b, int c)
	{
		//bonanza's are ellipsoids
		
		int aa =a*a, bb=b*b,cc=c*c, count=0;
		double XX,YY,ZZ;
		ThreePoint[] temp= new ThreePoint[(a*b*c*8)];
		//DebugLogger.console("the dimensions are "+a+" "+b+ " "+c+ " "+" the size is then "+(a*b*c)*8);
		for(int x=-a;x<a;x++)
		{
			for(int y=-b;y<b;y++)
			{
				for(int z=-c;z<c;z++)
				{
					XX = x*x/aa;
					YY = y*y/bb;
					ZZ = z*z/cc;
					if(1>= XX+YY+ZZ)
					{
						ThreePoint thepoint = new ThreePoint(x,y,z);
						//DebugLogger.console(" "+count);
						temp[count] = thepoint;
						count++;
					}
				}
			}
		}
	concantenate(temp);
	}

	private void concantenate(ThreePoint[] temp)
	{
		int count = 0;
		for(int i=0;i<temp.length;i++)
		{
			if(temp[i]!=null)
			{
				count++;
			}
		}
		this.points = new ThreePoint[count];
		count =0;
		for(int i=0;i<temp.length;i++)
		{
			if(temp[i]!=null)
			{
				this.points[count] = temp[i];
				count++;
			}
		}
	}
	
	public void rotateX(double theta)
	{
		double tau = Math.toRadians(theta);
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
		double xprime; double zprime;
		for(int i=0;i<points.length;i++)
		{
			if(points[i]!=null)
			{
				ThreePoint point = points[i];
				xprime = (double)(point.x)*Math.cos(tau)+ (double)(point.z)*Math.sin(tau);
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

	public void rotateRandom(Random rand) 
	{
		rotateY(Math.toRadians(rand.nextInt(180)-90));
		rotateX(Math.toRadians(rand.nextInt(180)-90));
		rotateZ(Math.toRadians(rand.nextInt(180)-90));
	}

	public void alighnToPoints(ThreePoint start, ThreePoint end, Random rand)
	{
		rotateZ(rand.nextInt(360));
		double vx = end.x - start.x;
		double vy = end.y - start.y;
		double vz = end.z - start.z;
		double r = Math.sqrt( vx*vx + vy*vy + vz*vz);
		if(vx==0)
		{
			vx=1;
		}
		if(vy==0)
		{
			vy=1;
		}
		double phi = Math.toDegrees(Math.atan((vx*vx +vz*vz)/vy));
		double theta = Math.toDegrees(Math.atan(vz/vx)+90);
		rotateX(phi);
		rotateY(theta);
	}
}
