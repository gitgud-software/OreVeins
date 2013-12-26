package com.icloud.kevinmendoza.OreVeins;

import org.bukkit.Material;

public class VeinClass {
	public ThreePoint start;
	public ThreePoint end;
	public TwoPoint startChunk;
	public TwoPoint endChunk;
	public String ore;
	public VeinClass(TwoPoint start, TwoPoint end, String ore)
	{
		this.ore = ore;
		this.endChunk = end;
		this.startChunk = start;
		this.start = new ThreePoint();
		this.end = new ThreePoint();
	}
}
