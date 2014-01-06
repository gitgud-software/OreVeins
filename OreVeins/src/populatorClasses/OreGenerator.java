package populatorClasses;

import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;
import geometryClasses.Vein;

import java.util.Random;

import org.bukkit.Chunk;

public class OreGenerator 
{

	public static void getOresFromChunk(Chunk chunk) 
	{
		Random rand = new Random();
		TwoPoint chunkPoint = new TwoPoint(chunk.getX(),chunk.getZ());
		chunkPoint.x = chunkPoint.x*16;
		chunkPoint.z = chunkPoint.z*16;

		if(rand.nextInt(10)==0)
		{
			ThreePoint start = new ThreePoint();
			start.x+=chunkPoint.x;
			start.z+=chunkPoint.z;
			Vein vein = new Vein(start, 100);
		}
	}

}
