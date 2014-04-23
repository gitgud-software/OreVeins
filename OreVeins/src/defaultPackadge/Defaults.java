/*******************************************************************************
 * OreVeins realistic ore distribution plugin
 * Copyright (C) 2014  Kevin Mendoza
 * kevinmendoza@mac.com
 * Major Contributors: Kevin Song, Alex Lin, Darren Chang, Drew Parliament, Zeno Hao
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *******************************************************************************/
package defaultPackadge;


import org.bukkit.configuration.file.FileConfiguration;




public class Defaults 
{
	public static Lapiz lapiz;
	public static BIF bif;
	public static Coal coal;
	public static Iron iron;
	public static Gold gold;
	public static Redstone redstone;
	public static Emerald emerald;
	public static Diamond diamond;
	
	public static void popAndReadDefaults(FileConfiguration configs)
	{
		addVeinSystemDefaults(configs);
		addOtherSystemDefaults(configs);
	}
	
	private static void addVeinSystemDefaults(FileConfiguration config) 
	{
		iron = new Iron();
		iron.readWriteConfigs(config);
		gold = new Gold();
		gold.readWriteConfigs(config);
		redstone = new Redstone();
		redstone.readWriteConfigs(config);
		emerald = new Emerald();
		emerald.readWriteConfigs(config);
	}
	
	private static void addOtherSystemDefaults(FileConfiguration configs)
	{
		diamond = new Diamond();
		diamond.readWriteConfigs(configs);
		coal = new Coal();
		coal.setDefaults(configs);
		bif = new BIF();
		bif.setDefaults(configs);
		lapiz = new Lapiz();
		lapiz.setDefaults(configs);
	}
	
}