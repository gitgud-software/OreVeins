package defaultPackadgeHelpers;

import java.util.Random;

import org.bukkit.configuration.file.FileConfiguration;

public class TruncatedSkewDistribution 
{
	public double min;
	public double max;
	public  double skew;
	public double bias;
	public String configPath;
	
	public void logValues(FileConfiguration config) 
	{
	
		if(!config.contains(configPath + ".Min"))
		{
			config.set(configPath + ".Min", min);
		}
		else
		{
			min = config.getInt(configPath+".Min");
		}
		if(!config.contains(configPath + ".Max"))
		{
			config.set(configPath + ".Max", max);
		}
		else
		{
			max = config.getInt(configPath+".Max");
		}
		if(!config.contains(this.configPath + ".Skew"))
		{
			config.set(configPath + ".Skew", skew);
		}
		else
		{
			skew = config.getInt(configPath + ".Skew");
		}
		if(!config.contains(configPath + ".Bias"))
		{
			config.set(configPath + ".Bias", bias);
		}
		else
		{
			bias = config.getInt(configPath +".Bias");
		}
	}
	
	public double getRVar(Random rand)
	{	
	        double range = max - min;
	        double mid = min + ( range / 2.0);
	        double unitGaussian = rand.nextGaussian();
	        double biasFactor = Math.exp(bias);
	        double retval = mid+(range*(biasFactor/(biasFactor+Math.exp(-unitGaussian/skew))-0.5));
	        return retval;
	}
}
