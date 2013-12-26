package com.icloud.kevinmendoza.OreVeins;
import org.bukkit.plugin.java.JavaPlugin;


public final class OreVeins extends JavaPlugin 
{
	 @Override
	    public void onEnable(){
	        // TODO Insert logic to be performed when the plugin is enabled
		 getLogger().info("onEnable has been invoked!");
		 getServer().getPluginManager().registerEvents(new ChunkGenListener(), this);
		   
	    }
	 
	    @Override
	    public void onDisable() {
	        // TODO Insert logic to be performed when the plugin is disabled
	    	getLogger().info("onDisable has been invoked!");
	    }
}
