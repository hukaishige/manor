package com.startup.app.analytic.message;

import java.util.Map;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public abstract class BaseMessage {
	public final static String TO_USER_NAME ="ToUserName";
	public final static String FROM_USER_NAME ="FromUserName";
	public final static String CREATE_TIME ="CreateTime";
	public final static String MSG_TYPE ="MsgType";
	public final static String MSG_ID ="MsgId";

	@XStreamAlias(TO_USER_NAME)
	private String toUserName;
	
	@XStreamAlias(FROM_USER_NAME)
	private String fromUserName;
	
	@XStreamAlias(CREATE_TIME)
	private long createTime;
	
	@XStreamAlias(MSG_TYPE)
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
	
	public long getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(long createTime) {
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
	
	public void deserialize(Map<String, String> map){
		toUserName=map.get(TO_USER_NAME);
        fromUserName=map.get(FROM_USER_NAME);
        createTime=Long.parseLong(map.get(CREATE_TIME));
        msgType=map.get(MSG_TYPE);
        msgId=map.get(MSG_ID);
	}
}
