package geometryClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;


import fileIO.VeinChunkReadWrite;

public class Vein 
{
	public ThreePoint endPoint;
	public ThreePoint startPoint;
	public ArrayList<ThreePoint> centers;
	public int strike;
	private TwoPoint chunk;
	private int branch;
	public Vein(ThreePoint startPoint, int strike, int branch)
	{
		if(strike>10)
		{
			Random rand = new Random();
			//DebugLogger.console("1");
			TwoPoint chunk = LineDrawingUtilityClass.getChunkCoords(startPoint);
			//DebugLogger.console("2");
			this.chunk = new TwoPoint(chunk.x,chunk.z);
			//DebugLogger.console("3");
			this.endPoint = LineDrawingUtilityClass.getEndPoint(startPoint, strike,rand, true);
			//DebugLogger.console("4");
			this.startPoint = startPoint;
			this.strike= strike;
			this.branch = branch;
			//DebugLogger.console("5");
		//	DebugLogger.console( "startpoint" + startPoint.x +" "+ startPoint.y +" "+ startPoint.z);
		//	DebugLogger.console( "endPoint" + this.endPoint.x +" "+ this.endPoint.y +" "+ this.endPoint.z);
			ArrayList<ThreePoint> line = LineDrawingUtilityClass.bezierCurve(startPoint,this.endPoint, rand);
			//DebugLogger.console("6");
			ArrayList<ThreePoint> nodes = addCrossSection(line, rand);
			//DebugLogger.console("7");
			line = null;
			VeinChunkReadWrite.parseCenters(this.chunk, "GOLD", this.centers);
			//DebugLogger.console("8");
			this.centers = null;
			if(nodes.size()>1)
			{
				for(int i=0;i<nodes.size();i++)
				{
					//DebugLogger.console("making vein");
					Vein vein = new Vein(nodes.get(i), (int)(this.strike*.75), this.branch);
				}
			}
			nodes = null;
		}
	}
	
	private ArrayList<ThreePoint> addCrossSection(ArrayList<ThreePoint> line,Random rand)
	{
		this.centers = new ArrayList<ThreePoint>();
		Shape crossSection = new Shape(1,3);
		ArrayList<ThreePoint> nodes = new ArrayList<ThreePoint>();
		crossSection.alighnToPoints(this.startPoint, this.endPoint);
		for(int i=0;i<line.size();i++)
		{
			if(rand.nextInt(this.branch)==0)
			{
				ThreePoint node = new ThreePoint();
				node.x = line.get(i).x;
				node.y = line.get(i).y;
				node.z = line.get(i).z;
				nodes.add(node);
			}
			this.centers.add(line.get(i));
			/*
			for(int j=0;j<crossSection.points.length;j++)
			{
				int y = crossSection.points[j].y + line.get(i).y;
				ThreePoint newPoint = new ThreePoint(crossSection.points[j].x + line.get(i).x,
													 y,
													 crossSection.points[j].z + line.get(i).z);
				if(!this.centers.contains(newPoint) && y > 2 && y < 127)
				{
					//DebugLogger.console("adding point!!"+ y);
					this.centers.add(newPoint);
				}
			}*/
		}
		return nodes;
	}
}
