package geometryClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


import fileIO.VeinChunkReadWrite;

public class Vein 
{
	public ThreePoint endPoint;
	public ThreePoint startPoint;
	public ArrayList<ThreePoint> centers;
	public String[][][] currentstuff;
	public int strike;
	private TwoPoint chunk;
	public Vein(ThreePoint startPoint, int strike)
	{
		this.startPoint = startPoint;
		Random rand = new Random();
		this.strike= strike;
		TwoPoint chunk = LineDrawingUtilityClass.getChunkCoords(startPoint);
		this.chunk = new TwoPoint(chunk.x,chunk.z);
		this.endPoint = new ThreePoint(startPoint.x+60, 127, startPoint.z+60);
		ArrayList<ThreePoint> line = LineDrawingUtilityClass.bezierCurve(startPoint,this.endPoint, rand);
		addCrossSection(line);
		VeinChunkReadWrite.parseCenters(this.chunk, "GOLD", this.centers);
	}
	
	private void addCrossSection(ArrayList<ThreePoint> line)
	{
		this.centers = new ArrayList<ThreePoint>();
		Shape crossSection = new Shape(1,3);
		crossSection.alighnToPoints(this.startPoint, this.endPoint);
		for(int i=0;i<line.size();i++)
		{
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
			}
		}
	}
}
