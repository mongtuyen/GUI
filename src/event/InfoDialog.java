package event;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class InfoDialog extends WizardPage {

	String message;
	
	public InfoDialog(String message) {
		super("wizardPage");
		setTitle("Infomation");
		setDescription("");
		this.message = message;
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		Label lbmessage = new Label(container, SWT.NONE);
		lbmessage.setBounds(73, 85, 213, 33);
		lbmessage.setText(message);
		
	}
	
	
}
