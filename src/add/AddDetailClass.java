package add;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import connect.ServerConnector;
import detail.DetailOfClass;

public class AddDetailClass extends WizardPage {

	Student student;
	Set<Student> setStudent = new HashSet<>();
	int idClass = DetailOfClass.getClassID();
	Clazz clazz = ServerConnector.getInstance().getClassService().findById(idClass);

	static List list;
	String[] code;

	public AddDetailClass() {
		super("wizardPage");
		setTitle("Update list student");
		// setPageComplete(false);
	}

	@Override
	public void createControl(Composite parent) {
		parent.setSize(550, 750);
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		// GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 1;

		// input text
		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Class ID: " + clazz.getCode());

		Label label3 = new Label(container, SWT.NONE);
		label3.setText("Name: " + clazz.getName());

		Label label1 = new Label(container, SWT.NONE);
		label1.setText("List student");

		Composite enroll = new Composite(container, SWT.BORDER);
		setControl(enroll);
		enroll.setLayout(layout);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		GridLayout layoutEnroll = new GridLayout();
		enroll.setLayout(layoutEnroll);
		layoutEnroll.numColumns = 3;
		gd_composite.heightHint = 230;
		gd_composite.widthHint = 400;
		enroll.setLayoutData(gd_composite);
		// List student default
		list = new List(enroll, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.LEFT);
		int size = ServerConnector.getInstance().getStudentService().findAll().size();
		for (int i = 0; i < ServerConnector.getInstance().getStudentService().findAll().size(); i++) {
			list.add(ServerConnector.getInstance().getStudentService().findAll().get(i).getCode() + ": "
					+ ServerConnector.getInstance().getStudentService().findAll().get(i).getName());
		}

		GridData gd_list = new GridData(SWT.TOP, SWT.TOP, true, true, 1, 1);
		gd_list.widthHint = 150;
		gd_list.heightHint = 150;

		list.setLayoutData(gd_list);

		final Button buttonUpdate = new Button(enroll, SWT.NONE | SWT.PUSH | SWT.CENTER);
		buttonUpdate.setText("Add");
		// list studnet update
		final List listUpdate = new List(enroll, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.RIGHT);
		listUpdate.setLayoutData(gd_list);
		setStudent = clazz.getStudents();
		for (Student a : setStudent) {
			listUpdate.add(a.getCode() + ": " + a.getName());
			list.remove(a.getCode() + ": " + a.getName());
		}
		buttonUpdate.addSelectionListener(new SelectionAdapter() {
			int studentID;

			public void widgetSelected(SelectionEvent e) {
				String selected[] = list.getSelection();
				for (int i = 0; i < selected.length; i++) {
					listUpdate.add(selected[i]);
					list.remove(selected[i]);
					code = selected[i].split(":");
					System.out
							.println("-----------CODE 00--------------------------------------------------" + code[0]);
					for (int t = 0; t < ServerConnector.getInstance().getStudentService().findAll().size(); t++) {
						if (ServerConnector.getInstance().getStudentService().findAll().get(t).getCode()
								.equals(code[0])) {
							studentID = ServerConnector.getInstance().getStudentService().findAll().get(t).getId();
							student = ServerConnector.getInstance().getStudentService().findById(studentID);
							setStudent.add(student);
						}

					}
				}
			}
		});

		final Menu menu = new Menu(listUpdate);
		listUpdate.setMenu(menu);
		menu.addMenuListener(new MenuAdapter() {
			public void menuShown(MenuEvent e) {
				int selected = listUpdate.getSelectionIndex();
				String selectedText[] = listUpdate.getSelection();
				if (selected < 0 || selected >= listUpdate.getItemCount())
					return;

				MenuItem[] items = menu.getItems();
				for (int i = 0; i < items.length; i++) {
					items[i].dispose();
				}
				MenuItem newItem = new MenuItem(menu, SWT.NONE);
				newItem.setText("Delete");
				newItem.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						listUpdate.remove(selected);
						for (int i = 0; i < selectedText.length; i++) {
							list.add(selectedText[i]);
						}
					}

				});

			}
		});
		listUpdate.addListener(SWT.MenuDetect, new Listener() {
			@Override
			public void handleEvent(Event event) {
				if (listUpdate.getSelectionCount() <= 0) {
					event.doit = false;
				}
			}
		});

	}

	public Clazz getClazz() {
		clazz.setId(clazz.getId());
		clazz.setCode(clazz.getCode());
		clazz.setName(clazz.getName());
		clazz.setStudents(setStudent);
		return clazz;
	}

}
