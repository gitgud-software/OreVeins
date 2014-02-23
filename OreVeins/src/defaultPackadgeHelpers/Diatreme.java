package defaultPackadgeHelpers;

import org.bukkit.configuration.file.FileConfiguration;

public class Diatreme  
{
	public String thisPath;
	public TruncatedSkewDistribution offsetx;
	public TruncatedSkewDistribution offsety;
	public TruncatedSkewDistribution changex;
	public TruncatedSkewDistribution changey;
	public TruncatedSkewDistribution grade;
	public Diatreme(String path)
	{
		thisPath = path + ".Diatreme";
		offsetx = new TruncatedSkewDistribution();
		offsety= new TruncatedSkewDistribution();
		changex= new TruncatedSkewDistribution();
		changey= new TruncatedSkewDistribution();
		grade= new TruncatedSkewDistribution();
		offsetx.bias = 0.0; offsetx.skew = 1.0; offsetx.min = 2.0; offsetx.max = 12.0;
		offsety.bias = 0.0; offsety.skew = 1.0; offsety.min = 2.0; offsety.max = 12.0;
		changex.bias = 0.0; changex.skew = 1.0; changex.min = 5.0; changex.max = 15.0;
		changey.bias = 0.0; changey.skew = 1.0; changey.min = 5.0; changey.max = 15.0;
		grade.bias = 0.0; grade.skew = 1.0; grade.min = 0.5; grade.max = 4.0;
		offsetx.configPath = thisPath + ".MinWidth";
		offsety.configPath = thisPath + ".MinLength";
		changex.configPath = thisPath + ".ChangingWidth";
		changey.configPath = thisPath + ".ChangingLength";
		grade.configPath = thisPath + ".Grade";
	}
	public void setDefaults(FileConfiguration configs) 
	{
		grade.logValues(configs);
		offsetx.logValues(configs);
		offsety.logValues(configs);
		changex.logValues(configs);
		changey.logValues(configs);
		
	}
}
