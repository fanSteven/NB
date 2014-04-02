package org.nb.resource.register;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.apache.log4j.Logger;
import org.nb.mybatis.model.INB_User;
import org.nb.mybatis.model.NB_User;
import org.nb.reshelper.ResHelper;
import org.nb.tool.Constant;
import org.nb.tool.OperateLog;

@Path("register/email/active")
public class Active extends ResHelper {
	private static final Logger logger = Logger.getLogger(Active.class);

	private static INB_User inb_User = null;
	static {
		inb_User = session.getMapper(INB_User.class);
	}

	@GET
	public OperateLog active(@QueryParam("u") String u,
			@QueryParam("p") String p) {
		OperateLog operateLog = new OperateLog();
		try {
			String userName = u;
			String encryPass = p;
			Map<String, String> params = new HashMap<String, String>();
			params.put("email", userName);
			params.put("password", encryPass);
			NB_User nb_User = inb_User.matchUserFromActivity(params);
			if (nb_User == null) {
				operateLog.setId(Constant.ERRORCODE);
				operateLog.setLog("验证失败");
			} else if (new Date().compareTo(nb_User.getRegistertime()) > 24 * 60 * 60) {
				operateLog.setId(Constant.ERRORCODE);
				operateLog.setLog("已经过期");
			}
		} catch (Exception ex) {
			logger.error("activity user error", ex);
			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("服务器端异常");
		}

		return operateLog;
	}

}
