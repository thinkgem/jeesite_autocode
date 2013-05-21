package com.thinkgem.jeesite.autocode.about;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class AboutDialog extends TitleAreaDialog {
	private Text txtSdfdSdfs;

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public AboutDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		setMessage("Version: v0.8.1.beta");
		setTitle("JEESITE代码生成器");
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new FillLayout(SWT.VERTICAL));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));
		
		Composite composite = new Composite(container, SWT.BORDER);
		composite.setLayout(new GridLayout(1, false));
		
		txtSdfdSdfs = new Text(composite, SWT.BORDER | SWT.READ_ONLY | SWT.MULTI);
		txtSdfdSdfs.setEditable(false);
		txtSdfdSdfs.setBackground(SWTResourceManager.getColor(240,240,240));
		txtSdfdSdfs.setText("代码生成器，希望能节约大家重复编码的时间\r\n\r\n本代码生成器生成的代码基于jeesite基础开发框架，jeesite开发框架是快速、安全、高效的开发框架。\r\n\r\n本代码生成器只是简单的代码生成功能，更复杂的功能，请您使用过程中提出，以便完善\r\n\r\n如果您有好的建议：请EMAIL: someday_122@qq.com");
		GridData gd_txtSdfdSdfs = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_txtSdfdSdfs.heightHint = 139;
		txtSdfdSdfs.setLayoutData(gd_txtSdfdSdfs);
		
		Composite composite_1 = new Composite(container, SWT.BORDER);
		composite_1.setLayout(new GridLayout(3, false));
		new Label(composite_1, SWT.NONE);
		
		Label label = new Label(composite_1, SWT.NONE);
		label.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		label.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label.setText("设计：");
		
		Label lblgitlikewindmanemailsomedayqqcom = new Label(composite_1, SWT.NONE);
		lblgitlikewindmanemailsomedayqqcom.setText("风一样的男子(git account: likewindman    EMAIL:  someday_122@qq.com)");
		new Label(composite_1, SWT.NONE);
		
		Label lblEmail = new Label(composite_1, SWT.NONE);
		lblEmail.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEmail.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		lblEmail.setText("编码：");
		
		Label lblNewLabel = new Label(composite_1, SWT.NONE);
		lblNewLabel.setText("风一样的男子");
		new Label(composite_1, SWT.NONE);
		
		Label label_2 = new Label(composite_1, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 9, SWT.BOLD));
		label_2.setText("模板编制：");
		
		Label lblJeesite = new Label(composite_1, SWT.NONE);
		lblJeesite.setText("JEESITE");
		new Label(composite_1, SWT.NONE);
		
		Label label_3 = new Label(composite_1, SWT.NONE);
		new Label(composite_1, SWT.NONE);

		return area;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(578, 450);
	}
}
