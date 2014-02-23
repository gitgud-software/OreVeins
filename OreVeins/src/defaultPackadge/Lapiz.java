package defaultPackadge;

import org.bukkit.configuration.file.FileConfiguration;

import defaultPackadgeHelpers.Bonanza;
import defaultPackadgeHelpers.Branch;
import defaultPackadgeHelpers.Grade;
import defaultPackadgeHelpers.Height;
import defaultPackadgeHelpers.Strike;
import defaultPackadgeHelpers.TruncatedSkewDistribution;
import defaultPackadgeHelpers.Width;

public class Lapiz 
{
	public double probToSpawn;
	
	public TruncatedSkewDistribution lodewidth;
	public TruncatedSkewDistribution lodelength;
	public TruncatedSkewDistribution lodedepth;
	
	public TruncatedSkewDistribution mineralizationwidth;
	public TruncatedSkewDistribution mineralizationlength;
	public TruncatedSkewDistribution mineralizationdepth;
	
	public Grade grade;
	public Branch branch;
	public String string;
	public Lapiz() 
	{
		string = "LapizLazuliDeposit";
		
		probToSpawn = 0.8;//probability that a spawned chunk will make a lapiz deposit
		lodewidth = new TruncatedSkewDistribution();
		lodelength = new TruncatedSkewDistribution();
		lodedepth = new TruncatedSkewDistribution();
		mineralizationwidth = new TruncatedSkewDistribution();
		mineralizationlength = new TruncatedSkewDistribution();
		mineralizationdepth = new TruncatedSkewDistribution();
		lodewidth.bias = -2.0; lodewidth.skew = 2.0; lodewidth.min =2.0; lodewidth.max = 25.0;
		lodelength.bias = -1.0; lodelength.skew = 2.0; lodelength.min =5.0; lodelength.max = 25.0;
		lodedepth.bias = 1.0; lodedepth.skew = 2.0; lodedepth.min =10.0; lodedepth.max = 25.0;
		mineralizationwidth.bias = 0.0; mineralizationwidth.skew = 1.0; mineralizationwidth.min =10.0; mineralizationwidth.max = 50.0;
		mineralizationlength.bias = 0.0; mineralizationlength.skew = 1.0; mineralizationlength.min =30.0; mineralizationlength.max = 80.0;
		mineralizationdepth.bias = -0.5; mineralizationdepth.skew = 1.0; mineralizationdepth.min =20.0; mineralizationdepth.max = 100.0;
		lodewidth.configPath = string + ".Lodewidth";
		lodedepth.configPath = string + ".Lodedepth";
		lodelength.configPath = string + ".Lodelength";
		mineralizationwidth.configPath = string + ".Zonewidth";
		mineralizationdepth.configPath = string + ".Zonedepth";
		mineralizationlength.configPath = string + ".Zonelength";
		grade        = new Grade(string);//grade of lodes
		branch       = new Branch(string);;//number of lodes in deposit
	}

	public void setDefaults(FileConfiguration config)
	{
		setProbs(config);
		grade.logValues(config);
		branch.logValues(config);
		mineralizationwidth.logValues(config);
		mineralizationdepth.logValues(config);
		mineralizationlength.logValues(config);
		lodewidth.logValues(config);
		lodedepth.logValues(config);
		lodelength.logValues(config);
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
