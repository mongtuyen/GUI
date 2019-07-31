package test;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.ShellAdapter;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;


/**
 * http://www.ibm.com/developerworks/cn/opensource/os-cn-swtmulti/index.html?ca=drs-cn-1022
 */
public class test extends Composite {
	Text displayText = null;
	String[] comboItems = null;
	int[] comboSelection = null;
	Shell floatShell = null;
	List list = null;
	Button dropButton = null;

	public test(Composite parent, String[] items,
			int[] selection, int style) {
		super(parent, style);
		comboSelection = selection;
		comboItems = items;
		init();
	}

	private void init() {
		GridLayout layout = new GridLayout();
		layout.marginBottom = 0;
		layout.marginTop = 0;
		layout.marginLeft = 0;
		layout.marginRight = 0;
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		setLayout(layout);
		displayText = new Text(this, SWT.BORDER);
		displayText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		displayText.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent event) {
				super.mouseDown(event);
				initFloatShell();
			}
		});
	}

	private void initFloatShell() {
		Point p = displayText.getParent().toDisplay(displayText.getLocation());
		Point size = displayText.getSize();
		Rectangle shellRect = new Rectangle(p.x, p.y + size.y, size.x, 0);
		floatShell = new Shell(test.this.getShell(),
				SWT.NO_TRIM);

		GridLayout gl = new GridLayout();
		gl.marginBottom = 2;
		gl.marginTop = 2;
		gl.marginRight = 0;
		gl.marginLeft = 0;
		gl.marginWidth = 0;
		gl.marginHeight = 0;
		floatShell.setLayout(gl);

		list = new List(floatShell, SWT.BORDER | SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		for (String value : comboItems) {
			list.add(value);
		}
		list.setSelection(comboSelection); 
		
		GridData gd = new GridData(GridData.FILL_BOTH);
		list.setLayoutData(gd);
		floatShell.setSize(shellRect.width, 100);
		floatShell.setLocation(shellRect.x, shellRect.y);
		list.addMouseListener(new MouseAdapter() {
			public void mouseUp(MouseEvent event) {
				super.mouseUp(event);
				comboSelection = list.getSelectionIndices();
				if ((event.stateMask & SWT.CTRL) == 0) {
					floatShell.dispose();
					displayText();
				}
			}
		});

		floatShell.addShellListener(new ShellAdapter() {
			public void shellDeactivated(ShellEvent arg0) {
				if (floatShell != null && !floatShell.isDisposed()) {
					comboSelection = list.getSelectionIndices();
					displayText();
					floatShell.dispose();
				}
			}
		});

		floatShell.open();
	}

	private void displayText() {
		if (comboSelection != null && comboSelection.length > 0) {
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < comboSelection.length; i++) {
				if (i > 0)
					sb.append(",");
				sb.append(comboItems[comboSelection[i]]);
			}
			displayText.setText(sb.toString());
		} else {
			displayText.setText("");
		}
	}
	
	public void setText(String text) {
		displayText.setText(text);
	}
	
	public String getText() {
		return displayText.getText();
	}
	
	public Text getTextWidget() {
		return displayText;
	}
}