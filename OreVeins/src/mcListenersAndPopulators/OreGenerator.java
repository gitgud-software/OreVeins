/*******************************************************************************
 * OreVeins realistic ore distribution plugin
 * Copyright (C) 2014  Kevin Mendoza
 * kevinmendoza@mac.com
 * Major Contributors: Kevin Song, Alex Lin, Darren Chang, Drew Parliament, Zeno Hao
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package mcListenersAndPopulators;

import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;

import java.util.Random;

import org.bukkit.Bukkit;

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
		ThreePoint start = startPoint(chunkPoint);
		if(rand.nextInt((int)(100/Defaults.diamond.probToSpawn))==0)
		{
			DebugLogger.console("Made Diamond Pipe at"+start.toString());
			VolcanicSystem pipe = new VolcanicSystem(start,"DIAMOND");
		}
		 start = startPoint(chunkPoint);
		if(rand.nextInt((int)(100/Defaults.coal.probToSpawn))==0)
		{
			DebugLogger.console("Made Coal Seam at"+start.toString());
			SedimentHostedDepositSystem layers = new SedimentHostedDepositSystem(start,"COAL");
		}
		 start = startPoint(chunkPoint);
		if(rand.nextInt((int)(100/Defaults.bif.probToSpawn))==0)
		{
			DebugLogger.console("Made Banded Iron Formation (BIF) at"+start.toString());
			SedimentHostedDepositSystem layers = new SedimentHostedDepositSystem(start,"BIF");
		}
		 start = startPoint(chunkPoint);
		if(rand.nextInt((int)(100/Defaults.lapiz.probToSpawn))==0)
		{
			DebugLogger.console("Made Lapiz Deposit at"+start.toString());
			MetamorphicSystem system = new MetamorphicSystem(start);
		}
		 start = startPoint(chunkPoint);
		if(rand.nextInt((int)(100/Defaults.emerald.probToSpawn))==0)
		{
			DebugLogger.console("Made Emerald Vein at"+start.toString());
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
		 start = startPoint(chunkPoint);
		if(rand.nextInt((int)(100/Defaults.redstone.probToSpawn))==0)
		{
			DebugLogger.console("Made Redstone Vein at"+start.toString());
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
		 start = startPoint(chunkPoint);
		if(rand.nextInt((int)(100/Defaults.gold.probToSpawn))==0)
		{
			DebugLogger.console("Made Gold vein at"+start.toString());
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
		 start = startPoint(chunkPoint);
		if(rand.nextInt((int)(100/Defaults.iron.probToSpawn))==0)
		{
			DebugLogger.console("made Iron vein at"+start.toString());
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

	private static ThreePoint startPoint(TwoPoint chunk)
	{
		Random rand = new Random();
		ThreePoint start = new ThreePoint();
		start.x+=chunk.x;
		start.z+=chunk.z;
		int height = rand.nextInt(Bukkit.getServer().getWorld("world").getHighestBlockAt(start.x, start.z).getY())+1;
		start.y = height;
		return start;
	}

}
