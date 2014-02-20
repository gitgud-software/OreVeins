package defaultPackadgeHelpers;

import org.bukkit.configuration.file.FileConfiguration;

public class GeodeDef 
{
	public TruncatedSkewDistribution width;
	public TruncatedSkewDistribution height;
	public TruncatedSkewDistribution depth;
	public TruncatedSkewDistribution thickness;
	public GeodeDef(String path)
	{
		String newPath = path + ".Geode";
		width = new TruncatedSkewDistribution();
		height= new TruncatedSkewDistribution();
		depth= new TruncatedSkewDistribution();
		thickness= new TruncatedSkewDistribution();
		width.max =10;width.min=1;width.bias = -2;width.skew=2;
		height.max =10;height.min=1;height.bias = -2;height.skew=2;
		depth.max =10;depth.min=1;depth.bias = -2;depth.skew=2;
		thickness.max =4;thickness.min=1;thickness.bias = -2;thickness.skew=2;
		width.configPath = newPath + ".Width";
		height.configPath = newPath + ".Height";
		depth.configPath = newPath + ".Depth";
		thickness.configPath = newPath + ".Thickness";
	}
	
	public void logValues(FileConfiguration config)
	{
		width.logValues(config);
		height.logValues(config);
		depth.logValues(config);
		thickness.logValues(config);
	}
}
