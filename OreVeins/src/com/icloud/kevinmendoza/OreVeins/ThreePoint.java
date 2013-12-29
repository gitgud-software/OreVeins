package com.icloud.kevinmendoza.OreVeins;

import java.io.Serializable;
import java.util.Random;

/*ThreePoint is coord within chunk, TwoPoint is coord of chunk
ThreePoint and TwoPoint coordinates are NOT the same! */

public class ThreePoint implements Serializable {
	public int x;
	public int y;
	public int z;
	public ThreePoint(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	public ThreePoint()
	{
		Random rand = new Random();
		this.x =(int)(16*rand.nextDouble());
		this.y =(int)(200*rand.nextDouble());
		this.z =(int)(16*rand.nextDouble());
	}
@Override	
public boolean equals(Object ob)
	{
		if(ob == null || ob.getClass() != this.getClass())
		{
			return false;
		}
		else
		{
			ThreePoint obj = (ThreePoint)ob;
			if(obj.x == this.x && obj.y == this.y && obj.z == this.z)
			{
				return true;
			}
			else
			{
				return false;
			}
		}
	}
}
