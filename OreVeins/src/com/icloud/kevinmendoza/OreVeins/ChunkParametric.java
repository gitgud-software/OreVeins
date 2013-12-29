package com.icloud.kevinmendoza.OreVeins;

import java.io.Serializable;

public class ChunkParametric implements Serializable
{
	public TwoPoint theChunk;
	public double p1;
	public double p2;
	public ChunkParametric(TwoPoint theChunk, double p1, double p2)
	{
		this.p1 = p1;
		this.p2 = p2;
		this.theChunk = theChunk;
	}
}
