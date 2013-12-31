package com.icloud.kevinmendoza.OreVeins;
//this is the IO write to file class
//for some reason, delete doesn't work :/
//annd yeah, i mean its pretty simple
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class VeinChunkReadWrite 
{
	
	public void deleteChunkInfo(TwoPoint chunk)
	{
		try
		{
    		File file = new File("plugins/OreVeins/ChunkInfo/"+ chunk.location() +".txt");
    		if(file.delete())
    		{
    			DebugLogger.console(file.getName() + " is deleted!");
    		}
    		else
    		{
    			DebugLogger.console("Delete operation is failed.");
    		}
    	}
		catch(Exception e)
		{
			DebugLogger.console("Exception Delete operation is failed.");
    	}
	}
	
	public void writeChunkInfo(String key,String[][][] theOtherArray)
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

	public String[][][] readChunks(String entry)
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
	
}
