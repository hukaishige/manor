package com.startup.app.analytic.message.to;

import com.startup.app.analytic.message.PostMessage;

import org.codehaus.jackson.annotate.JsonProperty;

public class PostVideoMessage extends PostMessage{
	private Video video;

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public static class Video{
		@JsonProperty("media_id")
		private String mediaId;
		
		private String title;
		
		private String description;

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}

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
		
		
		
	}
}
