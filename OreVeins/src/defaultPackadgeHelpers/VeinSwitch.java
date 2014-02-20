package defaultPackadgeHelpers;

import java.util.Random;

public class VeinSwitch extends TruncatedSkewDistribution 
{


	public VeinSwitch(String path) 
	{
		this.configPath = path + ".ProbabilityOfVeinSwitching";
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
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
	}
	
	private void secondary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 0.5;
		}
	}
	
	private void tertiary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = 1.0;
		}
	}
	
	private void theDefault(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = -1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = -1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = -1.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.0;
			this.max = 3;
			this.skew = 1.0;
			this.bias = -1.0;
		}
	}
}
