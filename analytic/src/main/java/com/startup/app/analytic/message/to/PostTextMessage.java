package com.startup.app.analytic.message.to;

import com.startup.app.analytic.message.PostMessage;

public class PostTextMessage extends PostMessage{

	private Text text;
	
	public Text getText() {
		return text;
	}

	public void setText(Text text) {
		this.text = text;
	}

	public static class Text{
		private String content;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}
		
		
	}
}
