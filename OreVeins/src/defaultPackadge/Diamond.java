package defaultPackadge;

import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.Diatreme;
import defaultPackadgeHelpers.Grade;
import defaultPackadgeHelpers.Height;
import defaultPackadgeHelpers.Width;

public class Diamond 
{
	public double probToSpawn;
	public PrimaryVein veinDikes;
	public Diatreme diatreme;
	private String path = "DiamondVeinSystem";
	private int type =1;
	public Diamond()
	{
		probToSpawn = 0.001;
		veinDikes = new PrimaryVein(path);
		diatreme = new Diatreme(path);
	}
	
	public int getType()
	{
		return type;
	}
	public void readWriteConfigs(FileConfiguration configs)
	{
		setProbs(configs);
		diatreme.setDefaults(configs);
		veinDikes.setDefaults(configs);
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
