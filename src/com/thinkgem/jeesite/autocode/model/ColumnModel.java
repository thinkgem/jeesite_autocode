package com.thinkgem.jeesite.autocode.model;

import com.thinkgem.jeesite.autocode.util.StringUtils;

public class ColumnModel extends AbstractModelObject {
	String columnName;
	String javaName;
	String javaType;
	String dbType;//数据库声明的类型
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
		
		
		this.isQuery = "N";
		this.dictKey = "";
		
		this.isList = "Y";
		this.isEdit = "Y";
		
		String dbType = StringUtils.substringBefore(javaType, "(").toUpperCase();
		if("VARCHAR".equals(dbType) || "CHAR".equals(dbType) ||"VARCHAR2".equals(dbType)){
			this.javaType = "String";
		}else if("DATETIME".equals(dbType)){
			this.javaType = "Date";
		}else if("BIGINT".equals(dbType)){
			this.javaType = "Long";
		}
	}
	
	/**
	 * 做部分字段初始化操作,处理ORACLE数据库
	 */
	public void initOra(){
		//设置JAVA属性名
		javaName = StringUtils.getJavaName(columnName);
		
		//设置是否为主键
		if("PRI".equalsIgnoreCase(isParamKey)){
			this.isParamKey = "Y";
		}
		
		//忽略掉部分字段DATA_STATUS,CREATE_USER,CREATE_DATE,UPDATE_USER,UPDATE_DATE
		if("DATA_STATUS".equalsIgnoreCase(columnName)||"CREATE_USER".equalsIgnoreCase(columnName)||"CREATE_DATE".equalsIgnoreCase(columnName)||"UPDATE_USER".equalsIgnoreCase(columnName)||"UPDATE_DATE".equalsIgnoreCase(columnName)){
			this.isList = "N";
			this.isEdit = "N";
		}else{
			this.isList = "Y";
			this.isEdit = "Y";
		}
		
		this.isQuery = "N";		
		this.dictKey = "";
		
		//转换为大写比较
		this.dbType = this.dbType.toUpperCase();
		if("VARCHAR".equals(dbType) || "CHAR".equals(dbType) ||"VARCHAR2".equals(dbType)){
			this.javaType = "String";
		}else if("DATETIME".equals(dbType)||"DATE".equals(dbType)){
			this.javaType = "Date";
		}else if("BIGINT".equals(dbType)){
			this.javaType = "Long";
		}else if("NUMBER".equals(dbType)&&digits==0){
			this.javaType = "Long";
		}else if("NUMBER".equals(dbType)&&digits>0){
			this.javaType = "Double";
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

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}
	
	public boolean isKey(){
		return "Y".equals(this.isParamKey);
	}
	
	public boolean isNullAble(){
		return "Y".equals(this.isNull);
	}

	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("columnName=").append(columnName);
		str.append(",javaName=").append(javaName);
		str.append(",javaType=").append(javaType);
		str.append(",dbType=").append(dbType);
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
