package com.thinkgem.jeesite.autocode.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.thinkgem.jeesite.autocode.model.ColumnModel;
import com.thinkgem.jeesite.autocode.model.DataSource;
import com.thinkgem.jeesite.autocode.model.TableModel;

public class AutoCodeDbUtils {
	/**
	 * 数据库连接 MySql,SqlServer,Oracle driver-驱动名,url-连接地址及数据库,user-用户名,pwd-密码
	 * */
	public static Connection getConntion(DataSource ds) {
		try {
			Class.forName(ds.getDriver()).newInstance();
			Properties prop = new Properties();
			prop.put("user", ds.getUser());
			prop.put("password", ds.getPassword());
			if ("ORACLE".equals(ds.getDataBaseType())) {
				prop.put("remarksReporting", "true");
			}
			Connection conn = DriverManager.getConnection(ds.getUrl(), prop);
			return conn;
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static List<TableModel> getTableList(DataSource ds) {
		List<TableModel> list = new ArrayList<TableModel>();
		String sql = "";
		if ("ORACLE".equals(ds.getDataBaseType())) {
			
		}else if("MYSQL".equals(ds.getDataBaseType())){
			sql = "SHOW TABLE STATUS FROM `"+ds.getDbName()+"`";
		}
		
		Connection conn = null;
		try {
			conn = getConntion(ds);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				TableModel table = new TableModel();
				table.setName(rs.getString("NAME"));
				table.setComment(rs.getString("comment"));
				list.add(table);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return list;
	}

	
	public static List<ColumnModel> getColumnList(DataSource ds,String tableName){
		Connection conn = null;
		
		List<ColumnModel> list = new ArrayList<ColumnModel>();
		
		String sql = "";
		if ("ORACLE".equals(ds.getDataBaseType())) {
			
		}else if("MYSQL".equals(ds.getDataBaseType())){
			sql = "show full fields from `"+tableName+"`";
		}
		
		try {
			conn = getConntion(ds);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {				
				ColumnModel column = new ColumnModel();
				column.setColumnName(rs.getString("FIELD"));
				column.setComment(rs.getString("COMMENT"));
				column.setIsParamKey(rs.getString("KEY"));
				column.setJavaType(rs.getString("TYPE"));
				column.init();
				list.add(column);
			}
			return list;
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
	}
}
