package org.nb.resource.nb;

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
import org.nb.mybatis.model.INB_Entity;
import org.nb.mybatis.model.NB_Entity;
import org.nb.reshelper.ResHelper;
import org.nb.tool.Constant;
import org.nb.tool.OperateLog;

@Path("/nb/{nb_id}")
public class NBResource extends ResHelper {
	private static Logger logger = Logger.getLogger(NBResource.class);
	private static INB_Entity inb_Entity = null;
	static {
		inb_Entity = session.getMapper(INB_Entity.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public NB_Entity get_nb_by_id(@PathParam("nb_id") int nb_id) {
		return null;
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog verify(@PathParam("nb_id") int nb_id,
			@PathParam("nb_status") int nb_status) {
		OperateLog operateLog = new OperateLog();
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("nbId", nb_id);
		params.put("nbStatus", nb_status);

		try {
			inb_Entity.verify(params);
			session.commit();
		} catch (Exception ex) {
			session.rollback();
			logger.error("verify nb_entity error", ex);
			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("verify nb_entity error");
		} finally {

		}

		return operateLog;
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog delete(@PathParam("nb_id") int nb_id) {
		OperateLog operateLog = new OperateLog();

		try {
			inb_Entity.delete(nb_id);
			session.commit();
		} catch (Exception ex) {
			session.rollback();
			logger.error("delete nb_entity error", ex);
			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("delete nb_entity error");
		} finally {

		}
		return operateLog;
	}

	@Path("/up")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog up(@PathParam("nb_id") int nb_id) {
		OperateLog operateLog = new OperateLog();
		try {
			int up_Num = inb_Entity.getUpNum(nb_id);
			inb_Entity.setUpNum(nb_id, up_Num + 1);
			session.commit();
			operateLog.setId(up_Num + 1);
		} catch (Exception ex) {
			logger.error("nb upnum operate error", ex);
			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("nb upnum operate error");
			session.rollback();
		}
		return operateLog;
	}

	@Path("/down")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog down(@PathParam("nb_id") int nb_id) {
		OperateLog operateLog = new OperateLog();
		try {
			int down_Num = inb_Entity.getUpNum(nb_id);
			inb_Entity.setDownNum(nb_id, down_Num + 1);
			session.commit();
			operateLog.setId(down_Num + 1);
		} catch (Exception ex) {
			logger.error("nb downnum operate error", ex);
			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("nb downnum operate error");
			session.rollback();
		}
		return operateLog;
	}

}
