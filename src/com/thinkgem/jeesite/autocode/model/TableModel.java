/**
 * 
 */
package com.thinkgem.jeesite.autocode.model;

/**
 * 表名和表备注
 */
public class TableModel extends AbstractModelObject {
	String name;
	String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldValue = this.name;
		this.name = name;
		firePropertyChange("name", oldValue, name);
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		String oldValue = this.comment;
		this.comment = comment;
		firePropertyChange("comment", oldValue, comment);
	}

}
