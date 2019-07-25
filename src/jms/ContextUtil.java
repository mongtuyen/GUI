package jms;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ContextUtil {
	private static Context m_context;
	private final static String INITIAL_CONTEXT_FACTORY = "org.jboss.naming.remote.client.InitialContextFactory";
	private final static String PROVIDER_URL = "remote://localhost:4447";

	public static Context getInstance() {
		if (m_context == null) {
			try {
				Properties p = new Properties();
				p.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
				p.put(Context.PROVIDER_URL, PROVIDER_URL);
				m_context = new InitialContext(p);
			} catch (NamingException e) {
			}
		}
		return m_context;
	}

}
