package org.nb.db;

import java.io.IOException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class DBDispture {
	private static Logger logger = Logger.getLogger(DBDispture.class);
	private static SqlSessionFactory sqlSessionFactory = null;
	private static String CONFIG_FILE_LOCATION = "mybatis.xml";
	@SuppressWarnings("rawtypes")
	private static final ThreadLocal threadLocal = new ThreadLocal();

	@SuppressWarnings("unchecked")
	public static synchronized SqlSession currentSession() {
		SqlSession session = (SqlSession) threadLocal.get();
		if (session == null) {
			if (sqlSessionFactory == null) {
				try {
					sqlSessionFactory = new SqlSessionFactoryBuilder()
							.build(Resources
									.getResourceAsStream(CONFIG_FILE_LOCATION));
					session = (sqlSessionFactory == null) ? null
							: sqlSessionFactory.openSession();
				} catch (IOException e) {
					logger.error("get sql session error", e);
				}
				threadLocal.set(session);
			}
		}

		return session;
	}

	@SuppressWarnings("unchecked")
	public static void closeSession() {
		SqlSession session = (SqlSession) threadLocal.get();
		threadLocal.set(null);
		if (session != null) {
			session.close();
		}
	}
}
