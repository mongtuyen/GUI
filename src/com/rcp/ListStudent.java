package com.rcp;

import java.io.IOException;
import java.text.Collator;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
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

import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import connect.ServerConnector;

public class ListStudent {
	
	final static Logger logger = Logger.getLogger(ListStudent.class);
	private static Table table;
	static int sum;
	static Label labelStudentSum;

	@PostConstruct
	public void createComposite(Composite parent) throws IOException {
		parent.setLayout(new GridLayout(1, false));
		Label label_1 = new Label(parent, SWT.NONE);
		label_1.setText("LIST STUDENT");
		label_1.setBounds(130, 26, 100, 15);

		table = new Table(parent, SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);
		gd_table.heightHint = 60;
		table.setLayoutData(gd_table);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		String[] titles = { "ID", "Name", "Age", "Point", "Class" };
		final TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("ID");
		final TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setText("Name");
		final TableColumn column3 = new TableColumn(table, SWT.NONE);
		column3.setText("Age");
		final TableColumn column4 = new TableColumn(table, SWT.NONE);
		column4.setText("Point");
		final TableColumn column5 = new TableColumn(table, SWT.NONE);
		column5.setText("Class");
//		for (int i = 0; i < titles.length; i++) {
//			TableColumn column = new TableColumn(table, SWT.NULL);
//			column.setText(titles[i]);
//		}
		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}
		// Sort id
		column1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TableItem[] items = table.getItems();
				for (int i = 1; i < items.length; i++) {
					int value1 = Integer.parseInt(items[i].getText(0));
					for (int j = 0; j < i; j++) {
						int value2 = Integer.parseInt(items[j].getText(0));
						if (value1 < value2) {
							String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4) };
							items[i].dispose();
							TableItem item = new TableItem(table, SWT.NONE, j);
							item.setText(values);
							items = table.getItems();
							break;
						}
					}
				}
			}
		});
		table.setSortColumn(column1);
		table.setSortDirection(SWT.UP);
		// Sort name
		column2.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TableItem[] items = table.getItems();
				Collator collator = Collator.getInstance(Locale.getDefault());
				for (int i = 1; i < items.length; i++) {
					String value1 = items[i].getText(1);
					for (int j = 0; j < i; j++) {
						String value2 = items[j].getText(1);
						if (collator.compare(value1, value2) < 0) {
							String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4) };
							items[i].dispose();
							TableItem item = new TableItem(table, SWT.NONE, j);
							item.setText(values);
							items = table.getItems();
							break;
						}
					}
				}
			}
		});

		// sort age
		column3.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TableItem[] items = table.getItems();
				for (int i = 1; i < items.length; i++) {
					int value1 = Integer.parseInt(items[i].getText(2));
					for (int j = 0; j < i; j++) {
						int value2 = Integer.parseInt(items[j].getText(2));
						if (value1 < value2) {
							String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4) };
							items[i].dispose();
							TableItem item = new TableItem(table, SWT.NONE, j);
							item.setText(values);
							items = table.getItems();
							break;
						}
					}
				}
			}
		});
		// sort point
		column4.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TableItem[] items = table.getItems();
				for (int i = 1; i < items.length; i++) {
					float value1 = Float.parseFloat(items[i].getText(3));
					for (int j = 0; j < i; j++) {
						float value2 = Float.parseFloat(items[j].getText(3));
						if (value1 < value2) {
							String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4) };
							items[i].dispose();
							TableItem item = new TableItem(table, SWT.NONE, j);
							item.setText(values);
							items = table.getItems();
							break;
						}
					}
				}
			}
		});

		labelStudentSum = new Label(parent, SWT.NONE);
		labelStudentSum.setText("Total: " + sum);

		List<Student> l = ServerConnector.getInstance().getStudentService().findAll();
		updateStudentTable(l);

		Button btnAdd = new Button(parent, SWT.NONE);
		btnAdd.setText("Add");
		btnAdd.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				AddStudent addShell = new AddStudent();
				addShell.openShell(Display.getCurrent());
			}
		});

		Button buttonDelete = new Button(parent, SWT.NONE);
		buttonDelete.setText("Delete");
		buttonDelete.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				int[] selectedRows = table.getSelectionIndices();
				System.out.println("lenn:" + selectedRows.length);
				if (table.getSelection().length > 0) {
					for (int i = selectedRows.length - 1; i >= 0; i--) {
						int studentID = Integer.parseInt(table.getSelection()[i].getText());
						ServerConnector.getInstance().getStudentService().delete(studentID);
					}					
					MessageDialog.openInformation(new Shell(), "Confirm", "Delete successfull");
				} else {
					MessageDialog.openWarning(new Shell(), "Warning", "Please choose a student to delete");
				}
			}
		});

		Button checkUpdate = new Button(parent, SWT.NONE);
		checkUpdate.setText("Edit");
		checkUpdate.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				if (table.getSelection().length > 0) {
					int studentID = Integer.parseInt(table.getSelection()[0].getText());
					EditStudent editShell = new EditStudent();
					Student student = ServerConnector.getInstance().getStudentService().findById(studentID);
					editShell.openShell(Display.getCurrent(), student);
				} else {
					MessageDialog.openWarning(new Shell(), "Warning", "Please choose a student to edit");
				}
			}
		});
	}

	public static void updateStudentTable(List<Student> l) {
		table.removeAll();
		logger.info("update list student");
		for (int i = 0; i < l.size(); i++) {
			TableItem item = new TableItem(table, SWT.NULL);
			item.setText(0, String.valueOf(l.get(i).getId()));
			item.setText(1, l.get(i).getName());
			item.setText(2, String.valueOf(l.get(i).getAge()));
			item.setText(3, String.valueOf(l.get(i).getPoint()));

//			Set<Clazz> classes=l.get(i).getClasses();
//			String str=",";
//			
//			for (Clazz a:classes) {
//				str=str+a.getName();
//			}
//			item.setText(4, str);
		}
		for (int j = 0; j < table.getColumnCount(); j++) {
			table.getColumn(j).pack();
		}
		sum = l.size();
		labelStudentSum.setText("Total: " + sum);
		System.out.println(sum);
		logger.info(l.toString());
		
	}
}
