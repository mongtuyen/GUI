package test.esyncExec;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

public class Test {
	public static void main(String[] args) {

		  Display.getDefault().asyncExec(new Runnable() {

		   public void run() {

		    Dialog dialog = new CustomDialog(Display.getDefault().getActiveShell());
		    dialog.open();
		   }

		  });

		  System.out.println("I am done");
		  stopExitingTheUiApplication();
		 }

		 /**
		  * This stops the application from termination, when we use asyncExec method
		  */
		 private static void stopExitingTheUiApplication() {
		  while (!Display.getDefault().isDisposed()) {
		   if (!Display.getDefault().readAndDispatch()) {
		    Display.getDefault().sleep();
		   }
		  }

		 }
}
