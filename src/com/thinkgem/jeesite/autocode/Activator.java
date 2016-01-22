package com.thinkgem.jeesite.autocode;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.thinkgem.jeesite.autocode.crud.StartCurdWizard;
import com.thinkgem.jeesite.autocode.tree.StartTreeWizard;
import com.thinkgem.jeesite.autocode.util.FileUtils;
import com.thinkgem.jeesite.autocode.util.XmlObjectCrud;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	// The plug-in ID
	public static final String PLUGIN_ID = "com.thinkgem.jeesite.autocode"; //$NON-NLS-1$

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		initFtlFile();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
	
	private void initFtlFile(){
		copyCrudFtl();
		//copyTreeFtl();
	}
	
	/**
	 * 拷贝增删查改结构生成配置
	 */
	private void copyCrudFtl(){
		String ftlPath = XmlObjectCrud.getFtlPath();
		//copyCrudFtlSingle(ftlPath,"controller.ftl");
		//copyCrudFtlSingle(ftlPath,"dao.ftl");
		copyCrudFtlSingle(ftlPath,"entity.ftl");
		//copyCrudFtlSingle(ftlPath,"service.ftl");
		//copyCrudFtlSingle(ftlPath,"viewForm.ftl");
		//copyCrudFtlSingle(ftlPath,"viewList.ftl");
	}
	
	private void copyCrudFtlSingle(String ftlPath,String fileName){
		URL from = StartCurdWizard.class.getResource("template/"+fileName);
		File dist = new File(ftlPath+"/"+fileName);
		if(!dist.exists()){
			try {
				FileUtils.copyURLToFile(from, dist);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 拷贝树形结构生成配置
	 */
	private void copyTreeFtl(){
		String ftlPath = XmlObjectCrud.getFtlPath();
		copyTreeFtlSingle(ftlPath,"controller.ftl");
		copyTreeFtlSingle(ftlPath,"dao.ftl");
		copyTreeFtlSingle(ftlPath,"entity.ftl");
		copyTreeFtlSingle(ftlPath,"service.ftl");
		copyTreeFtlSingle(ftlPath,"viewForm.ftl");
		copyTreeFtlSingle(ftlPath,"viewList.ftl");
	}
	
	private void copyTreeFtlSingle(String ftlPath,String fileName){
		URL from = StartTreeWizard.class.getResource("template\\"+fileName);
		File dist = new File(ftlPath+"\\"+fileName);
		if(!dist.exists()){
			try {
				FileUtils.copyURLToFile(from, dist);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
