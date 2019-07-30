package edit;

import org.eclipse.jface.wizard.Wizard;

import com.tuyen.model.Clazz;

public class MyWizardEdit extends Wizard {

    protected EditClass one;
    Clazz clazz;
    public MyWizardEdit(Clazz clazz) {
        super();
        setNeedsProgressMonitor(true);
        this.clazz=clazz;
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
        return true;
    }
}
