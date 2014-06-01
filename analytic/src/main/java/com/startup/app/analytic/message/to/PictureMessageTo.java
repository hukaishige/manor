package com.startup.app.analytic.message.to;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class PictureMessageTo extends BaseMessage {
	@XStreamAlias("Image")
    private Image image;
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public static class Image{
		@XStreamAlias("MediaId")
	    private String mediaId;

		public String getMediaId() {
			return mediaId;
		}

		public void setMediaId(String mediaId) {
			this.mediaId = mediaId;
		}
	}
    
}
