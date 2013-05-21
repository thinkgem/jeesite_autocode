package com.thinkgem.jeesite.autocode;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.thinkgem.jeesite.autocode.crud.StartWizard;
import com.thinkgem.jeesite.autocode.util.FileUtils;
import com.thinkgem.jeesite.autocode.util.XmlObjectUtils;

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
		String ftlPath = XmlObjectUtils.getCrudFtlPath();
		copyFtl(ftlPath,"controller.ftl");
		copyFtl(ftlPath,"dao.ftl");
		copyFtl(ftlPath,"entity.ftl");
		copyFtl(ftlPath,"service.ftl");
		copyFtl(ftlPath,"viewForm.ftl");
		copyFtl(ftlPath,"viewList.ftl");
	}
	
	private void copyFtl(String ftlPath,String fileName){
		URL from = StartWizard.class.getResource("template\\"+fileName);
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
