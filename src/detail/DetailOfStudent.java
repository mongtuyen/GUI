//package detail;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Set;
//import javax.annotation.PostConstruct;
//import org.apache.log4j.Logger;
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.layout.GridData;
//import org.eclipse.swt.layout.GridLayout;
//import org.eclipse.swt.widgets.Button;
//import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Listener;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.TableColumn;
//import org.eclipse.swt.widgets.TableItem;
//
//import com.tuyen.model.Clazz;
//import com.tuyen.model.Student;
//
//import add.MyWizardClass;
//import add.MyWizardStudent;
//import connect.ServerConnector;
//import edit.MyWizardEdit;
//import edit.MyWizardStudentEdit;
//
//public class DetailOfStudent {
//
//	final static Logger logger = Logger.getLogger(DetailOfStudent.class);
//	private static Table table;
//	private static Table tableClass;
//	static int sum;
//	static Label labelStudentSum;
//	static int studentID;		
//	static Label labelClass;
//
//
//	@PostConstruct
//	public void createComposite(Composite parent) throws IOException {
//		parent.setLayout(new GridLayout(1, false));
//		Label label_1 = new Label(parent, SWT.NONE);
//		label_1.setText("LIST STUDENT");
//		label_1.setBounds(130, 26, 100, 15);
//
//		table = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL| SWT.FULL_SELECTION | SWT.MULTI);
//		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
//		gd_table.heightHint = 120;
//		table.setLayoutData(gd_table);
//		table.setHeaderVisible(true);
//		table.setLinesVisible(true);
//		String[] titles = { "ID", "Code", "Name", "Age", "Email", "Address" };
//		final TableColumn column0 = new TableColumn(table, SWT.NONE);
//		column0.setText("ID");
//		final TableColumn column1 = new TableColumn(table, SWT.NONE);
//		column1.setText("Code");
//		final TableColumn column2 = new TableColumn(table, SWT.NONE);
//		column2.setText("Name");
//		final TableColumn column3 = new TableColumn(table, SWT.NONE);
//		column3.setText("Age");
//		final TableColumn column4 = new TableColumn(table, SWT.NONE);
//		column4.setText("Email");
//		final TableColumn column5 = new TableColumn(table, SWT.NONE);
//		column5.setText("Address");
//
//		for (int i = 0; i < titles.length; i++) {
//			table.getColumn(i).pack();
//		}
//		
//		table.setSortColumn(column0);
//		table.setSortDirection(SWT.UP);
//		
//		labelStudentSum = new Label(parent, SWT.NONE);
//		labelStudentSum.setText("Total: " + sum);
//
//		List<Student> l = ServerConnector.getInstance().getStudentService().findAll();
//		updateStudentTable(l);
//
//		
//
//		// List student of class
//
//		labelClass = new Label(parent, SWT.NONE);
//		labelClass.setText("                                                           ");
//
//		tableClass = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
//		tableClass.setVisible(false);
//		
//		GridData gd_table1 = new GridData(SWT.TOP, SWT.FILL, true, true, 2, 1);// SWT.TOP
//		gd_table1.heightHint = 120;
//		gd_table1.widthHint = 160;
//		tableClass.setLayoutData(gd_table1);
//
//		tableClass.setHeaderVisible(true);
//		tableClass.setLinesVisible(true);
//
//		final TableColumn columnCodeClass = new TableColumn(tableClass, SWT.NONE);
//		columnCodeClass.setText("Class ID");
//		final TableColumn columnNameClass = new TableColumn(tableClass, SWT.NONE);
//		columnNameClass.setText("Name");
//
//		table.addListener(SWT.Selection, new Listener() {
//			
//
//			public void handleEvent(Event e) {
//				TableItem[] selection = table.getSelection();
//				for (int i = 0; i < selection.length; i++) {
//					studentID = Integer.parseInt(selection[i].getText());
//				}
//				listClassFromStudent(studentID);
//			}
//		});
//
//	}
//
//	public static void updateStudentTable(List<Student> l) {
//		table.removeAll();
//		logger.info("update list student");
//		for (int i = 0; i < l.size(); i++) {
//			TableItem item = new TableItem(table, SWT.NULL);
//			item.setText(0, String.valueOf(l.get(i).getId()));
//			item.setText(1, l.get(i).getCode());
//			item.setText(2, l.get(i).getName());
//			item.setText(3, String.valueOf(l.get(i).getAge()));
//			item.setText(4, String.valueOf(l.get(i).getEmail()));
//			item.setText(5, String.valueOf(l.get(i).getAddress()));
//		}
//		for (int j = 0; j < table.getColumnCount(); j++) {
//			table.getColumn(j).pack();
//		}
//		sum = l.size();
//		labelStudentSum.setText("Total: " + sum);
//		System.out.println(sum);
//		logger.info(l.toString());
//
//	}
//
//	public static void listClassFromStudent(int studentID) {
//		System.out.println("student id: "+studentID); 
//		tableClass.removeAll();
//		tableClass.setVisible(true);
//		Student student = ServerConnector.getInstance().getStudentService().findById(studentID);
//		labelClass.setText("LIST CLASS OF " + student.getName());
//
//		Set<Clazz> list = student.getClasses();
//		for (Clazz clazz : list) {
//			TableItem item = new TableItem(tableClass, SWT.BORDER);
//			item.setText(0, clazz.getCode());
//			item.setText(1, clazz.getName());
//		}
//
//		for (int j = 0; j < tableClass.getColumnCount(); j++) {
//			tableClass.getColumn(j).pack();
//		}
//	}
//}
