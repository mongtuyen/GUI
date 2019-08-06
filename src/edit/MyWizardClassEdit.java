package edit;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import com.tuyen.model.Clazz;

import connect.ServerConnector;
import detail.DetailOfClass;

public class MyWizardClassEdit extends Wizard {

    protected EditClass editClass;
    Clazz clazz;
    public MyWizardClassEdit(Clazz c) {
        super();
        setNeedsProgressMonitor(true);
        this.clazz=c;
    }

    @Override
    public String getWindowTitle() {
        return "Edit class " + clazz.getCode();
    }

    @Override
    public void addPages() {
        editClass = new EditClass(clazz);      
        addPage(editClass);  
    }

    @Override
    public boolean performFinish() {
    	clazz = editClass.getClazzEdit();
    	
    	ServerConnector.getInstance().getClassService().update(clazz);
    	System.out.println("LIST CLASS WIZARD: "+clazz.toString());
       DetailOfClass.listStudentFromClass(clazz.getId());
    	return true;
    }
    @Override
    public boolean performCancel() {
        boolean ans = MessageDialog.openConfirm(getShell(), "Confirmation", "Are you sure to cancel the task?");
        if(ans)
          return true;
        else
          return false;
      }  
}
