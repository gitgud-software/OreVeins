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
		File Ovein = new File("plugins/OreVeins");
		File config = new File("plugins/OreVeins/config.yml");
		File ChunkInfo = new File("plugins/OreVeins/ChunkInfo");
		File VeinInfo = new File("plugins/OreVeins/VeinInfo");
		File StringerInfo = new File("plugins/OreVeins/StringerInfo");
		try{
			Ovein.mkdir();
			config.createNewFile();
			ChunkInfo.mkdir();
			VeinInfo.mkdir();
			StringerInfo.mkdir();
		}
		catch (IOException e){ //Hooray for horrible programming practices!
			DebugLogger.console("Whoopsie! File creation failed!");
			e.printStackTrace();
			onDisable();
			//Also, fuck you checked exceptions!
		}		
	}
}
