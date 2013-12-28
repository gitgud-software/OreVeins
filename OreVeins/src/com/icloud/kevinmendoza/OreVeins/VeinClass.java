package com.icloud.kevinmendoza.OreVeins;

import java.util.ArrayList;


public class VeinClass {
	public ThreePoint start;
	public ThreePoint end;
	public TwoPoint startChunk;
	public TwoPoint endChunk;
	public String ore;
	public double dt;
	public int vx;
	public int vy;
	public int vz;
	public int p1x;
	public int p1y;
	public int p1z;
	public ArrayList<ChunkParametric> chunkInfo;
	public VeinClass(TwoPoint startChunk, TwoPoint endChunk, String ore)
	{
		DebugLogger.console("in vein constructor beginning");
		this.ore = ore;
		this.endChunk = endChunk;
		this.startChunk = startChunk;
		this.start = new ThreePoint();
		this.end = new ThreePoint();
		double distx1 = (double)(start.x + (startChunk.x*16));
		double disty1 = (double)(start.y);
		double distz1 = (double)(start.x + (startChunk.x*16));
		double distx2 = (double)(end.x + (endChunk.x*16));
		double disty2 = (double)(end.y);
		double distz2 = (double)(end.x + (endChunk.x*16));
		double totaldist = Math.sqrt(Math.pow(distx1-distx2,2) + Math.pow(disty1-disty2,2) + Math.pow(distz1 - distz2,2));
		this.dt = 1 / totaldist;
		DebugLogger.console("in vein constructor dt is"+ this.dt);
		/*
		 int lowChunkX = lowX >> 4;
		 int lowChunkZ = lowZ >> 4;
		 int highChunkX = highX >> 4;
		 int highChunkZ = highZ >> 4;
		 */
		createChunkInfo();
	}
	private void createChunkInfo()
	{
		this.chunkInfo = new ArrayList<ChunkParametric>();
		double t = 0;
		this.p1x = (start.x + (startChunk.x*16));
		int p2x= (end.x + (endChunk.x*16));
		this.p1y = (start.y );
		int p2y= (end.y );
		this.p1z = (start.z + (startChunk.z*16));
		int p2z = (end.z + (endChunk.z*16));
		this.vx = p2x - p1x;
		this.vy = p2y - p1y;
		this.vz = p2z - p1z;
		int x; int z; 
		x = p1x;
		z = p1z;
		int chx = x >> 4; int chz= z >> 4; double ti = 0;
		//DebugLogger.console("about to analyse endpoints to make vein");
		while (t<=1)
		{
			x = (int)(p1x + (double)(vx)*t);
			z = (int)(p1z + (double)(vz)*t);
			if(chx != x >> 4 || chz != z >> 4)
			{
					TwoPoint theChunk = new TwoPoint(chx,chz);
					ChunkParametric par = new ChunkParametric(theChunk,ti, t-dt); 
					this.chunkInfo.add(par);
					chx = x >> 4;
					chz = z >> 4;
					ti = t;
			}
			t = t + dt;
		}
		chx = x >> 4;
		chz = z >> 4;
		TwoPoint theChunk = new TwoPoint(chx,chz);
		ChunkParametric par = new ChunkParametric(theChunk,ti, 1); 
		this.chunkInfo.add(par);
		DebugLogger.console("added chunks to veins " + this.chunkInfo.size());
	}
	public Boolean contains(TwoPoint chunk)
	{
		for(int i = 0;i<this.chunkInfo.size();i++)
		{
			if(chunkInfo.get(i).theChunk.equals(chunk))
			{
				return true;
			}
		}
		return false;
	}
	public void clearChunk(TwoPoint chunk)
	{
		int index = 1;
		for(int i = 0;i< this.chunkInfo.size();i++)
		{
			if(chunkInfo.get(i).theChunk.equals(chunk))
			{
				index = i;
			}
		}
		chunkInfo.remove(index);
	}
	public ChunkParametric returnChunkInfo(TwoPoint chunk)
	{
		for(int i = 0;i< this.chunkInfo.size();i++)
		{
			if(chunkInfo.get(i).theChunk.equals(chunk))
			{
				return chunkInfo.get(i);
			}
		}
		return null;
	}
}
