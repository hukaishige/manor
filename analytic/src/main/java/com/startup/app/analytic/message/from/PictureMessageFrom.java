package com.startup.app.analytic.message.from;

import java.util.Map;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class PictureMessageFrom extends BaseMessage {
	public final static String MEDIA_ID ="MediaId";
	public final static String PIC_URL ="PicUrl";
	
	@XStreamAlias(MEDIA_ID)
    private String mediaId;
	
	@XStreamAlias(PIC_URL)
    private String picUrl;

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
    
	@Override
	public void deserialize(Map<String, String> map) {
		super.deserialize(map);
		
		mediaId = map.get(MEDIA_ID);
		picUrl = map.get(PIC_URL);
	}
}
