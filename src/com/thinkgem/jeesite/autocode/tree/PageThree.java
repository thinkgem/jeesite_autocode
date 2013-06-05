package com.thinkgem.jeesite.autocode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import swing2swt.layout.BorderLayout;

import com.thinkgem.jeesite.autocode.model.ColumnModel;
import com.thinkgem.jeesite.autocode.model.TableModel;
import com.thinkgem.jeesite.autocode.util.AutoCodeDbUtils;
import com.thinkgem.jeesite.autocode.util.StringUtils;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class PageThree extends WizardPage {
	private Table columnsTable;

	/**
	 * 列信息
	 */
	List<ColumnModel> columnList = new ArrayList<ColumnModel>();

	private TableViewer tableViewer;
	
	private String[] columnNames = new String[] {"columnName", "javaName", "javaType", "isNull", "isQuery", "isList", "isEdit", "comment", "dictKey"};

	/**
	 * Create the wizard.
	 */
	public PageThree() {
		super("PageThree");
		setTitle("设置字段信息");
		setDescription("设置字段信息");
	}
	
	public TableViewer getTableViewer() {
		return tableViewer;
	}

	public List<ColumnModel> getColumnList() {
		return columnList;
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new BorderLayout(3, 0));

		Composite composite = new Composite(container, SWT.BORDER);
		composite.setLayoutData(BorderLayout.EAST);
		composite.setLayout(new GridLayout(1, false));

		ToolBar toolBar = new ToolBar(composite, SWT.FLAT | SWT.RIGHT
				| SWT.VERTICAL);

		ToolItem tbiUp = new ToolItem(toolBar, SWT.NONE);
		tbiUp.setImage(ResourceManager.getPluginImage(
				"com.thinkgem.jeesite.autocode", "icons/up.png"));

		ToolItem tbiDown = new ToolItem(toolBar, SWT.NONE);
		tbiDown.setImage(ResourceManager.getPluginImage(
				"com.thinkgem.jeesite.autocode", "icons/down.png"));

		Composite composite_1 = new Composite(container, SWT.BORDER);
		composite_1.setLayoutData(BorderLayout.CENTER);
		composite_1.setLayout(new GridLayout(1, false));

		tableViewer = new TableViewer(composite_1, SWT.BORDER
				| SWT.FULL_SELECTION);
		tableViewer.setColumnProperties(columnNames);
		columnsTable = tableViewer.getTable();
		columnsTable.setHeaderVisible(true);
		columnsTable.setLinesVisible(true);
		columnsTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,
				1, 1));

		TableColumn tc1 = new TableColumn(columnsTable, SWT.CENTER, 0);
		tc1.setText("列名"); //$NON-NLS-1$
		TableColumn tc2 = new TableColumn(columnsTable, SWT.CENTER, 1);
		tc2.setText("属性名"); //$NON-NLS-1$
		TableColumn tc3 = new TableColumn(columnsTable, SWT.LEFT, 2);
		tc3.setText("说明"); //$NON-NLS-1$
		TableColumn tc4 = new TableColumn(columnsTable, SWT.CENTER, 3);
		tc4.setText("主键"); //$NON-NLS-1$
		TableColumn tc5 = new TableColumn(columnsTable, SWT.CENTER, 4);
		tc5.setText("列表"); //$NON-NLS-1$
		TableColumn tc6 = new TableColumn(columnsTable, SWT.CENTER, 5);
		tc6.setText("查询"); //$NON-NLS-1$
		TableColumn tc7 = new TableColumn(columnsTable, SWT.CENTER, 6);
		tc7.setText("修改"); //$NON-NLS-1$
		TableColumn tc8 = new TableColumn(columnsTable, SWT.CENTER, 7);
		tc8.setText("参数"); //$NON-NLS-1$

		tc1.setWidth(140);
		tc2.setWidth(90);
		tc3.setWidth(200);
		tc4.setWidth(70);
		tc5.setWidth(70);
		tc6.setWidth(70);
		tc7.setWidth(70);
		tc8.setWidth(70);
		
		CellEditor[] editors = new CellEditor[columnNames.length];
		editors[0] = new TextCellEditor(columnsTable, SWT.READ_ONLY);
		editors[1] = new TextCellEditor(columnsTable);
		editors[2] = new TextCellEditor(columnsTable);
		editors[3] = new CheckboxCellEditor(columnsTable);
		editors[4] = new CheckboxCellEditor(columnsTable);
		editors[5] = new CheckboxCellEditor(columnsTable);
		editors[6] = new CheckboxCellEditor(columnsTable);
		editors[7] = new TextCellEditor(columnsTable);
		tableViewer.setCellEditors(editors);

		tableViewer.setLabelProvider(new ColumnLabelProvider());
		tableViewer.setContentProvider(new ColumnContentProvider());
		tableViewer.setCellModifier(new ColumnCellModifier(this,this.columnNames));
		
		Composite composite_2 = new Composite(container, SWT.NONE);
		composite_2.setLayoutData(BorderLayout.NORTH);
		composite_2.setLayout(new GridLayout(4, false));
		
		Label lblNewLabel = new Label(composite_2, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("parent列");
		
		Combo combo = new Combo(composite_2, SWT.NONE);
		GridData gd_combo = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo.widthHint = 200;
		combo.setLayoutData(gd_combo);
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("parentids列");
		
		Combo combo_1 = new Combo(composite_2, SWT.NONE);
		GridData gd_combo_1 = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_combo_1.widthHint = 200;
		combo_1.setLayoutData(gd_combo_1);
		tableViewer.setInput(columnList);
	}

	public void onEnterPage() {
		StartTreeWizard wizard = (StartTreeWizard) super.getWizard();
		PageOne pageOne = (PageOne) wizard.getPage("PageOne");
		PageTwo pageTwo = (PageTwo) wizard.getPage("PageTwo");
		TableModel table = wizard.getTableModel();
		columnList = AutoCodeDbUtils.getColumnList(pageOne.getDs(),table.getName());
		tableViewer.refresh();
	}

	
	class ColumnContentProvider implements IStructuredContentProvider {

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

		public Object[] getElements(Object inputElement) {
			return columnList.toArray();
		}
	}
}

/**
 * LABEL 提供者
 * 
 */
class ColumnLabelProvider extends LabelProvider implements ITableLabelProvider {

	// Names of images used to represent checkboxes
	public static final String CHECKED_IMAGE = "checked";
	public static final String UNCHECKED_IMAGE = "unchecked";

	private static ImageRegistry imageRegistry = new ImageRegistry();

	static {
		String iconPath = "icons/";
		imageRegistry.put(CHECKED_IMAGE, ResourceManager.getPluginImage("com.thinkgem.jeesite.autocode", "icons/checked.png"));
		imageRegistry.put(UNCHECKED_IMAGE, ResourceManager.getPluginImage("com.thinkgem.jeesite.autocode", "icons/unchecked.png"));
	}

	/**
	 * Returns the image with the given key, or <code>null</code> if not found.
	 */
	private Image getImage(boolean isSelected) {
		String key = isSelected ? CHECKED_IMAGE : UNCHECKED_IMAGE;
		return imageRegistry.get(key);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ITableLabelProvider#getColumnImage(java.lang
	 * .Object, int)
	 */
	public Image getColumnImage(Object element, int columnIndex) {
		ColumnModel col = (ColumnModel) element;

		Image result = null;
		
		switch (columnIndex) {
		case 3:
			result = getImage("Y".equalsIgnoreCase(col.getIsParamKey()));
			break;
		case 4:
			result = getImage("Y".equalsIgnoreCase(col.getIsList()));
			break;
		case 5:
			result = getImage("Y".equalsIgnoreCase(col.getIsQuery()));
			break;
		case 6:
			result = getImage("Y".equalsIgnoreCase(col.getIsEdit()));
			break;
		default:
			break;
		}
		return result;
	}

	public String getColumnText(Object element, int columnIndex) {
		String result = "";
		ColumnModel col = (ColumnModel) element;
		switch (columnIndex) {
		case 0:
			result = col.getColumnName();
			break;
		case 1:
			result = col.getJavaName();
			break;
		case 2:
			result = col.getComment() == null ? "" : col.getComment();
			break;
		case 7:
			result = col.getDictKey();
			break;
		default:
			break;
		}
		return result;
	}

}

class ColumnCellModifier implements ICellModifier {
	PageThree pageThree;
	List columnNames;

	public ColumnCellModifier(PageThree pageThree, String[] columnNames) {
		this.pageThree = pageThree;
		this.columnNames = Arrays.asList(columnNames);
	}

	public boolean canModify(Object element, String property) {
		return true;
	}

	public Object getValue(Object element, String property) {
		int columnIndex = columnNames.indexOf(property);

		Object result = null;

		ColumnModel col = (ColumnModel) element;
		switch (columnIndex) {
		case 0:
			result = col.getColumnName();
			break;
		case 1:
			result = col.getJavaName();
			break;
		case 2:
			result = col.getComment();
			break;
		case 3:
			result = new Boolean(!"Y".equals(col.getIsParamKey()));
			break;
		case 4:
			result = new Boolean(!"Y".equals(col.getIsList()));
			break;
		case 5:
			result = new Boolean(!"Y".equals(col.getIsQuery()));
			break;
		case 6:
			result = new Boolean(!"Y".equals(col.getIsEdit()));
			break;
		case 7:
			result = col.getDictKey();
			break;
		default:
			result = "";
		}
		return result;
	}

	public void modify(Object element, String property, Object value) {
		int columnIndex = columnNames.indexOf(property);
		TableItem item = (TableItem) element;
		ColumnModel col = (ColumnModel)item.getData();
		switch (columnIndex) {
		case 0:
			col.setColumnName(StringUtils.trim((String)value));
			break;
		case 1:
			col.setJavaName(StringUtils.trim((String)value));
			break;
		case 2:
			col.setComment(StringUtils.trim((String)value));
			break;
		case 3:
			col.setIsParamKey(((Boolean) value).booleanValue()?"N":"Y");
			break;
		case 4:
			col.setIsList(((Boolean) value).booleanValue()?"N":"Y");
			break;
		case 5:
			col.setIsQuery(((Boolean) value).booleanValue()?"N":"Y");
			break;
		case 6:
			col.setIsEdit(((Boolean) value).booleanValue()?"N":"Y");
			break;
		case 7:
			col.setDictKey(StringUtils.trim((String)value));
			break;
		default:
			//result = "";
		}
		
		this.pageThree.getTableViewer().update(col, null);
	}

}