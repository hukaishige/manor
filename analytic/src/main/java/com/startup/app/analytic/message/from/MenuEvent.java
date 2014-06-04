package com.startup.app.analytic.message.from;

import java.util.Map;

import com.startup.app.analytic.message.Event;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class MenuEvent extends Event {
    public static final String EVENT_KEY = "EventKey";
	
	@XStreamAlias(EVENT_KEY)
	private String eventKey;

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	
	@Override
	public void deserialize(Map<String, String> map) {
		super.deserialize(map);
		
		eventKey = map.get(EVENT_KEY);
	}
	
}
