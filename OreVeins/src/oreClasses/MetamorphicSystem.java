package oreClasses;

import com.icloud.kevinmendoza.OreVeins.PointMapping;

import geometryClasses.Ellipsoid;
import geometryClasses.ThreePoint;

public class MetamorphicSystem extends OreSuper 
{
	public int width;
	public int height;
	public int depth;
	public int branch;

	public MetamorphicSystem(ThreePoint start)
	{
		initializeDefaults();
		Ellipsoid theMetaArea = new Ellipsoid(width,height,depth);
		int size = theMetaArea.points.length;
		int iteration;
		ThreePoint tempPoint;
		for(int i=0;i<branch;i++)
		{
			iteration = rand.nextInt(size);
			tempPoint = new ThreePoint(theMetaArea.points[iteration]);
			tempPoint.offSet(start);
			addSection(tempPoint);
			PointMapping.addArrayToPoints(centers, "LAPIZ");
		}
		drawPoints();
	}
	
	protected void initializeDefaults()
	{
		width = (int) lapiz.mineralizationwidth.getRVar(rand);
		height = (int) lapiz.mineralizationlength.getRVar(rand);
		depth = (int) lapiz.mineralizationdepth.getRVar(rand);
		branch = (int) lapiz.branch.getRVar(rand);
	}

	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		int a = (int) lapiz.lodedepth.getRVar(rand);
		int b = (int) lapiz.lodelength.getRVar(rand);
		int c =  (int) lapiz.lodewidth.getRVar(rand);
		int grade = (int)(100/lapiz.grade.getRVar(rand));
		Ellipsoid soid = new Ellipsoid(a,b,c);
		soid.rotateRandom(rand);
		for(int i=0;i<soid.points.length;i++)
		{
			if(rand.nextInt(grade)==0)
			{
				ThreePoint offset = new ThreePoint(centerPoint);
				offset.offSet(soid.points[i]);
				this.centers.add(offset);
			}
		}
	}

	@Override
	protected void makeBonanza(ThreePoint center) {
		// TODO Auto-generated method stub
		
	}

}
