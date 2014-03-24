package org.nb.resource.nb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.nb.mybatis.model.INB_Entity;
import org.nb.mybatis.model.NB_Entity;
import org.nb.reshelper.ResHelper;
import org.nb.tool.Constant;
import org.nb.tool.OperateLog;

@Path("/nbs")
public class NBSResource extends ResHelper {
	private static Logger logger = Logger.getLogger(NBResource.class);
	private static INB_Entity inb_Entity = null;
	static {
		inb_Entity = session.getMapper(INB_Entity.class);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog release(NB_Entity nb_Entity) {
		OperateLog operateLog = new OperateLog();
		try {
			operateLog.setId(inb_Entity.release(nb_Entity));
			session.commit();
		} catch (Exception ex) {
			session.rollback();
			logger.error("release nb_entity error", ex);
			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("release nb_entity error");
		} finally {

		}
		return operateLog;
	}

	@Path("/user_id/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<NB_Entity> get_nbs_by_userid(@PathParam("user_id") int user_id,
			@QueryParam("page_size") @DefaultValue("20") int page_size,
			@QueryParam("page_index") @DefaultValue("1") int page_index) {
		List<NB_Entity> nb_Entities = new ArrayList<NB_Entity>();
		try {
			int begin = (page_index - 1) * page_size;
			int end = begin + page_size;
			
			
			
			Map<String, Integer> params = new HashMap<String, Integer>();
			params.put("userId", user_id);
			params.put("start", begin);
			params.put("end", end);

			nb_Entities = inb_Entity.getUserNBS(params);
		} catch (Exception ex) {
			logger.error("get user nbs error", ex);
		}
		return nb_Entities;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<NB_Entity> get_nbs(
			@QueryParam("page_size") @DefaultValue("20") int page_size,
			@QueryParam("page_index") @DefaultValue("1") int page_index) {
		List<NB_Entity> nb_Entities = new ArrayList<NB_Entity>();
		try {
			int begin = (page_index - 1) * page_size;
			int end = begin + page_size;

			Map<String, Integer> params = new HashMap<String, Integer>();

			params.put("start", begin);
			params.put("end", end);

			nb_Entities = inb_Entity.getNBS(params);
		} catch (Exception ex) {
			logger.error("get user nbs error", ex);
		}
		return nb_Entities;
	}
}
