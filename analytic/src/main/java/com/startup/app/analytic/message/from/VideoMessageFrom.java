package com.startup.app.analytic.message.from;

import java.util.Map;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class VideoMessageFrom extends BaseMessage {
	public final static String MEDIA_ID ="MediaId";
	public final static String THUMB_MEDIA_ID ="ThumbMediaId";
	
	@XStreamAlias(MEDIA_ID)
    private String mediaId;
	
	@XStreamAlias(THUMB_MEDIA_ID)
	private String thumbMediaId;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
	
	@Override
	public void deserialize(Map<String, String> map) {
		super.deserialize(map);
		
		mediaId = map.get(MEDIA_ID);
		thumbMediaId = map.get(THUMB_MEDIA_ID);
	}
}
