package oreClasses;

import java.util.ArrayList;

import org.bukkit.Bukkit;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

import geometryClasses.Paraboloid;
import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;

public class VolcanicSystem extends OreSuper
{
	public TwoPoint change;
	public TwoPoint offset;
	public int grade;
	public int branch;
	public double branchfreq;
	
	public VolcanicSystem(ThreePoint start,String ore)
	{
		start.y=2;
		initializeDefaults();
		int height = Bukkit.getServer().getWorld("world").getHighestBlockAt(start.x, start.z).getY();
		centers = new ArrayList<ThreePoint>();
		branchfreq = 1/(double)(branch);
		Paraboloid shape = new Paraboloid(change, offset,height);
		for(int i=0;i<shape.centers.size();i++)
		{
			ThreePoint point = new ThreePoint(shape.centers.get(i));
			point.offSet(start);
			addSection(point);
		}
		PointMapping.addArrayToPoints(centers, ore);
		drawPoints();
	}

	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		if(rand.nextInt(this.grade)==0)
		{
			this.centers.add(centerPoint);
		}
	}

	protected void initializeDefaults()
	{
		offset = new TwoPoint((int)(diamond.diatreme.offsetx.getRVar(rand))
				, (int)(diamond.diatreme.offsety.getRVar(rand)), false);
		change = new TwoPoint((int)(diamond.diatreme.changex.getRVar(rand))
				, (int)(diamond.diatreme.changey.getRVar(rand)), false);
		grade = (int)(100/(diamond.diatreme.grade.getRVar(rand)));
		branch = (int)(diamond.veinDikes.branch.getRVar(rand));
	}

	@Override
	protected void makeBonanza(ThreePoint center) 
{
		// TODO Auto-generated method stub
		
	}

}
