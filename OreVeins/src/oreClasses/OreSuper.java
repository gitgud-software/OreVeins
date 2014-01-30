package oreClasses;

import java.util.ArrayList;
import java.util.Random;

import geometryClasses.ThreePoint;

public abstract class OreSuper 
{
	protected ThreePoint startPoint;
	protected ArrayList<ThreePoint> centers;
	protected String ore;
	protected int grade;
	protected Random rand;
	protected  void addPoints(ArrayList<ThreePoint> iterateOverPoints)
	{
		this.centers = new ArrayList<ThreePoint>();
		ArrayList<ThreePoint> section = new ArrayList<ThreePoint>();
		for(int i=0;i<iterateOverPoints.size();i++)
		{
			section = addSection(iterateOverPoints.get(i),section);
			if(section!=null)
				this.centers.addAll(section);
		}
	}
	protected abstract ArrayList<ThreePoint>  addSection(ThreePoint threePoint,ArrayList<ThreePoint> section); 
}
