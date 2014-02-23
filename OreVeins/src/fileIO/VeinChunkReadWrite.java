package fileIO;
//this is the IO write to file class
//for some reason, delete doesn't work :/
//annd yeah, i mean its pretty simple


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.HashMap;


import com.icloud.kevinmendoza.OreVeins.DebugLogger;

public class VeinChunkReadWrite 
{
	public static void delete(String path)
	{
		try
		{
			File file;
			file = new File(path);
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
	
	public static HashMap<String,Boolean> read()
	{
		try{
		FileInputStream fin;
		File popListFile = new File("plugins/OreVeins/popList.txt");
		popListFile.createNewFile();
		fin = new FileInputStream("plugins/OreVeins/popList.txt");
		ObjectInputStream ois = new ObjectInputStream(fin);
		Object obj =  ois.readObject();
		ois.close();
		fin.close();
		HashMap<String, Boolean> theMap = new HashMap<String,Boolean>();
		
			if(theMap.getClass() == obj.getClass() )
			{
				try
				{
					HashMap<String, Boolean> popList = (HashMap<String, Boolean> ) obj;
					//DebugLogger.console("successful fetch!");
					delete("plugins/OreVeins/popList.txt");
					return popList;
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
			return null;
		}
	}

	public static String[][][] read(String key)
	{
		try 
		{
			FileInputStream chunkdir;
			File veinFile = new File("plugins/OreVeins/ChunkInfo/"+ key +".txt");
			veinFile.createNewFile();
			chunkdir = new FileInputStream("plugins/OreVeins/ChunkInfo/"+key+".txt");
			ObjectInputStream ois = new ObjectInputStream(chunkdir);
			Object obj =  ois.readObject();
			ois.close();
			chunkdir.close();
			delete("plugins/OreVeins/ChunkInfo/"+ key +".txt");
			String[][][] test = new String[16][128][16];
			if(test.getClass() == obj.getClass() )
			{
				try
				{
					String[][][] points = (String[][][] ) obj;
					//DebugLogger.console("successful fetch!");
					delete("plugins/OreVeins/ChunkInfo/"+key+".txt");
					return points;
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
			return null;
		}
	}

	public static void write(String key, String[][][] points)
	{
		try 
		{
			FileOutputStream chunkdir;
			File veinFile = new File("plugins/OreVeins/ChunkInfo/"+ key +".txt");
			veinFile.createNewFile();
			chunkdir = new FileOutputStream("plugins/OreVeins/ChunkInfo/"+key+".txt");
			ObjectOutputStream chunkOut = new ObjectOutputStream(chunkdir);
			chunkOut.writeObject(points);
			chunkdir.close();
			chunkOut.close();
		}
		catch (Exception ex)
		{
			DebugLogger.console("can't save dir, chunk key is missing");
		}
	}
	
	public static void write(HashMap<String,Boolean> popList)
	{
		try 
		{
			FileOutputStream chunkdir;
			File veinFile = new File("plugins/OreVeins/popList.txt");
			veinFile.createNewFile();
			chunkdir = new FileOutputStream("plugins/OreVeins/popList.txt");
			ObjectOutputStream chunkOut = new ObjectOutputStream(chunkdir);
			chunkOut.writeObject(popList);
			chunkdir.close();
			chunkOut.close();
		}
		catch (Exception ex)
		{
			DebugLogger.console("Couldn't save popList. Dir is missing");
		}
	}
	
}
