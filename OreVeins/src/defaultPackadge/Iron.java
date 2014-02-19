package defaultPackadge;

import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.TruncatedSkewDistribution;


public class Iron 
{
	public PrimaryVein primaryVein;
	public SecondaryVein secondaryVein;
	public TertiaryVein tertiaryVein;
	public double probToSpawn;
	public String path = "IronVeinSystem";
	private TruncatedSkewDistribution chooseType;
	
	public Iron()
	{
		probToSpawn = 0.0;
		primaryVein = new PrimaryVein("IronVeinSystem");
		secondaryVein = new SecondaryVein("IronVeinSystem");
		tertiaryVein = new TertiaryVein("IronVeinSystem");
		chooseType.bias = 0.0; chooseType.skew = 0.0; chooseType.min = 0; chooseType.max = 4;
		chooseType.configPath = path + ".VeinTypeInitial";
	}
	public void readWriteConfigs(FileConfiguration configs)
	{
		setProbs(configs);
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
