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

import com.rcp.ListStudent;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import add.MyWizardClass;
import add.MyWizardStudent;
import connect.ServerConnector;
import edit.MyWizardEdit;
import edit.MyWizardStudentEdit;

public class DetailOfStudent {

	final static Logger logger = Logger.getLogger(DetailOfStudent.class);
	static int sum;
	static Label labelStudentSum;
	private static Table tableClass;
	private static Table table=ListStudent.getTable();
	static Label labelClass;

	@PostConstruct
	public void createComposite(Composite parent) throws IOException {
		parent.setLayout(new GridLayout(1, false));	

		// List student of class

		labelClass = new Label(parent, SWT.NONE);
		labelClass.setText("                                                           ");

		tableClass = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		tableClass.setVisible(false);
		
		GridData gd_table1 = new GridData(SWT.TOP, SWT.FILL, true, true, 2, 1);// SWT.TOP
		gd_table1.heightHint = 160;
		gd_table1.widthHint = 160;
		tableClass.setLayoutData(gd_table1);
		tableClass.setHeaderVisible(true);
		tableClass.setLinesVisible(true);

		final TableColumn columnCodeClass = new TableColumn(tableClass, SWT.NONE);
		columnCodeClass.setText("Class ID");
		final TableColumn columnNameClass = new TableColumn(tableClass, SWT.NONE);
		columnNameClass.setText("Name");
		table.addListener(SWT.DefaultSelection, new Listener() {
			int studentID;

			public void handleEvent(Event e) {
				TableItem[] selection = table.getSelection();
				for (int i = 0; i < selection.length; i++) {
					studentID = Integer.parseInt(selection[i].getText());
				}
				listClassFromStudent(studentID);
			}
		});
	}


	public static void listClassFromStudent(int studentID) {
		tableClass.removeAll();
		tableClass.setVisible(true);
		Student student = ServerConnector.getInstance().getStudentService().findById(studentID);
		labelClass.setText("LIST CLASS OF " + student.getName());

		Set<Clazz> list = student.getClasses();
		for (Clazz clazz : list) {
			TableItem item = new TableItem(tableClass, SWT.BORDER);
			item.setText(0, clazz.getCode());
			item.setText(1, clazz.getName());
		}

		for (int j = 0; j < tableClass.getColumnCount(); j++) {
			tableClass.getColumn(j).pack();
		}
	}
}
