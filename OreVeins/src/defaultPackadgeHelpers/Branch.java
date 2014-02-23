package defaultPackadgeHelpers;


public class Branch extends TruncatedSkewDistribution 
{
	public Branch(String path)
	{
		this.configPath = path + ".Branch";
		if(path.contains("PrimaryVein"))
		{
			primary(path);
		}
		else if(path.contains("SecondaryVein"))
		{
			secondary(path);
		}
		else if(path.contains("TertiaryVein"))
		{
			tertiary(path);
		}
		else
		{
			theDefault(path);
		}
	}
	
	private void primary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0;
			this.max = 5;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0;
			this.max = 5;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0;
			this.max = 5;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0;
			this.max = 5;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
	
	private void secondary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0;
			this.max = 4;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0;
			this.max = 4;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0;
			this.max = 4;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0;
			this.max = 4;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
	
	private void tertiary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
	
	private void theDefault(String ore)
	{
		 if(ore.contains("BandedIronFormation"))
		{
			String newPath = configPath + "Levels";
			configPath = newPath;
			this.min = 1.0;
			this.max =8.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Lapiz"))
		{
			this.min = 5;
			this.max = 20;
			this.skew = 0.5;
			this.bias = .25;
		}
		else if(ore.contains("Coal"))
		{
			String newPath = configPath + "Levels";
			configPath = newPath;
			this.min = 1;
			this.max = 8;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 10;
			this.max = 20;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
}