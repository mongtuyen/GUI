package edit;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import com.tuyen.model.Clazz;

import connect.ServerConnector;

public class MyWizardEdit extends Wizard {

    protected EditClass one;
    Clazz clazz;
    public MyWizardEdit(Clazz c) {
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
        one = new EditClass(clazz);      
        addPage(one);  
    }

    @Override
    public boolean performFinish() {
    	clazz = one.getClazzEdit();
    	ServerConnector.getInstance().getClassService().update(clazz);
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
