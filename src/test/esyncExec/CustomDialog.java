package test.esyncExec;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
public class CustomDialog extends Dialog {

	 public String name;
	 private Text text1 = null;
	 private Button button = null;

	 public CustomDialog(Shell parentShell) {
	  super(parentShell);
	 }

	 @Override
	 protected void okPressed() {
	  // Do operations if you want, when ok button pressed
	  name = text1.getText();
	  super.okPressed();
	 }

	 @Override
	 protected Control createDialogArea(Composite parent) {
	  Composite composite = (Composite) super.createDialogArea(parent);

	  GridLayout gridLayout = new GridLayout();
	  gridLayout.numColumns = 2;
	  composite.setLayout(gridLayout);

	  Label label1 = new Label(composite, SWT.NONE);
	  label1.setText("Name:");
	  text1 = new Text(composite, SWT.BORDER);

	  button = new Button(composite, SWT.CHECK);
	  button.setText("Have you been employed in the past six months?");

	  GridData gridData1 = new GridData();

	  gridData1.widthHint = 60;

	  label1.setLayoutData(gridData1);

	  GridData gridData4 = new GridData(GridData.FILL_HORIZONTAL);

	  text1.setLayoutData(gridData4);

	  GridData gridData7 = new GridData();
	  gridData7.horizontalSpan = 2;
	  button.setLayoutData(gridData7);

	  return composite;
	 }

	 @Override
	 protected void configureShell(Shell newShell) {
	  super.configureShell(newShell);
	  newShell.setText("My Custom Dialog");
	 }

	 @Override
	 protected Point getInitialSize() {
	  return new Point(450, 300);
	 }

	 @Override
	 public String toString() {
	  return "CustomDialog [name=" + name + "]";
	 }

	}
