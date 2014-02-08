package oreClasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import mcListenersAndPopulators.VeinDrawer;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;

public abstract class OreSuper 
{
	protected ThreePoint startPoint;//starting point of the vein
	protected ArrayList<ThreePoint> centers;//all the points that will be drawn by the vein
	protected String ore;
	protected int grade;
	protected Random rand;
	protected ThreePoint[] crossSection;
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
		DebugLogger.console("drawing veins");
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
	protected abstract void  addSection(ThreePoint centerPoint); 
}
