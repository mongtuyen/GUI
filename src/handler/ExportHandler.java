package handler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlException;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;
import connect.ServerConnector;

public class ExportHandler {
	final static Logger logger = Logger.getLogger(ExportHandler.class);

	@Execute
	public void execute(Shell shell) throws XmlException, IOException {
		FileDialog dialog = new FileDialog(shell, SWT.SAVE);
		dialog.setFilterNames(new String[] { "Xml Files", "All Files (*.*)" });
		dialog.setFilterExtensions(new String[] { "*.xml", "*.*", "*.rar" });
		dialog.open();
		String filename = dialog.getFileName();
		save(dialog.getFilterPath() + File.separator + filename);

	}

	static Set<Student> setStudent;
	static int idClass;
	static Student student;
	static Clazz classtmp;

	private static void save(String filename) {
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("StudentDefinitions");
			document.appendChild(root);
			root.setAttribute("xmlns", "uri://testXML/tuyen");
			// clazz
			List<Clazz> list = ServerConnector.getInstance().getClassService().findAll();
			for (int i = 0; i < list.size(); i++) {

				Element clazz = document.createElement("class");
				root.appendChild(clazz);
				Attr attrClass = document.createAttribute("operator");
				attrClass.setValue("create");
				clazz.setAttributeNode(attrClass);
				Element classId = document.createElement("id");
				classId.appendChild(document.createTextNode(String.valueOf(list.get(i).getId())));
				clazz.appendChild(classId);

				Element classCode = document.createElement("code");
				classCode.appendChild(document.createTextNode(String.valueOf(list.get(i).getCode())));
				clazz.appendChild(classCode);

				Element className = document.createElement("name");
				className.appendChild(document.createTextNode(list.get(i).getName()));
				clazz.appendChild(className);

				Element classSize = document.createElement("size");
				classSize.appendChild(document.createTextNode(String.valueOf(list.get(i).getSize())));
				clazz.appendChild(classSize);
				//enroll
				setStudent = list.get(i).getStudents();
				idClass = list.get(i).getId();

				List<Student> listStudent = new ArrayList<Student>(setStudent);
				for (int j = 0; j < listStudent.size(); j++) {
					Element enrollment = document.createElement("enrollment");
					root.appendChild(enrollment);
					Attr attrEnroll = document.createAttribute("operator");
					attrEnroll.setValue("create");
					enrollment.setAttributeNode(attrEnroll);
					Element classIdE = document.createElement("classCode");

					classtmp = ServerConnector.getInstance().getClassService().findById(idClass);
					classIdE.appendChild(document.createTextNode(classtmp.getCode()));
					enrollment.appendChild(classIdE);

					Element studentE = document.createElement("studentCode");
					studentE.appendChild(document.createTextNode(listStudent.get(j).getCode()));
					enrollment.appendChild(studentE);

				}
			}

			// student
			List<Student> lt = ServerConnector.getInstance().getStudentService().findAll();
			for (int i = 0; i < lt.size(); i++) {
				Element student = document.createElement("student");
				root.appendChild(student);
				Attr attrStudent = document.createAttribute("operator");
				attrStudent.setValue("create");
				student.setAttributeNode(attrStudent);
				Element studentId = document.createElement("id");
				studentId.appendChild(document.createTextNode(String.valueOf(lt.get(i).getId())));
				student.appendChild(studentId);

				Element studentCode = document.createElement("code");
				studentCode.appendChild(document.createTextNode(lt.get(i).getCode()));
				student.appendChild(studentCode);

				Element studentName = document.createElement("name");
				studentName.appendChild(document.createTextNode(lt.get(i).getName()));
				student.appendChild(studentName);

				Element studentAge = document.createElement("age");
				studentAge.appendChild(document.createTextNode(String.valueOf(lt.get(i).getAge())));
				student.appendChild(studentAge);

				Element studentEmail = document.createElement("email");
				studentEmail.appendChild(document.createTextNode(lt.get(i).getEmail()));
				student.appendChild(studentEmail);

				Element studentAddress = document.createElement("address");
				studentAddress.appendChild(document.createTextNode(lt.get(i).getAddress()));
				student.appendChild(studentAddress);
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(filename));
			transformer.transform(domSource, streamResult);
			MessageDialog.openInformation(new Shell(), "Confirm", "Export successfull");
			logger.info("Done creating XML File");
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

	}

}