package geometryClasses;



/*ThreePoint is coord within chunk, TwoPoint is coord of chunk
ThreePoint and TwoPoint coordinates are NOT the same! */

public class TwoPoint extends Point
{
	public int x;
	public int z;
	private Boolean isChunk;
	public TwoPoint(int x, int z,Boolean ch)
	{
		this.x = x;
		this.z = z;
		this.isChunk = ch;
	}
	public TwoPoint(String key)
	{
		String delims = "[:]";
		String[] tokens = key.split(delims);
		this.x = Integer.parseInt(tokens[0]);
		this.z = Integer.parseInt(tokens[1]);
	}
	public String toString(){
        return x + ":" + z; //CSV format
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
	@Override
	public String toChunkCoord() 
	{
		if(this.isChunk)
		{
			return x + ":" + z;
		}
		else
		{
			this.x = this.x>>4;
			this.z = this.z>>4;
			return this.x + ":" + this.z;
		}
		
	}
	@Override
	public TwoPoint toChunkCoordPoint() 
	{
		return this;
	}
}
