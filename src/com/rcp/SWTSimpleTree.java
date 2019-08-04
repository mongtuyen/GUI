package com.rcp;

import java.io.IOException;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ProgressBar;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class SWTSimpleTree {
	@PostConstruct
	public void createComposite(Composite parent) throws IOException {
	
		ExpandBar bar = new ExpandBar (parent, SWT.V_SCROLL);
		Image image = Display.getDefault().getSystemImage(SWT.ICON_WORKING);
		
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
		item0.setImage(image);
		


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
		ProgressBar progressBar1 = new ProgressBar(parent, SWT.NULL);
		ProgressBar progressBar2 = new ProgressBar(parent, SWT.SMOOTH);
		ProgressBar progressBar3 = new ProgressBar(parent, SWT.INDETERMINATE);
	
	}
	
	
}
//  Display display = new Display();
//  Shell shell = new Shell(display);
//  
//  Tree tree;
//
//  public SWTSimpleTree() {
//    shell.setLayout(new GridLayout());
//    
//    tree = new Tree(shell, SWT.BORDER);
//    
//    tree.setLayoutData(new GridData(GridData.FILL_BOTH));
//    
//    TreeItem item = new TreeItem(tree, SWT.NULL);
//    item.setText("ITEM");
//    
//    TreeItem item2 = new TreeItem(item, SWT.NULL);
//    item2.setText("ITEM2");
//    
//    TreeItem item3 = new TreeItem(item2, SWT.NULL);
//    item3.setText("ITEM3");
//    
//    System.out.println("item: " + item.getParent() + ", " + item.getParentItem());
//    System.out.println("item2: " + item2.getParent() + ", " + item2.getParentItem());
//    
//    System.out.println(tree.getItemCount());
//    System.out.println(tree.getItems().length);
//    
//    shell.setSize(300, 200);
//    shell.open();
//    //textUser.forceFocus();
//
//    // Set up the event loop.
//    while (!shell.isDisposed()) {
//      if (!display.readAndDispatch()) {
//        // If no more entries in event queue
//        display.sleep();
//      }
//    }
//
//    display.dispose();
//  }
//
//  private void init() {
//
//  }
//
//  public static void main(String[] args) {
//    new SWTSimpleTree();
//  }
