package org.nb.resource.login;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.nb.db.DBDispture;
import org.nb.mybatis.model.INB_User;
import org.nb.mybatis.model.NB_User;
import org.nb.resource.user.UsersResource;
import org.nb.tool.Constant;
import org.nb.tool.OperateLog;

@Path("/login")
public class LoginResource {
	public static Logger logger = Logger.getLogger(UsersResource.class);
	private static SqlSession session = null;
	private static INB_User inb_User = null;
	static {
		session = DBDispture.currentSession();
		inb_User = session.getMapper(INB_User.class);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog getUserInfo(NB_User user) {
		OperateLog operateLog = new OperateLog();
		try {
			if (inb_User.findUserByName(user) == null) {
				operateLog.setId(Constant.ERRORCODE);
				operateLog.setLog(Constant.LOGINERROR);
				return operateLog;
			}
		} catch (Exception ex) {
			logger.error("get user info by name error", ex);
		} finally {
			session.commit();
		}
		return operateLog;
	}

}
