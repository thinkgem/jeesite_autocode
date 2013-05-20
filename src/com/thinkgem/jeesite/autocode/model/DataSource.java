package com.thinkgem.jeesite.autocode.model;

public class DataSource extends AbstractModelObject {
	private String name;
	private String driver;
	private String url;
	private String user;
	private String password;
	private String dbName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldValue = this.name;
		this.name = name;
		firePropertyChange("name", oldValue, name);
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		String oldValue = this.driver;
		this.driver = driver;
		firePropertyChange("driver", oldValue, driver);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		String oldValue = this.url;
		this.url = url;
		firePropertyChange("url", oldValue, url);
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		String oldValue = this.user;
		this.user = user;
		firePropertyChange("user", oldValue, user);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		String oldValue = this.password;
		this.password = password;
		firePropertyChange("password", oldValue, password);
	}

	/**
	 * 获取数据库类型
	 * @return
	 */
	public String getDataBaseType(){
		if("oracle.jdbc.driver.OracleDriver".equals(this.driver)){
			return "ORACLE";
		}else if("com.mysql.jdbc.Driver".equals(this.driver)){
			return "MYSQL";
		}else{
			return "";
		}
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		String oldValue = this.dbName;
		this.dbName = dbName;
		firePropertyChange("dbName", oldValue, dbName);
	}

}
