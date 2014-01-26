package listeners;

import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;
import geometryClasses.Vein;

import java.util.Random;

import org.bukkit.Chunk;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.Defaults;
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
		//GOOOOLLLLDD vein system first nucleates out of the ethers here
		if(rand.nextInt(Defaults.gold.probability)==0)
		{
			DebugLogger.console("gold vein system nucleating");
			ThreePoint start = new ThreePoint();
			start.x+=chunkPoint.x;
			start.z+=chunkPoint.z;
			Vein vein = new Vein(start,Defaults.gold.strike,Defaults.gold.branch,"GOLD");
		}
	}

}
