package com.rcp;

import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.annotation.PostConstruct;
import org.apache.log4j.Logger;

//import org.apache.logging.log4j.LogManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import com.tuyen.model.Clazz;

import add.addClass;
import connect.ServerConnector;

public class ListClass {
	
	final static Logger logger = Logger.getLogger(ListClass.class);
	private static Table table;
	static int sum;
	static Label labelClassSum;
	

	@PostConstruct
	public void createComposite(Composite parent) throws IOException {
		parent.setLayout(new GridLayout(1, false));
		Label label_1 = new Label(parent, SWT.NONE);
		label_1.setText("LIST CLASS");
		label_1.setBounds(130, 26, 100, 15);

		table = new Table(parent, SWT.V_SCROLL | SWT.H_SCROLL | SWT.FULL_SELECTION | SWT.MULTI);
		GridData gd_table = new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1);// SWT.TOP
		gd_table.heightHint = 120;
		table.setLayoutData(gd_table);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		String[] titles = { "ID", "Code", "Name" };

//		for (int i = 0; i < titles.length; i++) {
//			TableColumn column = new TableColumn(table, SWT.BORDER);
//			column.setText(titles[i]);
//			column.setWidth(600);
//		}

		final TableColumn column0 = new TableColumn(table, SWT.NONE);
		column0.setText("ID");
		final TableColumn column1 = new TableColumn(table, SWT.NONE);
		column1.setText("Code");
		final TableColumn column2 = new TableColumn(table, SWT.NONE);
		column2.setText("Name");

		// sort id
		column0.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event e) {
				TableItem[] items = table.getItems();
				for (int i = 1; i < items.length; i++) {
					int value1 = Integer.parseInt(items[i].getText(0));
					for (int j = 0; j < i; j++) {
						int value2 = Integer.parseInt(items[j].getText(0));
						if (value1 < value2) {
							String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2)};
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
									String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2) };
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
							String[] values = { items[i].getText(0), items[i].getText(1), items[i].getText(2)};
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

		for (int i = 0; i < titles.length; i++) {
			table.getColumn(i).pack();
		}
		labelClassSum = new Label(parent, SWT.NONE);
		labelClassSum.setText("Total: " + sum);

		List<Clazz> l = ServerConnector.getInstance().getClassService().findAll();
		updateClazzTable(l);
		Button btnAdd = new Button(parent, SWT.NONE);
		btnAdd.setText("Add");
		btnAdd.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event arg0) {
				Shell shell = null;
				CreateClass a=new CreateClass(shell);
				
				addClass addShell = new addClass();
				//AddClazz addShell = new AddClazz();
				//addShell.openShell(Display.getCurrent());
//				// List<Clazz> l = ServerConnector.getInstance().getClassService().findAll();
				// updateClazzTable(l);
			}
		});

		Button buttonDelete = new Button(parent, SWT.NONE);
		buttonDelete.setText("Delete");
		buttonDelete.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				if (table.getSelection().length > 0) {
					int idClazz = Integer.parseInt(table.getSelection()[0].getText());
					boolean deleted = ServerConnector.getInstance().getClassService().delete(idClazz);
					if (deleted) {
						MessageDialog.openInformation(new Shell(), "Confirm", "Delete successfull");
					} else {
						Clazz clazz = ServerConnector.getInstance().getClassService().findById(idClazz);
						MessageDialog.openError(new Shell(), "Error", clazz.getName() + " exist in Student table");
					}
				} else {
					MessageDialog.openWarning(new Shell(), "Warning", "Please choose a class to delete");
				}
			}
		});

		Button checkUpdate = new Button(parent, SWT.NONE);
		checkUpdate.setText("Edit");
		checkUpdate.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				if (table.getSelection().length > 0) {
					int idClazz = Integer.parseInt(table.getSelection()[0].getText());
					EditClass editShell = new EditClass();
					Clazz clazz = ServerConnector.getInstance().getClassService().findById(idClazz);
					editShell.openShell(Display.getCurrent(), clazz);
				} else {
					MessageDialog.openWarning(new Shell(), "Warning", "Please choose a class to edit");

				}
			}
		});
		
	}

	public static void updateClazzTable(List<Clazz> list) {
		table.removeAll();
		logger.info("update list class");

		for (int i = 0; i < list.size(); i++) {
			TableItem item = new TableItem(table, SWT.BORDER);
			item.setText(0, String.valueOf(list.get(i).getId()));
			item.setText(2, list.get(i).getName());
			item.setText(1,list.get(i).getCode());
		}
		for (int j = 0; j < table.getColumnCount(); j++) {
			table.getColumn(j).pack();
		}
		sum = list.size();
		labelClassSum.setText("Total: " + sum);
		logger.info(list);

	}

}
