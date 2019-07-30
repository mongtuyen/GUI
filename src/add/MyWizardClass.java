package add;

import org.eclipse.jface.wizard.Wizard;

public class MyWizardClass extends Wizard {

    protected addClass one;
    
    public MyWizardClass() {
        super();
        setNeedsProgressMonitor(true);
    }

    @Override
    public String getWindowTitle() {
        return "Edit class";
    }

    @Override
    public void addPages() {
        one = new addClass();
        
        addPage(one);
      
    }

    @Override
    public boolean performFinish() {
        // Print the result to the console
        System.out.println(one.getClassID());
  
        return true;
    }
}
