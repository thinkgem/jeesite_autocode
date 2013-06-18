package com.thinkgem.jeesite.autocode.crud;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.wizard.Wizard;

import com.thinkgem.jeesite.autocode.model.ColumnModel;
import com.thinkgem.jeesite.autocode.model.TableConfigModel;
import com.thinkgem.jeesite.autocode.model.TableModel;
import com.thinkgem.jeesite.autocode.util.FreeMakerUtils;
import com.thinkgem.jeesite.autocode.util.StringUtils;
import com.thinkgem.jeesite.autocode.util.XmlObjectCrud;
import com.thinkgem.jeesite.autocode.util.XmlObjectUtils;

import freemarker.template.Configuration;

public class StartCurdWizard extends Wizard {
	//所有全局变量防止在这里
	
	TableModel tableModel = new TableModel();

	public StartCurdWizard() {
		setWindowTitle("树状机构生成");
		setHelpAvailable(false);
		setNeedsProgressMonitor(false);
	}

	@Override
	public void addPages() {
		addPage(new PageOne());
		addPage(new PageTwo());
		addPage(new PageThree());
		
		//设置上一次操作的路径
		TableConfigModel fileconfig = (TableConfigModel)XmlObjectUtils.objectXmlDecoder(XmlObjectCrud.getLeastTable());
		if(fileconfig!=null){
			PageTwo pageTwo = (PageTwo)super.getPage("PageTwo");
			TableConfigModel pttable = pageTwo.getTableConfig();
			pttable.setAuthor(fileconfig.getAuthor());
			pttable.setVersion(fileconfig.getVersion());
			pttable.setTopPackage(fileconfig.getTopPackage());
			pttable.setFilePath(fileconfig.getFilePath());
			pttable.setJspLocation(fileconfig.getJspLocation());
			pttable.setFunctionNameCn(fileconfig.getFunctionNameCn());
			pttable.setFunctionNameEn(fileconfig.getFunctionNameEn());
			pttable.setUrlPrefix(fileconfig.getUrlPrefix());
			pttable.setPermissionPrefix(fileconfig.getPermissionPrefix());
		}
	}

	@Override
	public boolean performFinish() {
		PageTwo pageTwo = (PageTwo)super.getPage("PageTwo");
		pageTwo.checkInput();
		
		TableConfigModel tableconfig = pageTwo.getTableConfig();
		PageThree pageThree = (PageThree)super.getPage("PageThree");
		XmlObjectUtils.objectXmlEncoder(pageThree.getColumnList(), XmlObjectCrud.getLeastColumns());		
		XmlObjectUtils.objectXmlEncoder(tableconfig,XmlObjectCrud.getLeastTable());
		//设置主键字段
		for(ColumnModel column:pageThree.getColumnList()){
			if(column.isKey()){
				tableconfig.setKey(column);
				break;
			}
		}
		Map data = new HashMap();
		data.put("columnList", pageThree.getColumnList());
		data.put("table", tableconfig);
		
		
		String separator = File.separator;
		String basePath = pageTwo.getTableConfig().getFilePath();
		
		//生成文件
		Configuration freemakerCfg = FreeMakerUtils.getFreeMarkerCfg(this.getClass(), "template");		
		//Configuration freemakerCfg = FreeMakerUtils.getFreeMarkerCfg(XmlObjectCrud.getFtlPath());		
		FreeMakerUtils.generateFile(freemakerCfg, "entity.ftl", data, "src\\"+StringUtils.replace(tableconfig.getTopPackage(), ".", "\\") + "\\entity\\", StringUtils.capitalize(tableconfig.getTableJavaName()) +".java", basePath);
		FreeMakerUtils.generateFile(freemakerCfg,"hbm.ftl",data,"src\\config\\hibernate\\",StringUtils.capitalize(tableconfig.getTableJavaName())+".hbm.xml",basePath);
		FreeMakerUtils.generateFile(freemakerCfg,"struts.ftl",data,"src\\config\\struts\\warehouse\\","tmp.xml",basePath);
		FreeMakerUtils.generateFile(freemakerCfg, "action.ftl", data, "src\\"+StringUtils.replace(tableconfig.getTopPackage(), ".", "\\") + "\\web\\", StringUtils.capitalize(tableconfig.getFunctionNameEn()) +"Action.java", basePath);
		FreeMakerUtils.generateFile(freemakerCfg, "service.ftl", data, "src\\"+StringUtils.replace(tableconfig.getTopPackage(), ".", "\\") + "\\services\\", StringUtils.capitalize(tableconfig.getFunctionNameEn()) +"Service.java", basePath);
		FreeMakerUtils.generateFile(freemakerCfg, "serviceImpl.ftl", data, "src\\"+StringUtils.replace(tableconfig.getTopPackage(), ".", "\\") + "\\services\\impl\\", StringUtils.capitalize(tableconfig.getFunctionNameEn()) +"ServiceImpl.java", basePath);
		FreeMakerUtils.generateFile(freemakerCfg, "qry.ftl", data, "src\\"+StringUtils.replace(tableconfig.getTopPackage(), ".", "\\") + "\\qry\\", StringUtils.capitalize(tableconfig.getFunctionNameEn()) +"Qry.java", basePath);
		
		FreeMakerUtils.generateFile(freemakerCfg, "list.ftl", data, "WebRoot\\"+StringUtils.replace(tableconfig.getJspLocation(), "/", "\\"), "list.jsp", basePath);
		FreeMakerUtils.generateFile(freemakerCfg, "edit.ftl", data, "WebRoot\\"+StringUtils.replace(tableconfig.getJspLocation(), "/", "\\"), "edit.jsp", basePath);
		FreeMakerUtils.generateFile(freemakerCfg, "view.ftl", data, "WebRoot\\"+StringUtils.replace(tableconfig.getJspLocation(), "/", "\\"), "view.jsp", basePath);


		//MessageDialog.openInformation(super.getShell(), "代码生成成功", "代码生成成功，生成代码存放路径："+tableconfig.getFilePath());
		return false;
	}
	
	
	
	public void setTableModel(String name,String comment){
		tableModel.setName(name);
		tableModel.setComment(comment);
	}

	public TableModel getTableModel() {
		return tableModel;
	}

	@Override
	public boolean canFinish() {
		if("PageThree".equals(super.getContainer().getCurrentPage().getName())){
			return true;
		}else{
			return false;
		}
	}
	
}
