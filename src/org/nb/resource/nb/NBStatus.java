package org.nb.resource.nb;

import org.nb.resource.user.UserStatus;

public enum NBStatus {

	release("发布", 1), pass("审核通过", 2), delete("删除", 3), report("被举报", 4);

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

	private String status;
	private int index;

	private NBStatus(String status, int index) {
		this.status = status;
		this.index = index;
	}
}
