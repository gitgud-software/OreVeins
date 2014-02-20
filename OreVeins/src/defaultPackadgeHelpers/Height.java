package defaultPackadgeHelpers;


public class Height extends TruncatedSkewDistribution {

	public Height(String path) 
	{
		this.configPath = path + ".Height";
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
			this.min = 5;
			this.max =10;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 5;
			this.max =10;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 5;
			this.max =10;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 2;
			this.max =8;
			this.skew = 1.0;
			this.bias = 0.0;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 5;
			this.max =15;
			this.skew = 1.0;
			this.bias = 0.0;
		}
	}
	
	private void secondary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 4;
			this.max =8;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 4;
			this.max =8;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 4;
			this.max =8;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 1;
			this.max =8;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 4;
			this.max =8;
			this.skew = 1.0;
			this.bias = -0.5;
		}
	}
	
	private void tertiary(String ore)
	{
		if(ore.contains("Gold"))
		{
			this.min = 1;
			this.max =2;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Iron"))
		{
			this.min = 1;
			this.max =2;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Redstone"))
		{
			this.min = 1;
			this.max =2;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Emerald"))
		{
			this.min = 1;
			this.max =2;
			this.skew = 1.0;
			this.bias = -0.5;
		}
		else if(ore.contains("Diamond"))
		{
			this.min = 1;
			this.max =3;
			this.skew = 1.0;
			this.bias = -0.5;
		}
	}
	
	private void theDefault(String ore)
	{
		if(ore.contains("BandedIronFormation"))
		{
			this.min = 25.0;
			this.max =100.0;
			this.skew = 2.0;
			this.bias = -0.75;
		}
		else if(ore.contains("Coal"))
		{
			this.min = 20.0;
			this.max = 100.0;
			this.skew = 3.0;
			this.bias = 3.0;
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