package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;
import java.util.Random;

public class Vein 
{
	public ThreePoint[] cross;
	public ThreePoint endPoint;
	public ArrayList<ThreePoint> centers;
	public int strike;
	public int branch;
	public int grade;
	public int bonanza;
	
	public Vein(ThreePoint startPoint, int strike, int branch, int grade, int bonanza, Random rand,int Area)
	{
		this.strike= strike;
		this.branch = branch;
		this.grade = grade;
		this.bonanza = bonanza;
		this.endPoint = LineDrawingUtilityClass.getEndPoint(startPoint, strike, rand);
		if(this.endPoint!=null)
		{
			this.centers = LineDrawingUtilityClass.bezierCurve(startPoint,endPoint, rand);
			DebugLogger.console("made Veins");
			Shape ShapeObj = new Shape();
			int A = rand.nextInt((int)Math.sqrt(Area)/2 +1)+2;
			int B = A - rand.nextInt(A-1);
			ShapeObj.ellipse(A,B);
			this.cross = ShapeObj.points;
			int vx = endPoint.x - startPoint.x;
			int vy = endPoint.y - startPoint.y;
			int vz= endPoint.z - startPoint.z;
			int R = (int)Math.sqrt(vx*vx+vy*vy+vz*vz);
			if(vx==0)
				vx=1;
			double theta = Math.acos(vy/R);
			double phi = Math.atan(vz/vx+90);
			double zaxis = 6.28*rand.nextDouble();
			ShapeObj.rotateY(phi);
			ShapeObj.rotateX(theta);
			ShapeObj.rotateZ(zaxis);
		}
	}

	
}
