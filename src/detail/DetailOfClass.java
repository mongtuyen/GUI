package detail;

import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;

//import org.apache.logging.log4j.LogManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.rcp.ListClass;
import com.rcp.ListStudent;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import add.MyWizardClass;
import add.addClass;
import connect.ServerConnector;
import edit.MyWizardEdit;

public class DetailOfClass {
	
	final static Logger logger = Logger.getLogger(DetailOfClass.class);
	
	private static Table tableClass=ListClass.getTable();
	private static Table tableDetailStudent;
	static Label textClass ;
	
	
	private static Table tableStudent=ListStudent.getTable();
	private static Table tableDetailclass;
	static Label textStudent ;
	
	
	@PostConstruct
	public void createComposite(Composite parent) throws IOException {
		parent.setLayout(new GridLayout(1, false));
				
		//List student of class
		
		textClass = new Label(parent, SWT.NONE);
		textClass.setText("                                                           ");		
		tableDetailStudent = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI|SWT.VIRTUAL);
		tableDetailStudent.setVisible(false);		
		GridData gd_table1 = new GridData(SWT.TOP, SWT.TOP, true, true, 2, 1);// SWT.TOP
		gd_table1.heightHint = 160;
		gd_table1.widthHint=160;
		tableDetailStudent.setLayoutData(gd_table1);
		tableDetailStudent.setHeaderVisible(true);
		tableDetailStudent.setLinesVisible(true);		
		final TableColumn column3 = new TableColumn(tableDetailStudent, SWT.NONE);
		column3.setText("Student ID");
		final TableColumn column4 = new TableColumn(tableDetailStudent, SWT.NONE);
		column4.setText("Name");		
		tableClass.addListener(SWT.DefaultSelection, new Listener() {
			int classID;
			public void handleEvent(Event e) {
				TableItem[] selection = tableClass.getSelection();
				for (int i = 0; i < selection.length; i++) {
					classID= Integer.parseInt(selection[i].getText());
				}
				listStudentFromClass(classID);
			}
		});				
		//List class of student
		textStudent = new Label(parent, SWT.NONE);
		textStudent.setText("                                                           ");

		tableDetailclass = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		tableDetailclass.setVisible(false);
		
		//GridData gd_table1 = new GridData(SWT.TOP, SWT.FILL, true, true, 2, 1);// SWT.TOP
		//gd_table1.heightHint = 160;
		//gd_table1.widthHint = 160;
		tableDetailclass.setLayoutData(gd_table1);
		tableDetailclass.setHeaderVisible(true);
		tableDetailclass.setLinesVisible(true);

		final TableColumn columnCodeClass = new TableColumn(tableDetailclass, SWT.NONE);
		columnCodeClass.setText("Class ID");
		final TableColumn columnNameClass = new TableColumn(tableDetailclass, SWT.NONE);
		columnNameClass.setText("Name");
		tableStudent.addListener(SWT.DefaultSelection, new Listener() {
			int studentID;

			public void handleEvent(Event e) {
				TableItem[] selection = tableStudent.getSelection();
				for (int i = 0; i < selection.length; i++) {
					studentID = Integer.parseInt(selection[i].getText());
				}
				listClassFromStudent(studentID);
			}
		});
		
	}

	
	public static void listStudentFromClass(int clazzID) {
		tableDetailStudent.removeAll();
		tableDetailStudent.setVisible(true);
		Clazz clazz=ServerConnector.getInstance().getClassService().findById(clazzID);
		textClass.setText("LIST STUDENT OF CLASS " +clazz.getCode());
		
		Set<Student> list=clazz.getStudents();
		for(Student student:list) {
			TableItem item = new TableItem(tableDetailStudent, SWT.BORDER);
			item.setText(0, student.getCode());		
			item.setText(1, student.getName());		
		}
		for (int j = 0; j < tableDetailStudent.getColumnCount(); j++) {
			tableDetailStudent.getColumn(j).pack();
		}
	}
	public static void listClassFromStudent(int studentID) {
		tableDetailclass.removeAll();
		tableDetailclass.setVisible(true);
		Student student = ServerConnector.getInstance().getStudentService().findById(studentID);
		textStudent.setText("LIST CLASS OF " + student.getName());

		Set<Clazz> list = student.getClasses();
		for (Clazz clazz : list) {
			TableItem item = new TableItem(tableDetailclass, SWT.BORDER);
			item.setText(0, clazz.getCode());
			item.setText(1, clazz.getName());
		}

		for (int j = 0; j < tableDetailclass.getColumnCount(); j++) {
			tableDetailclass.getColumn(j).pack();
		}
	}
}
