package ${table.topPackage}.qry;

import java.io.Serializable;

import com.calf.framework.util.BaseAdminQuery;

public class ${table.functionNameEn?cap_first}Qry extends BaseAdminQuery implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1937084884877261956L;
	
	<#list columnList as x>		
		<#if x.isQuery=="Y">
		/**
		 * <#if x.comment??>${x.comment}</#if>
		 **/
		${x.javaType} ${x.javaName};		
		</#if>
	</#list>
	
	<#list columnList as x>
	<#if x.isQuery=="Y">
		public ${x.javaType} get${x.javaName?cap_first}(){
			return this.${x.javaName};
		}
		public void set${x.javaName?cap_first}(${x.javaType} ${x.javaName}){
			this.${x.javaName} = ${x.javaName};
		}
	</#if>	
	</#list>
	
	<#list columnList as x>
	<#if x.isQuery=="Y">
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
	</#if>
	</#list>
}