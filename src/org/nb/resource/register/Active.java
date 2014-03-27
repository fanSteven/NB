package org.nb.resource.register;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import org.nb.tool.OperateLog;

@Path("register/email/active")
public class Active {
	@GET
	public OperateLog active(@QueryParam("u") String u,
			@QueryParam("p") String p) {
		OperateLog operateLog = new OperateLog();
		
		return operateLog;
	}

}
