package org.nb.resource.reply;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.nb.mybatis.model.INB_Reply;
import org.nb.mybatis.model.NB_Reply;
import org.nb.resource.BaseResource;
import org.nb.tool.Constant;
import org.nb.tool.OperateLog;

@Path("/replys")
public class ReplyResource extends BaseResource {
	private static Logger logger = Logger.getLogger(ReplyResource.class);
	private static INB_Reply inb_Reply;
	static {
		inb_Reply = session.getMapper(INB_Reply.class);
	}

	@Path("/rep_id/{rep_id}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog deleteRep(@PathParam("rep_id") int rep_id) {
		OperateLog operateLog = new OperateLog();
		try {
			inb_Reply.deleteReply(rep_id);
			session.commit();
		} catch (Exception ex) {
			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("delete reply error");
			logger.error("delete reply error", ex);
		}

		return operateLog;
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public OperateLog reply(NB_Reply nb_Reply) {
		OperateLog operateLog = new OperateLog();
		try {
			inb_Reply.reply(nb_Reply);
			session.commit();
		} catch (Exception ex) {
			operateLog.setId(Constant.ERRORCODE);
			operateLog.setLog("reply error");
			logger.error("reply error", ex);
		}
		return operateLog;
	}

	@Path("/user_id/{user_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<NB_Reply> get_User_Reply(@PathParam("user_id") int user_id) {
		List<NB_Reply> replies = new ArrayList<NB_Reply>();
		try {
			replies = inb_Reply.get_user_reply(user_id);
		} catch (Exception ex) {
			logger.error(String.format("get user {0} reply error", user_id), ex);
		}
		return replies;
	}

	@Path("/nb_id/{nb_id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<NB_Reply> get_NB_Reply(@PathParam("nb_id") int nb_id) {
		List<NB_Reply> replies = new ArrayList<NB_Reply>();
		try {
			replies = inb_Reply.get_nb_reply(nb_id);
		} catch (Exception ex) {
			logger.error(String.format("get nb {0} reply error", nb_id), ex);
		}
		return replies;
	}

}
