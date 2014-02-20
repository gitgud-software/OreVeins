package geometryClasses;

import java.util.ArrayList;
import java.util.Random;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;

public class Paraboloid 
{
	public ArrayList<ThreePoint> centers;
	private int maxheight;
	private TwoPoint offset;
	private double expa;
	private double expb;
	
	public Paraboloid(TwoPoint change, TwoPoint offsets, int maxheight)
	{
		this.maxheight = maxheight;
		this.offset = offsets;
		expa = Math.log(change.x)/maxheight;
		expb = Math.log(change.z)/maxheight;
		centers = new ArrayList<ThreePoint>();
		addEllipses();
	}

	private void addEllipses() 
	{
		int a; int b;
		Random rand = new Random();
		int yrot = rand.nextInt(360);
		ThreePoint one = new ThreePoint(0,0,0);
		ThreePoint two = new ThreePoint(0,1,0);
		for(int y = 2;y<this.maxheight;y++)
		{
			a = (int) (Math.exp(y*expa)+ offset.x);
			b = (int) (Math.exp(y*expb)+ offset.z);
			Ellipse cross = new Ellipse(a,b);
			cross.alighnToPoints(one,two);
			cross.rotateY(yrot);
			for(int i=0;i<cross.points.length;i++)
			{
				cross.points[i].y = y;
				this.centers.add(cross.points[i]);
			}
		}	
	}
}
