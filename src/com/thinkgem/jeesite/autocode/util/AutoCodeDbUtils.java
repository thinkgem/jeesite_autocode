package com.thinkgem.jeesite.autocode.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
			sql = "select table_name as name,comments as comments from user_tab_comments";
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
				table.setName(rs.getString("NAME").toLowerCase());
				if ("ORACLE".equals(ds.getDataBaseType())) {
					table.setComment(rs.getString("comments"));
				}else if("MYSQL".equals(ds.getDataBaseType())){
					table.setComment(rs.getString("comment"));
				}
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
			StringBuffer buf = new StringBuffer();
			buf.append("select a.COLUMN_NAME as FIELD, c.COMMENTS as COMMENTS,A.DATA_LENGTH,A.DATA_PRECISION,A.DATA_SCALE,a.DATA_TYPE,a.NULLABLE ");
			buf.append("  from user_tab_columns a, user_col_comments c   ");
			buf.append(" where a.TABLE_NAME = c.TABLE_NAME(+)            ");
			buf.append("   and a.COLUMN_NAME = c.COLUMN_NAME(+)          ");
			buf.append("   and a.TABLE_NAME = '").append(tableName.toUpperCase()).append("' ORDER BY COLUMN_ID ASC");
			sql = buf.toString();
		}else if("MYSQL".equals(ds.getDataBaseType())){
			sql = "show full fields from `"+tableName+"`";
		}
		
		try {
			conn = getConntion(ds);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next()) {
				ColumnModel column = new ColumnModel();
				column.setColumnName(rs.getString("FIELD").toLowerCase());
				
				if("MYSQL".equals(ds.getDataBaseType())){
					column.setIsParamKey(rs.getString("KEY"));
					column.setComment(rs.getString("COMMENT"));
					column.setJavaType(rs.getString("TYPE"));
				}else if ("ORACLE".equals(ds.getDataBaseType())) {
					column.setDbType(rs.getString("DATA_TYPE"));
					
					if(rs.getInt("DATA_PRECISION")>0){
						//如果是数字，此值会大于0
						column.setDatasize(rs.getInt("DATA_PRECISION"));
						column.setDigits(rs.getInt("DATA_SCALE"));
					}else{
						//如果是字符串，需要取DATA_LENGTH值
						column.setDatasize(rs.getInt(("DATA_LENGTH")));
					}
					
					column.setComment(rs.getString("COMMENTS"));					
					column.setIsNull(rs.getString("NULLABLE"));
					column.setIsParamKey("N");
				}
								
				list.add(column);
			}
			rs.close();
			
			if ("ORACLE".equals(ds.getDataBaseType())) {
				//获取主键
				StringBuffer buf = new StringBuffer();
				buf.append("select cu.COLUMN_NAME                            ");
				buf.append("  from user_cons_columns cu, user_constraints au ");
				buf.append(" where cu.constraint_name = au.constraint_name   ");
				buf.append("   and au.constraint_type = 'P'                  ");
				buf.append("   and au.table_name = '").append(tableName.toUpperCase()).append("'");

				ResultSet rspk = stmt.executeQuery(buf.toString());
				while (rspk.next()) {
					String columnName = rspk.getString("COLUMN_NAME");
					for(ColumnModel column:list){
						if(columnName.equalsIgnoreCase(column.getColumnName())){
							column.setIsParamKey("PRI");
							break;
						}
					}
				}
				rspk.close();
			}
			
			for(ColumnModel column:list){
				if("MYSQL".equals(ds.getDataBaseType())){
					column.init();
				}else if ("ORACLE".equals(ds.getDataBaseType())) {
					column.initOra();//针对ORACLE进行初始化
				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbUtils.closeQuietly(conn);
		}
		return null;
	}
	
	
	public static List<ColumnModel> getColumnListBySql(DataSource ds,final String sql){
		//SQL增加条件过滤，只选择一条记录		
		Connection conn = null;
		
		List<ColumnModel> list = new ArrayList<ColumnModel>();
		
		String newsql = sql;
		
		if ("ORACLE".equals(ds.getDataBaseType())) {
			
		}else if("MYSQL".equals(ds.getDataBaseType())){
			newsql += " limit 0,1"; 
		}

		try {
			conn = getConntion(ds);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData rsmd = rs.getMetaData();
			
			//spring dao 里面有具体的实现，回头翻阅下spring jdbc 代码
			List<ColumnModel> colList = new ArrayList();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				ColumnModel column = new ColumnModel();
				column.setColumnName(rsmd.getColumnLabel(i));
				column.setComment("");
				column.setDigits(rsmd.getScale(i));
				column.setJavaType(rsmd.getColumnTypeName(i));
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
