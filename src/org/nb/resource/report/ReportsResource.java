package org.nb.resource.report;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.nb.mybatis.model.INB_Report;
import org.nb.mybatis.model.NB_Report;
import org.nb.reshelper.ResHelper;
import org.nb.tool.Constant;
import org.nb.tool.OperateLog;

@Path("/reports")
public class ReportsResource extends ResHelper {
	private static Logger logger = Logger.getLogger(ReportsResource.class);
	private static INB_Report inb_Report;

	static {
		inb_Report = session.getMapper(INB_Report.class);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog report(NB_Report nb_Report) {
		OperateLog operateLog = new OperateLog();
		try {
			inb_Report.report(nb_Report);
			session.commit();
		} catch (Exception ex) {
			logger.error(
					String.format("report error which nb id is {0}",
							nb_Report.getReportNbId()), ex);

			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("report error");
		}

		return operateLog;
	}
}
