package add;


import java.io.IOException;

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
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;

import com.tuyen.model.Clazz;

import connect.ServerConnector;
import event.InfoDialog;
import event.OpenDialog;


public class addClass extends WizardPage {
	private Text classID;
	private Text className;
	//private Composite container;

	public addClass() {
		super("wizardPage");
		setTitle("Create new class");
		setDescription("");
	}

	@Override
	public void createControl(Composite parent) {
		parent.setSize(500, 800);
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		
		GridLayout layout = new GridLayout();
		container.setLayout(layout);
		layout.numColumns = 2;
		
		Label label1 = new Label(container, SWT.NONE);
		label1.setText("Class ID");

		classID = new Text(container, SWT.BORDER | SWT.SINGLE);
		classID.setText("");
		classID.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!classID.getText().isEmpty() && (!className.getText().isEmpty())) {
					setPageComplete(true);
				}
			}
		});

		Label label2 = new Label(container, SWT.NONE);
		label2.setText("Name");

		className = new Text(container, SWT.BORDER | SWT.SINGLE);
		className.setText("");
		className.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!classID.getText().isEmpty() && (!className.getText().isEmpty())) {
					setPageComplete(true);
				}
			}
		});
		
		
		
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		classID.setLayoutData(gd);
        className.setLayoutData(gd);
        setControl(container);
        setPageComplete(false);
        
        Button btnAdd = new Button(container, SWT.NONE);
		btnAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					String classCode = classID.getText();
					String clazzName = className.getText();
					
					if (!classCode.equals("") && clazzName.equals("")) {
						Clazz clazz = new Clazz();
						clazz.setCode(classCode);
						clazz.setName(clazzName);
						ServerConnector.getInstance().getClassService().persist(clazz);
						//new OpenDialog(new InfoDialog("Add new classroom: " + resultAdd)).openPage();
						//ClassroomPart.setTableClassroom();
						Display.getCurrent().getActiveShell().dispose();
					} else {
						new OpenDialog(new InfoDialog("Class name cannot null")).openPage();
					}
				} catch (NumberFormatException e1) {
					new OpenDialog(new InfoDialog("Invalid number")).openPage();
				}
			}
		});     
      
	}

}
