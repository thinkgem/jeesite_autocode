<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE struts PUBLIC
    "-//Apache Software Foundation//DTD Struts Configuration 2.0//EN"
    "http://struts.apache.org/dtds/struts-2.0.dtd">

<struts>

	<package name="warehouse_${table.functionNameEn}" namespace="/warehouse/"  extends="basePackage">	
  		<!--${table.functionNameCn}-->
  		<action name="${table.functionNameEn}_*" class="${table.topPackage}.web.${table.functionNameEn?cap_first}Action" method="{1}">
	        <interceptor-ref name="baseStack" />   
			<result name="edit">${table.jspLocation}/edit.jsp</result>
			<result name="list">${table.jspLocation}/list.jsp</result>
			<result name="view">${table.jspLocation}/view.jsp</result>
		</action>	
	</package>	
	
</struts>