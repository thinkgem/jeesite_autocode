<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
"http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
<hibernate-mapping>
    <class name="${table.topPackage}.entity.${table.tableJavaName?cap_first}" table="${table.tableName}">
    
    <#list columnList as x>		
		<#if x.key>		 
			<#if x.javaType=='Long'>
        <id name="${x.javaName}" type="java.lang.Long">
            <column name="${x.columnName?upper_case}" precision="${x.datasize}" scale="0"<#if !x.nullAble> not-null="true"</#if>/>
            </#if>
            <#if x.javaType=='String'>
        <id name="${x.javaName}" type="java.lang.String">
            <column name="${x.columnName?upper_case}" length="${x.datasize}"<#if !x.nullAble> not-null="true"</#if>/>
            </#if>
            <generator class="sequence">
                <param name="sequence">SEQ_${table.seqName}</param>
            </generator>
      	 </id>
		<#else>
		
				 
            <#if x.javaType=='Long'>
       <property name="${x.javaName}" type="java.lang.Long">
            <column name="${x.columnName?upper_case}" precision="${x.datasize}" scale="0"<#if !x.nullAble> not-null="true"</#if>/>
            </#if>
            <#if x.javaType=='String'>
		<property name="${x.javaName}" type="java.lang.String">
            <column name="${x.columnName?upper_case}" length="${x.datasize}"<#if !x.nullAble> not-null="true"</#if>/>
            </#if>
            <#if x.javaType=='Date'>
		<property name="${x.javaName}" type="java.util.Date">            
            <column name="${x.columnName?upper_case}" length="7"<#if !x.nullAble> not-null="true"</#if>/>
            </#if>
            <#if x.javaType=='Double'>
        <property name="${x.javaName}" type="java.lang.Double">
            <column name="${x.columnName?upper_case}" precision="${x.datasize}"<#if !x.nullAble> not-null="true"</#if>/>
            </#if>
        </property>
		</#if>
		</#list>
    </class>
</hibernate-mapping>