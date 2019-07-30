package edit;

import org.eclipse.jface.wizard.Wizard;

import com.tuyen.model.Student;

public class MyWizardStudentEdit extends Wizard {

    protected EditStudent one;
    Student student;
    public MyWizardStudentEdit(Student s) {
        super();
        setNeedsProgressMonitor(true);
        this.student=s;
    }

    @Override
    public String getWindowTitle() {
        return "Edit student "+ student.getCode();
    }

    @Override
    public void addPages() {
        one = new EditStudent(student);
        addPage(one);
      
    }

    @Override
    public boolean performFinish() {
        return true;
    }
}
