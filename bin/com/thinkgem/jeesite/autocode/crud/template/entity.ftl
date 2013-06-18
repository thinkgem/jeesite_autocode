package ${table.topPackage}.entity;

import java.util.Date;

import com.calf.framework.util.DateUtil;
import com.calf.framework.util.FormateUtil;

public class ${table.tableJavaName?cap_first} implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7210248768007789556L;
	
	<#list columnList as x>		
		<#if x.key>
		/**
		 * 主键
		 * <#if x.comment??>${x.comment}</#if>
		 **/
		${x.javaType} ${x.javaName};
		<#else>
		/**
		 * <#if x.comment??>${x.comment}</#if>
		 **/
		${x.javaType} ${x.javaName};
		</#if>
	</#list>
	
	public ${table.tableJavaName?cap_first}(){
	}
	
	<#list columnList as x>
		public ${x.javaType} get${x.javaName?cap_first}(){
			return this.${x.javaName};
		}		
		public void set${x.javaName?cap_first}(${x.javaType} ${x.javaName}){
			this.${x.javaName} = ${x.javaName};
		}
	</#list>
	
	<#list columnList as x>
		<#if x.javaType=='Double'>
		public String get${x.javaName?cap_first}Str(){
			return FormateUtil.getInstance().formateDouble(this.${x.javaName});
		}
		</#if>
		<#if x.javaType=='Date'>
		public String get${x.javaName?cap_first}Str(){
			return DateUtil.getInstance().dateToString(this.${x.javaName},DateUtil.patternA);
		}
		</#if>
	</#list>
	
	<#list columnList as x>
		<#if x.dictKey!="">
		public String get${x.javaName?cap_first}Str(){
			return FormateUtil.getInstance().getNameByCode("${x.dictKey}", ${x.javaName});
		}
		</#if>
	</#list>
	
	public boolean isCanDelete(){
		return "1".equals(this.dataStatus);
	}
}