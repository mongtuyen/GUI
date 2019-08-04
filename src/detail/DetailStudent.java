package detail;

import java.io.IOException;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.rcp.ListStudent;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import connect.ServerConnector;

public class DetailStudent {
	public static Composite parentComposite;
	int studentID=ListStudent.getStudentID();;
	@PostConstruct
	public void createComposite(Composite parent) throws IOException {
		parent.setLayout(new GridLayout(1, false));
		parentComposite = parent;
		//int studentID
		System.out.println("ID da chon ben list student la: "+studentID);
		Student student = ServerConnector.getInstance().getStudentService().findById(studentID);
		
		Table tableClass = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		//tableClass.setVisible(false);
		
		Label labelClass = new Label(parent, SWT.NONE);
		
	//	labelClass.setText("LIST CLASS OF " + student.getName());

		GridData gd_table1 = new GridData(SWT.TOP, SWT.FILL, true, true, 2, 1);// SWT.TOP
		gd_table1.heightHint = 120;
		gd_table1.widthHint = 160;
		tableClass.setLayoutData(gd_table1);

		tableClass.setHeaderVisible(true);
		tableClass.setLinesVisible(true);

		final TableColumn columnCodeClass = new TableColumn(tableClass, SWT.NONE);
		columnCodeClass.setText("Class ID");
		final TableColumn columnNameClass = new TableColumn(tableClass, SWT.NONE);
		columnNameClass.setText("Name");
		
		
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
