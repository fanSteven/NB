package org.nb.resource.user;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.nb.mybatis.model.INB_User;
import org.nb.mybatis.model.NB_User;
import org.nb.reshelper.ResHelper;
import org.nb.tool.Constant;
import org.nb.tool.OperateLog;

@Path("/users/{user_id}")
public class UserResource extends ResHelper {
	public static Logger logger = Logger.getLogger(UsersResource.class);

	private static INB_User inb_User = null;
	static {
		inb_User = session.getMapper(INB_User.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public NB_User getUserInfo(@PathParam("user_id") int user_id) {
		NB_User nb_User = null;
		try {
			nb_User = inb_User.findUserById(user_id);
		} catch (Exception ex) {
			session.rollback();
			logger.error("get user info by id error", ex);
		}
		return nb_User;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog updateUserInfo(@PathParam("user_id") int user_id,
			NB_User nb_User) {
		OperateLog operateLog = new OperateLog();
		try {
			inb_User.updateUserInfo(user_id, nb_User);
			session.commit();
		} catch (Exception ex) {
			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("update user info error");
			logger.error("update user info error", ex);
		}
		return operateLog;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog deleteUser(@PathParam("user_id") int user_id) {
		OperateLog operateLog = new OperateLog();
		try {
			Map<String, Integer> params = new HashMap<String, Integer>();
			params.put("user_id", user_id);
			params.put("user_status", UserStatus.delete.getIndex());
			inb_User.deleteUserStatus(params);
			session.commit();
		} catch (Exception ex) {
			logger.error("delete user error", ex);
			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("delete user error");
			session.rollback();
		}
		return operateLog;
	}
}
