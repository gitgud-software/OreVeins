package listeners;

import java.util.HashMap;
import java.util.Random;


import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.BlockPopulator;
import fileIO.VeinChunkReadWrite;
import geometryClasses.LineDrawingUtilityClass;

public class OrePopulator implements Listener
{
	@EventHandler
	public void onGenerate(ChunkPopulateEvent event) 
	{
		//go populate unpopulated chunk
		Chunk chunk = event.getChunk();
		OreGenerator.getOresFromChunk(chunk);
		String key = LineDrawingUtilityClass.convertToKey(chunk.getX(),chunk.getZ());
		if(VeinChunkReadWrite.loadedMap!=null)
		{
			drawOtherOres(VeinChunkReadWrite.loadedMap);
			VeinChunkReadWrite.loadedMap=null;
		}
		String[][][] oldOres = VeinChunkReadWrite.readChunks(key,true);
		if(oldOres !=null)
		{
			//DebugLogger.console("Drawing veins in existing chunk");
			VeinDrawer.drawVein(oldOres, chunk);
		}
		VeinChunkReadWrite.deleteChunkInfo(key,true);
		VeinChunkReadWrite.populatedList.put(key, true);
	}
	
	private void drawOtherOres(HashMap<String, String[][][]> loadedMap) 
	{
		String delims = "[:]";
		int x , z;
		Chunk chunk;
		for(String entry: loadedMap.keySet())
		{
			String[] tokens = entry.split(delims);
			x = Integer.parseInt(tokens[0]);
			z = Integer.parseInt(tokens[1]);
			if(loadedMap.get(entry)!=null)
			{
				//DebugLogger.console("Drawing veins in loaded chunks " + entry);
				chunk = Bukkit.getWorlds().get(0).getChunkAt(x, z);
				VeinDrawer.drawVein(loadedMap.get(entry), chunk);
			}
		}
	}
}
