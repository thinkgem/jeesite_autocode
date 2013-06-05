package com.thinkgem.jeesite.autocode.report;

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
import com.thinkgem.jeesite.autocode.util.SQLFormatter;
import com.thinkgem.jeesite.autocode.util.StringUtils;
import com.thinkgem.jeesite.autocode.util.XmlObjectUtils;
import org.eclipse.wb.swt.ResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.core.databinding.beans.PojoObservables;

public class PageOne extends WizardPage {
	private DataBindingContext m_bindingContext;
	
	private java.util.List dataSourceList = new ArrayList();
	
	private List connList = null;
	
	private DataSource ds;
	
	private String sql = "";
	
	private Text txtSql;
	
	private void updateButtons(){
		getWizard().getContainer().updateButtons();
	}
	
	/**
	 * Create the wizard.
	 */
	public PageOne() {
		super("PageOne");
		setTitle("根据SQL生成报表");
		setDescription("选择数据源，输入可以运行的SQL语句");
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
				updateButtons();
			}
		});
		GridData gd_connList = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_connList.heightHint = 400;
		connList.setLayoutData(gd_connList);
		
		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setLayout(new GridLayout(1, false));
		
		Label lblNewLabel_1 = new Label(composite_1, SWT.HORIZONTAL);
		lblNewLabel_1.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		lblNewLabel_1.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		lblNewLabel_1.setText("输入SQL语句");
		
		txtSql = new Text(composite_1, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI);
		txtSql.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				updateButtons();
			}
		});
		
		txtSql.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		Button btnsql = new Button(composite_1, SWT.NONE);
		btnsql.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//格式化SQL
				String newsql = SQLFormatter.formatSql(txtSql.getText());
				txtSql.setText(newsql);
			}
		});
		btnsql.setText("格式化SQL");		
		
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
	}	

	@Override
	public IWizardPage getNextPage() {
		//设置表信息
		StartReportWizard wizard = (StartReportWizard)super.getWizard();
		
		//设置列信息
		PageThree pageThree = (PageThree)wizard.getPage("PageThree");
		pageThree.onEnterPage();
		
		return super.getNextPage();
	}

	@Override
	public boolean canFlipToNextPage() {
		if(ds!=null&&StringUtils.isNotBlank(txtSql.getText())){
			return true;
		}
		return false;
	}

	public DataSource getDs() {
		return ds;
	}
	
	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue txtSqlObserveTextObserveWidget = SWTObservables.observeText(txtSql, SWT.Modify);
		IObservableValue sqlEmptyObserveValue = PojoObservables.observeValue(this, "sql");
		bindingContext.bindValue(txtSqlObserveTextObserveWidget, sqlEmptyObserveValue, null, null);
		//
		return bindingContext;
	}
}
