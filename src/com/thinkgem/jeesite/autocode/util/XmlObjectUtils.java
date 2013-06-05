package com.thinkgem.jeesite.autocode.util;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

import com.thinkgem.jeesite.autocode.crud.StartCurdWizard;

public class XmlObjectUtils {
	/**
	 * 把java的可序列化的对象(实现Serializable接口)序列化保存到XML文件里面,如果想一次保存多个可序列化对象请用集合进行封装
	 * 保存时将会用现在的对象原来的XML文件内容
	 * 
	 * @param obj
	 *            要序列化的可序列化的对象
	 * @param fileName
	 *            带完全的保存路径的文件名
	 * @throws FileNotFoundException
	 *             指定位置的文件不存在
	 * @throws IOException
	 *             输出时发生异常
	 * @throws Exception
	 *             其他运行时异常
	 */
	public static void objectXmlEncoder(Object obj, String fileName) {
		// 创建输出文件
		try {
			File fo = new File(fileName);
			// 文件不存在,就创建该文件
			if (!fo.exists()) {
				// 先创建文件的目录
				String path = fileName.substring(0, fileName.lastIndexOf('.'));
				File pFile = new File(path);
				pFile.mkdirs();
			}
			// 创建文件输出流
			FileOutputStream fos = new FileOutputStream(fo);
			// 创建XML文件对象输出类实例
			XMLEncoder encoder = new XMLEncoder(fos);
			// 对象序列化输出到XML文件
			encoder.writeObject(obj);
			encoder.flush();
			// 关闭序列化工具
			encoder.close();
			// 关闭输出流
			fos.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 读取由objSource指定的XML文件中的序列化保存的对象,返回的结果经过了List封装
	 * 
	 * @param objSource
	 *            带全部文件路径的文件全名
	 * @return 由XML文件里面保存的对象构成的List列表(可能是一个或者多个的序列化保存的对象)
	 * @throws FileNotFoundException
	 *             指定的对象读取资源不存在
	 * @throws IOException
	 *             读取发生错误
	 * @throws Exception
	 *             其他运行时异常发生
	 */
	public static Object objectXmlDecoder(String objSource) {
		//List objList = new ArrayList();
		Object obj = null;
		try {
			File fin = new File(objSource);
			FileInputStream fis = new FileInputStream(fin);
			XMLDecoder decoder = new XMLDecoder(fis);
			obj = decoder.readObject();
			/*try {
				while ((obj = decoder.readObject()) != null) {
					objList.add(obj);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}*/
			fis.close();
			decoder.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return obj;
	}
	
	/**
	 * @return
	 */
	public static String getDataSourceFileName(){
		String userDir = System.getProperty("user.dir");
		String fileName = userDir +"\\datasource.xml";
		return fileName;
	}
	
}
