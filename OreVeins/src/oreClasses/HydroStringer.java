package oreClasses;

import java.util.ArrayList;

import java.util.Random;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;


import geometryClasses.Line;
import geometryClasses.Shape;
import geometryClasses.ThreePoint;

public class HydroStringer extends OreSuper
{
	public ThreePoint endPoint;
	public int strike;
	private int branch;
	private ArrayList<ThreePoint> nodes;
	public HydroStringer(ThreePoint startPoint, int strike, int branch,String ore)
	{
		this.startPoint = startPoint;
		this.ore = ore;
		this.rand = new Random();
		this.endPoint = Line.getEndPoint(startPoint, strike, this.rand, true);
		this.branch = branch;
		this.strike = strike;
		//DebugLogger.console("StartPoint:"+this.startPoint.toString());
		//DebugLogger.console("Endpoint:"+this.endPoint.toString());
		if(Line.distance(startPoint,endPoint) >= 10 && !this.endPoint.equals(this.startPoint))
		{
			this.nodes = new ArrayList<ThreePoint>();
			ArrayList<ThreePoint> tempPoints = Line.bezierCurve(this.startPoint, this.endPoint, rand);
			this.centers = new ArrayList<ThreePoint>();
			addPoints(tempPoints);
			PointMapping.addArrayToPoints(this.centers, this.ore);
			HydroStringer branchVein;
			drawPoints();
			for(int i=0;i<this.nodes.size();i++)
			{
				branchVein = new HydroStringer(nodes.get(i), (int)(this.strike*0.5), (int)(this.branch*1.25), this.ore);
			}
		}
	}
	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		if(this.rand.nextInt(branch)==0)
		{
			ThreePoint newPoint = new ThreePoint(centerPoint.x, centerPoint.y, centerPoint.z);
			nodes.add(newPoint);
		}
		this.centers.add(centerPoint);
	}
}
