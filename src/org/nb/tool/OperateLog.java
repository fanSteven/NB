package org.nb.tool;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * @author steven
 * @date 2014-03-12
 */
@XmlRootElement
public class OperateLog {
	int id = 1;
	String log = "operate success";

	public OperateLog() {
	}

	public OperateLog(int _id, String _log) {
		id = _id;
		log = _log;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}
}