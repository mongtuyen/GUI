package edit;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Color;
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
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;
import com.tuyen.service.ClazzService;

import add.AddStudent1;
import connect.ServerConnector;
import event.InfoDialog;
import event.OpenDialog;

public class EditClass extends WizardPage {
	final static Logger logger = Logger.getLogger(EditClass.class);

	private Text classID;
	private Text className;
	private Text sizeClass;
	private Clazz clazz;
	static Set<Student> setStudent = new HashSet<>();
	static List list;
	Student studentAdd;
	Student studentDelete;
	String[] code;
	public EditClass(Clazz clazz) {
		super("wizardPage");
		setTitle("Update class");
		setDescription("Please enter Class details");
		this.clazz = clazz;
	}

	@Override
	public void createControl(Composite parent) {
		parent.setSize(500, 800);
		Color blue =Display.getCurrent().getSystemColor(SWT.COLOR_INFO_BACKGROUND);
		
		
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);

		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;

		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Class ID");

		classID = new Text(container, SWT.BORDER | SWT.SINGLE);
		classID.setText(clazz.getCode());
		classID.setBackground(blue);
		classID.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!className.getText().isEmpty() && !className.getText().isEmpty() && !sizeClass.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});
		classID.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				 String string = e.text;
			        Matcher matcher = Pattern.compile("[a-z A-Z 0-9]*+$").matcher(string);
			        if (!matcher.matches()) {
			            e.doit = false;
			            return;
			        }
			}
			
		});
		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Name");

		className = new Text(container, SWT.BORDER | SWT.SINGLE);
		className.setText(clazz.getName());
		className.setBackground(blue);
		
		className.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!className.getText().isEmpty() && !className.getText().isEmpty() && !sizeClass.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});
		className.addVerifyListener(new VerifyListener() {

			@Override
			public void verifyText(VerifyEvent e) {
				 String string = e.text;
			        Matcher matcher = Pattern.compile("[a-z A-Z]*+$").matcher(string);
			        if (!matcher.matches()) {
			            e.doit = false;
			            return;
			        }
			}
			
		});
		Label label3 = new Label(container, SWT.NONE);
		label3.setText("Number of student");

		sizeClass = new Text(container, SWT.BORDER | SWT.SINGLE);
		sizeClass.setText(String.valueOf(clazz.getSize()));
		sizeClass.setBackground(blue);
		sizeClass.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!className.getText().isEmpty() && !className.getText().isEmpty() && !sizeClass.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});
		sizeClass.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				 String string = e.text;
			        Matcher matcher = Pattern.compile("[0-9]*+$").matcher(string);
			        if (!matcher.matches()) {
			            e.doit = false;
			            return;
			        }
			}
			
		});
		Label labelList = new Label(container, SWT.NONE);
		labelList.setText("List student");

		Composite enroll = new Composite(container, SWT.BORDER);
		setControl(enroll);
		enroll.setLayout(layout);
		GridData gd_composite = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		GridLayout layoutEnroll = new GridLayout();
		enroll.setLayout(layoutEnroll);
		layoutEnroll.numColumns = 3;
		gd_composite.heightHint = 190;
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

		final Button buttonAdd = new Button(enroll, SWT.NONE | SWT.PUSH | SWT.CENTER);
		buttonAdd.setText("Add");
		// list studnet update
		final List listUpdate = new List(enroll, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL | SWT.H_SCROLL | SWT.RIGHT);
		listUpdate.setLayoutData(gd_list);
		listUpdate.setBackground(blue);
		setStudent = clazz.getStudents();
		for (Student a : setStudent) {
			listUpdate.add(a.getCode() + ": " + a.getName());
			list.remove(a.getCode() + ": " + a.getName());
		}
		
		buttonAdd.addSelectionListener(new SelectionAdapter() {
			int studentID;

			public void widgetSelected(SelectionEvent e) {
				String selected[] = list.getSelection();
				for (int i = 0; i < selected.length; i++) {
					
					code = selected[i].split(":");
					//logger.info("CODE 0: " + code[0]);
					for (int t = 0; t < ServerConnector.getInstance().getStudentService().findAll().size(); t++) {
						if (ServerConnector.getInstance().getStudentService().findAll().get(t).getCode()
								.equals(code[0])) {
							studentID = ServerConnector.getInstance().getStudentService().findAll().get(t).getId();
							studentAdd = ServerConnector.getInstance().getStudentService().findById(studentID);
							System.out.println("size set:"+(setStudent.size()+1)+"class size:"+clazz.getSize());
							if((setStudent.size()+1)<=clazz.getSize()) {
								setStudent.add(studentAdd);
								listUpdate.add(selected[i]);
								list.remove(selected[i]);
							}
							else {
								MessageDialog.openError(new Shell(), "Error", "Class " + clazz.getName() + " is full");
							}
							
						}

					}
				}
			}
		});

		final Menu menu = new Menu(listUpdate);
		listUpdate.setMenu(menu);
		menu.addMenuListener(new MenuAdapter() {
			int studentIDDelete;

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
							code = selectedText[i].split(":");
							// remove student
							for (int t = 0; t < ServerConnector.getInstance().getStudentService().findAll()
									.size(); t++) {
								if (ServerConnector.getInstance().getStudentService().findAll().get(t).getCode()
										.equals(code[0])) {
									studentIDDelete = ServerConnector.getInstance().getStudentService().findAll().get(t).getId();
									studentDelete = ServerConnector.getInstance().getStudentService().findById(studentIDDelete);
									
									setStudent.removeIf(new Predicate<Student>() {

										@Override
										public boolean test(Student t) {
											return t.getId() == studentIDDelete;
										}
									});
									System.out.println("List student sau khi xoa:" + setStudent.toString());

								}

							}
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

		classID.setLayoutData(gd);
		className.setLayoutData(gd);
		sizeClass.setLayoutData(gd);
		setControl(container);
		
	}

	public Clazz getClazzEdit() {
		String classCode = classID.getText();
		String clazzName = className.getText();
		clazz.setCode(classCode);
		clazz.setName(clazzName);
		clazz.setSize(Integer.parseInt(sizeClass.getText()));
		clazz.setStudents(setStudent);
		return clazz;

	}

}
