package com.icloud.kevinmendoza.OreVeins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class VeinChunkReadWrite 
{
	private void deleteVein(VeinClass vein)
	{
		try
		{
			String v = new Integer(vein.VeinID).toString();
    		File file = new File("plugins/OreVeins/ChunkInfo/"+v+".txt");
    		if(file.delete())
    		{
    			//DebugLogger.console(file.getName() + " is deleted!");
    		}
    		else
    		{
    			DebugLogger.console("Delete operation is failed.");
    		}
    	
    	}
		catch(Exception e)
		{
			DebugLogger.console("Delete operation is failed.");
    	}
	}
	
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
	
	public void writeChunkInfo(TwoPoint chunk,ArrayList<Integer> veins)
	{
		if(veins.isEmpty())
		{
			deleteChunkInfo(chunk);
		}
		else
		{
			try 
			{
				deleteChunkInfo(chunk);
				File veinFile = new File("plugins/OreVeins/ChunkInfo/"+ chunk.location() +".txt");
				veinFile.createNewFile();
				FileOutputStream chunkdir = new FileOutputStream("plugins/OreVeins/ChunkInfo/"+chunk.location()+".txt");
				ObjectOutputStream chunkOut = new ObjectOutputStream(chunkdir);
				chunkOut.writeObject(veins);
				chunkdir.close();
				chunkOut.close();
			}
			catch (Exception ex)
			{
				DebugLogger.console("Couldn't save vein. Dir is missing");
			}
		}
	}
	
	public void writeVein(VeinClass vein)
	{
		if(vein.chunkInfo.isEmpty())
		{
			deleteVein(vein);
			DebugLogger.console("deleting vein");
		}
		else
		{
			try 
			{
				//deleteVein(vein);
				String v = new Integer(vein.VeinID).toString();
				File veinFile = new File("plugins/OreVeins/VeinInfo/"+ v +".txt");
				veinFile.createNewFile();
				FileOutputStream veindir = new FileOutputStream("plugins/OreVeins/VeinInfo/"+ v +".txt");
				ObjectOutputStream veinOut = new ObjectOutputStream(veindir);
				veinOut.writeObject(vein);
				veindir.close();
				veinOut.close();
				DebugLogger.console("saving vein");
			}
			catch (Exception ex)
			{
				DebugLogger.console("Couldn't save vein. Dir is missing "+ ex.getMessage());
			}
		}
	}

	public ArrayList<Integer> readChunks(TwoPoint chunk)
	{
		try 
		{
			FileInputStream fin = new FileInputStream("plugins/OreVeins/ChunkInfo/"+chunk.location() + ".txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object obj =  ois.readObject();
			ois.close();
			fin.close();
			ArrayList<Integer> Ids = new ArrayList<Integer>();
			Ids.add(1);
			if(Ids.getClass() == obj.getClass() )
			{
				try
				{
					ArrayList<Integer> veinIds = (ArrayList<Integer>) obj;
					return veinIds;
				}
				catch (Exception e)
				{
					return null;
				}

			}
			else
				return null;
		}
		catch (Exception ex)
		{
			return null;
		}
	}
	
	public VeinClass readVein(int id)
	{
		try 
		{
			String theID  = new Integer(id).toString();
			FileInputStream fin = new FileInputStream("plugins/OreVeins/VeinInfo/"+ theID + ".txt");
			ObjectInputStream ois = new ObjectInputStream(fin);
			Object obj =  ois.readObject();
			ois.close();
			fin.close();
			VeinClass Ids =new VeinClass();
			if(Ids.getClass() == obj.getClass() )
			{
				VeinClass vein = (VeinClass) obj;
				return vein;
			}
			else
				return null;
		}
		catch (Exception ex)
		{
			return null;
		}
	}
	
}
