package com.icloud.kevinmendoza.OreVeins;

import java.util.Random;

/*ThreePoint is coord within chunk, TwoPoint is coord of chunk
ThreePoint and TwoPoint coordinates are NOT the same! */

public class ThreePoint {
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
		this.x =rand.nextInt(16);
		this.y =rand.nextInt(200);
		this.z =rand.nextInt(16);
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
