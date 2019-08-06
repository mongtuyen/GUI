package edit;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import com.tuyen.model.Student;

import connect.ServerConnector;
import detail.DetailOfClass;

public class MyWizardStudentEdit extends Wizard {

	protected EditStudent one;
	Student student;

	public MyWizardStudentEdit(Student s) {
		super();
		setNeedsProgressMonitor(true);
		this.student = s;
	}

	@Override
	public String getWindowTitle() {
		return "Edit student " + student.getCode();
	}

	@Override
	public void addPages() {
		one = new EditStudent(student);
		addPage(one);

	}

	@Override
	public boolean performFinish() {
		student = one.getStudent();
		ServerConnector.getInstance().getStudentService().update(student);
		DetailOfClass.listClassFromStudent(student.getId());
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
