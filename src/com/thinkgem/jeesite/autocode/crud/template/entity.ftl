/**
 * There are <a href="https://github.com/thinkgem/jeesite">JeeSite</a> code generation
 */
package ${table.topPackage}.entity.${table.tableJavaName}

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.BaseEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;


/**
 * ${table.tableComment}Entity
 * @author ${table.author}
 * @version ${table.version}
 */
@Entity
@Table(name = "${table.tableName}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${table.tableJavaName} extends BaseEntity {

	<#list columnList as x>
		/**
		 * <#if x.comment??>${x.comment}</#if>
		 **/
		private ${x.javaType} ${x.javaName};
	</#list>
		
}
