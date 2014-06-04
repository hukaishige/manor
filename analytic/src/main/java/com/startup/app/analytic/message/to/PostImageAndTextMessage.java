package com.startup.app.analytic.message.to;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.startup.app.analytic.message.PostMessage;

public class PostImageAndTextMessage extends PostMessage {
	
	private News news;
	
	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public static class News{
		private List<Article> articles;

		public List<Article> getArticles() {
			return articles;
		}

		public void setArticles(List<Article> articles) {
			this.articles = articles;
		}
		
		
	}

	public static class Article{
		private String title;
		
		private String description;
		
		@JsonProperty("picurl")
	    private String picUrl;
		
		private String url;

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

		public String getPicUrl() {
			return picUrl;
		}

		public void setPicUrl(String picUrl) {
			this.picUrl = picUrl;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
		
		
	}
}
