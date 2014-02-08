package oreClasses;

import java.util.ArrayList;
import java.util.Random;

import com.icloud.kevinmendoza.OreVeins.PointMapping;

import geometryClasses.Geode;
import geometryClasses.Line;
import geometryClasses.ThreePoint;

public class PegmatiteSystem extends OreSuper 
{
	public ThreePoint endPoint;
	public int strike;
	private int branch;
	private ArrayList<ThreePoint> nodes;//branch points
	private ArrayList<ThreePoint> air;
	private ArrayList<ThreePoint> redstone;
	private int bonanza;
	public PegmatiteSystem(ThreePoint startPoint, int strike, int branch, int bonanza ,String ore)
	{
		this.startPoint = startPoint;
		this.ore = ore;
		this.rand = new Random();
		this.endPoint = Line.getEndPoint(startPoint, strike, this.rand, true);
		this.branch = branch;
		this.strike =strike;
		this.bonanza = bonanza;
		//DebugLogger.console("StartPoint:"+this.startPoint.toString());
		//DebugLogger.console("Endpoint:"+this.endPoint.toString());
		if(Line.distance(startPoint,endPoint) >= 10 && !this.endPoint.equals(this.startPoint))
		{
			
			ArrayList<ThreePoint> tempPoints = Line.bezierCurve(this.startPoint, this.endPoint, rand);
			this.centers = new ArrayList<ThreePoint>();
			this.nodes = new ArrayList<ThreePoint>();
			this.air = new ArrayList<ThreePoint>();
			this.redstone = new ArrayList<ThreePoint>();
			addPoints(tempPoints);
			PointMapping.addArrayToPoints(this.centers, this.ore);
			drawPoints();
			PointMapping.addArrayToPoints(this.air, "AIR");
			HydroStringer branchVein;
			drawPoints();
			for(int i=0;i<this.nodes.size();i++)
			{
				branchVein = new HydroStringer(nodes.get(i), (int)(this.strike*0.5), (int)(this.branch*1.25), "REDSTONE");
			}
		}
	}
	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		if(this.rand.nextInt(this.bonanza)==0)
		{
			Geode theGeode = new Geode(5, 5, 5, 2);
			for(int i=0;i<theGeode.points.length;i++)
			{
				ThreePoint newPoint = new ThreePoint(theGeode.points[i]);
				newPoint.x +=centerPoint.x;
				newPoint.y +=centerPoint.y;
				newPoint.z +=centerPoint.z;
				this.air.add(newPoint);
			}
			for(int i=0;i<theGeode.theShell.size();i++)
			{
				ThreePoint newPoint = new ThreePoint(theGeode.theShell.get(i));
				newPoint.x +=centerPoint.x;
				newPoint.y +=centerPoint.y;
				newPoint.z +=centerPoint.z;
				this.centers.add(newPoint);
			}
		}
		this.centers.add(centerPoint);
	}

}
