package xml;

import java.io.File;
import java.io.IOException;

import org.apache.xmlbeans.XmlException;

import connect.ServerConnector;
import testXML.tuyen.StudentDefinitions;

public class TestXML {
	public static void main(String []args) throws XmlException, IOException {
      File f = new File("D:\\Project\\Student-app\\XMLBean\\student.xml");
      StudentDefinitions clazz = StudentDefinitions.Factory.parse(f);
      ServerConnector.getInstance().getClassService().getXMLFileFromGUI(clazz.toString());
      //ServerConnector.getInstance().getClassService().addListClass(arg0);
 }
}

