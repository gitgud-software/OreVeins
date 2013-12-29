package com.icloud.kevinmendoza.OreVeins;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.*;


public final class OreVeins extends JavaPlugin 
{
	@Override
	public void onEnable()
	{
		// TODO Insert logic to be performed when the plugin is enabled
		getLogger().info("onEnable has been invoked!");
		popFileTree();
		getServer().getPluginManager().registerEvents(new ChunkGenListener(), this);
	}

	@Override
	public void onDisable() 
	{
		// TODO Insert logic to be performed when the plugin is disabled
		getLogger().info("onDisable has been invoked!");
	}


	private void popFileTree()
	{
		File config = new File("config.yml");
		File VeinInfo = new File("VeinInfo");
		File ChunkInfo = new File("ChunkInfo");
		try{
			config.createNewFile();
			VeinInfo.mkdir();
			ChunkInfo.mkdir();
		}
		catch (IOException e){ //Hooray for horrible programming practices!
			DebugLogger.console("Whoopsie! File creation failed!");
			e.printStackTrace();
			//Also, fuck you checked exceptions!
		}		
	}
}
