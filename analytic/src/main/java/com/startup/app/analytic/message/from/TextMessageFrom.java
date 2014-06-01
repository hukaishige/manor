package com.startup.app.analytic.message.from;

import java.util.Map;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class TextMessageFrom extends BaseMessage{

	public final static String CONTENT ="Content";
	
	@XStreamAlias(CONTENT)
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public void deserialize(Map<String, String> map) {
		super.deserialize(map);
		
		content = map.get(CONTENT);
		System.out.println("request: "+content);
	}
	
	
}
