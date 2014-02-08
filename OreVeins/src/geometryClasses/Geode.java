package geometryClasses;

import java.util.ArrayList;

public class Geode extends Shape
{
	public ArrayList<ThreePoint> theShell;
	public Geode(int a, int b, int c, int thickness)
	{
		int dx=a - thickness;
		int dy=b - thickness;
		int dz=c - thickness;
		Ellipsoid shell = new Ellipsoid(a,b,c);
		Ellipsoid pocket = new Ellipsoid(dx,dy,dz);
		theShell = new ArrayList<ThreePoint>();
		createPointList(shell,pocket);
		this.points = pocket.points;
	}
	
	private void createPointList(Ellipsoid shell, Ellipsoid pocket) 
	{
		Boolean bool = true;
		for(int i=0;i<shell.points.length;i++)
		{
			for(int j=0;j<pocket.points.length;j++)
			{
				if(shell.points[i].equals(pocket.points[j]))
				{
					bool = false;
				}
			}
			if(bool)
			{
				this.theShell.add(shell.points[i]);
			}
			else
			{
				bool = true;
			}
		}
	}
}
