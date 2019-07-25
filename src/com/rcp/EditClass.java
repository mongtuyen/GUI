package com.rcp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.tuyen.model.Clazz;
import com.tuyen.service.ClazzService;

import connect.ServerConnector;

public class EditClass {

	public EditClass() {
		// TODO Auto-generated constructor stub
	}

	public void openShell(Display display, Clazz clazz) {
		
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setText("Edit class");
		shell.setSize(250, 250);
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("EDIT CLASS");

		Label lbID = new Label(shell, SWT.NONE);
		lbID.setText("ID:");
		Text textClazzID = new Text(shell, SWT.BORDER);	
		GridData data = new GridData(SWT.NONE, SWT.NONE, true, true);
		data.widthHint = 350;
		textClazzID.setLayoutData(data);		
		textClazzID.setText(clazz.getId().toString());
		textClazzID.setEditable(false);
		
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("Name Class:");
		Text textClazzName = new Text(shell, SWT.BORDER);
		textClazzName.setText(clazz.getName());
		textClazzName.setLayoutData(data);

		Button btnSave = new Button(shell, SWT.NULL);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String className = textClazzName.getText();
				int classID = Integer.parseInt(textClazzID.getText());
				Clazz clazz=new Clazz();
				clazz.setId(classID);
				clazz.setName(className);
				ServerConnector tmp = ServerConnector.getInstance();
				ClazzService clazzService = tmp.getClassService();
				clazzService.update(clazz);
			
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
