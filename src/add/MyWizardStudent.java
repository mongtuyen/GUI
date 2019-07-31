package add;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import com.tuyen.model.Student;

import connect.ServerConnector;

public class MyWizardStudent extends Wizard {

	protected AddStudent1 one;

	public MyWizardStudent() {
		super();
		setNeedsProgressMonitor(true);
	}

	@Override
	public String getWindowTitle() {
		return "Add student";
	}

	@Override
	public void addPages() {
		one = new AddStudent1();

		addPage(one);

	}

	@Override
	public boolean performFinish() {
		Student student = one.getStudent();
		ServerConnector.getInstance().getStudentService().persist(student);
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
