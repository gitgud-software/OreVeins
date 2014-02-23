package defaultPackadge;

import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.Bonanza;
import defaultPackadgeHelpers.Branch;
import defaultPackadgeHelpers.Grade;
import defaultPackadgeHelpers.Height;
import defaultPackadgeHelpers.VeinSwitch;
import defaultPackadgeHelpers.Strike;
import defaultPackadgeHelpers.Width;

public class SecondaryVein 
{
	private double probToSpawn;
	public VeinSwitch vswitch;
	public Grade grade;
	public Strike strike;
	public Width width;
	public Height height;
	public Bonanza bonanza;
	public Branch branch;
	public String string;
	public SecondaryVein(String ore) 
	{
		string = ore + ".SecondaryVein";
		if(ore.contains("Gold"))
		{
			probToSpawn = 2.0;
		}
		else if(ore.contains("Iron"))
		{
			probToSpawn = 2.0;
		}
		else if(ore.contains("Redstone"))
		{
			probToSpawn = 2.0;
		}
		else if(ore.contains("Emerald"))
		{
			probToSpawn = 2.0;
		}
		else if(ore.contains("Diamond"))
		{
			probToSpawn = 2.0;
		}
		else if(ore.contains("Lapiz"))
		{
			probToSpawn = 2.0;
		}
		grade        = new Grade(string);
		strike       = new Strike(string);
		width        = new Width(string);
		height       = new Height(string);
		bonanza      = new Bonanza(string);
		branch       = new Branch(string);;
		vswitch = new VeinSwitch(string);
	}

	public void setDefaults(FileConfiguration config)
	{
		setProbs(config);
		grade.logValues(config);
		strike.logValues(config);
		width.logValues(config);
		height.logValues(config);
		bonanza.logValues(config);
		branch.logValues(config);
		vswitch.logValues(config);
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
	public double getProbToSpawn() 
	{
		Random rand = new Random();
		return rand.nextDouble()*this.probToSpawn;
	}
}