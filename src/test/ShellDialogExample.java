package test;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;
  
public class ShellDialogExample {
   public static void main(String[] args) {
      Display display = new Display();
      Shell shell = new Shell(display);
  
      // pos x, pos y, width, height
      shell.setBounds(200, 200, 300, 200);
      shell.setText("SWT Checkbox Demonstration");
      shell.setLayout(new GridLayout());
  
      Group buttonGroup = new Group(shell, SWT.NONE);
      GridLayout gridLayout = new GridLayout();
      gridLayout.numColumns = 3;
      buttonGroup.setLayout(gridLayout);
      buttonGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
       
      SelectionListener selectionListener = new SelectionAdapter () {
         public void widgetSelected(SelectionEvent event) {
            Button button = ((Button) event.widget);
            System.out.print(button.getText());
            System.out.println(" selected = " + button.getSelection());
         };
      };
                   
      Button button1 = new Button(buttonGroup, SWT.CHECK);
      button1.setText("orange");
      button1.addSelectionListener(selectionListener);
      Button button2 = new Button(buttonGroup, SWT.CHECK);
      button2.setText("pear");
      button2.addSelectionListener(selectionListener);
      Button button3 = new Button(buttonGroup, SWT.CHECK);
      button3.setText("apple");
      button3.addSelectionListener(selectionListener);
       
      shell.open();
  
      while (!shell.isDisposed()) {
         if (!display.readAndDispatch()) {
            display.sleep();
         }
      }
      display.dispose();
   }
}