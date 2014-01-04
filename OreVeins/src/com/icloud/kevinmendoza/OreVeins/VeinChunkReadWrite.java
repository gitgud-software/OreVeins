package com.icloud.kevinmendoza.OreVeins;
//this is the IO write to file class
//for some reason, delete doesn't work :/
//annd yeah, i mean its pretty simple
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class VeinChunkReadWrite 
{
	
	public static void deleteChunkInfo(String key)
	{
		try
		{
    		File file = new File("plugins/OreVeins/ChunkInfo/"+ key +".txt");
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
	
	public static void writeChunkInfo(String key,String[][][] theOtherArray)
	{
			try 
			{
				File veinFile = new File("plugins/OreVeins/ChunkInfo/"+ key +".txt");
				veinFile.createNewFile();
				FileOutputStream chunkdir = new FileOutputStream("plugins/OreVeins/ChunkInfo/"+key+".txt");
				ObjectOutputStream chunkOut = new ObjectOutputStream(chunkdir);
				chunkOut.writeObject(theOtherArray);
				chunkdir.close();
				chunkOut.close();
			}
			catch (Exception ex)
			{
				DebugLogger.console("Couldn't save vein. Dir is missing");
			}
		
	}

	public static String[][][] readChunks(String entry)
	{
		try 
		{
			//DebugLogger.console("Fetching "+ entry);
			FileInputStream fin = new FileInputStream("plugins/OreVeins/ChunkInfo/"+entry + ".txt");
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
			//DebugLogger.console("ERROR!!3");
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

	public static ArrayList<VeinStartPoint> readOutPoints(String key)
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
}
