package test;


import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Text;

public class From {
	private Text txtInput;
	private static TableViewer tableViewer;
	private static Table table;
	
	private Button btn;

	@Inject
	private MDirtyable dirty;

	@PostConstruct
	public static void createPartControl(Composite parent) {
		// create a FormLayout and set its margin
		FormLayout layout = new FormLayout();
		layout.marginHeight = 5;
		layout.marginWidth = 5;
	 
		// set layout for parent
		parent.setLayout(layout);
	 
		Button button1 = new Button(parent, SWT.PUSH);
		button1.setText("B1");
	 
		// create a button or any other widget
		Button button2 = new Button(parent, SWT.PUSH);
		button2.setText("B2");
	 
		// create FormData and set each of its sides
		FormData formData = new FormData();
		formData.top = new FormAttachment(button1, 0);
		formData.bottom = new FormAttachment(30, 0);
		formData.left = new FormAttachment(10, 0);
		formData.right = new FormAttachment(50, 0);
	 
		// set FormDate for button
		button2.setLayoutData(formData);
	 
		// create a button or any other widget
		Button button3 = new Button(parent, SWT.PUSH);
		button3.setText("B2");
	 
		// create FormData and set each of its sides
		FormData formData2 = new FormData();
		formData2.top = new FormAttachment(button2, 0);
		formData2.bottom = new FormAttachment(50, 0);
		formData2.left = new FormAttachment(button2, 0);
		formData2.right = new FormAttachment(80, 0);
	 
		// set FormDate for button
		button3.setLayoutData(formData2);
	}
	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	@Persist
	public void save() {
		dirty.setDirty(false);
	}
//	public static void main(String args[]) {
//		createPartControl(null);
//	}
}
