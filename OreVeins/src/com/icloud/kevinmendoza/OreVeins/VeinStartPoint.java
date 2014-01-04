package com.icloud.kevinmendoza.OreVeins;

import java.io.Serializable;

public class VeinStartPoint implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6572732891233298111L;
	public ThreePoint start;
	public String ore;
	public int grade;
	public int branch;
	public int cross;
	public VeinStartPoint(ThreePoint threePoint2, String ore2, int grade2,
			int branch2, int cross2, int bonanza2) 
	{
		this.start = threePoint2;
		this.ore =ore2;
		this.grade = grade2;
		this.branch = branch2;
		this.cross = cross2;
		if(this.grade <5)
		{
			this.grade = 30;
		}
		if(this.branch <20)
		{
			this.branch = 70;
		}
		if(this.cross > 50)
		{
			this.cross = 10;
		}			
	}
}
