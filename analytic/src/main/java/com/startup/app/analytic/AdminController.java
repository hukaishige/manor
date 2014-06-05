package com.startup.app.analytic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.tomcat.jni.File;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.codehaus.jackson.map.util.JSONPObject;

import com.startup.app.analytic.message.AccessToken;
import com.startup.app.analytic.message.Menu;
import com.startup.app.analytic.message.MenuItem;
import com.startup.app.analytic.message.MessageType;
import com.startup.app.analytic.message.to.PostTextMessage;
import com.startup.app.analytic.message.to.TextMessageTo;
import com.startup.app.analytic.utils.MessageGenerater;
import com.startup.app.analytic.utils.Util;

@RestController
@RequestMapping("/admin")
public class AdminController {
	private AccessToken token;

	@RequestMapping(value = "accessToken", method = RequestMethod.GET)
	public String requireAccessToken(@RequestParam Map<String, Object> allParams) {
		token = Util.requireAccessToken();

		return token.getAccess_token();
	}

	@RequestMapping(value = "upload", method = RequestMethod.POST)
	public String upload(@RequestParam("media") CommonsMultipartFile media) {
		if (token == null) {
			token = Util.requireAccessToken();
		}

		FileItem item = media.getFileItem();
		try {
			InputStream stream = item.getInputStream();
			StringBuilder url = new StringBuilder(Constants.POST_UPLOAD_URL);
			url.append("?access_token=" + token.getAccess_token());
			url.append("&type=image");
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(url.toString());

			InputStreamEntity mutipart = new InputStreamEntity(stream, -1);
			mutipart.setContentType("image/jpeg");
			mutipart.setContentEncoding("utf-8");
			mutipart.setChunked(true);
			post.setEntity(mutipart);

			HttpResponse response = client.execute(post);

			HttpEntity entity = response.getEntity();

			String body = EntityUtils.toString(entity, Consts.UTF_8);
			System.out.println(body);
			return body;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return token.getAccess_token();
	}

	@RequestMapping(value = "menu/create", method = RequestMethod.GET)
	public String createMenu() {
		if (token == null) {
			token = Util.requireAccessToken();
		}

		StringBuilder url = new StringBuilder(Constants.POST_MENU_URL);
		url.append("?access_token=" + token.getAccess_token());
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url.toString());

		MenuItem firstItem = new MenuItem();
		firstItem.setKey("V1001_TODAY_MUSIC");
		firstItem.setName("Today");
		firstItem.setType("click");

		MenuItem secondItem = new MenuItem();
		secondItem.setKey("V1001_TODAY_SINGER");
		secondItem.setName("Profiles");
		secondItem.setType("click");

		MenuItem thirdItem = new MenuItem();
		thirdItem.setUrl("http://www.soso.com/");
		thirdItem.setName("Search");
		thirdItem.setType("view");

		MenuItem fourthItem = new MenuItem();
		fourthItem.setUrl("http://v.qq.com/");
		fourthItem.setName("Video");
		fourthItem.setType("view");

		MenuItem fifthItem = new MenuItem();
		fifthItem.setKey("V1001_GOOD");
		fifthItem.setName("Like");
		fifthItem.setType("click");

		List<MenuItem> subItems = new ArrayList<MenuItem>();
		subItems.add(thirdItem);
		subItems.add(fourthItem);
		subItems.add(fifthItem);

		MenuItem sub = new MenuItem();
		sub.setName("Menu");
		sub.setSub_button(subItems);

		Menu menu = new Menu();
		List<MenuItem> items = new ArrayList<MenuItem>();
		items.add(firstItem);
		items.add(secondItem);
		items.add(sub);

		menu.setButton(items);

		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		try {
			String menuString = mapper.writeValueAsString(menu);
			post.setEntity(new StringEntity(menuString, Consts.UTF_8));

			HttpResponse response = client.execute(post);

			HttpEntity entity = response.getEntity();

			String body = EntityUtils.toString(entity, Consts.UTF_8);
			System.out.println(body);
			return body;

		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "";

	}

	@RequestMapping(value = "text/send", method = RequestMethod.GET)
	public String sendTextMessage() {
		if (token == null) {
			token = Util.requireAccessToken();
		}

		StringBuilder url = new StringBuilder(Constants.POST_MESSAGE_URL);
		url.append("?access_token=" + token.getAccess_token());
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url.toString());
		for (String openId : Gather.openIds) {
			PostTextMessage msg = new PostTextMessage();
			msg.setToUser(openId);
			msg.setMsgType(MessageType.MSG_TYPE_TEXT);
			PostTextMessage.Text text = new PostTextMessage.Text();
			text.setContent("很高兴为您服雾");
			msg.setText(text);

			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(Inclusion.NON_NULL);
			try {
				String msgString = mapper.writeValueAsString(msg);
				post.setEntity(new StringEntity(msgString, Consts.UTF_8));

				HttpResponse response = client.execute(post);

				HttpEntity entity = response.getEntity();

				String body = EntityUtils.toString(entity, Consts.UTF_8);
				System.out.println(body);

			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return "ok";
	}
	
	@RequestMapping(value = "article/send", method = RequestMethod.GET)
	public String sendArfticleMessage() {
		if (token == null) {
			token = Util.requireAccessToken();
		}

		StringBuilder url = new StringBuilder(Constants.POST_MESSAGE_URL);
		url.append("?access_token=" + token.getAccess_token());
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url.toString());
		for (String openId : Gather.openIds) {
			try {
				String msgString = Util.generateArticle(openId);
				post.setEntity(new StringEntity(msgString, Consts.UTF_8));

				HttpResponse response = client.execute(post);

				HttpEntity entity = response.getEntity();

				String body = EntityUtils.toString(entity, Consts.UTF_8);
				System.out.println(body);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "ok";
	}
}
