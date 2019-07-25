package connect;

import java.io.IOException;
import java.util.List;

import javax.management.JMX;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;

import com.tuyen.model.Student;
import com.tuyen.service.ClazzService;
import com.tuyen.service.StudentService;

import jms.Consumer;

public class ServerConnector {

	private static ServerConnector instance = new ServerConnector();
	private static MBeanServerConnection m_mbConnectionServer;

	private ServerConnector() {
		System.out.println("Starting...");
		try {
			JMXServiceURL url = new JMXServiceURL("service:jmx:remoting-jmx://127.0.0.1:9999");
			JMXConnector connector = JMXConnectorFactory.connect(url);
			m_mbConnectionServer = connector.getMBeanServerConnection();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Connected...");
		Thread recieveMessage = new Thread(new Runnable() {
			@Override
			public void run() {
				new Consumer().receiveMessage();
			}
		});
		
		recieveMessage.start();
		System.out.println("start consumer done");
	}

	public static ServerConnector getInstance() {
		if (instance == null) {
			instance = new ServerConnector();
		}
		return instance;
	}

	public StudentService getStudentService() {
		StudentService studentService = null;
		try {
			ObjectName studentServiceName = new ObjectName("StudentManager:service=StudentService,type=student");
			studentService = JMX.newMBeanProxy(m_mbConnectionServer, studentServiceName, StudentService.class);
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
		return studentService;
	}

	public ClazzService getClassService() {
		ClazzService classService = null;
		try {
			ObjectName classServiceName = new ObjectName("StudentManager:service=ClazzService,type=clazz");
			classService = JMX.newMBeanProxy(m_mbConnectionServer, classServiceName, ClazzService.class);
		} catch (MalformedObjectNameException e) {
			e.printStackTrace();
		}
		return classService;
	}

	public static void main(String[] args) throws IOException {
		System.out.println();
		//ServerConnector.getInstance().getStudentService().delete(3);
		List<Student> a=ServerConnector.getInstance().getStudentService().findAll();
		for(int i=0;i<a.size();i++) {
			System.out.println(a.toString());
		}
	}

}
