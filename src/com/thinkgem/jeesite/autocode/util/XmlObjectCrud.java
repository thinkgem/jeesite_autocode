package com.thinkgem.jeesite.autocode.util;

import java.io.File;

/**
 * crud xml
 *
 */
public class XmlObjectCrud extends XmlObjectUtils {
	public static String getLeastColumns(){
		String userDir = System.getProperty("user.dir");
		String fileName = userDir +"\\crud-columns.xml";
		return fileName;
	}
	
	public static String getLeastTable(){
		String userDir = System.getProperty("user.dir");
		String fileName = userDir +"\\crud-table.xml";
		return fileName;
	}
	
	public static String getFtlPath(){
		String userDir = System.getProperty("user.dir");
		File dir = new File(userDir+"\\jeesite");
		if (!dir.exists()) {
			dir.mkdir();
		}
		File cruddir = new File(userDir+"\\jeesite\\crud");
		if (!cruddir.exists()) {
			cruddir.mkdir();			
		}
		return userDir+"\\jeesite\\crud\\ftl";
	}
}
