package com.thinkgem.jeesite.autocode.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import com.thinkgem.jeesite.autocode.about.AboutDialog;

/**
 * 弹出帮助ACTION
 *
 */
public class AboutAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	
	public void run(IAction action) {
		AboutDialog dialog = new AboutDialog(window.getShell());
		dialog.create();
		dialog.open();
	}

	public void selectionChanged(IAction action, ISelection selection) {

	}

	public void dispose() {

	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}

}
