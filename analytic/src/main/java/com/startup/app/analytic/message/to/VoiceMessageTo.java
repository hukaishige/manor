package com.startup.app.analytic.message.to;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class VoiceMessageTo extends BaseMessage {
	@XStreamAlias("Voice")
    private Voice voice;

	public Voice getVoice() {
		return voice;
	}

	public void setVoice(Voice voice) {
		this.voice = voice;
	}

	public static class Voice{
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
