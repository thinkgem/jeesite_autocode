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
	String sql;//执行的SQL语句
	
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
	
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("tableName=").append(tableName).append("\r\n");
		str.append("sql=").append(sql).append("\r\n");
		str.append("tableComment=").append(tableComment).append("\r\n");
		str.append("topPackage=").append(topPackage).append("\r\n");
		str.append("functionNameEn=").append(functionNameEn).append("\r\n");
		str.append("functionNameCn=").append(functionNameCn).append("\r\n");
		str.append("urlPrefix=").append(urlPrefix).append("\r\n");
		str.append("jspLocation=").append(jspLocation).append("\r\n");
		str.append("author=").append(author).append("\r\n");
		str.append("version=").append(version).append("\r\n");
		str.append("filePath=").append(filePath).append("\r\n");
		return str.toString();
	}
	
	
	
}
