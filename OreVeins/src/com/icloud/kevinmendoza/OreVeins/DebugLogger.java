package com.icloud.kevinmendoza.OreVeins;

import java.util.logging.Logger;

public class DebugLogger {
	    private static Logger log = Logger.getLogger("Minecraft");
	 
	    public static void console(String msg){
	        log.info("[OreVeins] " + msg);
	    }
	 
	    public static void console(String[] msg){
	        for(String s : msg){
	            log.info("[OreVeins] " + s);
	        }
	    }
	}


