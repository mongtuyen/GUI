package com.rcp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.tuyen.model.Clazz;
import com.tuyen.model.Student;
import com.tuyen.service.StudentService;

import connect.ServerConnector;

public class EditStudent {

	public EditStudent() {
		// TODO Auto-generated constructor stub
	}

	public void openShell(Display display, Student student) {
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setText("Edit student");
		shell.setSize(250,400);
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("ADD STUDENT");

		
		Label lbID = new Label(shell, SWT.NONE);
		lbID.setText("ID:");
		Text textStudentID = new Text(shell, SWT.BORDER);	
		GridData data = new GridData(SWT.NONE, SWT.NONE, true, true);
		data.widthHint = 350;
		textStudentID.setLayoutData(data);			
		textStudentID.setText(String.valueOf(student.getId()));
		textStudentID.setEditable(false);
		
		
		Label label = new Label(shell, SWT.NONE);
		label.setText("Name");
		Text textStudentName = new Text(shell, SWT.BORDER);
		textStudentName.setText(student.getName());
		textStudentName.setLayoutData(data);
		

		
		Label label2 = new Label(shell, SWT.NONE);
		label2.setText("Age");
		Text txtStudentAge = new Text(shell, SWT.BORDER);
		txtStudentAge.setText(String.valueOf(student.getAge()));
		txtStudentAge.setLayoutData(data);
		
		Label label3 = new Label(shell, SWT.NONE);
		label3.setText("Point");
		Text txtStudentPoint = new Text(shell, SWT.BORDER);
		txtStudentPoint.setText(String.valueOf(student.getPoint()));
		txtStudentPoint.setLayoutData(data);
		
		Label label4 = new Label(shell, SWT.NONE);
		label4.setText("Class");
		
		
		List<Clazz> l = ServerConnector.getInstance().getClassService().findAll();

		Combo comboClass = new Combo(shell, SWT.DROP_DOWN);
		comboClass.setText(student.getClasses().toString());
		for (int i = 0 ;i < l.size(); i++) {
			comboClass.add(l.get(i).getName());
		}
		comboClass.setLayoutData(data);	
		
		Button btnSave = new Button(shell, SWT.NULL);
		btnSave.setText("Save");

		btnSave.addSelectionListener(new SelectionAdapter() {
			int classID;
			@Override
			public void widgetSelected(SelectionEvent e) {
				String studentName =textStudentName.getText();
				int studentID = Integer.parseInt(textStudentID.getText());
				String className = comboClass.getText();
				for (int i = 0 ;i < l.size(); i++) {
					if(l.get(i).getName().equals(className)) {
						classID=l.get(i).getId();
					}
					comboClass.add(l.get(i).getName().toString());

				}
				int studentAge = Integer.parseInt(txtStudentAge.getText());
				float studentPoint = Float.parseFloat(txtStudentPoint.getText());
				
				Clazz clazz=ServerConnector.getInstance().getClassService().findById(classID);
				Set<Clazz> set=new HashSet<Clazz>();
				set.add(clazz);
				Student student = new Student();
				student.setId(studentID);
				student.setName(studentName);
				student.setAge(studentAge);
				student.setPoint(studentPoint);
				student.setClasses(set);
				ServerConnector tmp = ServerConnector.getInstance();
				StudentService studentService = tmp.getStudentService();
				studentService.update(student);
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
