package com.startup.app.analytic.message.to;

import org.codehaus.jackson.annotate.JsonProperty;

import com.startup.app.analytic.message.PostMessage;

public class PostMusicMessage extends PostMessage {

	private Music music;
	
	public Music getMusic() {
		return music;
	}

	public void setMusic(Music music) {
		this.music = music;
	}

	public static class Music{
		private String title;
		
		private String description;
		
		@JsonProperty("musicurl")
		private String musicUrl;
		
		@JsonProperty("hqmusicurl")
		private String hqMusicUrl;
		
		@JsonProperty("thumb_media_id")
		private String thumbMediaId;
		
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
		
		public String getMusicUrl() {
			return musicUrl;
		}
		
		public void setMusicUrl(String musicUrl) {
			this.musicUrl = musicUrl;
		}
		
		public String getHqMusicUrl() {
			return hqMusicUrl;
		}
		
		public void setHqMusicUrl(String hqMusicUrl) {
			this.hqMusicUrl = hqMusicUrl;
		}
		
		public String getThumbMediaId() {
			return thumbMediaId;
		}
		
		public void setThumbMediaId(String thumbMediaId) {
			this.thumbMediaId = thumbMediaId;
		}
		
		
	}
}
