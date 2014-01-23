package listeners;

import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;
import geometryClasses.Vein;

import java.util.Random;

import org.bukkit.Chunk;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
/* This class is the vein & ore controls spawn class. it gives the probability of all ores forming 
 * and instantiates the initial instances of them.
 * veins nucleate from these classes
 * diamond and bog iron are also made in these classes.
 * 
 */
public class OreGenerator 
{

	public static void getOresFromChunk(Chunk chunk) 
	{
		Random rand = new Random();
		TwoPoint chunkPoint = new TwoPoint(chunk.getX(),chunk.getZ());
		chunkPoint.x = chunkPoint.x*16;
		chunkPoint.z = chunkPoint.z*16;

		if(rand.nextInt(50)==0)
		{
			DebugLogger.console("Drawing new ores");
			ThreePoint start = new ThreePoint();
			start.x+=chunkPoint.x;
			start.z+=chunkPoint.z;
			Vein vein = new Vein(start, 200,75);
		}
	}

}
