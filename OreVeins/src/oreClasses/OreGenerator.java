package oreClasses;

import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;

import java.util.Random;


import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.Defaults;

public class OreGenerator 
{

	public static void GenerateOres(TwoPoint chunk) 
	{
		Random rand = new Random();
		TwoPoint chunkPoint = new TwoPoint(chunk.x,chunk.z);
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
