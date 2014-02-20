package mcListenersAndPopulators;

import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;

import java.util.Random;

import oreClasses.MetamorphicSystem;
import oreClasses.SedimentHostedDepositSystem;
import oreClasses.VeinSystem;
import oreClasses.VolcanicSystem;



import com.icloud.kevinmendoza.OreVeins.DebugLogger;

import defaultPackadge.Defaults;

public class OreGenerator 
{

	public static void GenerateOres(TwoPoint chunk) 
	{
		Random rand = new Random();
		TwoPoint chunkPoint = new TwoPoint(chunk.x,chunk.z,true);
		chunkPoint.x = chunkPoint.x*16;
		chunkPoint.z = chunkPoint.z*16;
		//GOOOOLLLLDD vein system first nucleates out of the ethers here\
		ThreePoint start = new ThreePoint();
		start.x+=chunkPoint.x;
		start.z+=chunkPoint.z;
		if(rand.nextInt((int)(100/Defaults.diamond.probToSpawn))==0)
		{
			VolcanicSystem pipe = new VolcanicSystem(start,"DIAMOND");
		}
		else if(rand.nextInt((int)(100/Defaults.coal.probToSpawn))==0)
		{
			SedimentHostedDepositSystem layers = new SedimentHostedDepositSystem(start,"COAL");
		}
		else if(rand.nextInt((int)(100/Defaults.bif.probToSpawn))==0)
		{
			SedimentHostedDepositSystem layers = new SedimentHostedDepositSystem(start,"BIF");
		}
		else if(rand.nextInt((int)(100/Defaults.lapiz.probToSpawn))==0)
		{
			MetamorphicSystem system = new MetamorphicSystem(start);
		}
		else if(rand.nextInt((int)(100/Defaults.emerald.probToSpawn))==0)
		{
			int branchType=1;
			double value = Defaults.emerald.chooseType.getRVar(rand);
			if(value <1)
			{
				branchType = 1;
			}
			else if(value <2)
			{
				value = 2;
			}
			else
			{
				value = 3;
			}
			VeinSystem sed = new VeinSystem("EMERALD",branchType, start, 0);
		}
		else if(rand.nextInt((int)(100/Defaults.redstone.probToSpawn))==0)
		{
			int branchType=1;
			double value = Defaults.redstone.chooseType.getRVar(rand);
			if(value <1)
			{
				branchType = 1;
			}
			else if(value <2)
			{
				value = 2;
			}
			else
			{
				value = 3;
			}
			VeinSystem sed = new VeinSystem("REDSTONE",branchType, start, 0);
		}
		else if(rand.nextInt((int)(100/Defaults.gold.probToSpawn))==0)
		{
			int branchType=1;
			double value = Defaults.gold.chooseType.getRVar(rand);
			if(value <1)
			{
				branchType = 1;
			}
			else if(value <2)
			{
				value = 2;
			}
			else
			{
				value = 3;
			}
			VeinSystem sed = new VeinSystem("GOLD",branchType, start, 0);
		}
		else if(rand.nextInt((int)(100/Defaults.iron.probToSpawn))==0)
		{
			int branchType=1;
			double value = Defaults.iron.chooseType.getRVar(rand);
			if(value <1)
			{
				branchType = 1;
			}
			else if(value <2)
			{
				value = 2;
			}
			else
			{
				value = 3;
			}
			VeinSystem sed = new VeinSystem("IRON",branchType, start, 0);
		}
	}



}
