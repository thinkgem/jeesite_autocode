<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@page import="com.calf.framework.util.CodeNameUtils"%>
<%@page import="com.calf.framework.util.Constants"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="/jsp/include/head.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link href="<%=path%>/res/css/base.css" rel="stylesheet" type="text/css"/>
<link href="<%=path%>/res/css/colorbox.css" rel="stylesheet" type="text/css"/>
<%@ include file="/jsp/include/basejs.jsp" %>
<script type="text/javascript">
	function gotoEdit(${table.key.javaName}){
		$('#edit_${table.key.javaName}').val(${table.key.javaName});
		$('#editForm').submit();
	}
	function gotoDelete(${table.key.javaName}){
		jConfirm('确认删除该${table.functionNameCn}信息?', '确认删除', function(cresult) {
			if(cresult){
				$.ajax({
					url: '<%=path %>${table.urlPrefix}_delete.action',
					data: 'entity.${table.key.javaName}='+${table.key.javaName},
					dataType: "json",
					success: function(data) {
						if (data.status == "success") {
							 $.gotoPage(${r"${page.pageNo}"});
						}else{
							jAlert('删除失败','提示信息');
						}
					}
				});
			}
		});	
	}
	function gotoView(${table.key.javaName}){
		$.colorbox({
			href:'<%=path %>${table.urlPrefix}_toView.action?entity.${table.key.javaName}='+${table.key.javaName}+'&data='+Math.random(),
			iframe:true,
			title: '查看${table.functionNameCn}',
			width:"80%", 
			height:"90%"
		});
	}
</script>
</head>
<body>
<div id="querytip" class="qrytip">loading....</div>

<form id="editForm" name="editForm" method="post" action="<%=path %>${table.urlPrefix}_toEdit.action">
	<input type="hidden" name="entity.${table.key.javaName}" id="edit_${table.key.javaName}" value=""/>
	<input type="hidden" name="qryHex" value="${r"${qry.qryHex}"}"/>
</form>

<div class="ajaxtabdiv">
	<div class="div_tab_header">
	    <div class="div_tab_header_1">
	        <div class="corner-1-report_nav"></div>
	        <div class="corner-2-report_nav"></div>
	        <ul class="rptMenu ajaxtabs">		            
	            <li><a href="#tab_1">${table.functionNameCn}列表</a></li>
	            <shiro:hasPermission name="${table.permissionPrefix}:edit"><li><a href="<%=path %>${table.urlPrefix}_toAdd.action">新增</a></li></shiro:hasPermission>
	        </ul>
	    </div>
	</div><!--div_tab_header-->
	
	<div class="div_tab_content_qry">
            <div id="tab_1" class="tab_content">
<form id="listForm" name="listForm" method="post"  action="<%=path %>${table.urlPrefix}_list.action">
	<table border="0" cellspacing="0" cellpadding="0" class="qryTable">
		<tr>
			<td>
			<#list columnList as x>
				<#if x.isQuery=="Y">
				<label><#if x.comment??>${x.comment}</#if></label><input type="text" name="qry.${x.javaName}" class="formTextS" id="${x.javaName}" value="${r"${qry."}${x.javaName}${r"}"}" />
				</#if>
			</#list>
			<input type="submit" id="searchButton" class="btn1" value="查 询"/>			
			</td>
		</tr>	
	</table>
	
	
		<c:choose>
		<c:when test="${r"${page.pageAmount"}==0}">
			<div class="nodatafound">
				没有查询到符合条件的${table.functionNameCn}
			</div>
		</c:when>
		<c:otherwise>		
		<table border="0" cellspacing="0" cellpadding="0" class="listTable">
			<tr>
			<#list columnList as x>
				<#if x.isList=="Y">
				<th><#if x.comment??>${x.comment}</#if></th>
				</#if>
			</#list>
			<th>操作</th>
			</tr>
			<c:forEach items="${r"${page.results}"}" var="b">
			<tr>
			<#list columnList as x>
				<#if x.isList=="Y">				
				<td class="center">${r"${b."}${x.javaName}<#if x.javaType=='Double'>Str</#if><#if x.javaType=='Date'>Str</#if><#if x.dictKey!="">Str</#if>${r"}"}</td>
				</#if>
			</#list>
				<td class="center" nowrap="true">
				<shiro:hasPermission name="${table.permissionPrefix}:edit">
				<a href="#none" title="修改" onclick="gotoEdit('${r"${b."}${table.key.javaName}${r"}"}')">修改</a>&nbsp;
				<c:if test="${r"${b.canDelete}"}">
				<a href="#none" title="删除" onclick="gotoDelete('${r"${b."}${table.key.javaName}${r"}"}')">删除</a>
				</c:if>				
				</shiro:hasPermission>
				<a href="#none" title="查看" onclick="gotoView('${r"${b."}${table.key.javaName}${r"}"}')">查看</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		
		<input type="hidden" name="qry.pageNo" id="pageNo" value="${r"${page.pageNo}"}" />
		<input type="hidden" name="qry.pageSize" id="pageNo" value="${r"${page.pageSize}"}" />
		<div class="pagination">${r"${page.pagination}"}</div>
		<input type="hidden" name="qry.orderCol" id="orderBy" value="${r"${qry.orderCol}"}" />
		<input type="hidden" name="qry.orderType" id="order" value="${r"${qry.orderType}"}" />
		</c:otherwise>
		</c:choose>
</form>
		</div><!--tab_content-->
  	</div><!--div_tab_content_qry-->
</div><!--ajaxtabdiv-->
</body>
</html>