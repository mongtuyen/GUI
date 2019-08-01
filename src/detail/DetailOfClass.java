package detail;

import java.io.IOException;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import com.rcp.ListClass;
import com.rcp.ListStudent;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;
import connect.ServerConnector;

public class DetailOfClass {

	final static Logger logger = Logger.getLogger(DetailOfClass.class);
	// class
	private static Table tableClass = ListClass.getTable();
	private static Table tableDetailStudent;
	static Label textClass;
	static Label codeClass;
	static Label nameClass;
	
	// student
	private static Table tableStudent = ListStudent.getTable();
	//private static Table tableDetailClass;
	static Label labelClass;
	static int studentID = ListStudent.getStudentID();
	static Label codeStudent;
	static Label nameStudent;
	static Label ageStudent;
	static Label mailStudent;
	static Label addressStudent;
	public static Composite parentComposite;
	

//	private int studentId=ListStudent.getStudentID();
//	static Label textStudent ;

	@PostConstruct
	public void createComposite(Composite parent) throws IOException {
		parent.setLayout(new GridLayout(1, false));

		parentComposite = parent;
		// class
		codeClass = new Label(parent, SWT.NONE);
		codeClass.setText("                                                           ");
		
		nameClass = new Label(parent, SWT.NONE);
		nameClass.setText("                                                           ");
		
		
		ageStudent = new Label(parent, SWT.NONE);
		ageStudent.setText("                                                           ");
		mailStudent = new Label(parent, SWT.NONE);
		mailStudent.setText("                                                           ");
		addressStudent = new Label(parent, SWT.NONE);
		addressStudent.setText("                                                           ");
		textClass = new Label(parent, SWT.NONE);
		textClass.setText("                                                           ");
		
		
		tableDetailStudent = new Table(parent,
				SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI | SWT.VIRTUAL);
		tableDetailStudent.setVisible(false);
		GridData gd_table1 = new GridData(SWT.TOP, SWT.TOP, true, true, 2, 1);// SWT.TOP
		gd_table1.heightHint = 160;
		gd_table1.widthHint = 300;
		tableDetailStudent.setLayoutData(gd_table1);
		tableDetailStudent.setHeaderVisible(true);
		tableDetailStudent.setLinesVisible(true);
		final TableColumn column3 = new TableColumn(tableDetailStudent, SWT.NONE);
		column3.setText("Student ID");
		final TableColumn column4 = new TableColumn(tableDetailStudent, SWT.NONE);
		column4.setText("Name");
		tableClass.addListener(SWT.Selection, new Listener() {
			int classID;

			public void handleEvent(Event e) {
				TableItem[] selection = tableClass.getSelection();
				for (int i = 0; i < selection.length; i++) {
					classID = Integer.parseInt(selection[i].getText());
				}
				logger.info("IIIIIIIIDDDDDDDD" + classID);
				System.out.println("fdsgsssss" + classID);
				listStudentFromClass(classID);
			}
		});

		// student
		codeStudent = new Label(parent, SWT.NONE);
		codeStudent.setText("                                                           ");
		
		nameStudent = new Label(parent, SWT.NONE);
		nameStudent.setText("                                                           ");
		
		
		
		
		labelClass = new Label(parent, SWT.NONE);
		labelClass.setText("                                                             ");

		/*
		 * tableDetailClass = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL |
		 * SWT.FULL_SELECTION | SWT.MULTI); tableDetailClass.setVisible(false);
		 * 
		 * //GridData gd_table13 = new GridData(SWT.TOP, SWT.FILL, true, true, 2, 1);//
		 * SWT.TOP //gd_table13.heightHint = 120; //gd_table13.widthHint = 160;
		 * tableDetailClass.setLayoutData(gd_table1);
		 * 
		 * tableDetailClass.setHeaderVisible(true);
		 * tableDetailClass.setLinesVisible(true);
		 * 
		 * final TableColumn columnCodeClass = new TableColumn(tableDetailClass,
		 * SWT.NONE); columnCodeClass.setText("Class ID"); final TableColumn
		 * columnNameClass = new TableColumn(tableDetailClass, SWT.NONE);
		 * columnNameClass.setText("Name");
		 */
		System.out.println("==============================================before add listener table student");
//		tableStudent.addListener(SWT.Selection, new Listener() {
//
//			public void handleEvent(Event e) {
//				TableItem[] selection = tableStudent.getSelection();
//				for (int i = 0; i < selection.length; i++) {
//					studentID = Integer.parseInt(selection[i].getText());
//				}
//				logger.info("IIIIIIIIDDDDDDDD student" + studentID);
//				System.out.println("fdsgsssss student" + studentID);
//				listClassFromStudent(studentID);
//			}
//		});
//		System.out.println("init table detail in composite");
		listClassFromStudent(studentID);
	}
	//class

	public static void listStudentFromClass(int clazzID) {
		//tableDetailClass.dispose();
		tableDetailStudent.removeAll();
		tableDetailStudent.setVisible(true);
		Clazz clazz = ServerConnector.getInstance().getClassService().findById(clazzID);
		codeClass.setText("Class ID: "+clazz.getCode());
		nameClass.setText("Name: "+ clazz.getName());
		ageStudent.setVisible(false);
		mailStudent.setVisible(false);
		addressStudent.setVisible(false);
		textClass.setText("List student");

		Set<Student> list = clazz.getStudents();
		for (Student student : list) {
			TableItem item = new TableItem(tableDetailStudent, SWT.BORDER);
			item.setText(0, student.getCode());
			item.setText(1, student.getName());
		}
		for (int j = 0; j < tableDetailStudent.getColumnCount(); j++) {
			tableDetailStudent.getColumn(j).pack();
		}
	}

	public static void listClassFromStudent(int studentID) {
		
		System.out.println("dusfggggggggfgggggggggg"+studentID);
		tableDetailStudent.setVisible(true);
		Student student = ServerConnector.getInstance().getStudentService().findById(studentID);
		codeClass.setText("Student ID: "+student.getCode());
		nameClass.setText("Name: "+student.getName());
		ageStudent.setVisible(true);
		mailStudent.setVisible(true);
		addressStudent.setVisible(true);
		ageStudent.setText("Age: "+student.getAge());
		mailStudent.setText("Email: "+student.getEmail());
		addressStudent.setText("Address: "+ student.getAddress());
		textClass.setText("List class");

		Set<Clazz> list = student.getClasses();
		for (Clazz clazz : list) {
			TableItem item = new TableItem(tableDetailStudent, SWT.BORDER);
			item.setText(0, clazz.getCode());
			item.setText(1, clazz.getName());
		}
		for (int j = 0; j < tableDetailStudent.getColumnCount(); j++) {
			tableDetailStudent.getColumn(j).pack();
		}
		
		//tableDetailStudent.dispose();
//		tableDetailClass.removeAll();
//		tableDetailClass.setVisible(true);
//		
//		Student student = ServerConnector.getInstance().getStudentService().findById(studentID);
//		codeStudent.setText("Student ID: "+student.getCode());
//		nameStudent.setText("Name: "+student.getName());
//		ageStudent.setText("Age: "+student.getAge());
//		mailStudent.setText("Email: "+student.getEmail());
//		addressStudent.setText("Address: "+ student.getAddress());
//		labelClass.setText("List class");
//		Set<Clazz> list = student.getClasses();
//		for (Clazz clazz : list) {
//			TableItem item = new TableItem(tableDetailClass, SWT.BORDER);
//			item.setText(0, clazz.getCode());
//			item.setText(1, clazz.getName());
//		}
//
//		for (int j = 0; j < tableDetailClass.getColumnCount(); j++) {
//			tableDetailClass.getColumn(j).pack();
//		}

	}
}
