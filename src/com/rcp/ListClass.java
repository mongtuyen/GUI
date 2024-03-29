package com.rcp;

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
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import add.MyWizardClass;
import add.addClass;
import connect.ServerConnector;
import edit.MyWizardClassEdit;

public class ListClass {

	final static Logger logger = Logger.getLogger(ListClass.class);
	private static Table table;
	private static Table tableStudent;
	static int sum;
	static Label labelClassSum;

	static Label sumStudent;

	int classID;
	static List<Clazz> l;

	public static List<Clazz> getL() {
		return l;
	}

	public void setL(List<Clazz> l) {
		this.l = l;
	}

	public int getClassID() {
		return classID;
	}

	public void setClassID(int classID) {
		this.classID = classID;
	}

	public static Table getTable() {
		return table;
	}

	public static void setTable(Table table) {
		ListClass.table = table;
	}

	public static int getSum() {
		return sum;
	}

	public static void setSum(int sum) {
		ListClass.sum = sum;
	}

	public static Label getLabelClassSum() {
		return labelClassSum;
	}

	public static void setLabelClassSum(Label labelClassSum) {
		ListClass.labelClassSum = labelClassSum;
	}

	public static Label getSumStudent() {
		return sumStudent;
	}

	public static void setSumStudent(Label sumStudent) {
		ListClass.sumStudent = sumStudent;
	}

	@PostConstruct
	public void createComposite(Composite parent) throws IOException {

		parent.setSize(30, 20);
		parent.setLayout(new GridLayout(1, false));
		Label label_1 = new Label(parent, SWT.NONE);
		label_1.setText("LIST CLASS");
		// label_1.setBounds(130, 26, 100, 15);

		table = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);

		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);// SWT.TOP
		gd_table.heightHint = 120;
		table.setLayoutData(gd_table);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		final TableColumn column0 = new TableColumn(table, SWT.NONE);
		column0.setText("ID");
		final TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("Code");
		final TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setText("Name");
		final TableColumn column3 = new TableColumn(table, SWT.NONE);
		column3.setText("Size");
		final TableColumn column4 = new TableColumn(table, SWT.NONE);
		column4.setText("Registered");
		final TableColumn column5 = new TableColumn(table, SWT.NONE);
		column5.setText("Vacant position");

		Menu menu = new Menu(parent);

		// menu update
		MenuItem update = new MenuItem(menu, SWT.NONE);
		update.setText("Update");
		update.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				int idClazz = Integer.parseInt(table.getSelection()[0].getText());
				Clazz clazz = ServerConnector.getInstance().getClassService().findById(idClazz);
				WizardDialog wizardDialog = new WizardDialog(parent.getShell(), new MyWizardClassEdit(clazz));
				if (wizardDialog.open() == Window.OK) {
					System.out.println("Ok pressed");
				} else {
					System.out.println("Cancel pressed");
				}
			}
		});
		// menu delete
		MenuItem remove = new MenuItem(menu, SWT.NONE);
		remove.setText("Delete");
		remove.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (table.getSelection().length > 0) {
					int idClazz = Integer.parseInt(table.getSelection()[0].getText());
					Clazz clazz=ServerConnector.getInstance().getClassService().findById(idClazz);
					//boolean deleted = 
					if (clazz.getStudents().isEmpty()) {
						ServerConnector.getInstance().getClassService().delete(idClazz);
						MessageDialog.openInformation(new Shell(), "Confirm", "Delete successfull");
					} else {
						//Clazz clazz = ServerConnector.getInstance().getClassService().findById(idClazz);
						MessageDialog.openError(new Shell(), "Error",
								"Class " + clazz.getName() + " has students enrolled.");
					}
				} else {
					MessageDialog.openWarning(new Shell(), "Warning", "Please choose a class to delete");
				}
			}
		});

		table.addListener(SWT.MenuDetect, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (table.getSelectionCount() <= 0) {
					event.doit = false;
				}
			}
		});
		table.setMenu(menu);

		// sort id
		column0.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TableItem[] items = table.getItems();
				for (int i = 1; i < items.length; i++) {
					int value1 = Integer.parseInt(items[i].getText(0));
					for (int j = 0; j < i; j++) {
						int value2 = Integer.parseInt(items[j].getText(0));
						if (value1 < value2) {
							String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4), items[i].getText(5) };
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
		// sort code
		column1.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				// sort column 2
				TableItem[] items = table.getItems();
				Collator collator = Collator.getInstance(Locale.getDefault());
				for (int i = 1; i < items.length; i++) {
					String value1 = items[i].getText(1);
					for (int j = 0; j < i; j++) {
						String value2 = items[j].getText(1);
						if (collator.compare(value1, value2) < 0) {
							String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4), items[i].getText(5) };
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
		// sort name
		column2.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				// sort column 2
				TableItem[] items = table.getItems();
				Collator collator = Collator.getInstance(Locale.getDefault());
				for (int i = 1; i < items.length; i++) {
					String value1 = items[i].getText(2);
					for (int j = 0; j < i; j++) {
						String value2 = items[j].getText(2);
						if (collator.compare(value1, value2) < 0) {
							String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2),
									items[i].getText(3), items[i].getText(4), items[i].getText(5) };
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

		table.setSortColumn(column0);
		table.setSortDirection(SWT.UP);

		labelClassSum = new Label(parent, SWT.NONE);
		labelClassSum.setText("Total: " + sum);

		l = ServerConnector.getInstance().getClassService().findAll();
		updateClazzTable(l);


		Button buttonDelete = new Button(parent, SWT.NONE);
		buttonDelete.setText("Delete selected item");
		buttonDelete.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				int[] selectedRows = table.getSelectionIndices();
				if (table.getSelection().length > 0) {
					for (int i = selectedRows.length - 1; i >= 0; i--) {
						// int studentID = Integer.parseInt(table.getSelection()[i].getText());
						int idClazz = Integer.parseInt(table.getSelection()[i].getText());
						ServerConnector.getInstance().getClassService().delete(idClazz);
					}
					MessageDialog.openInformation(new Shell(), "Confirm", "Delete successfull");
				} else {
					MessageDialog.openWarning(new Shell(), "Warning", "Please choose a class to delete");
				}

			}
		});


		// List student of class
		sumStudent = new Label(parent, SWT.NONE);
		sumStudent.setText("                                                           ");

		tableStudent = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI | SWT.BORDER);
		tableStudent.setVisible(false);

		GridData gd_table1 = new GridData(SWT.TOP, SWT.FILL, true, true, 2, 1);// SWT.TOP
		gd_table1.heightHint = 120;
		gd_table1.widthHint = 160;
		tableStudent.setLayoutData(gd_table1);

		tableStudent.setHeaderVisible(true);
		tableStudent.setLinesVisible(true);


		final TableColumn columnID = new TableColumn(tableStudent, SWT.NONE);
		columnID.setText("Student ID");
		final TableColumn columnName = new TableColumn(tableStudent, SWT.NONE);
		columnName.setText("Name");

	}

	public static void updateClazzTable(List<Clazz> list) {

		table.removeAll();
		logger.info("update list class");

		for (int i = 0; i < list.size(); i++) {
			TableItem item = new TableItem(table, SWT.BORDER);
			Button edit = new Button(table, SWT.BORDER);

			item.setText(0, String.valueOf(list.get(i).getId()));
			item.setText(2, list.get(i).getName());
			item.setText(1, list.get(i).getCode());
			item.setText(3, String.valueOf(list.get(i).getSize()));
			item.setText(4, String.valueOf(list.get(i).getStudents().size()));
			item.setText(5, String.valueOf((list.get(i).getSize() - list.get(i).getStudents().size())));
		}
		for (int j = 0; j < table.getColumnCount(); j++) {
			table.getColumn(j).pack();
		}
		sum = list.size();
		labelClassSum.setText("Total: " + sum);
		logger.info(list);

	}

//	public static void listStudentFromClass(int clazzID) {
//		tableStudent.removeAll();
//		tableStudent.setVisible(true);
//		
//		Clazz clazz=ServerConnector.getInstance().getClassService().findById(clazzID);
//		sumStudent.setText("LIST STUDENT OF CLASS " +clazz.getCode());
//		
//		Set<Student> list=clazz.getStudents();
//		for(Student student:list) {
//			TableItem item = new TableItem(tableStudent, SWT.BORDER);
//			item.setText(0, student.getCode());		
//			item.setText(1, student.getName());		
//		}
//
//		for (int j = 0; j < tableStudent.getColumnCount(); j++) {
//			tableStudent.getColumn(j).pack();
//		}
//	}

}
