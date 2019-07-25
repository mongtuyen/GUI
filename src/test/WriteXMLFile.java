//package test;
//
//import java.io.File;
//import java.util.List;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//import org.w3c.dom.Attr;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//
//import com.tuyen.model.Clazz;
//import com.tuyen.model.Student;
//
//import connect.ServerConnector;
//
//public class WriteXMLFile {
//
//	public static final String xmlFilePath = "D:\\xmlfile.xml";
//
//	public static void main(String argv[]) {
//
//		try {
//
//			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
//
//			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
//
//			Document document = documentBuilder.newDocument();
//
//			// root element
//			Element root = document.createElement("student-manager");
//			document.appendChild(root);
//
//			// employee element
//			Element clazz = document.createElement("class");
//			Element student = document.createElement("student");
//
//			root.appendChild(clazz);
//			root.appendChild(student);
//
//			
//			
//			// set an attribute to staff element
//			Attr attr = document.createAttribute("operator");
//			attr.setValue("create");
//			clazz.setAttributeNode(attr);
//
//			Attr attr1 = document.createAttribute("operator");
//			attr1.setValue("create");
//			student.setAttributeNode(attr1);
//			
//			// clazz
//
//			List<Clazz> list = ServerConnector.getInstance().getClassService().findAll();
//			for (int i=0;i<list.size();i++) {
//				Element classId = document.createElement("id");
//				classId.appendChild(document.createTextNode(String.valueOf(list.get(i).getId())));
//				clazz.appendChild(classId);
//
//				Element className = document.createElement("name");
//				className.appendChild(document.createTextNode(list.get(i).getName()));
//				clazz.appendChild(className);
//
//			}
//
//			// student
//			List<Student> lt=ServerConnector.getInstance().getStudentService().findAll();
//			for (int i=0;i<lt.size();i++) {
//			Element studentId = document.createElement("id");
//			studentId.appendChild(document.createTextNode(String.valueOf(lt.get(i).getId())));
//			student.appendChild(studentId);
//
//			// lastname element
//			Element studentName = document.createElement("name");
//			studentName.appendChild(document.createTextNode(lt.get(i).getName()));
//			student.appendChild(studentName);
//			
//			Element studentAge = document.createElement("age");
//			studentAge.appendChild(document.createTextNode(String.valueOf(lt.get(i).getAge())));
//			student.appendChild(studentAge);
//			
//			Element studentPoint = document.createElement("point");
//			studentPoint.appendChild(document.createTextNode(Float.toString(lt.get(i).getPoint())));
//			student.appendChild(studentPoint);
//			
//			Element studentClazz = document.createElement("clazz");
//			studentClazz.appendChild(document.createTextNode(lt.get(i).getClazz().getName()));
//			student.appendChild(studentClazz);
//			
//			}
//			// create the xml file
//			// transform the DOM Object to an XML File
//			TransformerFactory transformerFactory = TransformerFactory.newInstance();
//			Transformer transformer = transformerFactory.newTransformer();
//			DOMSource domSource = new DOMSource(document);
//			StreamResult streamResult = new StreamResult(new File(xmlFilePath));
//
//			// If you use
//			// StreamResult result = new StreamResult(System.out);
//			// the output will be pushed to the standard output ...
//			// You can use that for debugging
//
//			transformer.transform(domSource, streamResult);
//
//			System.out.println("Done creating XML File");
//
//		} catch (ParserConfigurationException pce) {
//			pce.printStackTrace();
//		} catch (TransformerException tfe) {
//			tfe.printStackTrace();
//		}
//	}
//}
////package test;
////
////import java.io.FileWriter;
////import java.io.IOException;
////import org.jdom.Attribute;
////import org.jdom.Document;
////import org.jdom.Element;
////import org.jdom.output.Format;
////import org.jdom.output.XMLOutputter;
////
////public class WriteXMLFile {
////	public static void main(String[] args) {
////
////	  try {
////
////		Element company = new Element("company");
////		Document doc = new Document(company);
////		doc.setRootElement(company);
////
////		Element staff = new Element("staff");
////		staff.setAttribute(new Attribute("id", "1"));
////		staff.addContent(new Element("firstname").setText("yong"));
////		staff.addContent(new Element("lastname").setText("mook kim"));
////		staff.addContent(new Element("nickname").setText("mkyong"));
////		staff.addContent(new Element("salary").setText("199999"));
////
////		doc.getRootElement().addContent(staff);
////
////		Element staff2 = new Element("staff");
////		staff2.setAttribute(new Attribute("id", "2"));
////		staff2.addContent(new Element("firstname").setText("low"));
////		staff2.addContent(new Element("lastname").setText("yin fong"));
////		staff2.addContent(new Element("nickname").setText("fong fong"));
////		staff2.addContent(new Element("salary").setText("188888"));
////
////		doc.getRootElement().addContent(staff2);
////
////		// new XMLOutputter().output(doc, System.out);
////		XMLOutputter xmlOutput = new XMLOutputter();
////
////		// display nice nice
////		xmlOutput.setFormat(Format.getPrettyFormat());
////		xmlOutput.output(doc, new FileWriter("c:\\file.xml"));
////
////		System.out.println("File Saved!");
////	  } catch (IOException io) {
////		System.out.println(io.getMessage());
////	  }
////	}
////}