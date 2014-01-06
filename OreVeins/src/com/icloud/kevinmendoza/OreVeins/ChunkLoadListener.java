package com.icloud.kevinmendoza.OreVeins;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;

public final class ChunkLoadListener implements Listener  
{

	@EventHandler
	public void onGenerate(ChunkLoadEvent event) 
	{
		if(!event.isNewChunk())
		{
			String[][][] draw =	VeinChunkReadWrite.readChunks(LineDrawingUtilityClass.convertToKey(event.getChunk().getX(), event.getChunk().getZ()), false);
			if(draw !=null)
			{
				DebugLogger.console("Drawing veins in existing chunk");
				VeinDrawer.drawVein(draw, event.getChunk());
				
			}
			VeinChunkReadWrite.deleteChunkInfo(LineDrawingUtilityClass.convertToKey(event.getChunk().getX(), event.getChunk().getZ()), false);
		}
	}
}
