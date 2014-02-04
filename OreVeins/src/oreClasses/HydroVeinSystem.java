package oreClasses;

import java.util.ArrayList;

import java.util.Random;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;


import geometryClasses.Line;
import geometryClasses.Shape;
import geometryClasses.ThreePoint;

public class HydroVeinSystem extends OreSuper
{
	public ThreePoint endPoint;
	public int strike;
	private int branch;
	private ArrayList<ThreePoint> nodes;//branch points
	public HydroVeinSystem(ThreePoint startPoint, int strike, int branch,String ore)
	{
		this.startPoint = startPoint;
		this.ore = ore;
		this.rand = new Random();
		this.endPoint = Line.getEndPoint(startPoint, strike, this.rand, true);
		this.branch = branch;
		this.strike =strike;
		//DebugLogger.console("StartPoint:"+this.startPoint.toString());
		//DebugLogger.console("Endpoint:"+this.endPoint.toString());
		if(Line.distance(startPoint,endPoint) >= 10 && !this.endPoint.equals(this.startPoint))
		{
			this.nodes = new ArrayList<ThreePoint>();
			ArrayList<ThreePoint> tempPoints = Line.bezierCurve(this.startPoint, this.endPoint, rand);
			this.centers = new ArrayList<ThreePoint>();
			addPoints(tempPoints);
			//PointMapping.addArrayToPoints(this.nodes, "DIAMOND");
			PointMapping.addArrayToPoints(this.centers, this.ore);
		}
	}
	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		if(this.rand.nextInt(this.branch)==0)
		{
			ThreePoint newPoint = new ThreePoint(centerPoint);
			nodes.add(newPoint);
		}
		this.centers.add(centerPoint);
	}

}
