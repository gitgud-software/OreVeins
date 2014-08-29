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

import java.util.ArrayList;
import java.util.HashMap;

public class SupergeneEnrichment 
{
	private static HashMap<String, ArrayList<ThreePoint> > dirtPoints = new HashMap<String, ArrayList<ThreePoint> >();
	private static HashMap<String, ArrayList<ThreePoint> > gravelPoints = new HashMap<String, ArrayList<ThreePoint> >();
	private static HashMap<String, ArrayList<ThreePoint> > sandPoints = new HashMap<String, ArrayList<ThreePoint> >();
	private static HashMap<String, ArrayList<ThreePoint> > grassPoints = new HashMap<String, ArrayList<ThreePoint> >();
	private static HashMap<String, ArrayList<ThreePoint> > sandstonePoints = new HashMap<String, ArrayList<ThreePoint> >();

	public static void setDirtPoints(String ore, ThreePoint point) 
	{
		ArrayList<ThreePoint> points;
		if(!dirtPoints.containsKey(ore))
		{
			points = new ArrayList<ThreePoint>();
		}
		else
		{
			points = dirtPoints.get(ore);
		}
		points.add(point);
		dirtPoints.put(ore, points);
	}
	public static void setGravelPoints(String ore, ThreePoint point)
	{
		ArrayList<ThreePoint> points;
		if(!gravelPoints.containsKey(ore))
		{
			points = new ArrayList<ThreePoint>();
		}
		else
		{
			points = gravelPoints.get(ore);
		}
		points.add(point);
		gravelPoints.put(ore, points);
	}
	public static void setSandPoints(String ore, ThreePoint point)
	{
		ArrayList<ThreePoint> points;
		if(!sandPoints.containsKey(ore))
		{
			points = new ArrayList<ThreePoint>();
		}
		else
		{
			points = sandPoints.get(ore);
		}
		points.add(point);
		sandPoints.put(ore, points);
	}
	public static void setGrassPoints(String ore, ThreePoint point)
	{
		ArrayList<ThreePoint> points;
		if(!grassPoints.containsKey(ore))
		{
			points = new ArrayList<ThreePoint>();
		}
		else
		{
			points = grassPoints.get(ore);
		}
		points.add(point);
		grassPoints.put(ore, points);
	}
	public static void setSandstonePoints(String ore, ThreePoint point)
	{
		ArrayList<ThreePoint> points;
		if(!sandstonePoints.containsKey(ore))
		{
			points = new ArrayList<ThreePoint>();
		}
		else
		{
			points = sandstonePoints.get(ore);
		}
		points.add(point);
		sandstonePoints.put(ore, points);
	}

	
	
}
