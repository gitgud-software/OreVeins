package listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

import populatorClasses.VeinDrawer;


import fileIO.VeinChunkReadWrite;
import geometryClasses.LineDrawingUtilityClass;

public final class ChunkLoadListener implements Listener  
{

	@EventHandler
	public void onLoad(ChunkLoadEvent event) 
	{
		if(event.getChunk().getBlock(1, 1,1)!=null)//load blocks into already populated chunk
		{
			String[][][] draw =	VeinChunkReadWrite.readChunks(LineDrawingUtilityClass.convertToKey(event.getChunk().getX(), event.getChunk().getZ()), false);
			if(draw !=null)
			{
				//DebugLogger.console("Drawing veins in existing chunk");
				VeinDrawer.drawVein(draw, event.getChunk());
			}
			VeinChunkReadWrite.deleteChunkInfo(LineDrawingUtilityClass.convertToKey(event.getChunk().getX(), event.getChunk().getZ()), false);
		}
	}
}
