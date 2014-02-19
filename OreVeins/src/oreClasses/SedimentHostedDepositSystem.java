package oreClasses;

import geometryClasses.Ellipse;
import geometryClasses.Line;
import geometryClasses.ThreePoint;

import java.util.ArrayList;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

public class SedimentHostedDepositSystem extends OreSuper 
{
	private int strike;
	private int width;
	private int height;
	private int grade;
	private int levels;
	public SedimentHostedDepositSystem(ThreePoint start, String ore)
	{
		this.ore = ore;
		initializeDefaults();
		ThreePoint base = new ThreePoint(0,0,0);
		ThreePoint end = Line.getEndPoint(base, strike/4, rand,true);
		if(Line.distance(start, end)/levels > 2)
		{
			ArrayList<ThreePoint> levelOffset = Line.bresenHamAlgo(start,end);
			end = Line.getEndPoint(start, strike, rand, false);
			nodes = Line.bezierCurve(start, end, rand);
			int max = levelOffset.size();
			int randindex;
			ThreePoint offset;
			for(int i=0;i<levels;i++)
			{
				randindex = rand.nextInt(max);
				offset = levelOffset.get(randindex);
				addSection(offset);
				PointMapping.addArrayToPoints(centers, ore);
			}
			drawPoints();
		}
	}
	
	protected void initializeDefaults()
	{
		if(ore.contains("BIF"))
		{
			strike = (int) bif.strike.getRVar(rand);
			width = (int) bif.width.getRVar(rand);
			height = (int) bif.height.getRVar(rand);
			grade = (int) (1/bif.grade.getRVar(rand));
			levels = (int) bif.Levels.getRVar(rand);
			ore = "IRON";
		}
		else if(ore.contains("COAL"))
		{
			strike = (int) coal.strike.getRVar(rand);
			width = (int) coal.width.getRVar(rand);
			height = (int) coal.height.getRVar(rand);
			grade = (int) (1/coal.grade.getRVar(rand));
			levels = (int) coal.Levels.getRVar(rand);
		}
		Ellipse newEllipse = new Ellipse(width,height);
		crossSection = newEllipse.points;
	}

	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		ThreePoint tempPoint;
		for(int i=0;i<nodes.size();i++)
		{
			for(int j=0;j<crossSection.length;j++)
			{
				if(rand.nextInt(grade)==0)
				{
					tempPoint = new ThreePoint(centerPoint);
					tempPoint.offSet(crossSection[j]);
					tempPoint.offSet(nodes.get(i));
					centers.add(tempPoint);
				}
			}
		}
	}

	@Override
	protected void makeBonanza(ThreePoint center) {
		// TODO Auto-generated method stub
		
	}


}
