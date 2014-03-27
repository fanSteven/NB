package org.nb.resource.user;

import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.nb.Encryp.EncrypSHA;
import org.nb.mail.Mail;
import org.nb.mybatis.model.INB_User;
import org.nb.mybatis.model.NB_User;
import org.nb.reshelper.ResHelper;
import org.nb.tool.Constant;
import org.nb.tool.OperateLog;

@Path("/users")
public class UsersResource extends ResHelper {
	public static Logger logger = Logger.getLogger(UsersResource.class);

	private static INB_User inb_User = null;
	static {
		inb_User = session.getMapper(INB_User.class);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog register(NB_User user) {
		OperateLog operate = new OperateLog();
		try {
			UnLegalUser unLegalUser = checkUser(user);
			if (unLegalUser.equals(UnLegalUser.noerror)) {

				user.setStatus(UserStatus.register.getIndex());
				user.setRegistertime(new Date());
				user.setPassword(new String(new EncrypSHA().encrypt(user
						.getPassword())));
				Mail.sendMail(new UserActivityInfo(user.getEmail(), user
						.getPassword()));
				int result = inb_User.register(user);
				operate.setId(result);
			} else {
				operate.setId(Constant.ERRORCODE);
				operate.setLog(unLegalUser.getInfo());
			}
			session.commit();
		} catch (Exception ex) {
			session.rollback();
			logger.error("register user error", ex);
			operate.setLog("register user error");
		}
		return operate;
	}
}
