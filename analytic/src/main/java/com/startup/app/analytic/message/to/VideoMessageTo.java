package com.startup.app.analytic.message.to;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class VideoMessageTo extends BaseMessage {
	@XStreamAlias("Video")
    private Video video;
	
	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}
	
	public static class Video{
		@XStreamAlias("MediaId")
	    private String mediaId;
		
		@XStreamAlias("Title")
		private String title;
		
		@XStreamAlias("Description")
		private String description;

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

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
		
		
	}
	
}



