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
<link href="<%=path%>/res/css/base.css?20130617" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>
</head>
<body>
<table border="0" cellspacing="0" cellpadding="0" class="editTable">
	<#list columnList as x>
	<#if x.isEdit=="Y">
	<tr>
		<td class="right viewheader" style="width:20%"><#if x.comment??>${x.comment}</#if></td>
		<td style="width:80%">${r"${entity."}${x.javaName}<#if x.javaType=='Double'>Str</#if><#if x.javaType=='Date'>Str</#if><#if x.dictKey!="">Str</#if>${r"}"}</td>
	</tr>
	</#if>
	</#list>
	<tr>
	<th class="center" colspan="2"><input type="button" class="btn1" value="关 闭" onclick="javascript:parent.$.fn.colorbox.close();"/></th>
	</tr>
</table>
</body>
</html>