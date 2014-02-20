package defaultPackadgeHelpers;


public class Bonanza extends TruncatedSkewDistribution 
{
	public Bonanza(String path)
	{
		this.configPath = path + ".Bonanza";
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
			this.min = 0.001;
			this.max = 0.01;
			this.skew = 1.0;
			this.bias = -2.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.01;
			this.max = 0.1;
			this.skew = 1.0;
			this.bias = -1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.005;
			this.max = 0.02;
			this.skew = 1.0;
			this.bias = -2.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.0003;
			this.max = 0.01;
			this.skew = 2.0;
			this.bias = -2.0;
		}
	}
	
	private void secondary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.005;
			this.max = 0.03;
			this.skew = 2.0;
			this.bias = -1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.09;
			this.max = 0.9;
			this.skew = 3.0;
			this.bias = -2.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.01;
			this.max = 0.1;
			this.skew = 3.0;
			this.bias = -2.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.0003;
			this.max = 0.1;
			this.skew = 2.0;
			this.bias = -2.0;
		}
	}
	
	private void tertiary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 0.05;
			this.max = 0.1;
			this.skew = 2.0;
			this.bias = -1.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 0.9;
			this.max = 1;
			this.skew = 1.0;
			this.bias = -1.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 0.1;
			this.max = 1.0;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 0.003;
			this.max = 0.3;
			this.skew = 1.0;
			this.bias = -2.0;
		}
	}
	
	private void theDefault(String ore)
	{
		 if(ore.contains("Emerald"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 0.0125;
			this.max = 2.0;
			this.skew = 1.0;
			this.bias = 0.5;
		}
	}
}
