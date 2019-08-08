package add;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolTip;
import com.tuyen.model.Clazz;
import connect.ServerConnector;
import event.InfoDialog;
import event.OpenDialog;

public class addClass extends WizardPage {
	private Text classID;
	private Text className;
	private Text classSize;

	public addClass() {
		super("wizardPage");
		setTitle("Create new class");
		setDescription("Please enter Class details");
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
		// classID.setText("");
		classID.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (classID.getText().isEmpty() && className.getText().isEmpty()) {//&&!classSize.getText().isEmpty()
					setPageComplete(false);
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
		// className.setText("");
		className.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (className.getText().isEmpty() && className.getText().isEmpty()) { //&&!classSize.getText().isEmpty()
					setPageComplete(false);
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

		classSize = new Text(container, SWT.BORDER | SWT.SINGLE);
		// className.setText("");
		
//		classSize.addVerifyListener(new VerifyListener() {
//			@Override
//			public void verifyText(VerifyEvent e) {
//				 String string = e.text;
//			        Matcher matcher = Pattern.compile("[0-9]*+$").matcher(string);
//			        if (!matcher.matches()) {
//			            e.doit = false;
//			            return;
//			        }
//			}
//			
//		});
		final ToolTip toolTip = new ToolTip(getShell(), SWT.BALLOON | SWT.ICON_WARNING);
		
		classSize.addModifyListener(e -> {
			String string = classSize.getText();
			String message = null;
			try {
				int value = Integer.parseInt(string);
				int maximum = 120;
				int minimum = 0;
				if (value > maximum) {
					message = "Current input is greater than the maximum limit ("+maximum+")";
					//setPageComplete(false);
				} else if (value < minimum) {
					message = "Current input is less than the minimum limit ("+minimum+")";
					//setPageComplete(false);
				}
			} catch (Exception ex) {
				message = "Current input is not numeric";
				//setPageComplete(false);
			}
			if (message != null) {
				classSize.setForeground(Display.getCurrent().getSystemColor(SWT.COLOR_RED));
				Rectangle rect = classSize.getBounds();
				GC gc = new GC(classSize);
				Point pt = gc.textExtent(string);
				gc.dispose();
				toolTip.setLocation(Display.getCurrent().map(parent, null, rect.x + pt.x, rect.y + rect.height));
				toolTip.setMessage(message);
				toolTip.setVisible(true);
			} else {
				toolTip.setVisible(false);
				classSize.setForeground(null);
				if (!className.getText().isEmpty() && !className.getText().isEmpty()&&!classSize.getText().isEmpty()) {
					setPageComplete(true);
				}
				//setPageComplete(true);
			}
		});
//		classSize.addModifyListener(new ModifyListener() {
//			@Override
//			public void modifyText(ModifyEvent event) {
//				 String txt = ((Text) event.getSource()).getText();
//		            try {
//		                int num = Integer.parseInt(txt);
//		                // Checks on num
//		            } catch (NumberFormatException e) {
//		            	MessageDialog.openError(new Shell(), "Error", "Size is a number");
//		            
//		            }
//			}		
//		});
		classSize.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (!className.getText().isEmpty() && !className.getText().isEmpty()&&!classSize.getText().isEmpty()) {
					setPageComplete(false);
				}
			}
		});
		classID.setLayoutData(gd);
		className.setLayoutData(gd);
		classSize.setLayoutData(gd);
		setControl(container);
		setPageComplete(false);

	}

	public Clazz getClazzEdit() {
		String classCode = classID.getText();
		String clazzName = className.getText();		
		Clazz clazz = new Clazz();
		clazz.setCode(classCode);
		clazz.setName(clazzName);
		clazz.setSize(Integer.parseInt(classSize.getText()));
		return clazz;

	}

}
