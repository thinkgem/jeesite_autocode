package ${table.topPackage}.services.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.calf.framework.dao.CriteriaUtils;
import com.calf.framework.services.impl.BaseServiceImpl;
import com.calf.framework.util.Constants;
import ${table.topPackage}.entity.${table.tableJavaName?cap_first};
import ${table.topPackage}.qry.${table.functionNameEn?cap_first}Qry;
import com.calf.framework.vo.Page;
import ${table.topPackage}.services.${table.functionNameEn?cap_first}Service;

@Service("${table.functionNameEn}Service")
public class ${table.functionNameEn?cap_first}ServiceImpl extends BaseServiceImpl implements ${table.functionNameEn?cap_first}Service{
	/**
	 * 查找分页信息
	 */
	public Page find${table.functionNameEn?cap_first}Page(${table.functionNameEn?cap_first}Qry qry){
		Criteria criteria = hibernateDao.createCriteria(${table.tableJavaName?cap_first}.class);		
		<#list columnList as x>
		<#if x.isQuery=="Y">
		CriteriaUtils.addEq(criteria, "${x.javaName}", qry.get${x.javaName?cap_first}());
		</#if>
		</#list>
		criteria.add(Restrictions.eq("dataStatus", Constants.YES));
		CriteriaUtils.addOrder(criteria,qry.getOrderCol(),qry.getOrderType());
		return super.hibernateDao.pagedQuery(criteria, qry.getPageNo(), qry.getPageSize());
	}	
	
	/**
	 * 保存
	 **/
	public String save${table.functionNameEn?cap_first}(${table.tableJavaName?cap_first} entity){
		super.save(entity);
		return null;
	}
	/**
	 * 删除
	 */
	public String delete${table.functionNameEn?cap_first}(${table.tableJavaName?cap_first} entity){
		entity.setDataStatus(Constants.NO);
		super.save(entity);
		return null;
	}
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(${table.key.javaType} ${table.key.javaName}){
		${table.tableJavaName?cap_first} entity = super.hibernateDao.get(${table.tableJavaName?cap_first}.class, ${table.key.javaName});
		return entity==null;
	}
}