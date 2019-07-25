package com.rcp;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;
import connect.ServerConnector;

public class AddStudent {

	public void openShell(Display display) {
		GridData data = new GridData(SWT.NONE, SWT.NONE, true, true);
		data.widthHint = 350;
		Shell shell = new Shell(display);
		shell.setLayout(new GridLayout(1, false));
		shell.setText("Add student");
		shell.setSize(300, 500);
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setText("ADD STUDENT");

//		Label lbID = new Label(shell, SWT.NONE);
//		lbID.setText("ID");
//		Text txtStudentID = new Text(shell, SWT.BORDER);
//		txtStudentID.setLayoutData(data);
		
		Label label1 = new Label(shell, SWT.NONE);
		label1.setText("Name");
		Text txtName = new Text(shell, SWT.BORDER);
		txtName.setLayoutData(data);
		
		Label label2 = new Label(shell, SWT.NONE);
		label2.setText("Age");
		Text txtAge = new Text(shell, SWT.BORDER);
		txtAge.setLayoutData(data);
		
		Label label3 = new Label(shell, SWT.NONE);
		label3.setText("Point");
		Text txtPoint = new Text(shell, SWT.BORDER);
		txtPoint.setLayoutData(data);
		
		Label label4 = new Label(shell, SWT.NONE);
		label4.setText("Class");
		List<Clazz> l = ServerConnector.getInstance().getClassService().findAll();
		
		Table table = new Table(shell, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL
    	        | SWT.H_SCROLL);
		for (int i = 0; i < l.size(); i++) {
    	      TableItem item = new TableItem(table, SWT.NONE);
    	      item.setText(l.get(i).getName().toString());
    	    }
    	    table.setSize(100, 100);
    	    table.addListener(SWT.Selection, new Listener() {
    	      public void handleEvent(Event event) {
    	    	  TableItem[] indices = table.getSelection();
    	    	  
    	    	  System.out.println( indices);
    	    	  System.out.println("Selected items:");
    	            for (int i=0; i<indices.length; i++) {
    	               System.out.println(indices.toString());  
    	            }
//    	        String string = event.detail == SWT.CHECK ? "Checked"
//    	            : "Selected";
//    	        System.out.println(event.item + " " + string);
    	      }
    	    });
		//table.setLayout(data);
		
		
		
		//add Class
		Combo comboClass = new Combo(shell, SWT.MULTI);
		for (int i = 0; i < l.size(); i++) {
			comboClass.add(l.get(i).getName().toString());
		}
		comboClass.setLayoutData(data);
		
		
		//end add class
		Button btnSave = new Button(shell, SWT.NULL);
		btnSave.addListener(SWT.Selection, new Listener() {
			int classID;

			@Override
			public void handleEvent(Event arg0) {
				
				
				String studentName = txtName.getText();
				//int studentID = Integer.parseInt(txtStudentID.getText());
				String className = comboClass.getText();
				for (int i = 0; i < l.size(); i++) {
					if (l.get(i).getName().equals(className)) {
						classID = l.get(i).getId();
					}
					comboClass.add(l.get(i).getName().toString());

				}
				int studentAge = Integer.parseInt(txtAge.getText());
				float studentPoint = Float.parseFloat(txtPoint.getText());
				Clazz clazz = ServerConnector.getInstance().getClassService().findById(classID);
				Set<Clazz> set=new HashSet<Clazz>();
				set.add(clazz);
				Student student = new Student();
				student.setName(studentName);
				student.setAge(studentAge);
				student.setPoint(studentPoint);
				student.setClasses(set);
				ServerConnector.getInstance().getStudentService().persist(student);
				shell.dispose();
			}
		});
		btnSave.setText("Save");

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
			//System.out.println("running");
		}
		shell.dispose();

	}

}
