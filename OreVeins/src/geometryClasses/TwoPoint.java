package geometryClasses;



/*ThreePoint is coord within chunk, TwoPoint is coord of chunk
ThreePoint and TwoPoint coordinates are NOT the same! */

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
		if(ob == null || ob.getClass() != this.getClass())
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
	public String location() 
	{
		String theX = new Integer(this.x).toString();
		String theZ = new Integer(this.z).toString();
		String filename = theX + ":" + theZ;
		return filename;
	}
}
