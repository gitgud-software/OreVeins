package defaultPackadge;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.Branch;
import defaultPackadgeHelpers.Grade;
import defaultPackadgeHelpers.Height;
import defaultPackadgeHelpers.Strike;
import defaultPackadgeHelpers.Width;

public class BIF 
{
	public double probToSpawn;
	public Grade grade;
	public Strike strike;
	public Width width;
	public Height height;
	public Branch Levels;
	public String string;
	public BIF() 
	{
		string = "BandedIronFormations";
		probToSpawn = 0.0;
		grade        = new Grade(string);
		strike       = new Strike(string);
		width        = new Width(string);
		height       = new Height(string);
	}

	public void setDefaults(FileConfiguration config)
	{
		setProbs(config);
		grade.logValues(config);
		strike.logValues(config);
		width.logValues(config);
		height.logValues(config);
	}
	
	private void setProbs(FileConfiguration config)
	{
		if(!config.contains(string + ".SpawnProbability"))
		{
			config.set(string + ".SpawnProbability", probToSpawn);
		}
		else
		{
			probToSpawn = config.getInt(string+".SpawnProbability");
		}
	}
}
