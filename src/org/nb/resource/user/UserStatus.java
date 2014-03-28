package org.nb.resource.user;

/**
 * 
 * @author Think 1:注册 2:没注册 3:被封号
 */
public enum UserStatus {
	noactivity("未激活", 0), activitied("已激活", 1), unregister("注销", 2), seal("封号",
			3), delete("删除", 4);

	private String status;
	private int index;

	private UserStatus(String status, int index) {
		this.status = status;
		this.index = index;
	}

	public static String getStatus(int index) {
		for (UserStatus status : UserStatus.values()) {
			if (status.getIndex() == index) {
				return status.getStatus();
			}
		}
		return null;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

}
