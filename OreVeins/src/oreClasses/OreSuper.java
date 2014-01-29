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
		ArrayList<ThreePoint> section = new ArrayList<ThreePoint>();
		for(int i=0;i<iterateOverPoints.size();i++)
		{
			this.centers.addAll(addSection(iterateOverPoints.get(i),section));
		}
	}
	protected abstract ArrayList<ThreePoint>  addSection(ThreePoint threePoint,ArrayList<ThreePoint> section); 
}
