package geometryClasses;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;


public class LineDrawingUtilityClass 
{
	
	public static ArrayList<ThreePoint> bresenHamAlgo(ThreePoint start, ThreePoint end)
	{
		ArrayList<ThreePoint> thePoints = new ArrayList<ThreePoint>();
		int xmult =1,ymult=1,zmult=1,x,y,z;
		int Dz = end.z -start.z;
		int Dy = end.y -start.y;
		int Dx = end.x -start.x;
		x = start.x;
		y = start.y;
		z = start.z;
		if(Dz < 0)
		{
			Dz=-Dz;
			zmult=-1;
		}
		if(Dy < 0)
		{
			Dy=-Dy;
			ymult=-1;
		}
		if(Dx < 0)
		{
			Dx=-Dx;
			zmult=-1;
		}
		int dx2 = Dx*2;
		int dy2 = Dy*2;
		int dz2 = Dz*2;
		if(Dx >= Dy && Dx >= Dz)
		{
			int ERRXY = dy2 - Dx;
			int ERRXZ = dz2 - Dx;
			for(int i =0;i<Dx;i++)
			{
				if(ERRXY >0)
				{
					y +=ymult;
					ERRXY -=dx2;
				}
				if(ERRXZ >0)
				{
					z +=zmult;
					ERRXZ -=dx2;
				}
				ERRXY +=dy2;
				ERRXZ +=dz2;
				x+=xmult;
				ThreePoint point = new ThreePoint(x,y,z);
				thePoints.add(point);
			}
		}
		else if (Dy > Dx && Dy >= Dz)
		{
			int errxy = dx2-Dy;
			int errzy = dz2-Dy;
			for(int i =0;i<Dy;i++)
			{
				if(errxy > 0)
				{
					x+=xmult;
					errxy-=dy2;
				}
				if(errzy > 0)
				{
					z+=zmult;
					errzy-=dy2;
				}
				errxy+=dx2;
				errzy+=dz2;
				y+=ymult;
				ThreePoint point = new ThreePoint(x,y,z);
				thePoints.add(point);
			}
		}
		else
		{
			int erryz = dy2-Dz;
			int errxz = dx2-Dz;
			for(int i =0;i<Dz;i++)
			{
				if(erryz > 0)
				{
					y+=ymult;
					erryz-=dz2;
				}
				if(errxz > 0)
				{
					x+=xmult;
					errxz-=dz2;
				}
				erryz+=dy2;
				errxz+=dx2;
				z+=ymult;
				ThreePoint point = new ThreePoint(x,y,z);
				thePoints.add(point);
			}
		}
		thePoints.add(start);
		return thePoints;
	}
	
	public static ArrayList<ThreePoint> bezierCurve(ThreePoint start, ThreePoint end,Random rand)
	{
		int vx = end.x - start.x;
		int vy = end.y - start.y;
		int vz = end.z - start.z;
		int dist = (int)Math.sqrt(vx*vx + vy*vy + vz*vz);
		int qui = dist / 30;
		if(qui < 1)
		{
			qui =1;
		}
		int vrx = vx/qui, vry = vy/qui, vrz = vz/qui;
		int x = start.x;
		int y = start.y;
		int z = start.z;
		ArrayList<ThreePoint> centers = new ArrayList<ThreePoint>();
		while (true)//create nodes to offset
		{
			ThreePoint center = new ThreePoint(x,y,z);
			centers.add(center);
			x+=vrx;
			y+=vry;
			z+=vrz;
			vx = x - start.x;
			vy = y - start.y;
			vz = z - start.z;
			int r = (int)Math.sqrt(vx*vx + vy*vy + vz*vz);
			if(r>dist)
			{
				break;
			}
		}
		int maxStrike = 2*dist / (centers.size()-1);
		ThreePoint[] offsets = new ThreePoint[centers.size()];
		offsets[0]= start;
		offsets[offsets.length-1] = end;
		for(int i=1;i<offsets.length-1;i++)
		{
			offsets[i]= getEndPoint(centers.get(i), maxStrike, rand,true);
		}
		x=0;y=0;z=0;
		int n = offsets.length-1;
		double t=0;
		//DebugLogger.console("centers & qui" + centers.size() + " "+ qui);
		double step = 1.0/20.0;//divide vein into 20 sections, use the centers to offset.
		ThreePoint[] points = new ThreePoint[21];
		int count = 0;
		while(true)
		{
			x=0;y=0;z=0;
			for(int i=0;i<=n;i++)
			{
				x+=(int)(binomialCoeff(n,i)*Math.pow(1-t, n-i)*Math.pow(t, i)*offsets[i].x);
				y+=(int)(binomialCoeff(n,i)*Math.pow(1-t, n-i)*Math.pow(t, i)*offsets[i].y);
				z+=(int)(binomialCoeff(n,i)*Math.pow(1-t, n-i)*Math.pow(t, i)*offsets[i].z);
			}
			ThreePoint thePoint = new ThreePoint(x,y,z);
			points[count] = thePoint;
			count++;
			if(t >= 1)
			{
				break;
			}
			t = t + step;
		}//connect points with straight lines
		ArrayList<ThreePoint> veinPoints = new ArrayList<ThreePoint>();
		for(int i=1;i<points.length;i++)
		{
			veinPoints.addAll(bresenHamAlgo(points[i-1],points[i]));
		}
		return veinPoints;
	}

	public static int binomialCoeff(int n, int k)
	{
		int coeff = 1;
		for (int i = n - k + 1; i <= n; i++) 
		{
			coeff *= i;
		}
		for (int i = 1; i <= k; i++) 
		{
			coeff /= i;
		}
		return coeff;
	}

	public static ThreePoint getEndPoint(ThreePoint start, int strike, Random rand, Boolean varyRadius) 
	{
		if(strike==0)
			return null;
		int it=0;
		double phi,theta;
		int x,z,y;
		int rad = strike;
		while(true)
		{
			if(varyRadius==true)
			{
				rad = rand.nextInt(strike);
			}
			phi = ((double)rand.nextInt(314))/100.0;
			y = (int) (rad*Math.cos(phi)) + start.y;
			if( y <128 && y >2 )
				break;
		}
		theta = ((double)rand.nextInt(628)/100.0);
		x = (int)(rad*Math.cos(theta)*Math.sin(phi)) + start.x;
		z = (int)(rad*Math.sin(theta)*Math.sin(phi)) + start.z;
		ThreePoint test = new ThreePoint(x,y,z);
		TwoPoint chunkChords = getChunkCoords(test);
		//DebugLogger.console("tried" + it + " to find good chunk");
		ThreePoint endpoint = new ThreePoint(x,y,z);
		return endpoint;
	}
	
	public static TwoPoint getChunkCoords(ThreePoint point)
	{
		TwoPoint newPoint = new TwoPoint(point.x>>4,point.z>>4);
		return newPoint;
	}
	
	public static ThreePoint shiftCoords(ThreePoint point)
	{
		//DebugLogger.console("start point" + point.x + " "+ point.y + " " + point.z);
		ThreePoint newPoint = new ThreePoint(Math.abs(point.x-16*(point.x>>4)), point.y ,Math.abs(point.z-16*(point.z>>4)));
		//DebugLogger.console("start point" + newPoint.x + " "+  newPoint.y + " " +  newPoint.z);
		return newPoint;
	}

	public static String convertToKey(int x, int z)
	{
		String chx = new Integer(x).toString();
		String chz = new Integer(z).toString();
		String key = chx +":" + chz;
		return key;
	}

	public static String convertToKey(ThreePoint point)
	{
		String chx = new Integer(point.x>>4).toString();
		String chz = new Integer(point.z>>4).toString();
		String key = chx +":" + chz;
		return key;
	}
	
	public static String convertToKey(TwoPoint chunk)
	{

		String chx = new Integer(chunk.x).toString();
		String chz = new Integer(chunk.z).toString();
		String key = chx +":" + chz;
		return key;
	}
	
}
