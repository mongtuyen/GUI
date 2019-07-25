package test;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class ShellDialogExample {
	public ShellDialogExample() {
	    Display d = new Display();
	    Shell s = new Shell(d);
	    s.setSize(300, 300);
	    s.open();
	    DialogExample de = new DialogExample(s);
	    String result = de.open();
	    System.out.println(result);
	    while (!s.isDisposed()) {
	      if (!d.readAndDispatch())
	        d.sleep();
	    }
	    d.dispose();

	  }

	  public static void main(String[] argv) {
	    new ShellDialogExample();
	  }

}
