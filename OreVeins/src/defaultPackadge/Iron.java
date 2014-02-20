package defaultPackadge;

import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.TruncatedSkewDistribution;
import defaultPackadgeHelpers.VeinSwitch;


public class Iron 
{
	public PrimaryVein primaryVein;
	public SecondaryVein secondaryVein;
	public TertiaryVein tertiaryVein;
	public double probToSpawn;
	public String path = "IronVeinSystem";
	public VeinSwitch chooseType;
	
	public Iron()
	{
		probToSpawn = 0.05;
		primaryVein = new PrimaryVein("IronVeinSystem");
		secondaryVein = new SecondaryVein("IronVeinSystem");
		tertiaryVein = new TertiaryVein("IronVeinSystem");
		chooseType = new VeinSwitch("IronVeinSystem.VeinTypeInitial");
	}
	public void readWriteConfigs(FileConfiguration configs)
	{
		setProbs(configs);
		chooseType.logValues(configs);
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
	}

}
