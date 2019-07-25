package event;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class Warning extends WizardPage {
	String message;
	protected Warning(String message) {
		
		super("wizardPage");
		setTitle("Infomation");
		setDescription("");
		this.message = message;
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		Label lbmessage = new Label(container, SWT.NONE);
		lbmessage.setBounds(73, 85, 213, 33);
		lbmessage.setText(message);
	}

}
