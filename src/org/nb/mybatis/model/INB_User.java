package org.nb.mybatis.model;

import java.util.Map;

public interface INB_User {
	public NB_User findUserById(int id);

	public int register(NB_User user);

	public NB_User findUserByName(NB_User user);

	public NB_User matchUserFromActivity(Map<String, String> params);

	public int updateUserInfo(int user_id, NB_User nb_User);

	public int deleteUserStatus(Map<String, Integer> params);
}
