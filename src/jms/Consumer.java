package jms;

import java.util.List;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.NamingException;
import org.eclipse.swt.widgets.Display;
import com.rcp.ListClass;
import com.rcp.ListStudent;
import com.tuyen.model.Clazz;
import com.tuyen.model.Student;
import connect.ServerConnector;

public class Consumer {
	private final static String TOPIC_NAME = "jms/topic/mytopic";

	public void receiveMessage() {
		System.out.println("Started consumer...");
		TopicConnection topicConnection = (TopicConnection) ConnectionUtil.createTopicConnection();
		Context context = ContextUtil.getInstance();
		try {
			TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = (Topic) context.lookup(TOPIC_NAME);

			TopicSubscriber subscriber = topicSession.createSubscriber(topic);
			MessageListener listener = new MessageListener() {

				@Override
				public void onMessage(Message arg0) {
					TextMessage textMessage = (TextMessage) arg0;
					try {
						if (textMessage.getText().contains("class")) {
							List<Clazz> newListClass = ServerConnector.getInstance().getClassService().findAll();
							Display.getDefault().asyncExec(new Runnable() {
								public void run() {
									ListClass.updateClazzTable(newListClass);
								}
							});
							try {
								System.out.println("Received: " + textMessage.getText());
							} catch (JMSException e) {
								e.printStackTrace();
							}

						} else {
							List<Student> newListStudent = ServerConnector.getInstance().getStudentService().findAll();
							Display.getDefault().asyncExec(new Runnable() {
								public void run() {
									ListStudent.updateStudentTable(newListStudent);
								}
							});
							try {
								System.out.println("Received: " + textMessage.getText());
							} catch (JMSException e) {
								e.printStackTrace();
							}
						}

						System.out.println("Recieve: " + arg0);
					} catch (JMSException e1) {
						e1.printStackTrace();
					}
				}
			};
			subscriber.setMessageListener(listener);
			topicConnection.start();
			while (true) {

			}

		} catch (JMSException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Consumer().receiveMessage();
	}

}
