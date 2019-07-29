package com.rcp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddStudentComposist extends Composite{
	//AddStudentComposist() {
		//super();
		// TODO Auto-generated constructor stub
	//}

	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;

	public AddStudentComposist(Composite parent, int style) {
		super(parent, style);
		//setFont(SWTResourceManager.getFont("Segoe UI", 18, SWT.BOLD));
		//setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		
		Label lblAddStudent = new Label(this, SWT.NONE);
		lblAddStudent.setForeground(SWTResourceManager.getColor(SWT.COLOR_LIST_SELECTION));
		lblAddStudent.setFont(SWTResourceManager.getFont("Segoe UI", 13, SWT.BOLD));
		lblAddStudent.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblAddStudent.setBounds(123, 22, 105, 39);
		lblAddStudent.setText("Add student");
		
		text = new Text(this, SWT.BORDER);
		text.setBounds(159, 72, 122, 21);
		
		text_1 = new Text(this, SWT.BORDER);
		text_1.setBounds(159, 122, 122, 21);
		
		text_2 = new Text(this, SWT.BORDER);
		text_2.setBounds(159, 172, 122, 21);
		
		text_3 = new Text(this, SWT.BORDER);
		text_3.setBounds(159, 220, 122, 21);
		
		Label lblId = new Label(this, SWT.NONE);
		lblId.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblId.setBounds(69, 75, 55, 15);
		lblId.setText("Student ID");
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblName.setText("Name");
		lblName.setBounds(69, 128, 55, 15);
		
		Label lblAge = new Label(this, SWT.NONE);
		lblAge.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblAge.setText("Age");
		lblAge.setBounds(69, 178, 55, 15);
		
		Label lblEmail = new Label(this, SWT.NONE);
		lblEmail.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblEmail.setText("Email");
		lblEmail.setBounds(69, 226, 55, 15);
		
		text_4 = new Text(this, SWT.BORDER);
		text_4.setBounds(159, 272, 122, 21);
		
		Label lblAddress = new Label(this, SWT.NONE);
		lblAddress.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblAddress.setText("Address");
		lblAddress.setBounds(69, 278, 55, 15);
		
		Button btnSave = new Button(this, SWT.NONE);
		btnSave.setBounds(96, 446, 75, 25);
		btnSave.setText("Save");
		
		Button btnCancel = new Button(this, SWT.NONE);
		btnCancel.setBounds(204, 446, 75, 25);
		btnCancel.setText("Cancel");
		
		List list = new List(this, SWT.BORDER);
		list.setItems(new String[] {"A", "B", "C", "D", "E", "F"});
		list.setBounds(96, 352, 74, 66);
		
		List list_1 = new List(this, SWT.BORDER);
		list_1.setBounds(204, 352, 71, 66);
		
		Label lblClass = new Label(this, SWT.NONE);
		lblClass.setBackground(SWTResourceManager.getColor(SWT.COLOR_TITLE_BACKGROUND_GRADIENT));
		lblClass.setText("Class");
		lblClass.setBounds(69, 319, 55, 15);
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(187, 352, 2, 64);

}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
