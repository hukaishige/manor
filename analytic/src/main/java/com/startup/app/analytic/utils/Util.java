package com.startup.app.analytic.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import com.startup.app.analytic.Constants;
import com.startup.app.analytic.message.AccessToken;
import com.startup.app.analytic.message.MessageType;
import com.startup.app.analytic.message.to.ImageAndTextMessageTo;
import com.startup.app.analytic.message.to.PostImageAndTextMessage;

public class Util {
	public static AccessToken requireAccessToken(){
		StringBuilder url = new StringBuilder(Constants.GET_ACCESS_TOKEN_URL);
		url.append("?grant_type=client_credential");
		url.append("&appid="+Constants.APP_ID);
		url.append("&secret="+Constants.APP_SECRET);
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(url.toString());
		try {
			HttpResponse response = client.execute(get);
			HttpEntity entity = response.getEntity();  
			 
            String body = EntityUtils.toString(entity, Consts.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            AccessToken token = mapper.readValue(body, AccessToken.class);
            System.out.println(body);
            return token;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			client.getConnectionManager().shutdown();  
		}
		
		return null;
	}
	
	public static String generateArticle(String openId) throws Exception{
		PostImageAndTextMessage msg = new PostImageAndTextMessage();
		PostImageAndTextMessage.News news = new PostImageAndTextMessage.News();
		List<PostImageAndTextMessage.Article> articles = new ArrayList<PostImageAndTextMessage.Article>();
		PostImageAndTextMessage.Article article = new PostImageAndTextMessage.Article();
		article.setTitle(new String("百货聘长腿男模帮女顾客试鞋".getBytes(Consts.UTF_8)));
		article.setDescription("first description");
		article.setPicUrl("http://r3.sinaimg.cn/2/2014/0601/e2/a/06484615/original.jpg");
		article.setUrl("http://www.sina.cn/?vt=4");
		articles.add(article);
		
		article = new PostImageAndTextMessage.Article();
		article.setTitle("深港澳车展美车模");
		article.setDescription("beautiful girl");
		article.setPicUrl("http://u1.sinaimg.cn/upload/2014/0530/17/549a9efc.jpg");
		article.setUrl("http://www.sina.cn/?vt=4");
		articles.add(article);
		
		news.setArticles(articles);
		msg.setNews(news);
		msg.setMsgType(MessageType.MSG_TYPE_NEWS);
		msg.setToUser(openId);
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
	    String msgString = mapper.writeValueAsString(msg);
	    
	    return msgString;
	}
}
