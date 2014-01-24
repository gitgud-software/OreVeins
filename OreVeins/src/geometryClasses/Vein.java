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
		Random rand = new Random();
		this.startPoint = startPoint;
		this.endPoint = LineDrawingUtilityClass.getEndPoint(startPoint, strike,rand, true);
		double r = Math.sqrt((startPoint.x -endPoint.x)*(startPoint.x -endPoint.x) + 
				(startPoint.y-endPoint.y)*(startPoint.y-endPoint.y)
				+ (startPoint.z-endPoint.z)*(startPoint.z-endPoint.z));
		
		if(!this.endPoint.equals(startPoint) && r >10)
		{
			TwoPoint chunk = LineDrawingUtilityClass.getChunkCoords(startPoint);
			this.chunk = new TwoPoint(chunk.x,chunk.z);
			this.startPoint = startPoint;
			this.strike= strike;
			this.branch = branch;
			ArrayList<ThreePoint> line = LineDrawingUtilityClass.bezierCurve(startPoint,this.endPoint, rand);
			//DebugLogger.console("6");
			ArrayList<ThreePoint> nodes = addCrossSection(line, rand);
			//DebugLogger.console("7");
			line = null;
			//DebugLogger.console("entering loop4");
			//DebugLogger.console("8");
			//DebugLogger.console("entering loop5");
			if(nodes.size()>1)
			{
				for(int i=0;i<nodes.size();i++)
				{
					//DebugLogger.console("making vein");
					Vein vein = new Vein(nodes.get(i), (int)(this.strike*.1), (int) (this.branch*.5));
				}
			}
			VeinChunkReadWrite.parseCenters(this.chunk, "GOLD", this.centers);
			this.centers = null;
			nodes = null;
		}
	}
	
	private ArrayList<ThreePoint> addCrossSection(ArrayList<ThreePoint> line,Random rand)
	{
		this.centers = new ArrayList<ThreePoint>();
		Shape crossSection = new Shape(3,7);
		
		ArrayList<ThreePoint> nodes = new ArrayList<ThreePoint>();
		crossSection.alighnToPoints(this.startPoint, this.endPoint, rand);
		//DebugLogger.console("entering loop3");
		for(int i=0;i<line.size();i++)
		{
			if(rand.nextInt(this.branch)==0)//branch freq
			{//adding in branching
				nodes.add(line.get(i));
			}
			if(rand.nextInt(100)==0)//bonanza freq
			{//bonanzas
				Shape bonanza = new Shape(rand.nextInt(5)+1,rand.nextInt(5)+1, rand.nextInt(5)+1);
				bonanza.rotateRandom(rand);
				for(int j=0;j<bonanza.points.length;j++)
				{
					int y = bonanza.points[j].y + line.get(i).y;
					ThreePoint newPoint = new ThreePoint(bonanza.points[j].x + line.get(i).x,y,
							bonanza.points[j].z + line.get(i).z);
					if(!this.centers.contains(newPoint) && y > 2 && y < 127)
					{
						//DebugLogger.console("adding point!!"+ y);
						this.centers.add(newPoint);
					}
				}
			}
			else
			{//grade
				for(int j=0;j<crossSection.points.length;j++)
				{
					int y = crossSection.points[j].y + line.get(i).y;
					ThreePoint newPoint = new ThreePoint(crossSection.points[j].x + line.get(i).x,y,
							crossSection.points[j].z + line.get(i).z);
					if(!this.centers.contains(newPoint) && y > 2 && y < 127)
					{
						//DebugLogger.console("adding point!!"+ y);
						//if(rand.nextInt(15)==0)//grade code
							this.centers.add(newPoint);
					}
				}
			}
		}
		return nodes;
	}
}
