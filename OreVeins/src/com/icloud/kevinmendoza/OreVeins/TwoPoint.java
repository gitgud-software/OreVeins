package com.icloud.kevinmendoza.OreVeins;

public class TwoPoint 
{
	public int x;
	public int z;
	public TwoPoint(int x, int z)
	{
		this.x = x;
		this.z = z;
	}
	@Override
	public boolean equals(Object ob)
	{
		if(ob == null || ob != this.getClass())
		{
			return false;
		}
		else
		{
			TwoPoint object = (TwoPoint)ob;
			if(object.x == this.x && object.z == this.z)
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
