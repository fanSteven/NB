package org.nb.resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.nb.db.DBDispture;

public class BaseResource {
	public static Logger logger;
	public static SqlSession session = null;
	static {
		session = DBDispture.currentSession();
	}
}
