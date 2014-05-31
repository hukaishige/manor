package com.startup.app.analytic.message;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class BaseMessage {

	@XStreamAlias("ToUserName")
	private String toUserName;
	
	@XStreamAlias("FromUserName")
	private String fromUserName;
	
	@XStreamAlias("CreateTime")
	private String createTime;
	
	@XStreamAlias("MsgType")
	private String msgType;
	
	@XStreamAlias("MsgId")
	private String msgId;
	
	public String getToUserName() {
		return toUserName;
	}
	
	public void setToUserName(String toUser) {
		this.toUserName = toUser;
	}
	
	public String getFromUserName() {
		return fromUserName;
	}
	
	public void setFromUserName(String fromUser) {
		this.fromUserName = fromUser;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getMsgType() {
		return msgType;
	}
	
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	
	public String getMsgId() {
		return msgId;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	
}
