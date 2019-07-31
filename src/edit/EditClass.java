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
import com.tuyen.service.ClazzService;

import connect.ServerConnector;
import event.InfoDialog;
import event.OpenDialog;

public class EditClass extends WizardPage {
	private Text classID;
	private Text className;
	private Clazz clazz;

	public EditClass(Clazz clazz) {
		super("wizardPage");
		setTitle("Update class");
		setDescription("Please enter Student details");
		this.clazz = clazz;
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
		label1.setText("Class ID");

		classID = new Text(container, SWT.BORDER | SWT.SINGLE);
		classID.setText(clazz.getCode());

		classID.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!className.getText().isEmpty() && !className.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});

		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Name");

		className = new Text(container, SWT.BORDER | SWT.SINGLE);
		className.setText(clazz.getName());
		className.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!className.getText().isEmpty() && !className.getText().isEmpty()) {
					setPageComplete(true);
				}
			}
		});

		classID.setLayoutData(gd);
		className.setLayoutData(gd);
		setControl(container);
		setPageComplete(false);

		// Button btnSave = new Button(shell, SWT.NULL);
//		btnSave.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				String className = textClazzName.getText();
//				int classID = Integer.parseInt(textClazzID.getText());
//				Clazz clazz=new Clazz();
//				clazz.setId(classID);
//				clazz.setName(className);
//				ServerConnector tmp = ServerConnector.getInstance();
//				ClazzService clazzService = tmp.getClassService();
//				clazzService.update(clazz);
//			
//				shell.dispose();
//				
//			}
//		});
//		btnSave.setText("Save");
//        

//		Button btnSave = new Button(container, SWT.NONE);
//		btnSave.setText("Save");
//	
//		btnSave.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//
////				if (classCode.equals("")) {
////					new OpenDialog(new InfoDialog("Class code cannot null")).openPage();
////				}
////				if (clazzName.equals("")) {
////					new OpenDialog(new InfoDialog("Class name cannot null")).openPage();
////				}
////				if (!classCode.equals("") && !clazzName.equals("")) {
//				String classCode = classID.getText();
//				String clazzName = className.getText();
//				clazz.setCode(classCode);
//				clazz.setName(clazzName);
//				ServerConnector.getInstance().getClassService().update(clazz);
//				// new OpenDialog(new InfoDialog("Add new classroom: " + resultAdd)).openPage();
//				new OpenDialog(new InfoDialog("Edit class successfull")).openPage();
//				// ClassroomPart.setTableClassroom();
//				Display.getCurrent().getActiveShell().dispose();
//				// }
//
//			}
//		});

	}

	public Clazz getClazzEdit() {
		String classCode = classID.getText();
		String clazzName = className.getText();
		// if (!classCode.equals(" ") && !clazzName.equals(" ")) {
		clazz.setCode(classCode);
		clazz.setName(clazzName);
//		}
//		else {
//			new OpenDialog(new InfoDialog("Please fill in all information")).openPage();		
//		}
		return clazz;

	}

}
