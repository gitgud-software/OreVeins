package defaultPackadgeHelpers;


public class Width extends TruncatedSkewDistribution 
{

	public Width(String path) 
	{
		this.configPath = path + ".Width";
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
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Coal"))
		{
			this.min = 3.0;
			this.max = 15.0;
			this.skew = 8.0;
			this.bias = 4.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.0125;
			this.max = 2.0;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Lapiz"))
		{
			this.min = 1.25;
			this.max = 3.0;
			this.skew = 1.6;
			this.bias = 0.2;
		}
	}
	
	private void secondary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Coal"))
		{
			this.min = 3.0;
			this.max = 15.0;
			this.skew = 8.0;
			this.bias = 4.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.0125;
			this.max = 2.0;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("LAPIZ"))
		{
			this.min = 1.25;
			this.max = 3.0;
			this.skew = 1.6;
			this.bias = 0.2;
		}
	}
	
	private void tertiary(String ore)
	{
		if(ore.contains("Iron"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Lapiz"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Coal"))
		{
			this.min = 3.0;
			this.max = 15.0;
			this.skew = 8.0;
			this.bias = 4.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.0125;
			this.max = 2.0;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Lapiz"))
		{
			this.min = 1.25;
			this.max = 3.0;
			this.skew = 1.6;
			this.bias = 0.2;
		}
	}
	
	private void theDefault(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Coal"))
		{
			this.min = 3.0;
			this.max = 15.0;
			this.skew = 8.0;
			this.bias = 4.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.0125;
			this.max = 2.0;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Lapiz"))
		{
			this.min = 1.25;
			this.max = 3.0;
			this.skew = 1.6;
			this.bias = 0.2;
		}
	}
}
