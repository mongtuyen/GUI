package jms;


import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.NamingException;

public class Pulisher {
	private final static String TOPIC_NAME = "jms/topic/mytopic";

	public void sendMessage(String strMessage) {
		TopicConnection topicConnection = (TopicConnection) ConnectionUtil.createTopicConnection();
		Context context = ContextUtil.getInstance();
		try {
			TopicSession topicSession = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
			Topic topic = (Topic) context.lookup(TOPIC_NAME);
			topicConnection.start();
			TopicPublisher publisher = topicSession.createPublisher(topic);
			TextMessage message = topicSession.createTextMessage(strMessage);
			
			publisher.publish(message);
			// System.out.println("Send: "+message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int i = 0;
		while (true) {
			new Pulisher().sendMessage("message " + i);
			i++;
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
