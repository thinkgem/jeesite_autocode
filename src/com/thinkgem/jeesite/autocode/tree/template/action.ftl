package ${table.topPackage}.web;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.calf.framework.util.Constants;
import com.calf.framework.util.ObjectUtils;
import com.calf.framework.vo.AdminUserInfo;
import com.calf.framework.vo.Page;
import ${table.topPackage}.entity.${table.tableJavaName?cap_first};
import ${table.topPackage}.qry.${table.functionNameEn?cap_first}Qry;
import ${table.topPackage}.services.${table.functionNameEn?cap_first}Service;
import com.calf.framework.web.BaseAction;
import com.calf.framework.web.util.RequiresPermissions;


public class ${table.functionNameEn?cap_first}Action extends BaseAction {

		${table.functionNameEn?cap_first}Service ${table.functionNameEn}Service;

		${table.functionNameEn?cap_first}Qry qry;

		Page page;

		${table.tableJavaName?cap_first} entity;
		
		/**
		 * 列表
		 */
		@RequiresPermissions(value = "${table.permissionPrefix}:view",requiresUser=true)
		public String list() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if(StringUtils.isNotBlank(super.qryHex)){
				qry = (${table.functionNameEn?cap_first}Qry)ObjectUtils.getObjectFromHex(qryHex);
			}
			if(qry==null){
				qry = new ${table.functionNameEn?cap_first}Qry();
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			//设置默认排序号
			if(StringUtils.isBlank(qry.getOrderCol())){
				qry.setOrderCol("createDate");
				qry.setOrderType(Constants.DESC);
			}
			
			qry.setUserInfo(userInfo);
			page = ${table.functionNameEn}Service.find${table.functionNameEn?cap_first}Page(qry);
			return "list";
		}
		/**
		 * 新增
		 **/
		@RequiresPermissions(value = "${table.permissionPrefix}:edit",requiresUser=true)
		public String toAdd() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = new ${table.tableJavaName?cap_first}();
			super.event="ADD";
			super.title="新增";
			return "edit";
		}
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "${table.permissionPrefix}:edit",requiresUser=true)
		public String toEdit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = ${table.functionNameEn}Service.get(${table.tableJavaName?cap_first}.class,entity.get${table.key.javaName?cap_first}());
			super.event="EDIT";
			super.title="修改";
			return "edit";
		}
		/**
		 * 修改动作
		 **/
		@RequiresPermissions(value = "${table.permissionPrefix}:edit",requiresUser=true)
		public String edit() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			if("ADD".equals(super.event)){
				//新增操作
				entity.set${table.key.javaName?cap_first}(null);				
				entity.setDataStatus(Constants.YES);
				entity.setCreateUser(userInfo.getUserId());
				entity.setCreateDate(new Date());
				${table.functionNameEn}Service.save${table.functionNameEn?cap_first}(entity);
				super.addAttribute("qry.orderCol", "createDate");
				super.addAttribute("qry.orderType", "0");
				super.saveMessage("${table.functionNameCn}新增成功");
			}else if("EDIT".equals(super.event)){
				//修改操作
				${table.tableJavaName?cap_first} db = ${table.functionNameEn}Service.get(${table.tableJavaName?cap_first}.class,entity.get${table.key.javaName?cap_first}());
				
				<#list columnList as x>
				<#if x.isEdit=="Y">
				db.set${x.javaName?cap_first}(entity.get${x.javaName?cap_first}());
				</#if>
				</#list>
				
				db.setUpdateUser(userInfo.getUserId());
				db.setUpdateDate(new Date());
				
				${table.functionNameEn}Service.save${table.functionNameEn?cap_first}(db);
				super.saveMessage("${table.functionNameCn}修改保存成功");
				super.addAttribute("qryHex", super.qryHex);
			}
			super.redirectUrl = "${table.urlPrefix}_list.action";
			return super.GLOBAL_SUCCESS;
		}
		/**
		 * 删除
		 **/
		@RequiresPermissions(value = "${table.permissionPrefix}:edit",requiresUser=true)
		public String delete() throws Exception{			
			AdminUserInfo userInfo = super.getUserInfo();
			entity = ${table.functionNameEn}Service.get(${table.tableJavaName?cap_first}.class,entity.get${table.key.javaName?cap_first}());
			if(entity.isCanDelete()){
				this.${table.functionNameEn}Service.delete${table.functionNameEn?cap_first}(entity);
				super.renderJsonSuccess("删除成功!");
			}else{
				super.renderJsonError("该记录已被删除!");
			}
			return null;
		}
		
		/**
		 * 修改
		 **/
		@RequiresPermissions(value = "${table.permissionPrefix}:view",requiresUser=true)
		public String toView() throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			entity = ${table.functionNameEn}Service.get(${table.tableJavaName?cap_first}.class,entity.get${table.key.javaName?cap_first}());
			super.title="${table.functionNameCn}详细信息";
			return "view";
		}
		
		/**
		 * 判断是否唯一
		 * @return
		 */
		public String checkUnique()throws Exception{
			AdminUserInfo userInfo = super.getUserInfo();
			boolean isCorrect = ${table.functionNameEn}Service.isUnique(entity.get${table.key.javaName?cap_first}());
			super.rendText(String.valueOf(isCorrect));
			return null;
		}
		
		public ${table.functionNameEn?cap_first}Qry getQry(){
			return this.qry;
		}
		public void setQry(${table.functionNameEn?cap_first}Qry qry){
			this.qry = qry;
		}
		
		public ${table.functionNameEn?cap_first}Service get${table.functionNameEn?cap_first}Service(){
			return this.${table.functionNameEn}Service;
		}
		public void set${table.functionNameEn?cap_first}Service(${table.functionNameEn?cap_first}Service ${table.functionNameEn}Service){
			this.${table.functionNameEn}Service = ${table.functionNameEn}Service;
		}
		public Page getPage() {
			return this.page;
		}
		public void setPage(Page page) {
			this.page = page;
		}
		public ${table.tableJavaName?cap_first} getEntity(){
			return entity;
		}
		public void setEntity(${table.tableJavaName?cap_first} entity){
			this.entity = entity;
		}
}