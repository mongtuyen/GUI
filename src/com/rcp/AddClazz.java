package com.rcp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import com.tuyen.model.Clazz;
import connect.ServerConnector;

public class AddClazz {
	public void openShell(Display display) {
		GridData data = new GridData(SWT.NONE, SWT.NONE, true, true);
		data.widthHint = 350;
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setSize(250, 250);
		shell.setText("Add class");
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("ADD CLASS");

//		Label lbID = new Label(shell, SWT.NONE);
//		lbID.setText("ID:");
//		Text txtID = new Text(shell, SWT.BORDER);
//		txtID.setLayoutData(data);
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("Name Class:");
		Text txtClass = new Text(shell, SWT.BORDER);
		txtClass.setLayoutData(data);

		Button btnSave = new Button(shell, SWT.NULL);
		btnSave.addListener(SWT.Selection, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				String className = txtClass.getText();
				//int classID = Integer.parseInt(txtID.getText());
				Clazz clazz = new Clazz();
				//clazz.setId(classID);
				clazz.setName(className);
				ServerConnector.getInstance().getClassService().persist(clazz);
				System.out.println("done");
				shell.dispose();
			}
		});
		btnSave.setText("Save");

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		shell.dispose();
	}
}
