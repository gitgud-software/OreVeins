package defaultPackadge;

import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.GeodeDef;
import defaultPackadgeHelpers.TruncatedSkewDistribution;

public class Emerald 
{
	public PrimaryVein primaryVein;
	public SecondaryVein secondaryVein;
	public TertiaryVein tertiaryVein;
	public GeodeDef geode;
	public double probToSpawn;
	private String path = "EmeraldVeinSystem";
	private TruncatedSkewDistribution chooseType;
	public Emerald()
	{
		primaryVein = new PrimaryVein(path);
		secondaryVein = new SecondaryVein(path);
		tertiaryVein = new TertiaryVein(path);
		geode = new GeodeDef(path);
		chooseType.bias = 0.0; chooseType.skew = 0.0; chooseType.min = 0; chooseType.max = 4;
		chooseType.configPath = path + ".VeinTypeInitial";
	}
	
	public void readWriteConfigs(FileConfiguration configs)
	{
		setProbs(configs);
		geode.logValues(configs);
		primaryVein.setDefaults(configs);
		secondaryVein.setDefaults(configs);
		tertiaryVein.setDefaults(configs);
	}
	
	private void setProbs(FileConfiguration config)
	{
		if(!config.contains(path + ".SpawnProbability"))
		{
			config.set(path + ".SpawnProbability", probToSpawn);
		}
		else
		{
			probToSpawn = config.getInt(path+".SpawnProbability");
		}
		chooseType.logValues(config);
	}
	
	public int getType()
	{
		Random rand = new Random();
		double thetype = chooseType.getRVar(rand);
		if(thetype < 1)
		{
			return 1;
		}
		else if (thetype < 2)
		{
			return 2;
		}
		else
		{
			return 3;
		}
	}
}
