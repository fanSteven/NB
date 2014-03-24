package org.nb.mybatis.model;

import java.util.List;

public interface INB_Reply {

	public int reply(NB_Reply nb_Reply);

	public List<NB_Reply> get_user_reply(int user_id);

	public List<NB_Reply> get_nb_reply(int nb_id);

	public int deleteReply(int rep_id);
}
