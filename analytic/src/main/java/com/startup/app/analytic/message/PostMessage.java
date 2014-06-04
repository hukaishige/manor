package com.startup.app.analytic.message;

import org.codehaus.jackson.annotate.JsonProperty;

public class PostMessage {

	@JsonProperty("touser")
	private String toUser;
	
	@JsonProperty("msgtype")
	private String msgType;

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
}
