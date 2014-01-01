package com.icloud.kevinmendoza.OreVeins;

import java.io.Serializable;
import java.util.ArrayList;

public class Stringer implements Serializable
{
	public String ore;
	public int grade;
	public int bonanza;
	public int branch;
	public int contin;
	public ThreePoint startpoint;
	public ThreePoint endpoint;
	private ArrayList<ThreePoint> theOres;
	private Ellipse crossSection;
}
