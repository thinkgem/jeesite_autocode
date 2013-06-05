package com.thinkgem.jeesite.autocode.util;

import java.io.File;

/**
 * crud xml
 *
 */
public class XmlObjectReport extends XmlObjectUtils {
	public static String getLeastColumns(){
		String userDir = System.getProperty("user.dir");
		String fileName = userDir +"\\report-columns.xml";
		return fileName;
	}
	
	public static String getLeastTable(){
		String userDir = System.getProperty("user.dir");
		String fileName = userDir +"\\report-table.xml";
		return fileName;
	}
	
	public static String getFtlPath(){
		String userDir = System.getProperty("user.dir");
		File dir = new File(userDir+"\\jeesite");
		if (!dir.exists()) {
			dir.mkdir();
		}
		File cruddir = new File(userDir+"\\jeesite\\report");
		if (!cruddir.exists()) {
			cruddir.mkdir();			
		}
		return userDir+"\\jeesite\\report\\ftl";
	}
}
