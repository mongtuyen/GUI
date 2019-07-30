package add;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import connect.ServerConnector;
import event.InfoDialog;
import event.OpenDialog;


public class AddStudent1 extends WizardPage {
	private Text code;
	private Text name;
	private Text mail;
	private Text address;
	private Text age;
	
	
	
	
	//private Composite container;

	public AddStudent1() {
		super("wizardPage");
		setTitle("Create new student");
		setDescription("");
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

		//Combo travelDate = new Combo(container, SWT.BORDER | SWT.READ_ONLY);
		//travelDate.setLayoutData(gd);

		code = new Text(container, SWT.BORDER | SWT.SINGLE);
		//classID.setText("");
		code.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!code.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});

		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Name");

		name = new Text(container, SWT.BORDER | SWT.SINGLE);
		//className.setText("");
		name.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!name.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});
		
		Label label3 = new Label(container, SWT.NONE);
		label3.setText("Age");

		age = new Text(container, SWT.BORDER | SWT.SINGLE);
		//className.setText("");
		age.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!age.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});
		
		
		Label label4 = new Label(container, SWT.NONE);
		label4.setText("Email");

		mail = new Text(container, SWT.BORDER | SWT.SINGLE);
		//className.setText("");
		mail.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!mail.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});
		
		
		Label label5 = new Label(container, SWT.NONE);
		label5.setText("Address");

		address = new Text(container, SWT.BORDER | SWT.SINGLE);
		//className.setText("");
		address.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!address.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});
		
		Label label6 = new Label(container, SWT.NONE);
		label6.setText("Class");

		List list = new List(container, SWT.BORDER);
		for (int i=0;i<ServerConnector.getInstance().getClassService().findAll().size();i++) {
			list.add(ServerConnector.getInstance().getClassService().findAll().get(i).getName());
		}
		
		list.setLayoutData(new RowData(240, 100));
		code.setLayoutData(gd);
        name.setLayoutData(gd);
        address.setLayoutData(gd);
        mail.setLayoutData(gd);
        age.setLayoutData(gd);
        
        setControl(container);
        setPageComplete(false);
        
        Button btnAdd = new Button(container, SWT.NONE);
        btnAdd.setText("Add");
        btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
					String studentCode = code.getText();
					String studentName = name.getText();
					String studentAge = age.getText();
					String studentMail = mail.getText();
					String studentAddress = address.getText();
					System.out.println(studentCode+"nhgfvu  "+studentName+ "fjh  "+studentMail+"utfn  "+studentAddress+"sfd "+studentAge);
					if (!studentCode.equals("") && !studentName.equals("")&& !studentAge.equals("")&& !studentMail.equals("")&& !studentAddress.equals("")) {
						Student student=new Student();
						student.setCode(studentCode);
						student.setName(studentName);
						student.setAge(Integer.parseInt(studentAge));
						student.setEmail(studentMail);
						student.setAddress(studentAddress);
						ServerConnector.getInstance().getStudentService().persist(student);
						//new OpenDialog(new InfoDialog("Add new classroom: " + resultAdd)).openPage();
						//ClassroomPart.setTableClassroom();
						Display.getCurrent().getActiveShell().dispose();
					}
					
			}
		});  
		
		
	}

}
