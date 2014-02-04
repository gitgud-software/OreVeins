package com.icloud.kevinmendoza.OreVeins;

import fileIO.VeinChunkReadWrite;
import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;

import java.util.ArrayList;
import java.util.HashMap;

public class PointMapping 
{
	private static HashMap<String,String[][][]> addedPoints;
	//^contains all points added in by ore generation
	private static HashMap<String,Boolean> populatedList;
	//^contains list of all chunks already generated
	private static HashMap<String,Boolean> loadedAndGenerated;
	//^contains list of all chunks already generated and Loaded?
	
	private static void initializeMaps()
	{
		addedPoints = new HashMap<String,String[][][]>();
		populatedList = new HashMap<String,Boolean>();
		loadedAndGenerated = new HashMap<String,Boolean>();
	}
	
	public static void populatePopList()
	{
		initializeMaps();
		HashMap<String,Boolean> temp;
		temp = VeinChunkReadWrite.read();
		if(temp!=null)
		{
			populatedList=temp;
		}
	}
	
	public static void addToPopList(TwoPoint chunk)
	{
		populatedList.remove(chunk.toString());
		populatedList.put(chunk.toString(),true);
	}
	
	private static void mergeStrings(String key, String[][][] newStringArray) 
	{
		String[][][] oldOreArray = addedPoints.get(key);
		String oldOre;
		String newOre;
		if(oldOreArray!=null)
		{
			for(int x=0;x<16;x++)
			{
				for(int z=0;z<16;z++)
				{
					for(int y=1;y<128;y++)
					{
						newOre = newStringArray[x][y][z];
						oldOre = oldOreArray[x][y][z];
						if(newOre!=null)
						{
							if(oldOre==null || oldOre.contains("COAL"))
							{
								oldOreArray[x][y][x] = newOre;
							}
						}
					}
				}
			}
			addedPoints.remove(key);
			addedPoints.put(key, oldOreArray);
		}
		else
		{
			addedPoints.put(key, newStringArray);
		}
	}
	
	public static void addToPoints(TwoPoint chunk) 
	{
		
		String[][][] newStringArray = VeinChunkReadWrite.read(chunk.toString());
		if(newStringArray!=null)
			mergeStrings(chunk.toString(),newStringArray);
	}
	
	public static void addArrayToPoints(ArrayList<ThreePoint> thePoints, String blockType)
	{
		String key;
		HashMap<String,String[][][]> tempHashMap = new HashMap<String,String[][][]>();
		ThreePoint thePoint;
		String[][][] theMatrix;
		for(int i =0;i<thePoints.size();i++)
		{
			key = thePoints.get(i).toChunkCoord();
			if(thePoints.get(i).y<127 && thePoints.get(i).y > 1)
			{
				if(tempHashMap.containsKey(key))
				{
					theMatrix = tempHashMap.get(key);
					thePoint = thePoints.get(i);
					thePoint.shiftCoords();
					theMatrix[thePoint.dx][thePoint.y][thePoint.dz] = blockType;
					tempHashMap.put(key, theMatrix);
				}
				else
				{
					thePoint = thePoints.get(i);
					thePoint.shiftCoords();
					theMatrix = new String[16][128][16];
					theMatrix[thePoint.dx][thePoint.y][thePoint.dz] = blockType;
					tempHashMap.put(key, theMatrix);
				}
			}
		}
		for(String entry: tempHashMap.keySet())
		{
			mergeStrings(entry,tempHashMap.get(entry));
		}
	}
	
	public static void removePoints(TwoPoint chunk) 
	{
		addedPoints.remove(chunk.toString());
	}
	
	public static void addToLoaded(TwoPoint chunk) 
	{
		loadedAndGenerated.remove(chunk.toString());
		loadedAndGenerated.put(chunk.toString(),true);
	}
	
	public static void removeFromLoaded(TwoPoint chunk) 
	{
		loadedAndGenerated.remove(chunk.toString());
	}
	
	public static HashMap<String,String[][][]> getDrawListAndRemove() 
	{
		HashMap<String,String[][][]> drawList = new HashMap<String,String[][][]>();
		for(String entry : loadedAndGenerated.keySet())
		{
			if(addedPoints.containsKey(entry))
			{
				drawList.put(entry,addedPoints.get(entry));
				addedPoints.remove(entry);
			}
		}
		return drawList;
	}
	
	public static void savePopulatedList() 
	{
		VeinChunkReadWrite.write(populatedList);
	}
	public static void savePoints()
	{
		for(String entry : addedPoints.keySet())
		{
			VeinChunkReadWrite.write(entry, addedPoints.get(entry));
		}
		addedPoints.clear();
	}
	public static Boolean popMapExists(String entry)
	{
		if(populatedList.containsKey(entry))
			return true;
		else
			return false;
	}
	
	public static void refreshLoadedPoints() 
	{
		savePoints();
		for(String entry : loadedAndGenerated.keySet())
		{
			addedPoints.put(entry,VeinChunkReadWrite.read(entry));
		}
	}
	public static Boolean getPop(String string) 
	{
		return populatedList.get(string);
	}
	public static Boolean getLoaded(String string) 
	{
		return loadedAndGenerated.get(string);
	}
	public static String[][][] getPoints(String string) 
	{
		return addedPoints.get(string);
	}

}
