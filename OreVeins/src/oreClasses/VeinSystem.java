package oreClasses;

import java.util.ArrayList;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

import defaultPackadge.Defaults;
import defaultPackadge.PrimaryVein;
import defaultPackadge.SecondaryVein;
import defaultPackadge.TertiaryVein;
import defaultPackadgeHelpers.VeinSwitch;
import geometryClasses.Ellipse;
import geometryClasses.Ellipsoid;
import geometryClasses.Geode;
import geometryClasses.Line;
import geometryClasses.ThreePoint;

public class VeinSystem extends OreSuper 
{
	protected int grade;//frequency to number
	protected int strike;//number
	protected int levels;//number
	protected int bonanza;//frequency to number
	protected int branch;//frequency to number//number
	protected VeinSwitch veinSwitch;//frequency to number
	protected int width;//number
	protected int height;//number
	protected int type;// one for main, two for second, three for tert
	protected int iterationmax = 10;
	protected Ellipse section;
	protected ArrayList<ThreePoint> veinPoints;
	protected int currentIter;
	protected ArrayList<ThreePoint> air;
 	public VeinSystem(String ore, int type, ThreePoint start, int iteration)
	{
		currentIter = iteration;
		if(currentIter<6)
		{
			this.ore = ore;
			this.type = type;
			initializeDefaults();
			//DebugLogger.console(grade+":" +strike+":" +levels+":" +bonanza+":" +
			//branch+"."+ width+":" +height+":" +type);
			end = Line.getEndPoint(start, strike, this.rand, true);
			if(ore.contains("EMERALD"))
				air = new ArrayList<ThreePoint>();
			if(Line.distance(start,end) >= 1)
			{
				//DebugLogger.console("passed distance test");
				//branch prob is 
				nodes = new ArrayList<ThreePoint>();
				veinPoints = new ArrayList<ThreePoint>();
				centers = new ArrayList<ThreePoint>();	
				section = new Ellipse(width,height);
				section.alighnToPoints(start, end, rand);
				crossSection = section.points;
				veinPoints = Line.bezierCurve(start, end, rand);
				if(branch>1)
					branch = veinPoints.size()/branch;
				addPoints(veinPoints);
				PointMapping.addArrayToPoints(centers, ore);
				drawPoints();
				if(ore.contains("EMERALD"))
				{
					PointMapping.addArrayToPoints(air, "AIR");
					drawPoints();
				}
				VeinSystem branchVein;
				double newType;
				int branchType;
				//DebugLogger.console("branch number:"+nodes.size() + " iterationcurrent:"+currentIter+" veintype:"+ type + "centers"+centers.size());
				for(int i=0;i<nodes.size();i++)
				{
					newType = veinSwitch.getRVar(rand);
					if(newType <1)
					{
						branchType = 1;
					}
					else if(newType <2)
					{
						branchType = 2;
					}
					else
					{
						branchType = 3;
					}
					branchVein = new VeinSystem(ore, branchType,nodes.get(i),currentIter+1);
				}
			}
		}
	}

	protected void initializeDefaults()
	{
		//DebugLogger.console("initializing");
		
		if(ore.contains("IRON"))
		{
			if(type==1)
			{
				assignPrimary(iron.primaryVein);
			}
			else if(type==2)
			{
				assignSecondary(iron.secondaryVein);
			}
			else
			{
				assignTertiary(iron.tertiaryVein);
			}
		}
		else if(ore.contains("GOLD"))
		{
			if(type==1)
			{
				assignPrimary(gold.primaryVein);
			}
			else if(type==2)
			{
				assignSecondary(gold.secondaryVein);
			}
			else
			{
				assignTertiary(gold.tertiaryVein);
			}
		}
		else if(ore.contains("REDSTONE"))
		{
			if(type==1)
			{
				assignPrimary(redstone.primaryVein);
			}
			else if(type==2)
			{
				assignSecondary(redstone.secondaryVein);
			}
			else
			{
				assignTertiary(redstone.tertiaryVein);
			}
		}
		else if(ore.contains("EMERALD"))
		{
			if(type==1)
			{
				assignPrimary(emerald.primaryVein);
			}
			else if(type==2)
			{
				assignSecondary(emerald.secondaryVein);
			}
			else
			{
				assignTertiary(emerald.tertiaryVein);
			}
		}
		else if(ore.contains("DIAMOND"))
		{
			assignPrimary(diamond.veinDikes);
		}
		 section = new Ellipse(width,height);
	}
	
	protected void assignTertiary(TertiaryVein tertiaryVein) 
	{
		grade = (int)((100/tertiaryVein.grade.getRVar(rand)));//frequency to number
		strike = (int)(((tertiaryVein.strike.getRVar(rand))));//number
		bonanza= (int)(((100/tertiaryVein.bonanza.getRVar(rand))));
		branch=(int)(((tertiaryVein.branch.getRVar(rand))));
		veinSwitch=tertiaryVein.vswitch;   //?
		width = (int)(((tertiaryVein.width.getRVar(rand))));
		height = (int)(((tertiaryVein.height.getRVar(rand))));
	}

	protected void assignSecondary(SecondaryVein secondaryVein)
	{
		grade = (int)((100/secondaryVein.grade.getRVar(rand)));//frequency to number
		strike = (int)((secondaryVein.strike.getRVar(rand)));//number
		bonanza= (int)((100/secondaryVein.bonanza.getRVar(rand)));
		branch=(int)((secondaryVein.branch.getRVar(rand)));
		veinSwitch=secondaryVein.vswitch;   //?
		width = (int)((secondaryVein.width.getRVar(rand)));
		height = (int)((secondaryVein.height.getRVar(rand)));
		
	}

	protected void assignPrimary(PrimaryVein primaryVein) 
	{
		grade = (int)((100/primaryVein.grade.getRVar(rand)));//frequency to number
		strike = (int)((primaryVein.strike.getRVar(rand)));//number
		bonanza= (int)((100/primaryVein.bonanza.getRVar(rand)));
		branch=(int)((primaryVein.branch.getRVar(rand)));
		veinSwitch=primaryVein.vswitch;   //?
		width = (int)((primaryVein.width.getRVar(rand)));
		height = (int)((primaryVein.height.getRVar(rand)));
		
	}
	
	@Override
	protected void addSection(ThreePoint centerPoint) 
	{
		int breaking; int temp;
		ArrayList<Integer> chosen = new ArrayList<Integer>();
		if(crossSection.length/grade>0)
		{
			for(int i =0;i<crossSection.length/grade;i++)
			{
				breaking=0;
				ThreePoint tempPoint = new ThreePoint(centerPoint);
				while(true)
				{
					temp = rand.nextInt(crossSection.length);
					if(!chosen.contains(temp))
					{
						chosen.add(temp);
						break;
					}
					if(breaking>crossSection.length*1.5)
						break;
					breaking++;
				}
				tempPoint.offSet(crossSection[temp]);
				if(tempPoint.y>2 && tempPoint.y<128)
					centers.add(tempPoint);
			}
			chosen = null;
		}
		else
		{
			for(int i =0;i<crossSection.length;i++)
			{
				if(rand.nextInt(grade)==0)
				{
					ThreePoint tempPoint = new ThreePoint(centerPoint);
					tempPoint.offSet(crossSection[i]);
					if(tempPoint.y>2 && tempPoint.y<128)
						centers.add(tempPoint);
				}
			}
		}
		if(branch>1)
		{
			if(rand.nextInt(branch)==0)
			{
				ThreePoint newPoint = new ThreePoint(centerPoint);
				newPoint.offSet(crossSection[rand.nextInt(crossSection.length)]);
				nodes.add(newPoint);
			}
		}
		if(rand.nextInt(bonanza)==0)
		{
			ThreePoint newPoint = new ThreePoint(centerPoint);
			newPoint.offSet(crossSection[rand.nextInt(crossSection.length)]);
			makeBonanza(newPoint);
		}
	}
	
	@Override
	protected void makeBonanza(ThreePoint center)
	{
		if(!ore.contains("EMERALD"))
		{
			Ellipsoid bon = new Ellipsoid(rand.nextInt(3)+1, rand.nextInt(3)+1, rand.nextInt(3)+1);
			bon.rotateRandom(rand);
			for(int i=0;i<bon.points.length;i++)
			{
				ThreePoint newPoint = new ThreePoint(center);
				newPoint.offSet(bon.points[i]);
				centers.add(newPoint);
			}
		}
		else
		{
			Geode bon = new Geode(emerald.geode.depth.getRVar(rand),
										emerald.geode.height.getRVar(rand),
										emerald.geode.width.getRVar(rand),
										emerald.geode.thickness.getRVar(rand));
			for(int i=0;i<bon.theShell.size();i++)
			{
				ThreePoint newPoint = new ThreePoint(center);
				newPoint.offSet(bon.theShell.get(i));
				centers.add(newPoint);
			}
			for(int i=0;i<bon.points.length;i++)
			{
				ThreePoint newPoint = new ThreePoint(center);
				newPoint.offSet(bon.points[i]);
				air.add(newPoint);
			}
		}
	}

}
