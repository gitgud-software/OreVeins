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
		else if(ore.contains("EMERALD"))
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
		else if(ore.contains("Lapiz"))
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
		if(ore.contains("Redstone"))
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
		else if(ore.contains("Diamond"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("EMERALD"))
		{
			this.min = 0.5;
			this.max = 5.0;
			this.skew = 2.0;
			this.bias = 1.0;
		}
		else if(ore.contains("Iron"))
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
	
	private void tertiary(String ore)
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
		else if(ore.contains("EMERALD"))
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
		else if(ore.contains("EMERALD"))
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
	@Override
	public double getRVar(Random rand)
	{	
	        double range = max - min;
	        double mid = min + range / 2.0;
	        double unitGaussian = rand.nextGaussian();
	        double biasFactor = Math.exp(bias);
	        double retval = mid+(range*(biasFactor/(biasFactor+Math.exp(-unitGaussian/skew))-0.5));
	        if(retval>=mid)
	        {
	        	return 0.0;
	        }
	        else
	        {
	        	return 1.0;
	        }
	}
}
