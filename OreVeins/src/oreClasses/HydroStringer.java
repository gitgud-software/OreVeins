package oreClasses;

import java.util.ArrayList;

import java.util.Random;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;


import geometryClasses.LineDrawingUtilityClass;
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
		this.ore = ore;
		this.rand = new Random();
		this.startPoint = startPoint;
		this.startPoint.y = 60;
		this.endPoint = LineDrawingUtilityClass.getEndPoint(startPoint, strike,rand, true);
		double r = Math.sqrt((startPoint.x -endPoint.x)*(startPoint.x -endPoint.x) + 
				(startPoint.y-endPoint.y)*(startPoint.y-endPoint.y)
				+ (startPoint.z-endPoint.z)*(startPoint.z-endPoint.z));
		if(!this.endPoint.equals(startPoint) && r >10 && branch > 5)
		{
			this.nodes = new ArrayList<ThreePoint>();
			this.startPoint = startPoint;
			this.strike= strike;
			this.branch = branch;
			//DebugLogger.console("making bezier");
			ArrayList<ThreePoint> line = LineDrawingUtilityClass.bezierCurve(startPoint,this.endPoint, rand);
			//DebugLogger.console("adding section");
			addPoints(line);
			line = null;
			if(nodes.size()>1)
			{
				DebugLogger.console("Branching:"+nodes.size());
				for(int i=0;i<nodes.size();i++)
				{
					//DebugLogger.console("making vein");
					HydroStringer vein = new HydroStringer(nodes.get(i), (int)(this.strike*.3), (int) (this.branch*.75),"REDSTONE");
				}
			}
		//	DebugLogger.console("parsing");
			PointMapping.addArrayToPoints(this.centers, this.ore);
			this.centers = null;
			nodes = null;
		}
	}
	
	@Override
	protected ArrayList<ThreePoint> addSection(ThreePoint threePoint, ArrayList<ThreePoint> section) 
	{
		section.clear();
		if(rand.nextInt(this.branch)==0)//branch freq
		{//adding in branching
			//this.nodes.add(line.get(i));
		}
		if(rand.nextInt(this.branch)==0)//bonanza freq
		{
			nodes.add(threePoint);
			Shape bonanza = new Shape(rand.nextInt(5)+1,rand.nextInt(5)+1, rand.nextInt(5)+1);
			bonanza.rotateRandom(rand);
			for(int j=0;j<bonanza.points.length;j++)
			{
				int y = bonanza.points[j].y +threePoint.y;
				if(y > 2 && y < 127)
				{
					ThreePoint newPoint = new ThreePoint(bonanza.points[j].x + threePoint.x,y,
							bonanza.points[j].z + threePoint.z);
					if(!this.centers.contains(newPoint))
					{
						section.add(newPoint);
					}
				}
			}
		}
		else
		{
			if(threePoint.y>2 && threePoint.y<256)
				section.add(threePoint);
			/*//DebugLogger.console("adding cross seect to point");
			for(int j=0;j<crossSection.points.length;j++)
			{
				int y = crossSection.points[j].y + line.get(i).y;
				if(y > 2 && y < 127)
				{
					ThreePoint newPoint = new ThreePoint(crossSection.points[j].x + line.get(i).x,y,
							crossSection.points[j].z + line.get(i).z);
					if(!this.centers.contains(newPoint))
					{
						//if(rand.nextInt(15)==0)//grade code
						this.centers.add(newPoint);
					}
				}
			}*/
		}
		return section;
	}
}
