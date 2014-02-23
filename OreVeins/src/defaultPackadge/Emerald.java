package defaultPackadge;

import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.GeodeDef;
import defaultPackadgeHelpers.TruncatedSkewDistribution;
import defaultPackadgeHelpers.VeinSwitch;

public class Emerald 
{
	public PrimaryVein primaryVein;
	public SecondaryVein secondaryVein;
	public TertiaryVein tertiaryVein;
	public GeodeDef geode;
	public double probToSpawn;
	private String path = "EmeraldVeinSystem";
	public VeinSwitch chooseType;
	public Emerald()
	{
		probToSpawn = 0.1;
		primaryVein = new PrimaryVein(path);
		secondaryVein = new SecondaryVein(path);
		tertiaryVein = new TertiaryVein(path);
		geode = new GeodeDef(path);
		chooseType = new VeinSwitch(path+ ".VeinTypeInitial");
	}
	
	public void readWriteConfigs(FileConfiguration configs)
	{
		setProbs(configs);
		chooseType.logValues(configs);
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
	}
}
