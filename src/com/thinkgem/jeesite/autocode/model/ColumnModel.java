package com.thinkgem.jeesite.autocode.model;

import com.thinkgem.jeesite.autocode.util.StringUtils;

public class ColumnModel extends AbstractModelObject {
	String columnName;
	String javaName;
	String javaType;
	int datasize;//数据大小
	int digits;//小数点位数
	String isNull;
	String isQuery;
	String isList;
	String isEdit;
	String comment;
	String isParamKey;
	String dictKey;//数据字典ID
	
	/**
	 * 做部分字段初始化操作
	 */
	public void init(){
		//设置JAVA属性名
		javaName = StringUtils.getJavaName(columnName);
		
		//设置是否为主键
		if("PRI".equalsIgnoreCase(isParamKey)){
			this.isParamKey = "Y";
		}
		this.isList = "Y";
		this.isQuery = "N";
		this.isEdit = "Y";
		this.dictKey = "";
		
		String dbType = StringUtils.substringBefore(javaType, "(").toUpperCase();
		if("VARCHAR".equals(dbType) || "CHAR".equals(dbType)){
			this.javaType = "String";
		}else if("DATETIME".equals(dbType)){
			this.javaType = "Date";
		}else if("BIGINT".equals(dbType)){
			this.javaType = "Long";
		}
			
	}
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getJavaName() {
		return javaName;
	}
	public void setJavaName(String javaName) {
		this.javaName = javaName;
	}
	public String getJavaType() {
		return javaType;
	}
	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getIsQuery() {
		return isQuery;
	}
	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}
	public String getIsList() {
		return isList;
	}
	public void setIsList(String isList) {
		this.isList = isList;
	}
	public String getIsEdit() {
		return isEdit;
	}
	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getIsParamKey() {
		return isParamKey;
	}
	public void setIsParamKey(String isParamKey) {
		this.isParamKey = isParamKey;
	}
	public String getDictKey() {
		return dictKey;
	}
	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}
	public int getDatasize() {
		return datasize;
	}
	public void setDatasize(int datasize) {
		this.datasize = datasize;
	}
	public int getDigits() {
		return digits;
	}
	public void setDigits(int digits) {
		this.digits = digits;
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("columnName=").append(columnName);
		str.append(",javaName=").append(javaName);
		str.append(",javaType=").append(javaType);
		str.append(",isNull=").append(isNull);
		str.append(",isQuery=").append(isQuery);
		str.append(",isList=").append(isList);
		str.append(",isEdit=").append(isEdit);
		str.append(",comment=").append(comment);
		str.append(",isParamKey=").append(isParamKey);
		str.append(",dictKey=").append(dictKey);
		return str.toString();
	}

	
}
