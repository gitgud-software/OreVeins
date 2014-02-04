package com.icloud.kevinmendoza.OreVeins;


import org.bukkit.configuration.file.FileConfiguration;


public class Defaults 
{
	public static OreInfo coal;
	public static OreInfo bIF;
	public static OreInfo bog;
	public static OreInfo iron;
	public static OreInfo gold;
	public static OreInfo lapiz;
	public static OreInfo redstone;
	public static OreInfo emerald;
	public static OreInfo diamond;
	
	public static void popAndReadDefaults(FileConfiguration configs)
	{
		addGoldDefaults(configs);
	}
	private static void addGoldDefaults(FileConfiguration config) 
	{
		gold = new OreInfo();
		if(!config.contains("GoldVeinSystem.Strike"))
		{
			config.set("GoldVeinSystem.Strike", 200);
			gold.strike = 200;
		}
		else
		{
			gold.strike = config.getInt("GoldVeinSystem.Strike");
		}
		if(!config.contains("GoldVeinSystem.Branch"))
		{
			config.set("GoldVeinSystem.Branch", 50);
			gold.branch = 50;
		}
		else
		{
			gold.branch = config.getInt("GoldVeinSystem.Branch");
		}
		if(!config.contains("GoldVeinSystem.Probability"))
		{
			config.set("GoldVeinSystem.Probability", 40);
			gold.probability = 40;
		}
		else
		{
			gold.probability = config.getInt("GoldVeinSystem.Probability");
		}
	}
}
