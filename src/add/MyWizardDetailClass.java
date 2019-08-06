package add;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import connect.ServerConnector;
import detail.DetailOfClass;

public class MyWizardDetailClass extends Wizard {

	protected AddDetailClass detailClass;
	Clazz clazz;

	public MyWizardDetailClass(Clazz l) {
		super();
		setNeedsProgressMonitor(true);
		this.clazz=l;
	}

	@Override
	public String getWindowTitle() {
		return "Detail class";
	}

	@Override
	public void addPages() {
		detailClass = new AddDetailClass();	
		addPage(detailClass);

	}

	@Override
	public boolean performFinish() {
		System.out.println("Class ben wizard truoc khi update: "+clazz.getStudents().toString());	
		clazz = detailClass.getClazz();
		System.out.println("Class ben wizard sau khi update: "+clazz.getStudents().toString());
		ServerConnector.getInstance().getClassService().persist(clazz);
		DetailOfClass.listStudentFromClass(clazz.getId());
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
