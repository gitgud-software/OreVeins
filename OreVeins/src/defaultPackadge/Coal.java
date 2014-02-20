package defaultPackadge;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.Branch;
import defaultPackadgeHelpers.Grade;
import defaultPackadgeHelpers.Height;
import defaultPackadgeHelpers.Strike;
import defaultPackadgeHelpers.Width;

public class Coal 
{
	public double probToSpawn;
	public Grade grade;
	public Strike strike;
	public Width width;
	public Height height;
	public Branch levels;
	public String string;
	public Coal() 
	{
		string = "CoalBeds";
		probToSpawn = 0.013;
		grade        = new Grade(string);
		strike       = new Strike(string);
		width        = new Width(string);
		height       = new Height(string);
		levels 		 = new Branch(string);
	}

	public void setDefaults(FileConfiguration config)
	{
		setProbs(config);
		grade.logValues(config);
		levels.logValues(config);
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
