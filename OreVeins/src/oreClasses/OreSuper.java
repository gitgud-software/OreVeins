package oreClasses;

import java.util.ArrayList;
import java.util.Random;

import geometryClasses.ThreePoint;

public abstract class OreSuper 
{
	protected ThreePoint startPoint;//starting point of the vein
	protected ArrayList<ThreePoint> centers;//all the points that will be drawn by the vein
	protected String ore;
	protected int grade;
	protected Random rand;
	protected ThreePoint[] crossSection;
	protected void addPoints(ArrayList<ThreePoint> iterateOverPoints)
	{
		this.centers = new ArrayList<ThreePoint>();
		for(int i=0;i<iterateOverPoints.size();i++)
		{
			addSection(iterateOverPoints.get(i));
		}
	}
	protected abstract void  addSection(ThreePoint centerPoint); 
}
