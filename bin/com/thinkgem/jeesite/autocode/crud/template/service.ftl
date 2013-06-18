package ${table.topPackage}.services;

import java.util.List;

import com.calf.framework.services.BaseService;
import ${table.topPackage}.entity.${table.tableJavaName?cap_first};
import ${table.topPackage}.qry.${table.functionNameEn?cap_first}Qry;
import com.calf.framework.vo.Page;

public interface ${table.functionNameEn?cap_first}Service extends BaseService{
	/**
	 * 保存
	 **/
	public String save${table.functionNameEn?cap_first}(${table.tableJavaName?cap_first} entity);
	/**
	 * 删除
	 */
	public String delete${table.functionNameEn?cap_first}(${table.tableJavaName?cap_first} entity);
	/**
	 * 查找分页信息
	 */
	public Page find${table.functionNameEn?cap_first}Page(${table.functionNameEn?cap_first}Qry qry);
	
	/**
	 * 判断编码是否唯一
	 * @return
	 */
	public boolean isUnique(${table.key.javaType} ${table.key.javaName});
}