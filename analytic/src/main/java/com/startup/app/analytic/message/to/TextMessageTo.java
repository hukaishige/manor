package com.startup.app.analytic.message.to;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class TextMessageTo extends BaseMessage{

	@XStreamAlias("Content")
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
