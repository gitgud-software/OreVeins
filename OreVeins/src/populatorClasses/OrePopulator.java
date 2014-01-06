package populatorClasses;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import fileIO.VeinChunkReadWrite;
import geometryClasses.LineDrawingUtilityClass;

public class OrePopulator extends BlockPopulator
{
	@Override
	public void populate(World world, Random rand, Chunk chunk) 
	{
		OreReplacer.removeOres(chunk);
		//go populate unpopulated chunk
		OreGenerator.getOresFromChunk(chunk);
		if(VeinChunkReadWrite.loadedMap!=null)
		{
			drawOtherOres(VeinChunkReadWrite.loadedMap);
			VeinChunkReadWrite.loadedMap=null;
		}
		String[][][] oldOres = VeinChunkReadWrite.readChunks(LineDrawingUtilityClass.convertToKey(chunk.getX(),chunk.getZ()),true);
		if(oldOres !=null)
		{
			//DebugLogger.console("Drawing veins in existing chunk");
			VeinDrawer.drawVein(oldOres, chunk);
		}
		VeinChunkReadWrite.deleteChunkInfo(LineDrawingUtilityClass.convertToKey(chunk.getX(),chunk.getZ()),true);
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
