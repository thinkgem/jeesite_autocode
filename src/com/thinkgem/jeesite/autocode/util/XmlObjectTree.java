package com.thinkgem.jeesite.autocode.util;

import java.io.File;

/**
 * crud xml
 *
 */
public class XmlObjectTree extends XmlObjectUtils {
	public static String getLeastColumns(){
		String userDir = System.getProperty("user.dir");
		String fileName = userDir +"\\tree-columns.xml";
		return fileName;
	}
	
	public static String getLeastTable(){
		String userDir = System.getProperty("user.dir");
		String fileName = userDir +"\\tree-table.xml";
		return fileName;
	}
	
	public static String getFtlPath(){
		String userDir = System.getProperty("user.dir");
		File dir = new File(userDir+"\\jeesite");
		if (!dir.exists()) {
			dir.mkdir();
		}
		File cruddir = new File(userDir+"\\jeesite\\tree");
		if (!cruddir.exists()) {
			cruddir.mkdir();			
		}
		return userDir+"\\jeesite\\tree\\ftl";
	}
}
