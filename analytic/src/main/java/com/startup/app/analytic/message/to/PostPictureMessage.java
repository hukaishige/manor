package com.startup.app.analytic.message.to;

import org.codehaus.jackson.annotate.JsonProperty;

public class PostPictureMessage {
	private Image image;
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public static class Image{
		@JsonProperty("media_id")
		private String mediaId;

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
		
	}
}
