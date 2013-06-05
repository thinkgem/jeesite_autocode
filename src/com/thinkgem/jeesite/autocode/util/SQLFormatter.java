package com.thinkgem.jeesite.autocode.util;

import blanco.commons.sql.format.BlancoSqlFormatter;
import blanco.commons.sql.format.BlancoSqlFormatterException;
import blanco.commons.sql.format.BlancoSqlRule;

/**
 * 格式化SQL语句 代码来源 https://github.com/kyama/SQLFormatter
 * 
 */
public class SQLFormatter {
	public static String formatSql(String sql) {
		String newsql = new String();
		BlancoSqlRule rule = new BlancoSqlRule();
		String[] mySqlFuncs = {
				// getNumericFunctions
				"ABS", "ACOS", "ASIN", "ATAN", "ATAN2", "BIT_COUNT", "CEILING",
				"COS", "COT", "DEGREES", "EXP",
				"FLOOR",
				"LOG",
				"LOG10",
				"MAX",
				"MIN",
				"MOD",
				"PI",
				"POW",
				"POWER",
				"RADIANS",
				"RAND",
				"ROUND",
				"SIN",
				"SQRT",
				"TAN",
				"TRUNCATE",
				// getStringFunctions
				"ASCII", "BIN", "BIT_LENGTH", "CHAR", "CHARACTER_LENGTH",
				"CHAR_LENGTH", "CONCAT", "CONCAT_WS", "CONV", "ELT",
				"EXPORT_SET", "FIELD", "FIND_IN_SET", "HEX,INSERT", "INSTR",
				"LCASE", "LEFT", "LENGTH", "LOAD_FILE", "LOCATE", "LOCATE",
				"LOWER", "LPAD", "LTRIM", "MAKE_SET", "MATCH", "MID", "OCT",
				"OCTET_LENGTH", "ORD", "POSITION", "QUOTE", "REPEAT",
				"REPLACE", "REVERSE", "RIGHT", "RPAD", "RTRIM", "SOUNDEX",
				"SPACE", "STRCMP", "SUBSTRING",
				"SUBSTRING",
				"SUBSTRING",
				"SUBSTRING",
				"SUBSTRING_INDEX",
				"TRIM",
				"UCASE",
				"UPPER",
				// getSystemFunctions
				"DATABASE", "USER",
				"SYSTEM_USER",
				"SESSION_USER",
				"PASSWORD",
				"ENCRYPT",
				"LAST_INSERT_ID",
				"VERSION",
				// getTimeDateFunctions
				"DAYOFWEEK", "WEEKDAY", "DAYOFMONTH", "DAYOFYEAR", "MONTH",
				"DAYNAME", "MONTHNAME", "QUARTER", "WEEK", "YEAR", "HOUR",
				"MINUTE", "SECOND", "PERIOD_ADD", "PERIOD_DIFF", "TO_DAYS",
				"FROM_DAYS", "DATE_FORMAT", "TIME_FORMAT", "CURDATE",
				"CURRENT_DATE", "CURTIME", "CURRENT_TIME", "NOW", "SYSDATE",
				"CURRENT_TIMESTAMP", "UNIX_TIMESTAMP", "FROM_UNIXTIME",
				"SEC_TO_TIME", "TIME_TO_SEC" };
		rule.setFunctionNames(mySqlFuncs);

		BlancoSqlFormatter formatter = new BlancoSqlFormatter(rule);
		
		try {
			newsql = formatter.format(sql);
		} catch (BlancoSqlFormatterException e) {
			return sql;
		}
		return newsql;
	}
}
