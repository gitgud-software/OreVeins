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
		width.max =4;width.min=3;width.bias = 4;width.skew=4;
		height.max =4;height.min=3;height.bias = 4;height.skew=4;
		depth.max =4;depth.min=3;depth.bias = 4;depth.skew=4;
		thickness.max =4;thickness.min=3;thickness.bias = 4;thickness.skew=4;
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
