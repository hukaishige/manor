package com.startup.app.analytic.message.from;

import java.util.Map;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class LinkMessageFrom extends BaseMessage {
	public final static String TITLE ="Title";
	public final static String DESCRIPTION ="Description";
	public final static String URL ="Url";
	
	@XStreamAlias(TITLE)
	private String title;
	
	@XStreamAlias(DESCRIPTION)
	private String description;
	
	@XStreamAlias(URL)
	private String url;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public void deserialize(Map<String, String> map) {
		super.deserialize(map);
		
		title = map.get(TITLE);
		description = map.get(DESCRIPTION);
		url = map.get(URL);
	}
}
