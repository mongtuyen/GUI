package add;

import org.eclipse.jface.wizard.Wizard;

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
        // Print the result to the console
      
        return true;
    }
}
