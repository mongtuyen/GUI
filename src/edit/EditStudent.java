package edit;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;

import connect.ServerConnector;
import event.InfoDialog;
import event.OpenDialog;


public class EditStudent extends WizardPage {
	private Text code;
	private Text name;
	private Text mail;
	private Text address;
	private Text age;
	Student student;
	
	
	//private Composite container;

	public EditStudent(Student student) {
		super("wizardPage");
		setTitle("Create new student");
		setDescription("");
		this.student=student;
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
		code.setText(student.getCode());
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
		name.setText(student.getName());
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
		age.setText(String.valueOf(student.getAge()));
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
		mail.setText(student.getEmail());
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
		address.setText(student.getAddress());
		address = new Text(container, SWT.BORDER | SWT.SINGLE);
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
//					Student a=new Student();
//					a.setCode(studentCode);
//					a.setName(studentName);
//					a.setAge(Integer.parseInt(studentAge));
//					a.setAddress(studentAddress);
//					a.setEmail(studentMail);
					
					System.out.println(studentCode+"nhgfvu  "+studentName+ "fjh  "+studentMail+"utfn  "+studentAddress+"sfd "+studentAge);
					//if (!studentCode.equals("") && !studentName.equals("")&& !studentAge.equals("")&& !studentMail.equals("")&& !studentAddress.equals("")) {
						Student student=new Student();
						student.setCode(studentCode);
						student.setName(studentName);
						student.setAge(Integer.parseInt(studentAge));
						student.setEmail(studentMail);
						student.setAddress(studentAddress);
						ServerConnector.getInstance().getStudentService().update(student);
						//new OpenDialog(new InfoDialog("Add new classroom: " + resultAdd)).openPage();
						//ClassroomPart.setTableClassroom();
						Display.getCurrent().getActiveShell().dispose();
					//}
					
			}
		});  
		
		
	}

}
