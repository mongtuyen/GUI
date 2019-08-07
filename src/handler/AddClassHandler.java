package handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import add.MyWizardClass;

public class AddClassHandler {
	@Execute
	public void execute(IWorkbench workbench, Shell shell) {
		WizardDialog wizardDialog = new WizardDialog(shell.getShell(), new MyWizardClass());
		if (wizardDialog.open() == Window.OK) {
			System.out.println("Ok pressed");
		} else {
			System.out.println("Cancel pressed");
		}
	}
}
