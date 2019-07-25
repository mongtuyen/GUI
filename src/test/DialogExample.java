package test;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.TreeItem;

public class DialogExample {
  public static void main(String[] args) {
	  Display display = new Display();
      Shell shell = new Shell(display);
      Table table = new Table(shell, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL
    	        | SWT.H_SCROLL);
    	    for (int i = 0; i < 12; i++) {
    	      TableItem item = new TableItem(table, SWT.NONE);
    	      item.setText("Item " + i);
    	    }
    	    table.setSize(100, 100);
    	    table.addListener(SWT.Selection, new Listener() {
    	      public void handleEvent(Event event) {
    	        String string = event.detail == SWT.CHECK ? "Checked"
    	            : "Selected";
    	        System.out.println(event.item + " " + string);
    	      }
    	    });
    	    shell.setSize(200, 200);
    	    shell.open();
    	    while (!shell.isDisposed()) {
    	      if (!display.readAndDispatch())
    	        display.sleep();
    	    }
    	    display.dispose();
//      // pos x, pos y, width, height
//      shell.setBounds(200, 200, 300, 200);
//      shell.setText("SWT List Demonstration");
//      shell.setLayout(new GridLayout());
//  
//      String []items = {"Belgium", "The Netherlands", "France", "Germany", "Italy",
//                        "Luxembourg", "Switzerland", "Austria", "Finland", "Iceland" };
//        
//      final List list = new List(shell, SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
//      list.setLayoutData(new GridData(GridData.FILL_HORIZONTAL | GridData.FILL_VERTICAL));
//      list.setItems (items);
//              
//      shell.open();
//        
//      shell.addShellListener(new ShellAdapter() {
//         public void shellClosed(ShellEvent se) {
//            int[] indices = list.getSelectionIndices();
//            System.out.println("Selected items:");
//            for (int i=0; i<indices.length; i++) {
//               System.out.println(list.getItem(indices[i]));  
//            }
//         }
//      });     
//  
//      while (!shell.isDisposed()) {
//         if (!display.readAndDispatch()) {
//            display.sleep();
//         }
//      }
//        
//      display.dispose();
//   }
//     
//   public static void recurseTree(TreeItem item) {
//      System.out.println(item.getText() + "tChecked=" + item.getChecked());
//      TreeItem[] treeItems = item.getItems();
//      for (int i=0; i<treeItems.length; i++) {
//         TreeItem treeItem = treeItems[i];
//         recurseTree(treeItem);
//      }  
   }
}