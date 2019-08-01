package detail;

import java.io.IOException;
import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.rcp.ListClass;
import com.rcp.ListStudent;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import add.MyWizardClass;
import add.MyWizardStudent;
import connect.ServerConnector;
import edit.MyWizardEdit;
import edit.MyWizardStudentEdit;

public class DetailOfStudent {

	
	//class
//	private static Table clazz= ListClass.getTable();
//	private static Table tableDetailStudent;
//	static Label textClass;
	
	
	//student
	
	final static Logger logger = Logger.getLogger(DetailOfStudent.class);
	private static Table table=ListStudent.getTable();
	private static Table studentTable;
	static int sum;
	static Label labelStudentSum;
	static int studentID;		
	static Label labelClass;

	
	@PostConstruct
	public void createComposite(Composite parent) throws IOException {
		parent.setLayout(new GridLayout(1, false));
			
		//class
		
//		textClass = new Label(parent, SWT.NONE);
//		textClass.setText("                                                           ");
//		tableDetailStudent = new Table(parent,
//				SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI | SWT.VIRTUAL);
//		tableDetailStudent.setVisible(false);
//		GridData gd_table1 = new GridData(SWT.TOP, SWT.TOP, true, true, 2, 1);// SWT.TOP
//		gd_table1.heightHint = 160;
//		gd_table1.widthHint = 160;
//		tableDetailStudent.setLayoutData(gd_table1);
//		tableDetailStudent.setHeaderVisible(true);
//		tableDetailStudent.setLinesVisible(true);
//		final TableColumn column3 = new TableColumn(tableDetailStudent, SWT.NONE);
//		column3.setText("Student ID");
//		final TableColumn column4 = new TableColumn(tableDetailStudent, SWT.NONE);
//		column4.setText("Name");
//		clazz.addListener(SWT.DefaultSelection, new Listener() {
//			int classID;
//
//			public void handleEvent(Event e) {
//				TableItem[] selection = clazz.getSelection();
//				for (int i = 0; i < selection.length; i++) {
//					classID = Integer.parseInt(selection[i].getText());
//
//				}
//				logger.info("IIIIIIIIDDDDDDDD" + classID);
//				System.out.println("fdsgsssss" + classID);
//				listStudentFromClass(classID);
//			}
//		});
		
		
		// student

		labelClass = new Label(parent, SWT.NONE);
		labelClass.setText("gi                                                           ");

		studentTable = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		studentTable.setVisible(false);
		
		GridData gd_table1 = new GridData(SWT.TOP, SWT.TOP, true, true, 2, 1);// SWT.TOP
		gd_table1.heightHint = 160;
		gd_table1.widthHint = 300;
		studentTable.setLayoutData(gd_table1);

		studentTable.setHeaderVisible(true);
		studentTable.setLinesVisible(true);

		final TableColumn columnCodeClass = new TableColumn(studentTable, SWT.NONE);
		columnCodeClass.setText("Class ID");
		final TableColumn columnNameClass = new TableColumn(studentTable, SWT.NONE);
		columnNameClass.setText("Name");

		table.addListener(SWT.DefaultSelection, new Listener() {
			

			public void handleEvent(Event e) {
				TableItem[] selection = table.getSelection();
				for (int i = 0; i < selection.length; i++) {
					studentID = Integer.parseInt(selection[i].getText());
				}
				listClassFromStudent(studentID);
			}
		});

	}
	//class
//	public static void listStudentFromClass(int clazzID) {
//		tableDetailStudent.removeAll();
//		tableDetailStudent.setVisible(true);
//		Clazz clazz = ServerConnector.getInstance().getClassService().findById(clazzID);
//		textClass.setText("LIST STUDENT OF CLASS " + clazz.getCode());
//
//		Set<Student> list = clazz.getStudents();
//		for (Student student : list) {
//			TableItem item = new TableItem(tableDetailStudent, SWT.BORDER);
//			item.setText(0, student.getCode());
//			item.setText(1, student.getName());
//		}
//		for (int j = 0; j < tableDetailStudent.getColumnCount(); j++) {
//			tableDetailStudent.getColumn(j).pack();
//		}
//	}

//student
	public static void listClassFromStudent(int studentID) {
		studentTable.removeAll();
		studentTable.setVisible(true);
		Student student = ServerConnector.getInstance().getStudentService().findById(studentID);
		labelClass.setText("List class of student " + student.getName());

		Set<Clazz> list = student.getClasses();
		for (Clazz clazz : list) {
			TableItem item = new TableItem(studentTable, SWT.BORDER);
			item.setText(0, clazz.getCode());
			item.setText(1, clazz.getName());
		}

		for (int j = 0; j < studentTable.getColumnCount(); j++) {
			studentTable.getColumn(j).pack();
		}
	}
}
