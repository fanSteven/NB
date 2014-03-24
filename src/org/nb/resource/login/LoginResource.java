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
	public NB_User getUserInfo(NB_User user) {
		NB_User nb_User = null;
		try {
			nb_User = inb_User.findUserByName(user);
		} catch (Exception ex) {
			logger.error("get user info by name error", ex);
		} finally {
			session.commit();
		}
		return nb_User;
	}

}
