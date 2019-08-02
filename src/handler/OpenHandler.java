package handler;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import connect.ServerConnector;
import testXML.tuyen.StudentDefinitions;

public class OpenHandler {
	final static Logger logger = Logger.getLogger(OpenHandler.class);

	@Execute
	public void execute(Shell shell) throws XmlException, IOException {
		FileDialog dialog = new FileDialog(shell, SWT.PUSH);
		dialog.setText("Open file");
		String[] filterExt = { "*.xml" };
		dialog.setFilterExtensions(filterExt);
		String selected = dialog.open();
		File f = new File(selected);
		StudentDefinitions catalog = StudentDefinitions.Factory.parse(f);
		ServerConnector.getInstance().getClassService().getXMLFileFromGUI(catalog.toString());
		ServerConnector.getInstance().getStudentService().getXMLFileFromGUI(catalog.toString());
		logger.info("File path: " + selected);
		MessageDialog.openInformation(new Shell(), "Confirm", "Import successfull");
	
	}

}