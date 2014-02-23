package defaultPackadgeHelpers;


public class Grade extends TruncatedSkewDistribution 
{
	public Grade(String path)
	{
		this.configPath = path + ".Grade";
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
			this.min = 1;
			this.max = 3.5;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 1;
			this.max = 5.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = .75;
			this.max = 4;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.75;
			this.max = 3.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
	
	private void secondary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 1.0;
			this.max = 7.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 1.0;
			this.max = 7.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 1.0;
			this.max = 7.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 1.0;
			this.max = 7.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.75;
			this.max = 7.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
	
	private void tertiary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 3.0;
			this.max = 10.0;
			this.skew = 2.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 3.0;
			this.max = 10.0;
			this.skew = 2.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 3.0;
			this.max = 10.0;
			this.skew = 2.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 3.0;
			this.max = 10.0;
			this.skew = 2.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.04;
			this.max = 2.0;
			this.skew = 1.0;
			this.bias = 0.5;
		}
	}
	
	private void theDefault(String ore)
	{
		if(ore.contains("BandedIronFormation"))
		{
			this.min = 1.0;
			this.max =15.0;
			this.skew = 1.0;
			this.bias = 0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Coal"))
		{
			this.min = 2.0;
			this.max = 10.0;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Lapiz"))
		{
			this.min = 1.25;
			this.max = 3.0;
			this.skew = 1.0;
			this.bias = 0.5;
		}
	}
	
}
