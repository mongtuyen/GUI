package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.naming.Context;
import javax.naming.NamingException;

public class ConnectionUtil {
	private final static String CONNECTION_FACTORY_NAME = "jms/RemoteConnectionFactory";

	public static Connection createTopicConnection() {
		Connection topicConnection = null;
		try {
			Context context = ContextUtil.getInstance();
			ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup(CONNECTION_FACTORY_NAME);
			topicConnection = connectionFactory.createConnection();

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return topicConnection;
	}

	public static void main(String[] args) {
		System.out.println(createTopicConnection());
	}
}
