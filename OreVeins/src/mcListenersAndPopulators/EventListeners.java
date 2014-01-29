package mcListenersAndPopulators;

import java.util.HashMap;

import geometryClasses.TwoPoint;

import oreClasses.OreGenerator;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldInitEvent;

import com.icloud.kevinmendoza.OreVeins.PointMapping;

public final class EventListeners implements Listener 
{
	@EventHandler
	public void onLoad(ChunkLoadEvent event) 
	{
		TwoPoint chunk = new TwoPoint(event.getChunk().getX(),event.getChunk().getZ());
		PointMapping.addToPoints(chunk);
		if(PointMapping.popMapExists(chunk.toString()))
		{
			PointMapping.addToLoaded(chunk);
		}
	}
	
	@EventHandler 
	public void  onUnload(ChunkUnloadEvent event)
	{
		TwoPoint chunk = new TwoPoint(event.getChunk().getX(),event.getChunk().getZ());
		PointMapping.removeFromLoaded(chunk);
	}
	@EventHandler
	public void onInit(WorldInitEvent event) 
	{
		OreReplacer pop = new OreReplacer();
		if(!Bukkit.getWorlds().get(0).getPopulators().contains(pop))
		{
			Bukkit.getWorlds().get(0).getPopulators().add(pop);
		}
	}
	@EventHandler
	public void onGenerate(ChunkPopulateEvent event) 
	{
		TwoPoint chunk = new TwoPoint(event.getChunk().getX(),event.getChunk().getZ());
		OreGenerator.GenerateOres(chunk);
		PointMapping.addToLoaded(chunk);
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
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent event)
	{
		PointMapping.refreshLoadedPoints();
	}
}
