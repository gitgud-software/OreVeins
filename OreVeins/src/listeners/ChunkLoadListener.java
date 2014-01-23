package listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;



import fileIO.VeinChunkReadWrite;
import geometryClasses.LineDrawingUtilityClass;

public final class ChunkLoadListener implements Listener  
{

	@EventHandler
	public void onLoad(ChunkLoadEvent event) 
	{
		String key = LineDrawingUtilityClass.convertToKey(event.getChunk().getX(),event.getChunk().getZ());
		if(VeinChunkReadWrite.populatedList.get(key)!=null)//load blocks into already populated chunk
		{
			String[][][] draw =	VeinChunkReadWrite.readChunks(key, false);
			if(draw !=null)
			{
				DebugLogger.console("Drawing veins in existing chunk");
				VeinDrawer.drawVein(draw, event.getChunk());
			}
			VeinChunkReadWrite.deleteChunkInfo(key, false);
		}
	}
}
