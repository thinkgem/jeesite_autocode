<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="com.calf.framework.util.CodeNameUtils"%>
<%@ include file="/jsp/include/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>
<script type="text/javascript">
	$(document).ready(function(){
	<#list columnList as x>
	<#if x.isParamKey=="Y">
		$('#${x.javaName}').val('${r"${entity."}${x.javaName}${r"}"}');
	</#if>
	</#list>
	});
</script>
</head>
<body>
<div class="ajaxtabdiv11">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="ajaxtabs">		            
	            <li><a href="<%=path %>${table.urlPrefix}_list.action">${table.functionNameCn}列表</a></li>
	            <li><a href="#none" class="current">${r"${title}"}</a></li>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
            
<form id="validateForm" name="validateForm" action="<%=path %>${table.urlPrefix}_edit.action" method="post">
	<input type="hidden" name="qryHex" value="${r"${qryHex}"}"/>
	<input type="hidden" name="event" value="${r"${event}"}"/>
	<table border="0" cellspacing="0" cellpadding="0" class="editTable">
	
		<c:choose>
			<c:when test="${r"${event=='ADD'}"}">
			</c:when>
			<c:otherwise>
		<tr>
			<td class="right" style="width:20%"><#if table.key.comment??>${table.key.comment}</#if></td>
			<td style="width:80%">
			${r"${entity."}${table.key.javaName}${r"}"}
			<input type="hidden" name="entity.${table.key.javaName}" value="${r"${entity."}${table.key.javaName}${r"}"}"  id="${table.key.javaName}"/>
			</td>
		</tr>			
			</c:otherwise>
		</c:choose>
		
		<#list columnList as x>
		<#if x.isEdit=="Y">
		<tr>
			<td class="right" style="width:20%"><#if x.isNull!="Y"><label class="requiredtext">*</label></#if><label class="lable2"><#if x.comment??>${x.comment}</#if></label></td>
			<td style="width:80%">
			<#if x.isParamKey!="Y">
			<input type="text" name="entity.${x.javaName}" class="formText<#if x.isNull=="Y"> {byteRangeLength:[0,${x.datasize}]}</#if><#if x.isNull!="Y"> {required: true,byteRangeLength:[0,${x.datasize}],messages: {required:'请输入<#if x.comment??>${x.comment}</#if>'}}</#if>" id="${x.javaName}" value="${r"${entity."}${x.javaName}<#if x.javaType=='Double'>Str</#if><#if x.javaType=='Date'>Str</#if><#if x.isParamKey=="Y">Str</#if>${r"}"}"/>
			
			</#if>
			<#if x.dictKey!="">			
			<%=CodeNameUtils.getInstance().generateSelect("<select name=\"entity.${x.javaName}\" id=\"${x.javaName}\" class=\"formSelect <#if x.isNull!="Y">{required: true,messages: {required:'请选择<#if x.comment??>${x.comment}</#if>'}}</#if>\">","","${x.dictKey}")%>
			</#if>
			</td>
		</tr>
		</#if>
		</#list>
		<tr>
		<th class="center" colspan="2"><input type="submit" class="btn1" value="保 存"/><input type="button" class="btn1" value="返 回" onclick="history.go(-1)"/></th>
		</tr>
	</table>	
</form>

		</div>            
  	</div><!--div_tab_content-->
</div><!--ajaxtabdiv-->
</body>
</html>