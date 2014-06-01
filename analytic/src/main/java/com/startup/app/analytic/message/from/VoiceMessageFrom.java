package com.startup.app.analytic.message.from;

import java.util.Map;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class VoiceMessageFrom extends BaseMessage {
	public final static String MEDIA_ID ="MediaId";
	public final static String FORMAT ="Format";
	
	@XStreamAlias(MEDIA_ID)
    private String mediaId;
	
	@XStreamAlias(FORMAT)
    private String format;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
	@Override
	public void deserialize(Map<String, String> map) {
		super.deserialize(map);
		
		mediaId = map.get(MEDIA_ID);
		format = map.get(FORMAT);
	}
}
