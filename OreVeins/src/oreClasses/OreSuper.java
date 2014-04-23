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
package oreClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import mcListenersAndPopulators.VeinDrawer;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

import defaultPackadge.BIF;
import defaultPackadge.Coal;
import defaultPackadge.Defaults;
import defaultPackadge.Diamond;
import defaultPackadge.Emerald;
import defaultPackadge.Gold;
import defaultPackadge.Iron;
import defaultPackadge.Lapiz;
import defaultPackadge.PrimaryVein;
import defaultPackadge.Redstone;
import defaultPackadge.SecondaryVein;
import defaultPackadge.TertiaryVein;
import defaultPackadgeHelpers.Bonanza;
import defaultPackadgeHelpers.Branch;
import defaultPackadgeHelpers.Grade;
import defaultPackadgeHelpers.Height;
import defaultPackadgeHelpers.Strike;
import defaultPackadgeHelpers.VeinSwitch;
import defaultPackadgeHelpers.Width;

import geometryClasses.Ellipse;
import geometryClasses.Line;
import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;

public abstract class OreSuper 
{
	protected String ore;
	protected ThreePoint start;//starting point of the vein
	protected ThreePoint end;
	protected ArrayList<ThreePoint> nodes= new ArrayList<ThreePoint>();;//branch points
	protected ArrayList<ThreePoint> centers = new ArrayList<ThreePoint>();//all the points that will be drawn by the vein
	protected ThreePoint[] crossSection;//cross section of the vein
	protected Random rand = new Random();
	protected Coal coal = Defaults.coal;
	protected BIF bif = Defaults.bif;
	protected Iron iron = Defaults.iron;
	protected Gold gold = Defaults.gold;
	protected Redstone redstone = Defaults.redstone;
	protected Lapiz lapiz = Defaults.lapiz;
	protected Emerald emerald = Defaults.emerald;
	protected Diamond diamond = Defaults.diamond;
	
	protected abstract void initializeDefaults();
	
	protected abstract void  addSection(ThreePoint centerPoint);
	
	protected abstract void makeBonanza(ThreePoint center);
	
	protected void addPoints(ArrayList<ThreePoint> iterateOverPoints)
	{
		this.centers = new ArrayList<ThreePoint>();
		for(int i=0;i<iterateOverPoints.size();i++)
		{
			addSection(iterateOverPoints.get(i));
		}
	}
	
	public void drawPoints()
	{
		//DebugLogger.console("drawing veins");
		HashMap<String,String[][][]> drawableChunks = PointMapping.getDrawListAndRemove();
		TwoPoint drawingChunk;
		Chunk chunkObj;
		for(String entry: drawableChunks.keySet())
		{
			drawingChunk = new TwoPoint(entry);
			chunkObj = Bukkit.getWorlds().get(0).getChunkAt(drawingChunk.x, drawingChunk.z);
			VeinDrawer.drawVein(drawableChunks.get(entry), chunkObj);
		}
	}
	
}
