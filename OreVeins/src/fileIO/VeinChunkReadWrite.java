package fileIO;
//this is the IO write to file class
//for some reason, delete doesn't work :/
//annd yeah, i mean its pretty simple
import geometryClasses.LineDrawingUtilityClass;
import geometryClasses.ThreePoint;
import geometryClasses.TwoPoint;
import geometryClasses.VeinStartPoint;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;

import com.icloud.kevinmendoza.OreVeins.DebugLogger;

public class VeinChunkReadWrite 
{
	public static HashMap<String,String[][][]> loadedMap;
	
	public static void deleteChunkInfo(String key, boolean b)
	{
		try
		{
			File file;
			if(b)
			{
				file = new File("plugins/OreVeins/ChunkInfo/"+ key +".txt");
			}
			else
			{
				file = new File("plugins/OreVeins/PrevChunkInfo/"+ key +".txt");
			}
    		if(file.delete())
    		{
    			//DebugLogger.console(file.getName() + " is deleted!");
    		}
    		else
    		{
    			//DebugLogger.console("Delete operation is failed.");
    		}
    	}
		catch(Exception e)
		{
			DebugLogger.console("Exception Delete operation is failed.");
    	}
	}
	
	public static void writeChunkInfo(String key,String[][][] theOtherArray, boolean b)
	{
		//DebugLogger.console("writing" + key);
		String[][][] prevChunks = readChunks(key,b);
		if(prevChunks !=null)
		{
			for(int x =0;x<16;x++)
			{
				for(int z=0;z<16;z++)
				{
					for(int y=1;y<127;y++)
					{
						if(prevChunks[x][y][z]==null 
								|| prevChunks[x][y][z].contains("COAL"))
							if(theOtherArray[x][y][z]!=null)
							{
								prevChunks[x][y][z] = theOtherArray[x][y][z];
							}
					}
				}
			}
		}
		else
		{
			prevChunks = theOtherArray;
		}
		try 
		{
			FileOutputStream chunkdir;
			if(b)
			{
				File veinFile = new File("plugins/OreVeins/ChunkInfo/"+ key +".txt");
				veinFile.createNewFile();
				 chunkdir = new FileOutputStream("plugins/OreVeins/ChunkInfo/"+key+".txt");
			}
			else
			{
				File veinFile = new File("plugins/OreVeins/PrevChunkInfo/"+ key +".txt");
				veinFile.createNewFile();
				chunkdir = new FileOutputStream("plugins/OreVeins/PrevChunkInfo/"+key+".txt");
			}
			ObjectOutputStream chunkOut = new ObjectOutputStream(chunkdir);
			chunkOut.writeObject(prevChunks);
			chunkdir.close();
			chunkOut.close();
		}
		catch (Exception ex)
		{
			DebugLogger.console("Couldn't save vein. Dir is missing");
		}

	}

	public static String[][][] readChunks(String entry, boolean loaded)
	{
		//DebugLogger.console("reading" + entry);
		try 
		{
			//DebugLogger.console("Fetching "+ entry);
			FileInputStream fin;
			if(loaded)
			{
				File veinFile = new File("plugins/OreVeins/ChunkInfo/"+ entry +".txt");
				veinFile.createNewFile();
				fin = new FileInputStream("plugins/OreVeins/ChunkInfo/"+entry+".txt");
			}
			else
			{
				File veinFile = new File("plugins/OreVeins/PrevChunkInfo/"+ entry +".txt");
				veinFile.createNewFile();
				fin = new FileInputStream("plugins/OreVeins/PrevChunkInfo/"+entry+".txt");
			}
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object obj =  ois.readObject();
			ois.close();
			fin.close();
			String[][][] chunk = new String[16][128][16];
			if(chunk.getClass() == obj.getClass() )
			{
				try
				{
					String[][][] veinIds = (String[][][]) obj;
					//DebugLogger.console("successful fetch!");
					return veinIds;
				}
				catch (Exception e)
				{
					DebugLogger.console("ERROR!!1");
					return null;
				}

			}
			else
			{
				DebugLogger.console("ERROR!!2");
				return null;
			}
		}
		catch (Exception ex)
		{
			//DebugLogger.console("no chunks at coordinate" );
			return null;
		}
	}

	public static void writeEndPoint(ThreePoint threePoint, String ore, int grade, int branch, int cross, int bonanza) 
	{
		VeinStartPoint seed = new VeinStartPoint(threePoint,ore,grade,branch,cross,bonanza);// TODO Auto-generated method stub
		ArrayList<VeinStartPoint> test = new ArrayList<VeinStartPoint>();
		String chx = new Integer(threePoint.x>>4).toString();
		String chz = new Integer(threePoint.z>>4).toString();
		String key = chx + ":" + chz;
		try 
		{
			//DebugLogger.console("Fetching "+ entry);
			FileInputStream fin = new FileInputStream("plugins/OreVeins/VeinInfo/"+ key + ".txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object obj =  ois.readObject();
			ois.close();
			fin.close();
			if(obj!=null)
			{
				if(test.getClass() == obj.getClass())
				{
					test = (ArrayList<VeinStartPoint>) obj;
				}
			}
			deletePoints(key);
		}
		catch (Exception ex)
		{
			
		}
		try{
			
			test.add(seed);
			File veinFile = new File("plugins/OreVeins/VeinInfo/"+ key +".txt");
			veinFile.createNewFile();
			FileOutputStream chunkdir = new FileOutputStream("plugins/OreVeins/VeinInfo/"+key+".txt");
			ObjectOutputStream chunkOut = new ObjectOutputStream(chunkdir);
			chunkOut.writeObject(test);
			chunkdir.close();
			chunkOut.close();
		}
		catch (Exception ex)
		{
			DebugLogger.console("Couldn't save veinseed. Dir is missing" +ex.toString());
		}
	}

	public static ArrayList<VeinStartPoint> readOutPoints(String key,Boolean loaded)
	{
		try 
		{
			//DebugLogger.console("Fetching "+ entry);
			ArrayList<VeinStartPoint> test = new ArrayList<VeinStartPoint>();
			FileInputStream fin = new FileInputStream("plugins/OreVeins/VeinInfo/"+ key + ".txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object obj =  ois.readObject();
			ois.close();
			fin.close();
			if(obj!=null)
			{
				if(test.getClass() == obj.getClass())
				{
					test = (ArrayList<VeinStartPoint>) obj;
					return test;
				}
				else
				{
					return null;
				}
			}
			else
			{
				return null;
			}
		}
		catch (Exception ex)
		{
			return null;
		}
		
	}
	
	public static void deletePoints(String key) 
	{
		try
		{
    		File file = new File("plugins/OreVeins/VeinInfo/"+ key +".txt");
    		if(file.delete())
    		{
    			//DebugLogger.console(file.getName() + " is deleted!");
    		}
    		else
    		{
    			//DebugLogger.console("Delete operation is failed.");
    		}
    	}
		catch(Exception e)
		{
			DebugLogger.console("Exception Delete operation is failed.");
    	}
		
	}
	
	public static void parseCenters(TwoPoint chunk, String ore, ArrayList<ThreePoint> centers)
	{
		HashMap<String,String[][][]> allPoints    = new HashMap<String,String[][][]>();
		for(int i =0;i<centers.size();i++)
		{
			String key = LineDrawingUtilityClass.convertToKey(centers.get(i));
			if(centers.get(i).y<127)
			{
				if(allPoints.containsKey(key))
				{
					String[][][] theMatrix = allPoints.get(key);
					ThreePoint thepoint = centers.get(i);
					ThreePoint shift = LineDrawingUtilityClass.shiftCoords(thepoint);
					//DebugLogger.console("x, y, z" + shift.x + " "+ shift.y + " " + shift.z );
					if(theMatrix[shift.x][shift.y][shift.z] == null
							||theMatrix[shift.x][shift.y][shift.z]== "COAL")
					{
						theMatrix[shift.x][thepoint.y][shift.z] = ore;
						allPoints.put(key, theMatrix);
					}
				}
				else
				{
					ThreePoint thepoint = centers.get(i);
					ThreePoint shift = LineDrawingUtilityClass.shiftCoords(thepoint);
					String[][][] theMatrix = new String[16][128][16];
					theMatrix[shift.x][shift.y][shift.z] = ore;
					allPoints.put(key, theMatrix);
				}
			}
		}
		for(String entry: allPoints.keySet())
		{
			partitionChunkInfo(entry, allPoints.get(entry));
		}
	}
	
	private static void partitionChunkInfo(String key,String[][][] partition)
	{
		String delims = "[:]";
		String[] tokens = key.split(delims);
		int x = Integer.parseInt(tokens[0]);
		int z = Integer.parseInt(tokens[1]);
		if(Bukkit.getWorlds().get(0).isChunkLoaded(x, z)) //returns true if loaded
		{	
			Chunk testChunk = Bukkit.getWorlds().get(0).getChunkAt(x,z);
			if(testChunk.getBlock(1, 1, 1)==null)//is not populated, save to file
			{
				DebugLogger.console("Do I even get called?");
				writeChunkInfo(key, partition,true);
			}
			else//is populated and loaded
			{
				if(loadedMap==null)
				{
					loadedMap = new HashMap<String,String[][][]>();
				}
				loadedMap.put(key, partition);
			}
		}
		else//not loaded
		{
			if(Bukkit.getWorlds().get(0).loadChunk(x,z,false))//true, is populated
			{
				Bukkit.getWorlds().get(0).unloadChunk(x,z);
				writeChunkInfo(key, partition,false);
			}
			else//false, not populated
			{
				writeChunkInfo(key, partition,true);
			}
		}
	}
}
