package com.thinkgem.jeesite.autocode.crud;

import java.util.ArrayList;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.wb.swt.SWTResourceManager;

import com.thinkgem.jeesite.autocode.model.DataSource;
import com.thinkgem.jeesite.autocode.model.TableModel;
import com.thinkgem.jeesite.autocode.util.AutoCodeDbUtils;
import com.thinkgem.jeesite.autocode.util.XmlObjectCrud;
import com.thinkgem.jeesite.autocode.util.XmlObjectUtils;

public class PageOne extends WizardPage {
	private DataBindingContext m_bindingContext;
	private Table table;
	
	private java.util.List dataSourceList = new ArrayList();
	
	/*
	 * 表LIST
	 */
	private java.util.List tableList = new ArrayList();
	
	private List connList = null;
	
	private TableViewer tableViewer;
	
	private DataSource ds;
	
	private void updateButtons(){
		getWizard().getContainer().updateButtons();
	}
	
	/**
	 * Create the wizard.
	 */
	public PageOne() {
		super("PageOne");
		setTitle("选择数据连接");
		setDescription("请选择数据库连接,模板文件位于："+XmlObjectCrud.getFtlPath());
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(container, SWT.NONE);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel = new Label(composite, SWT.SHADOW_NONE | SWT.CENTER);
		lblNewLabel.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblNewLabel.setText("选择数据源");
		
		connList = new List(composite, SWT.BORDER);
		connList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//更新表列表
				ds = (DataSource)dataSourceList.get(connList.getSelectionIndex());
				tableList.clear();
				java.util.List<TableModel> tmList = AutoCodeDbUtils.getTableList(ds);
				for(TableModel tm:tmList){
					tableList.add(tm);
				}
				tableViewer.refresh();
				m_bindingContext.updateModels();
				updateButtons();
			}
		});
		GridData gd_connList = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_connList.heightHint = 400;
		connList.setLayoutData(gd_connList);
		
		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.HORIZONTAL);
		lblNewLabel_1.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 1, 1));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		lblNewLabel_1.setText("选择表名");
		
		tableViewer = new TableViewer(composite_1, SWT.BORDER | SWT.FULL_SELECTION);
		table = tableViewer.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection select = (IStructuredSelection)tableViewer.getSelection();
				TableModel tm = (TableModel)select.getFirstElement();
				setWizardTm(tm);
				updateButtons();
			}
		});
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(190);
		tblclmnNewColumn.setText("表名");
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(168);
		tblclmnNewColumn_1.setText("中文名称");
		
		
		java.util.List tmp = (java.util.List)XmlObjectUtils.objectXmlDecoder(XmlObjectUtils.getDataSourceFileName());
		if(tmp != null){
			dataSourceList = tmp;
		}		
		
		String[] connItems = new String[dataSourceList.size()];
		for (int i = 0; i < dataSourceList.size(); i++) {
			DataSource ds = (DataSource) dataSourceList.get(i);
			connItems[i] = ds.getName();
		}
		connList.setItems(connItems);
		sashForm.setWeights(new int[] {206, 427});
		
		m_bindingContext = initDataBindings();
		
		TableModel m = new TableModel();
		m.setName("table");
		m.setComment("comment");
		tableList.add(m);
		
	}
	
	private void setWizardTm(TableModel tm){
		StartCurdWizard wizard = (StartCurdWizard)super.getWizard();
		wizard.setTableModel(tm.getName(), tm.getComment());
		
		//设置表信息
		PageTwo pageTwo = (PageTwo)wizard.getPage("PageTwo");
		pageTwo.onEnterPage(tm.getName(), tm.getComment());
		
		//设置列信息
		PageThree pageThree = (PageThree)wizard.getPage("PageThree");
		pageThree.onEnterPage();
	}
	
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();

		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = BeansObservables.observeMaps(listContentProvider.getKnownElements(), TableModel.class, new String[]{"name", "comment"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		WritableList writableList = new WritableList(tableList, TableModel.class);
		tableViewer.setInput(writableList);
		//
		return bindingContext;
	}

	@Override
	public IWizardPage getNextPage() {
		return super.getNextPage();
	}

	@Override
	public boolean canFlipToNextPage() {
		if (getErrorMessage() != null) return false;
		if(!tableViewer.getSelection().isEmpty()) {
			return true;
		}
		return false;
	}

	public DataSource getDs() {
		return ds;
	}
	
}
