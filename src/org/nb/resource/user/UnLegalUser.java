package org.nb.resource.user;

/**
 * 
 * @author Think 1:邮箱已存在 2:用户名已经存在 3:没有邮箱信息 4:没有密码信息5:正确
 */
public enum UnLegalUser {
	exitEmail("邮箱已经存在", 1), exitUserName("用户名已经存在", 2), noEmail("邮箱为空", 3), noPassword(
			"密码为空", 4), noerror("录入正确", 5);

	private String info;
	private int index;

	UnLegalUser(String info, Integer index) {
		this.info = info;
		this.index = index;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
}
