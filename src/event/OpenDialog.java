package event;

import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Shell;

public class OpenDialog extends Wizard {

	private WizardPage wizardPage;

	public OpenDialog(WizardPage wizardPage) {
		this.wizardPage = wizardPage;
	}

	@Override
	public void addPages() {
		addPage(wizardPage);
	}

	@Override
	public boolean performFinish() {
		// System.out.println(createClass.getTitle());
		return true;
	}
	
	public void closePage(WizardPage wizardPage){
		wizardPage.dispose();
	}
	
	public void openPage(){
		WizardDialog wd = new WizardDialog(new Shell(), new OpenDialog(wizardPage));
		wd.open();
	}
	

	

}
