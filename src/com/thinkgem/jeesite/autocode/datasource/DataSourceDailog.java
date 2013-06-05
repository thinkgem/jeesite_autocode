package com.thinkgem.jeesite.autocode.datasource;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.thinkgem.jeesite.autocode.model.DataSource;
import com.thinkgem.jeesite.autocode.util.AutoCodeDbUtils;
import com.thinkgem.jeesite.autocode.util.DbUtils;
import com.thinkgem.jeesite.autocode.util.XmlObjectUtils;

public class DataSourceDailog extends Dialog {
	private DataBindingContext m_bindingContext;
	private Table dstable;
	private Text txtName;
	private Text txtDriver;
	private Text txtUrl;
	private Text txtUserName;
	private Text txtPassword;
	private List dataSourceList = new ArrayList();
	private TableViewer tableViewer;
	private Button btnDel;
	private Button btnTest;
	private Button btnNew;
	private Text txtDbName;
	private Button btnSave;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public DataSourceDailog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(container, SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.BORDER);
		composite.setLayout(new GridLayout(1, false));
		
		tableViewer = new TableViewer(composite, SWT.BORDER | SWT.FULL_SELECTION);
		dstable = tableViewer.getTable();
		dstable.setLinesVisible(true);
		dstable.setHeaderVisible(true);
		dstable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		TableColumn tblclmnNewColumn = new TableColumn(dstable, SWT.NONE);
		tblclmnNewColumn.setWidth(120);
		tblclmnNewColumn.setText("数据源名称");
		
		TableColumn tableColumn = new TableColumn(dstable, SWT.NONE);
		tableColumn.setWidth(150);
		tableColumn.setText("数据库驱动");
		
		TableColumn tblclmnurl = new TableColumn(dstable, SWT.NONE);
		tblclmnurl.setWidth(220);
		tblclmnurl.setText("URL");
		
		TableColumn tableColumn_1 = new TableColumn(dstable, SWT.NONE);
		tableColumn_1.setWidth(120);
		tableColumn_1.setText("用户名");
		
		TableColumn tableColumn_2 = new TableColumn(dstable, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("密码");
		
		Composite composite_2 = new Composite(sashForm, SWT.BORDER);
		GridLayout gl_composite_2 = new GridLayout(3, false);
		gl_composite_2.verticalSpacing = 10;
		composite_2.setLayout(gl_composite_2);
		
		Label lblNewLabel = new Label(composite_2, SWT.RIGHT);
		GridData gd_lblNewLabel = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblNewLabel.widthHint = 113;
		lblNewLabel.setLayoutData(gd_lblNewLabel);
		lblNewLabel.setText("数据源名称");
		
		txtName = new Text(composite_2, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		
		Label lblNewLabel_1 = new Label(composite_2, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("数据库驱动");
		
		txtDriver = new Text(composite_2, SWT.BORDER);
		txtDriver.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		
		Label lblNewLabel_2 = new Label(composite_2, SWT.NONE);
		lblNewLabel_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_2.setText("URL地址");
		
		txtUrl = new Text(composite_2, SWT.BORDER);
		txtUrl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		
		Label lblNewLabel_3 = new Label(composite_2, SWT.NONE);
		lblNewLabel_3.setToolTipText("密码");
		lblNewLabel_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_3.setText("用户名");
		
		txtUserName = new Text(composite_2, SWT.BORDER);
		txtUserName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		
		Label lblNewLabel_4 = new Label(composite_2, SWT.NONE);
		lblNewLabel_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_4.setText("密码");
		
		txtPassword = new Text(composite_2, SWT.BORDER);
		txtPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(composite_2, SWT.NONE);
		
		Label label = new Label(composite_2, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("数据库名称");
		
		txtDbName = new Text(composite_2, SWT.BORDER);
		txtDbName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel_5 = new Label(composite_2, SWT.NONE);
		lblNewLabel_5.setText("Mysql数据库需要填写");
		
		Composite composite_1 = new Composite(sashForm, SWT.BORDER);
		composite_1.setLayout(new GridLayout(5, false));
		
		
		btnNew = new Button(composite_1, SWT.NONE);
		btnNew.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DataSource ds = new DataSource();
				ds.setName("新数据源");				
				dataSourceList.add(ds);				
				tableViewer.refresh();
				tableViewer.setSelection(new StructuredSelection(ds), true);
				m_bindingContext.updateModels();
			}
		});
		GridData gd_btnNew = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_btnNew.widthHint = 79;
		btnNew.setLayoutData(gd_btnNew);
		btnNew.setText("新建");
		
		btnSave = new Button(composite_1, SWT.NONE);
		btnSave.setEnabled(false);
		btnSave.setToolTipText("保存所有配置");
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				XmlObjectUtils.objectXmlEncoder(dataSourceList, XmlObjectUtils.getDataSourceFileName());				
			}
		});
		GridData gd_btnSave = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_btnSave.widthHint = 79;
		btnSave.setLayoutData(gd_btnSave);
		btnSave.setText("保存配置");
		
		btnDel = new Button(composite_1, SWT.NONE);
		btnDel.setEnabled(false);
		btnDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				IStructuredSelection select = (IStructuredSelection)tableViewer.getSelection();
				DataSource ds = (DataSource)select.getFirstElement();
				boolean confirm = MessageDialog.openConfirm(getShell(),
						"确认删除",
						"是否确认删除'"
								+ ds.getName() + "'数据源配置吗?");
				if (confirm) {
					dataSourceList.remove(ds);
					tableViewer.refresh();
					m_bindingContext.updateModels();
				}
			}
		});
		GridData gd_btnDel = new GridData(SWT.LEFT, SWT.CENTER, false, true, 1, 1);
		gd_btnDel.widthHint = 79;
		btnDel.setLayoutData(gd_btnDel);
		btnDel.setText("删除");
		
		btnTest = new Button(composite_1, SWT.NONE);
		btnTest.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				IStructuredSelection select = (IStructuredSelection)tableViewer.getSelection();
				DataSource ds = (DataSource)select.getFirstElement();
				Connection conn = AutoCodeDbUtils.getConntion(ds);
				if(conn==null){
					MessageDialog.openError(getShell(), "连接失败", "连接失败，请检查连接配置");
				}else{
					MessageDialog.openInformation(getShell(), "连接成功", "数据源("+txtName.getText()+")连接成功");
				}				
				DbUtils.closeQuietly(conn);
			}
		});
		btnTest.setEnabled(false);
		GridData gd_btnTest = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnTest.widthHint = 79;
		btnTest.setLayoutData(gd_btnTest);
		btnTest.setText("测试连接");
		
		Button button = new Button(composite_1, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
		GridData gd_button = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_button.widthHint = 79;
		button.setLayoutData(gd_button);
		button.setText("关闭");
		sashForm.setWeights(new int[] {180, 208, 48});
		
		this.loadConfig();		
		return container;
	}

	/**
	 * 导入配置信息
	 */
	private void loadConfig(){
		List tmp = (List)XmlObjectUtils.objectXmlDecoder(XmlObjectUtils.getDataSourceFileName());
		if(tmp != null){
			dataSourceList = tmp;
		}
	}
	
	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		m_bindingContext = initDataBindings();		
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(738, 500);
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("数据源设置"); 
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		ObservableListContentProvider listContentProvider = new ObservableListContentProvider();
		IObservableMap[] observeMaps = BeansObservables.observeMaps(listContentProvider.getKnownElements(), DataSource.class, new String[]{"name", "driver", "url", "user", "password"});
		tableViewer.setLabelProvider(new ObservableMapLabelProvider(observeMaps));
		tableViewer.setContentProvider(listContentProvider);
		//
		WritableList writableList = new WritableList(dataSourceList, DataSource.class);
		tableViewer.setInput(writableList);
		//
		IObservableValue tableViewerObserveSingleSelection = ViewersObservables.observeSingleSelection(tableViewer);
		IObservableValue tableViewerDriverObserveDetailValue = PojoObservables.observeDetailValue(tableViewerObserveSingleSelection, "name", String.class);
		IObservableValue textTypeObserveTextObserveWidget = SWTObservables.observeText(txtName, SWT.Modify);
		bindingContext.bindValue(tableViewerDriverObserveDetailValue, textTypeObserveTextObserveWidget, null, null);
		//
		IObservableValue tableViewerObserveSingleSelection_1 = ViewersObservables.observeSingleSelection(tableViewer);
		IObservableValue tableViewerClassObserveDetailValue = BeansObservables.observeDetailValue(tableViewerObserveSingleSelection_1, DataSource.class, "driver", String.class);
		IObservableValue txtDriverObserveTextObserveWidget = SWTObservables.observeText(txtDriver, SWT.Modify);
		bindingContext.bindValue(tableViewerClassObserveDetailValue, txtDriverObserveTextObserveWidget, null, null);
		//
		IObservableValue tableViewerObserveSingleSelection_2 = ViewersObservables.observeSingleSelection(tableViewer);
		IObservableValue tableViewerUrlObserveDetailValue = BeansObservables.observeDetailValue(tableViewerObserveSingleSelection_2, DataSource.class, "url", String.class);
		IObservableValue txtUrlObserveTextObserveWidget = SWTObservables.observeText(txtUrl, SWT.Modify);
		bindingContext.bindValue(tableViewerUrlObserveDetailValue, txtUrlObserveTextObserveWidget, null, null);
		//
		IObservableValue tableViewerObserveSingleSelection_3 = ViewersObservables.observeSingleSelection(tableViewer);
		IObservableValue tableViewerUserObserveDetailValue = BeansObservables.observeDetailValue(tableViewerObserveSingleSelection_3, DataSource.class, "user", String.class);
		IObservableValue txtUserNameObserveTextObserveWidget = SWTObservables.observeText(txtUserName, SWT.Modify);
		bindingContext.bindValue(tableViewerUserObserveDetailValue, txtUserNameObserveTextObserveWidget, null, null);
		//
		IObservableValue tableViewerObserveSingleSelection_4 = ViewersObservables.observeSingleSelection(tableViewer);
		IObservableValue tableViewerPwdObserveDetailValue = BeansObservables.observeDetailValue(tableViewerObserveSingleSelection_4, DataSource.class, "password", String.class);
		IObservableValue txtPasswordObserveTextObserveWidget = SWTObservables.observeText(txtPassword, SWT.Modify);
		bindingContext.bindValue(tableViewerPwdObserveDetailValue, txtPasswordObserveTextObserveWidget, null, null);
		//
		IObservableValue dstableObserveSingleSelectionIndexObserveWidget = SWTObservables.observeSingleSelectionIndex(dstable);
		IObservableValue btnDelObserveEnabledObserveWidget = SWTObservables.observeEnabled(btnDel);
		bindingContext.bindValue(dstableObserveSingleSelectionIndexObserveWidget, btnDelObserveEnabledObserveWidget, new SelectionUpdateValueStrategy(), new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
		//
		IObservableValue dstableObserveSingleSelectionIndexObserveWidget_1 = SWTObservables.observeSingleSelectionIndex(dstable);
		IObservableValue btnTestObserveEnabledObserveWidget = SWTObservables.observeEnabled(btnTest);
		bindingContext.bindValue(dstableObserveSingleSelectionIndexObserveWidget_1, btnTestObserveEnabledObserveWidget, new SelectionUpdateValueStrategy(), new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
		//
		IObservableValue tableViewerObserveSingleSelection_5 = ViewersObservables.observeSingleSelection(tableViewer);
		IObservableValue tableViewerDbNameObserveDetailValue = BeansObservables.observeDetailValue(tableViewerObserveSingleSelection_5, DataSource.class, "dbName", String.class);
		IObservableValue txtDbNameTextObserveValue = SWTObservables.observeText(txtDbName, SWT.Modify);
		bindingContext.bindValue(tableViewerDbNameObserveDetailValue, txtDbNameTextObserveValue, null, null);
		//
		IObservableValue dstableObserveSingleSelectionIndexObserveWidget_2 = SWTObservables.observeSingleSelectionIndex(dstable);
		IObservableValue btnSaveObserveEnabledObserveWidget = SWTObservables.observeEnabled(btnSave);
		bindingContext.bindValue(dstableObserveSingleSelectionIndexObserveWidget_2, btnSaveObserveEnabledObserveWidget,new SelectionUpdateValueStrategy(), new UpdateValueStrategy(UpdateValueStrategy.POLICY_NEVER));
		//
		return bindingContext;
	}
}
