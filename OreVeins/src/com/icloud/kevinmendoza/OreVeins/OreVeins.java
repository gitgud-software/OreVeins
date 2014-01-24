package com.icloud.kevinmendoza.OreVeins;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fileIO.VeinChunkReadWrite;

import java.io.*;

import listeners.ChunkLoadListener;
import listeners.OrePopulator;
import listeners.WorldListener;

public final class OreVeins extends JavaPlugin 
{
	File config;
	FileConfiguration newConfigs;
	@Override
	public void onEnable()
	{
		// TODO Insert logic to be performed when the plugin is enabled
		getLogger().info("onEnable has been invoked!");
		popFileTree();
		getServer().getPluginManager().registerEvents(new ChunkLoadListener(), this);
		getServer().getPluginManager().registerEvents(new WorldListener(),this);
		getServer().getPluginManager().registerEvents(new OrePopulator(),this);
		VeinChunkReadWrite.loadPopulatedList();
		newConfigs = YamlConfiguration.loadConfiguration(config);
		Defaults.popAndReadDefaults(newConfigs);
		saveNewConfig();
	}

	@Override
	public void onDisable() 
	{
		// TODO Insert logic to be performed when the plugin is disabled
		getLogger().info("onDisable has been invoked!");
		VeinChunkReadWrite.savePopulatedList();
	}
	
	private void popFileTree()
	{
		File Ovein = new File("plugins/OreVeins");
		config = new File("plugins/OreVeins/config.yml");
		File popList = new File("plugins/OreVeins/popList.txt");
		File ChunkInfo = new File("plugins/OreVeins/ChunkInfo");
		File VeinInfo = new File("plugins/OreVeins/VeinInfo");
		File StringerInfo = new File("plugins/OreVeins/PrevChunkInfo");
		try
		{
			Ovein.mkdir();
			popList.createNewFile();
			config.createNewFile();
			ChunkInfo.mkdir();
			VeinInfo.mkdir();
			StringerInfo.mkdir();
		}
		catch (IOException e)
		{ //Hooray for horrible programming practices!
			DebugLogger.console("Whoopsie! File creation failed!");
			e.printStackTrace();
			onDisable();
			//Also, fuck you checked exceptions!
		}		
	}
	public void saveNewConfig()
	{
		try
		{
			newConfigs.save(config);
		}
		catch(Exception e)
		{
		}
	}
}
