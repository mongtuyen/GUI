package add;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;

import connect.ServerConnector;

public class MyWizardClass extends Wizard {

    protected addClass one;
    
    public MyWizardClass() {
        super();
        setNeedsProgressMonitor(true);
    }

    @Override
    public String getWindowTitle() {
        return "Add class";
    }

    @Override
    public void addPages() {
        one = new addClass();
        
        addPage(one);
      
    }

    @Override
    public boolean performFinish() {
    	ServerConnector.getInstance().getClassService().persist(one.getClazzEdit());
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
