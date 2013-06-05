package com.thinkgem.jeesite.autocode.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;


public class FreeMakerUtils {
	
	public static Configuration getFreeMarkerCfg(String ftlPath) {
		Configuration freemarkerCfg = new Configuration();
		freemarkerCfg.setBooleanFormat("true,false");
		freemarkerCfg.setNumberFormat("#");
		freemarkerCfg.setDefaultEncoding("UTF-8");
		try {
			freemarkerCfg.setDirectoryForTemplateLoading(new File(ftlPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return freemarkerCfg;
	}

	public static boolean generateFile(Configuration cfg,
			String templateFileName, Map propMap, String relPath,
			String fileName, String rootDir) {
		try {
			Template t = cfg.getTemplate(templateFileName);

			creatDirs(rootDir, relPath);

			File dir = new File(rootDir + "/" + relPath);
			if (!dir.exists()) {
				dir.mkdir();
			}

			File afile = new File(rootDir + "/" + relPath + "/"
					+ fileName);

			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(afile), "UTF-8"));

			t.process(propMap, out);
			out.close();
		} catch (TemplateException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public static boolean creatDirs(String aParentDir, String aSubDir) {
		File aFile = new File(aParentDir);
		if (aFile.exists()) {
			File aSubFile = new File(aParentDir + "/" + aSubDir);
			if (!aSubFile.exists()) {
				return aSubFile.mkdirs();
			} else {
				return true;
			}
		} else {
			return false;
		}
	}
}
