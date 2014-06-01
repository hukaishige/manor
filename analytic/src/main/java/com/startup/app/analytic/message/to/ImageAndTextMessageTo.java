package com.startup.app.analytic.message.to;

import java.util.List;

import com.startup.app.analytic.message.BaseMessage;
import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("xml")
public class ImageAndTextMessageTo extends BaseMessage {

	@XStreamAlias("ArticleCount")
	private long articleCount;
	
	@XStreamAlias("Articles")
	private List<Article> articles;
	
	public long getArticleCount() {
		return articleCount;
	}


	public void setArticleCount(long articleCount) {
		this.articleCount = articleCount;
	}


	public List<Article> getArticles() {
		return articles;
	}


	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	@XStreamAlias("item")
	public static class Article{
		@XStreamAlias("Title")
		private String title;
		
		@XStreamAlias("Description")
		private String description;
		
		@XStreamAlias("PicUrl")
	    private String picUrl;
		
		@XStreamAlias("Url")
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
