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

import java.util.HashMap;

import geometryClasses.TwoPoint;


import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.event.world.ChunkPopulateEvent;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.event.world.WorldInitEvent;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;
import com.icloud.kevinmendoza.OreVeins.PointMapping;

public final class EventListeners implements Listener 
{
	@EventHandler
	public void onLoad(ChunkLoadEvent event) 
	{
		TwoPoint chunk = new TwoPoint(event.getChunk().getX(),event.getChunk().getZ(),true);
		PointMapping.addToPoints(chunk);
		if(PointMapping.popMapExists(chunk.toString()))
		{
			PointMapping.addToLoaded(chunk);
		}
	}
	
	@EventHandler 
	public void  onUnload(ChunkUnloadEvent event)
	{
		TwoPoint chunk = new TwoPoint(event.getChunk().getX(),event.getChunk().getZ(),true);
		PointMapping.removeFromLoaded(chunk);
	}
	@EventHandler
	public void onInit(WorldInitEvent event) 
	{
		event.getWorld().getPopulators().add(new OreReplacer());
	}
	@EventHandler
	public void onGenerate(ChunkPopulateEvent event) 
	{
		TwoPoint chunk = new TwoPoint(event.getChunk().getX(),event.getChunk().getZ(),true);
		OreGenerator.GenerateOres(chunk);
		PointMapping.addToLoaded(chunk);
		HashMap<String,String[][][]> drawableChunks = PointMapping.getDrawListAndRemove();
		TwoPoint drawingChunk;
		Chunk chunkObj;
		if(!drawableChunks.isEmpty())
		{
			for(String entry: drawableChunks.keySet())
			{
				drawingChunk = new TwoPoint(entry);
				chunkObj = Bukkit.getWorlds().get(0).getChunkAt(drawingChunk.x, drawingChunk.z);
				if(drawableChunks.get(entry)!=null)
				{
					VeinDrawer.drawVein(drawableChunks.get(entry), chunkObj);
				}
			}
		}
	}
	@EventHandler
	public void onPlayerLogout(PlayerQuitEvent event)
	{
		PointMapping.refreshLoadedPoints();
	}
}
