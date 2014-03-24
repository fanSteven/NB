package org.nb.reshelper;

import org.nb.mybatis.model.NB_User;
import org.nb.resource.BaseResource;
import org.nb.resource.user.UnLegalUser;

public class ResHelper extends BaseResource {

	protected UnLegalUser checkUser(NB_User user) {
		// if (user.getEmail().trim().equals("")) {
		// return UnLegalUser.noEmail;
		// } else
		if (user.getPassword().trim().equals("")) {
			return UnLegalUser.noPassword;
		} else if (isExitEmail(user.getEmail())) {
			return UnLegalUser.exitEmail;
		} else if (isExitUserName(user.getName())) {
			return UnLegalUser.exitUserName;
		}
		return UnLegalUser.noerror;
	}

	private boolean isExitEmail(String email) {
		return false;
	}

	private boolean isExitUserName(String userName) {
		return false;
	}
}
