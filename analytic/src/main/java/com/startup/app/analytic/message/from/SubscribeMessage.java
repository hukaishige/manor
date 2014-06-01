package com.startup.app.analytic.message.from;

import java.util.Map;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class SubscribeMessage extends BaseMessage{

	public static final String EVENT = "Event";
	
	@XStreamAlias(EVENT)
	private String event;

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	@Override
	public void deserialize(Map<String, String> map) {
		super.deserialize(map);
		
		event = map.get(EVENT);
	}
	
	
}
