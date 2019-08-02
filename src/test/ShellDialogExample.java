package test;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class ShellDialogExample {
  public static void main(String[] args) {
    Display display = new Display();
    Shell shell = new Shell(display);
    shell.setSize(280, 300);
    shell.setText("Table Example");

    final Text text = new Text(shell, SWT.BORDER);
    text.setBounds(25, 240, 220, 25);

    Table table = new Table(shell, SWT.CHECK | SWT.BORDER | SWT.V_SCROLL
        | SWT.H_SCROLL);
    table.setHeaderVisible(true);
    String[] titles = { "Col 1", "Col 2", "Col 3", "Col 4" };

    for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
      TableColumn column = new TableColumn(table, SWT.NULL);
      column.setText(titles[loopIndex]);
    }

    for (int loopIndex = 0; loopIndex < 24; loopIndex++) {
      TableItem item = new TableItem(table, SWT.NULL);
      item.setText("Item " + loopIndex);
      item.setText(0, "Item " + loopIndex);
      item.setText(1, "Yes");
      item.setText(2, "No");
      item.setText(3, "A table item");
    }

    for (int loopIndex = 0; loopIndex < titles.length; loopIndex++) {
      table.getColumn(loopIndex).pack();
    }

    table.setBounds(25, 25, 220, 200);

    table.addListener(SWT.Selection, new Listener() {
      public void handleEvent(Event event) {
        if (event.detail == SWT.CHECK) {
          text.setText("You checked " + event.item);
        } else {
          text.setText("You selected " + event.item);
        }
      }
    });

    shell.open();
    while (!shell.isDisposed()) {
      if (!display.readAndDispatch())
        display.sleep();
    }
    display.dispose();
  }
}
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.Canvas;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Listener;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Text;
//
//public class ShellDialogExample {
//
//  public static void main(String[] args) {
//    Display display = new Display();
//    Shell shell = new Shell(display);
//    
//    Text t = new Text(shell, SWT.SINGLE | SWT.BORDER);
//    t.setBounds(10, 85, 100, 32);
//
//    Text t2 = new Text(shell, SWT.SINGLE | SWT.BORDER);
//    t2.setBounds(10, 25, 100, 32);
//
//    t2.addListener(SWT.FocusIn, new Listener() {
//      public void handleEvent(Event e) {
//        System.out.println("focus in");
//      }
//    });
//    t2.addListener(SWT.FocusOut, new Listener() {
//      public void handleEvent(Event e) {
//       System.out.println("focus out");
//      }
//    });
//    
//    shell.setSize(200, 200);
//    shell.open();
//    while (!shell.isDisposed()) {
//      if (!display.readAndDispatch())
//        display.sleep();
//    }
//    display.dispose();
//  }
//}
//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.graphics.Rectangle;
//import org.eclipse.swt.layout.FillLayout;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Listener;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.TableColumn;
//import org.eclipse.swt.widgets.TableItem;
//
//public class ShellDialogExample {
//
//  public static void main(String[] args) {
//    Display display = new Display();
//    final Image image = display.getSystemImage(SWT.ICON_INFORMATION);
//    Shell shell = new Shell(display);
//    shell.setText("Images on the right side of the TableItem");
//    shell.setLayout(new FillLayout());
//    Table table = new Table(shell, SWT.MULTI | SWT.FULL_SELECTION);
//    table.setHeaderVisible(true);
//    table.setLinesVisible(true);
//    int columnCount = 3;
//    for (int i = 0; i < columnCount; i++) {
//      TableColumn column = new TableColumn(table, SWT.NONE);
//      column.setText("Column " + i);
//    }
//    int itemCount = 8;
//    for (int i = 0; i < itemCount; i++) {
//      TableItem item = new TableItem(table, SWT.NONE);
//      item.setText(new String[] { "item " + i + " a", "item " + i + " b", "item " + i + " c" });
//    }
//    /*
//     * NOTE: MeasureItem, PaintItem and EraseItem are called repeatedly.
//     * Therefore, it is critical for performance that these methods be as
//     * efficient as possible.
//     */
//    Listener paintListener = new Listener() {
//      public void handleEvent(Event event) {
//        switch (event.type) {
//        case SWT.MeasureItem: {
//          Rectangle rect = image.getBounds();
//          event.width += rect.width;
//          event.height = Math.max(event.height, rect.height + 2);
//          break;
//        }
//        case SWT.PaintItem: {
//          int x = event.x + event.width;
//          Rectangle rect = image.getBounds();
//          int offset = Math.max(0, (event.height - rect.height) / 2);
//          event.gc.drawImage(image, x, event.y + offset);
//          break;
//        }
//        }
//      }
//    };
//    table.addListener(SWT.MeasureItem, paintListener);
//    table.addListener(SWT.PaintItem, paintListener);
//
//    for (int i = 0; i < columnCount; i++) {
//      table.getColumn(i).pack();
//    }
//    shell.setSize(500, 200);
//    shell.open();
//    while (!shell.isDisposed()) {
//      if (!display.readAndDispatch())
//        display.sleep();
//    }
//    if (image != null)
//      image.dispose();
//    display.dispose();
//  }
//}

//import org.eclipse.swt.SWT;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Listener;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.TableItem;
//
//public class ShellDialogExample {
//
//	public static void main(String[] args) {
//		Display display = new Display();
//		Shell shell = new Shell(display);
//		final Table table = new Table(shell, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
//		for (int i = 0; i < 16; i++) {
//			TableItem item = new TableItem(table, 0);
//			item.setText("Item " + i);
//		}
//		table.setBounds(0, 0, 100, 100);
//		table.addListener(SWT.DefaultSelection, new Listener() {
//			public void handleEvent(Event e) {
//				String string = "";
//				TableItem[] selection = table.getSelection();
//				for (int i = 0; i < selection.length; i++)
//					string += selection[i] + " ";
//				System.out.println("DefaultSelection={" + string + "}");
//			}
//		});
//
//		shell.pack();
//		shell.open();
//		while (!shell.isDisposed()) {
//			if (!display.readAndDispatch())
//				display.sleep();
//		}
//		display.dispose();
//	}
//}

//import org.eclipse.swt.SWT;
//import org.eclipse.swt.graphics.Point;
//import org.eclipse.swt.graphics.Rectangle;
//import org.eclipse.swt.widgets.Display;
//import org.eclipse.swt.widgets.Event;
//import org.eclipse.swt.widgets.Listener;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.swt.widgets.Table;
//import org.eclipse.swt.widgets.TableColumn;
//import org.eclipse.swt.widgets.TableItem;
//
//public class ShellDialogExample {
//
//  public static void main(String[] args) {
//    Display display = new Display();
//    Shell shell = new Shell(display);
//    final Table table = new Table(shell, SWT.BORDER | SWT.V_SCROLL);
//    table.setHeaderVisible(true);
//    table.setLinesVisible(true);
//    final int rowCount = 64, columnCount = 4;
//    for (int i = 0; i < columnCount; i++) {
//      TableColumn column = new TableColumn(table, SWT.NONE);
//      column.setText("Column " + i);
//    }
//    for (int i = 0; i < rowCount; i++) {
//      TableItem item = new TableItem(table, SWT.NONE);
//      for (int j = 0; j < columnCount; j++) {
//        item.setText(j, "Item " + i + "-" + j);
//      }
//    }
//    for (int i = 0; i < columnCount; i++) {
//      table.getColumn(i).pack();
//    }
//    Point size = table.computeSize(SWT.DEFAULT, 200);
//    table.setSize(size);
//    shell.pack();
//    table.addListener(SWT.MouseDown, new Listener() {
//      public void handleEvent(Event event) {
//        Rectangle clientArea = table.getClientArea();
//        Point pt = new Point(event.x, event.y);
//        int index = table.getTopIndex();
//        while (index < table.getItemCount()) {
//          boolean visible = false;
//          TableItem item = table.getItem(index);
//          for (int i = 0; i < columnCount; i++) {
//            Rectangle rect = item.getBounds(i);
//            if (rect.contains(pt)) {
//              System.out.println("Item " + index + "-" + i);
//            }
//            if (!visible && rect.intersects(clientArea)) {
//              visible = true;
//            }
//          }
//          if (!visible)
//            return;
//          index++;
//        }
//      }
//    });
//    shell.open();
//    while (!shell.isDisposed()) {
//      if (!display.readAndDispatch())
//        display.sleep();
//    }
//    display.dispose();
//  }
//}