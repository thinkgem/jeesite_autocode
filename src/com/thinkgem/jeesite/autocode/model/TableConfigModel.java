package com.thinkgem.jeesite.autocode.model;

import com.thinkgem.jeesite.autocode.util.StringUtils;

public class TableConfigModel {
	String tableName;
	String tableComment;
	String topPackage;
	String functionNameEn;
	String functionNameCn;
	String urlPrefix;
	String jspLocation;
	String author;
	String version;
	String filePath;
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
	public String getTableJavaName(){
		return StringUtils.getJavaName(this.tableName);
	}
	
	public String getTableComment() {
		return tableComment;
	}
	public void setTableComment(String tableComment) {
		this.tableComment = tableComment;
	}
	public String getTopPackage() {
		return topPackage;
	}
	public void setTopPackage(String topPackage) {
		this.topPackage = topPackage;
	}
	public String getFunctionNameEn() {
		return functionNameEn;
	}
	public void setFunctionNameEn(String functionNameEn) {
		this.functionNameEn = functionNameEn;
	}
	public String getFunctionNameCn() {
		return functionNameCn;
	}
	public void setFunctionNameCn(String functionNameCn) {
		this.functionNameCn = functionNameCn;
	}
	public String getUrlPrefix() {
		return urlPrefix;
	}
	public void setUrlPrefix(String urlPrefix) {
		this.urlPrefix = urlPrefix;
	}
	public String getJspLocation() {
		return jspLocation;
	}
	public void setJspLocation(String jspLocation) {
		this.jspLocation = jspLocation;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}
