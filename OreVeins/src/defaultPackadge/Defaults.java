package defaultPackadge;


import org.bukkit.configuration.file.FileConfiguration;




public class Defaults 
{
	public static Lapiz lapiz;
	public static BIF bif;
	public static Coal coal;
	public static Iron iron;
	public static Gold gold;
	public static Redstone redstone;
	public static Emerald emerald;
	public static Diamond diamond;
	
	public static void popAndReadDefaults(FileConfiguration configs)
	{
		addVeinSystemDefaults(configs);
		addOtherSystemDefaults(configs);
	}
	
	private static void addVeinSystemDefaults(FileConfiguration config) 
	{
		iron = new Iron();
		iron.readWriteConfigs(config);
		gold = new Gold();
		gold.readWriteConfigs(config);
		redstone = new Redstone();
		redstone.readWriteConfigs(config);
		emerald = new Emerald();
		emerald.readWriteConfigs(config);
	}
	
	private static void addOtherSystemDefaults(FileConfiguration configs)
	{
		diamond = new Diamond();
		diamond.readWriteConfigs(configs);
		coal = new Coal();
		coal.setDefaults(configs);
		bif = new BIF();
		bif.setDefaults(configs);
		lapiz = new Lapiz();
		lapiz.setDefaults(configs);
	}
	
}