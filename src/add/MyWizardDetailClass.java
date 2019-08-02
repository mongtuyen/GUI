package add;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import connect.ServerConnector;

public class MyWizardDetailClass extends Wizard {

	protected AddDetailClass one;

	public MyWizardDetailClass() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public String getWindowTitle() {
		return "Detail class";
	}

	@Override
	public void addPages() {
		one = new AddDetailClass();

		addPage(one);

	}

	@Override
	public boolean performFinish() {
		Clazz clazz = one.getClazz();
		ServerConnector.getInstance().getClassService().update(clazz);
		return true;
	}

	@Override
	public boolean performCancel() {
		boolean ans = MessageDialog.openConfirm(getShell(), "Confirmation", "Are you sure to cancel the task?");
		if (ans)
			return true;
		else
			return false;
	}
}
