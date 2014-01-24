package geometryClasses;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;


public class LineDrawingUtilityClass 
{
	/*public static void main(String[] args){
        ArrayList<ThreePoint> tester;
        ThreePoint zero = new ThreePoint(0,0,0);
        Random rand = new Random();
        ThreePoint endpoint = new ThreePoint(rand.nextInt(200),rand.nextInt(200),rand.nextInt(200));
        //fuck this syntax
        System.out.println("Working");
        for(int i=0;i<1000000;i++)
        {
        	tester = bezierCurve(zero, endpoint, rand);
        }
        System.out.println("Done");
        /*String output = "";
        StringBuilder sb = new StringBuilder();
        
        for (ThreePoint pt : tester){
            sb.append(pt.toString());
            sb.append("\n");
        }
        
        System.out.println(sb.toString());
        
    }*/
	public static ArrayList<ThreePoint> bresenHamAlgo(ThreePoint start, ThreePoint end)
    {
        ArrayList<ThreePoint> thePoints = new ArrayList<ThreePoint>();
        thePoints.add(start);
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
            xmult=-1;
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
                //System.out.println("Point is:" +point.toString());
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
               // System.out.println("Point is:" +point.toString());
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
                z+=zmult;
                ThreePoint point = new ThreePoint(x,y,z);
               // System.out.println("Point is:" +point.toString());
                thePoints.add(point);
            }
        }
        return thePoints;
    }
    
    public static ArrayList<ThreePoint> bezierCurve(ThreePoint start, ThreePoint end, Random rand)
    {
        int vx = end.x - start.x;
        int vy = end.y - start.y;
        int vz = end.z - start.z;
        int dist = (int)Math.sqrt(vx*vx + vy*vy + vz*vz); //Distance between start and end points
        int x = start.x;
        int y = start.y;
        int z = start.z;
        double step;
        ThreePoint[] offsets;
        //We figure out how many random points to disperse in the middle of the line, based off of
        //the distance between the endpoints.
        if(dist<15)
        {
            offsets =new ThreePoint[2];
            step = 1.0;
        }
        else if(dist>=15 && dist < 60)
        {
            offsets =new ThreePoint[3];
            ThreePoint mid = new ThreePoint(x+vx/2, y+vy/2,z+vz/2);
            offsets[1] = getEndPoint(mid, dist/3, rand, true);
            step = 1.0/4;
        }
        else if(dist>=60 && dist < 120)
        {
            offsets =new ThreePoint[4];
            ThreePoint midone = new ThreePoint(x+vx/3, y+vy/3,z+vz/3);
            offsets[1] = getEndPoint(midone, dist/4, rand, true);
            ThreePoint midtwo = new ThreePoint(x+(2*vx/3), y+(2*vy/3),z+(2*vz/3));
            offsets[2] = getEndPoint(midtwo, dist/4, rand, true);
            step = 1.0/6;
        }
        else
        {
            offsets =new ThreePoint[5];
            ThreePoint midone = new ThreePoint(x+(vx/4), y+(vy/4),z+(vz/4));
            offsets[1] = getEndPoint(midone, dist/6, rand, true);
            ThreePoint midtwo = new ThreePoint(x+(2*vx/4), y+(2*vy/4),z+(2*vz/4));
            offsets[2] = getEndPoint(midtwo, dist/6, rand, true);
            ThreePoint midthree = new ThreePoint(x+(3*vx/4), y+(3*vy/4),z+(3*vz/4));
            offsets[3] = getEndPoint(midthree, dist/6, rand, true);
            step = 1.0/10;
            //DebugLogger.console("21");
        }
        //Offsets now contains the offset points (NOT THE OFFSET DISTANCE!!) for the middle
        //sections. Set beginning and endpts.
        int n = offsets.length-1;
        offsets[0] = start;
        offsets[n] = end;
        double t=step;
        ThreePoint prev = start;
        ThreePoint next;
        //DebugLogger.console("entering loop1");
        ArrayList<ThreePoint> veinPoints = new ArrayList<ThreePoint>();
       // System.out.print("The line is start:" +offsets[0].toString() + " end:"+offsets[n].toString());
        while(t < 1.0 + step)
        {
        	x=0;
        	y=0;
        	z=0;
        	for(int i=0;i<=n;i++)
        	{
        		//These lines perform the summation needed for a Bezier curve
        		x+=(int)(doubleOps(i,n,t)*(double)offsets[i].x);
        		y+=(int)(doubleOps(i,n,t)*(double)offsets[i].y);
        		z+=(int)(doubleOps(i,n,t)*(double)offsets[i].z);
        	}
        	//DebugLogger.console("t is x:" + x+" y:"+ y + " z:"+ z);
        	next = new ThreePoint(x,y,z);
        	//System.out.println("nodes are start:" + prev.toString());
        	veinPoints.addAll(bresenHamAlgo(prev,next));
        	//System.out.println("nodes and ends:" + next.toString());
        	prev = next;
        	t = t + step;
        }//connect points with straight lines
        //DebugLogger.console("entering loop2");
        //DebugLogger.console("size of array" + veinPoints.size());
        return veinPoints;
    }

	private static double doubleOps(int i, int n, double t)
	{
		return binomialCoeff(n,i)*Math.pow(1-t, n-i)*Math.pow(t, i);
	}
	
	public static int binomialCoeff(int n, int k) //N choose K or (\frac{n}{k})
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
			return start;
		int it=0;
		double phi,theta;
		int x,z,y;
		int rad = strike;
		while(true)
		{
			if(varyRadius==true)
			{
				rad = (int)((rad*.75)*rand.nextGaussian()) +rad;
			}
			if(rad < 5)
				rad = 5;
			phi = ((double)rand.nextInt(314))/100.0;
			y = (int) (rad*Math.cos(phi)) + start.y;
			if( y <128 && y >2 )
				break;
			if(start.y-strike > 125)
				break;
			it++;
			if(it>50)
				return start;
		}
		theta = ((double)rand.nextInt(628)/100.0);
		x = (int)(rad*Math.cos(theta)*Math.sin(phi)) + start.x;
		z = (int)(rad*Math.sin(theta)*Math.sin(phi)) + start.z;
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
