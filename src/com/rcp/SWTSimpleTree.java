package com.rcp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class SWTSimpleTree {
	@PostConstruct
	public void createComposite(Composite parent) throws IOException {
		
		ExpandBar bar = new ExpandBar (parent, SWT.V_SCROLL);
		Image image = Display.getDefault().getSystemImage(SWT.ICON_INFORMATION);
		
//		URL url = new URL("C:\\Users\\ntmongtuyen\\eclipse-workspace\\RCPTest\\icons\\icons8-student-male-20.png");
//		InputStream is = url.openStream();
//		Image image1 = new Image(Display.getCurrent(), is);
		
		Composite composite = new Composite (bar, SWT.NONE);
		GridLayout layout = new GridLayout ();
		layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 10;
		layout.verticalSpacing = 10;
		composite.setLayout(layout);
		
		Label label = new Label (composite, SWT.NONE);
		label.setText("Class");
		Label label2 = new Label (composite, SWT.NONE);
		label2.setText("Student");
		
		ExpandItem item0 = new ExpandItem (bar, SWT.NONE, 0);
		item0.setText("Manage Student");
		item0.setHeight(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item0.setControl(composite);
		//item0.setImage(image);

//		Tree tree = new Tree(parent, SWT.BORDER);
//		TreeItem item = new TreeItem(tree, SWT.NULL);
//		item.setText("Manage Student");
//		TreeItem item2 = new TreeItem(item, SWT.NULL);
//
//		item2.setText("Class");
//		item2.addListener(SWT.Selection, new Listener() {
//			public void handleEvent(Event e) {
//				String string = "";
//				TreeItem[] selection = tree.getSelection();
//				for (int i = 0; i < selection.length; i++)
//					string += selection[i] + " ";
//				System.out.println("Selection={" + string + "}");
//			}
//		});
//
//		TreeItem item3 = new TreeItem(item, SWT.NULL);
//		item3.setText("Student");
//		item3.addListener(SWT.Selection, new Listener() {
//			public void handleEvent(Event e) {
//				String string = "";
//				TreeItem[] selection = tree.getSelection();
//				for (int i = 0; i < selection.length; i++)
//					string += selection[i] + " ";
//				System.out.println("Selection={" + string + "}");
//			}
//		});
		
		
//		Group group = new Group(parent, SWT.SHADOW_ETCHED_IN);
//		group.setLocation(50, 50);		
//		group.setText("Process");
//		Label label7 = new Label(group, SWT.NONE);
//		label7.setText("Label in Group");
//		label7.setLocation(20,20);
//		label7.pack();
		//ProgressBar progressBar1 = new ProgressBar(parent, SWT.NULL);
	//	progressBar1.setSelection(50);
//		ProgressBar progressBar2 = new ProgressBar(parent, SWT.SMOOTH);
//		ProgressBar progressBar3 = new ProgressBar(parent, SWT.INDETERMINATE);	
		//group.pack();
	}		
}
