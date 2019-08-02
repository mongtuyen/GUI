package edit;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import add.AddStudent1;
import connect.ServerConnector;
import event.InfoDialog;
import event.OpenDialog;

public class EditStudent extends WizardPage {
	final static Logger logger = Logger.getLogger(AddStudent1.class);

	private Text code;
	private Text name;
	private Text mail;
	private Text address;
	private Text age;
	Student student;
	Set<Clazz> setClass = new HashSet<>();
	Clazz clazz;
	static List list;

	public EditStudent(Student student) {
		super("wizardPage");
		setTitle("Update student");
		setDescription("Please enter Student details");
		this.student = student;
	}

	@Override
	public void createControl(Composite parent) {
		parent.setSize(500, 800);
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);

		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;

		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Student ID");

		code = new Text(container, SWT.BORDER | SWT.SINGLE);
		code.setText(student.getCode());
		code.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!code.getText().isEmpty() && !name.getText().isEmpty() && !age.getText().isEmpty()
						&& !mail.getText().isEmpty() && !address.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});

		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Name");

		name = new Text(container, SWT.BORDER | SWT.SINGLE);
		name.setText(student.getName());
		name.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!code.getText().isEmpty() && !name.getText().isEmpty() && !age.getText().isEmpty()
						&& !mail.getText().isEmpty() && !address.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});

		Label label3 = new Label(container, SWT.NONE);
		label3.setText("Age");

		age = new Text(container, SWT.BORDER | SWT.SINGLE);
		age.setText(String.valueOf(student.getAge()));
		age.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!code.getText().isEmpty() && !name.getText().isEmpty() && !age.getText().isEmpty()
						&& !mail.getText().isEmpty() && !address.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});

		Label label4 = new Label(container, SWT.NONE);
		label4.setText("Email");

		mail = new Text(container, SWT.BORDER | SWT.SINGLE);
		mail.setText(student.getEmail());
		mail.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!code.getText().isEmpty() && !name.getText().isEmpty() && !age.getText().isEmpty()
						&& !mail.getText().isEmpty() && !address.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});

		Label label5 = new Label(container, SWT.NONE);
		label5.setText("Address");
		address = new Text(container, SWT.BORDER | SWT.SINGLE);
		address.setText(student.getAddress());
		address.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!code.getText().isEmpty() && !name.getText().isEmpty() && !age.getText().isEmpty()
						&& !mail.getText().isEmpty() && !address.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});
		Label label6 = new Label(container, SWT.NONE);
		label6.setText("Class");

		// register
		Composite enroll = new Composite(container, SWT.BORDER);
		setControl(enroll);
		enroll.setLayout(layout);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		GridLayout layoutEnroll = new GridLayout();
		enroll.setLayout(layoutEnroll);
		layoutEnroll.numColumns = 3;
		gd_composite.heightHint = 90;
		gd_composite.widthHint = 320;
		enroll.setLayoutData(gd_composite);

		list = new List(enroll, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.LEFT);
		int size = ServerConnector.getInstance().getClassService().findAll().size();
		for (int i = 0; i < ServerConnector.getInstance().getClassService().findAll().size(); i++) {
			list.add(ServerConnector.getInstance().getClassService().findAll().get(i).getName());
		}
		GridData gd_list = new GridData(SWT.NONE, SWT.TOP, true, true, 1, 1);
		gd_list.heightHint = 60;
		gd_list.widthHint = 100;

		list.setLayoutData(gd_list);

		final Button b1 = new Button(enroll, SWT.NONE | SWT.PUSH | SWT.CENTER);
		b1.setText("Register");

		final List listUpdate = new List(enroll, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.RIGHT);
		setClass = student.getClasses();
		for (Clazz tmp : setClass) {
			listUpdate.add(tmp.getName());
		}
		listUpdate.setLayoutData(gd_list);
		b1.addSelectionListener(new SelectionAdapter() {
			int classID;

			public void widgetSelected(SelectionEvent e) {
				String selected[] = list.getSelection();
				for (int i = 0; i < selected.length; i++) {
					listUpdate.add(selected[i]);
					list.remove(selected[i]);
					for (int t = 0; t < ServerConnector.getInstance().getClassService().findAll().size(); t++) {
						if (ServerConnector.getInstance().getClassService().findAll().get(t).getName()
								.equals(selected[i])) {
							classID = ServerConnector.getInstance().getClassService().findAll().get(t).getId();
							logger.info("ID selected" + classID);
							clazz = ServerConnector.getInstance().getClassService().findById(classID);
							setClass.add(clazz);
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
		code.setLayoutData(gd);
		name.setLayoutData(gd);
		address.setLayoutData(gd);
		mail.setLayoutData(gd);
		age.setLayoutData(gd);

		setControl(container);
		setPageComplete(false);

	}

	public Student getStudent() {
		String studentCode = code.getText();
		String studentName = name.getText();
		String studentAge = age.getText();
		String studentMail = mail.getText();
		String studentAddress = address.getText();
		if (!studentCode.equals(" ") && !studentName.equals(" ") && !studentAge.equals(" ") && !studentMail.equals(" ")
				&& !studentAddress.equals(" ")) {
			student.setCode(studentCode);
			student.setName(studentName);
			student.setAge(Integer.parseInt(studentAge));
			student.setEmail(studentMail);
			student.setAddress(studentAddress);
			student.setClasses(setClass);
		} else {
			new OpenDialog(new InfoDialog("Please fill in all information")).openPage();

		}
		return student;

	}

}
