package test;

import java.io.File;

import java.io.FileWriter;

import java.io.IOException;

import org.eclipse.swt.SWT;

import org.eclipse.swt.widgets.Display;

import org.eclipse.swt.widgets.FileDialog;

import org.eclipse.swt.widgets.Shell;

public class SaveFileDialogSample {

	private static void save(String filename) {

		String content = "content to be saved to the file.";

		FileWriter fw = null;

		try {

			fw = new FileWriter(filename);

			fw.write(content);

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				fw.close();

			} catch (IOException e1) {

				e1.printStackTrace();

			}

		}

	}

	public static void main(String[] args) {

		Display display = new Display();

		Shell shell = new Shell(display);

		shell.open();

		FileDialog dialog = new FileDialog(shell, SWT.SAVE);

		dialog.setFilterNames(new String[] { "Batch Files", "All Files (*.*)" });

		dialog.setFilterExtensions(new String[] { "*.bat", "*.*" }); // Windows wild

//dialog.setFilterPath ("c:\\"); //Windows path 

		dialog.open();

		String filename = dialog.getFileName();

		save(dialog.getFilterPath() + File.separator + filename);

		while (!shell.isDisposed()) {

			if (!display.readAndDispatch())
				display.sleep();

		}

		display.dispose();

	}

}