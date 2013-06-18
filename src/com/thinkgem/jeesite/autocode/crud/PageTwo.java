package com.thinkgem.jeesite.autocode.crud;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.thinkgem.jeesite.autocode.model.TableConfigModel;
import com.thinkgem.jeesite.autocode.util.StringUtils;

public class PageTwo extends WizardPage {
	private DataBindingContext m_bindingContext;
	private Text txtTableName;
	private Text txtTopPackage;
	private Text txtFunctionNameEn;
	private Text txtFunctionNameCn;
	private Text txtUrlPrefix;
	private Text txtAuthor;
	private Text txtVersion;
	private Text txtJspLocation;
	private Text txtTableComment;
	
	private TableConfigModel tableConfig = new TableConfigModel();
	private Text txtFilePath;
	private Text txtPermission;

	/**
	 * Create the wizard.
	 */
	public PageTwo() {
		super("PageTwo");
		setTitle("配置生成选项");
		setDescription("配置全局生成信息");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout(3, false));
		
		Label lblNewLabel_1 = new Label(container, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel_1.setText("表名");
		
		txtTableName = new Text(container, SWT.BORDER);
		txtTableName.setEditable(false);
		txtTableName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label label_3 = new Label(container, SWT.NONE);
		label_3.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_3.setText("表备注");
		
		txtTableComment = new Text(container, SWT.BORDER);
		txtTableComment.setEditable(false);
		txtTableComment.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label label = new Label(container, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setText("顶层包名");
		
		txtTopPackage = new Text(container, SWT.BORDER);
		txtTopPackage.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label lblen = new Label(container, SWT.NONE);
		lblen.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblen.setText("功能名(EN)");
		
		txtFunctionNameEn = new Text(container, SWT.BORDER);
		txtFunctionNameEn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label lblJsp = new Label(container, SWT.NONE);
		lblJsp.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblJsp.setText("模块名(CN)");
		
		txtFunctionNameCn = new Text(container, SWT.BORDER);
		txtFunctionNameCn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label lblurl = new Label(container, SWT.NONE);
		lblurl.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblurl.setText("URL前缀");
		
		txtUrlPrefix = new Text(container, SWT.BORDER);
		txtUrlPrefix.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("JSP路径");
		
		txtJspLocation = new Text(container, SWT.BORDER);
		txtJspLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label label_5 = new Label(container, SWT.NONE);
		label_5.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_5.setText("权限前缀");
		
		txtPermission = new Text(container, SWT.BORDER);
		txtPermission.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label label_1 = new Label(container, SWT.NONE);
		label_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_1.setText("作者");
		
		txtAuthor = new Text(container, SWT.BORDER);
		txtAuthor.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label label_2 = new Label(container, SWT.NONE);
		label_2.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_2.setText("版本信息");
		
		txtVersion = new Text(container, SWT.BORDER);
		txtVersion.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(container, SWT.NONE);
		
		Label label_4 = new Label(container, SWT.NONE);
		label_4.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label_4.setText("代码生成路径");
		
		txtFilePath = new Text(container, SWT.BORDER | SWT.READ_ONLY);
		txtFilePath.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button button = new Button(container, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.OPEN);
				dialog.setFilterPath("");// 设置默认的路径 //$NON-NLS-1$
				dialog.setText("选择代码生成路径");//设置对话框的标题 //$NON-NLS-1$
				dialog.setFilterPath("E:/work/rcp_workspace/"); //$NON-NLS-1$
				dialog.setMessage("请选择相应的文件夹"); //$NON-NLS-1$
				String srcPath = dialog.open();
				txtFilePath.setText(srcPath);
			}
		});
		button.setText("选择");
		m_bindingContext = initDataBindings();
	}

	public TableConfigModel getTableConfig() {
		return tableConfig;
	}
	
	public void onEnterPage(String name,String comment){
		this.txtTableName.setText(name);
		this.txtTableComment.setText(comment);		
	}	
	
	public boolean checkInput(){
		super.setMessage("");
		if(StringUtils.isBlank(txtTopPackage.getText())){
			setMessage("请输入顶层包名", IMessageProvider.ERROR);
			return false;
		}
		
		if(StringUtils.isBlank(txtFunctionNameEn.getText())){
			setMessage("请输入功能名(EN)", IMessageProvider.ERROR);
			return false;
		}
		
		if(StringUtils.isBlank(txtFunctionNameCn.getText())){
			setMessage("请输入功能名(CN)", IMessageProvider.ERROR);
			return false;
		}
		
		if(StringUtils.isBlank(txtUrlPrefix.getText())){
			setMessage("请输入URL前缀", IMessageProvider.ERROR);
			return false;
		}
		
		if(StringUtils.isBlank(txtJspLocation.getText())){
			setMessage("请输入JSP路径", IMessageProvider.ERROR);
			return false;
		}
		
		if(StringUtils.isBlank(txtFilePath.getText())){
			setMessage("请选择代码生成路径", IMessageProvider.ERROR);
			return false;
		}
		
		return true;
	}
	
	@Override
	public IWizardPage getNextPage() {
		if(!checkInput()){
			return this;
		}else{
			return super.getNextPage();
		}
	}

	@Override
	public IWizardPage getPreviousPage() {
		return super.getPreviousPage();
	}

	@Override
	public boolean canFlipToNextPage() {
		return super.canFlipToNextPage();
	}
	protected DataBindingContext initDataBindings() {
		DataBindingContext bindingContext = new DataBindingContext();
		//
		IObservableValue txtTableNameObserveTextObserveWidget = SWTObservables.observeText(txtTableName, SWT.Modify);
		IObservableValue tableConfigTableNameObserveValue = PojoObservables.observeValue(tableConfig, "tableName");
		bindingContext.bindValue(txtTableNameObserveTextObserveWidget, tableConfigTableNameObserveValue, null, null);
		//
		IObservableValue txtTableCommentObserveTextObserveWidget = SWTObservables.observeText(txtTableComment, SWT.Modify);
		IObservableValue tableConfigTableCommentObserveValue = PojoObservables.observeValue(tableConfig, "tableComment");
		bindingContext.bindValue(txtTableCommentObserveTextObserveWidget, tableConfigTableCommentObserveValue, null, null);
		//
		IObservableValue txtFunctionNameEnObserveTextObserveWidget = SWTObservables.observeText(txtFunctionNameEn, SWT.Modify);
		IObservableValue tableConfigFunctionNameEnObserveValue = PojoObservables.observeValue(tableConfig, "functionNameEn");
		bindingContext.bindValue(txtFunctionNameEnObserveTextObserveWidget, tableConfigFunctionNameEnObserveValue, null, null);
		//
		IObservableValue txtFunctionNameCnObserveTextObserveWidget = SWTObservables.observeText(txtFunctionNameCn, SWT.Modify);
		IObservableValue tableConfigFunctionNameCnObserveValue = PojoObservables.observeValue(tableConfig, "functionNameCn");
		bindingContext.bindValue(txtFunctionNameCnObserveTextObserveWidget, tableConfigFunctionNameCnObserveValue, null, null);
		//
		IObservableValue txtUrlPrefixObserveTextObserveWidget = SWTObservables.observeText(txtUrlPrefix, SWT.Modify);
		IObservableValue tableConfigUrlPrefixObserveValue = PojoObservables.observeValue(tableConfig, "urlPrefix");
		bindingContext.bindValue(txtUrlPrefixObserveTextObserveWidget, tableConfigUrlPrefixObserveValue, null, null);
		//
		IObservableValue txtJspLocationObserveTextObserveWidget = SWTObservables.observeText(txtJspLocation, SWT.Modify);
		IObservableValue tableConfigJspLocationObserveValue = PojoObservables.observeValue(tableConfig, "jspLocation");
		bindingContext.bindValue(txtJspLocationObserveTextObserveWidget, tableConfigJspLocationObserveValue, null, null);
		//
		IObservableValue txtAuthorObserveTextObserveWidget = SWTObservables.observeText(txtAuthor, SWT.Modify);
		IObservableValue tableConfigAuthorObserveValue = PojoObservables.observeValue(tableConfig, "author");
		bindingContext.bindValue(txtAuthorObserveTextObserveWidget, tableConfigAuthorObserveValue, null, null);
		//
		IObservableValue txtVersionObserveTextObserveWidget = SWTObservables.observeText(txtVersion, SWT.Modify);
		IObservableValue tableConfigVersionObserveValue = PojoObservables.observeValue(tableConfig, "version");
		bindingContext.bindValue(txtVersionObserveTextObserveWidget, tableConfigVersionObserveValue, null, null);
		//
		IObservableValue txtFilePathObserveTextObserveWidget = SWTObservables.observeText(txtFilePath, SWT.Modify);
		IObservableValue tableConfigFilePathObserveValue = PojoObservables.observeValue(tableConfig, "filePath");
		bindingContext.bindValue(txtFilePathObserveTextObserveWidget, tableConfigFilePathObserveValue, null, null);
		//
		IObservableValue txtTopPackageObserveTextObserveWidget = SWTObservables.observeText(txtTopPackage, SWT.Modify);
		IObservableValue tableConfigTopPackageObserveValue = PojoObservables.observeValue(tableConfig, "topPackage");
		bindingContext.bindValue(txtTopPackageObserveTextObserveWidget, tableConfigTopPackageObserveValue, null, null);
		//
		IObservableValue txtPermissionObserveTextObserveWidget = SWTObservables.observeText(txtPermission, SWT.Modify);
		IObservableValue tableConfigPermissionPrefixObserveValue = PojoObservables.observeValue(tableConfig, "permissionPrefix");
		bindingContext.bindValue(txtPermissionObserveTextObserveWidget, tableConfigPermissionPrefixObserveValue, null, null);
		//
		return bindingContext;
	}
}
