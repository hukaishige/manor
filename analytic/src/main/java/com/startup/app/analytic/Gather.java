package com.startup.app.analytic;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.startup.app.analytic.message.BaseMessage;
import com.startup.app.analytic.message.Event;
import com.startup.app.analytic.message.EventType;
import com.startup.app.analytic.message.MessageType;
import com.startup.app.analytic.message.from.LinkMessageFrom;
import com.startup.app.analytic.message.from.MenuEvent;
import com.startup.app.analytic.message.from.PictureMessageFrom;
import com.startup.app.analytic.message.from.SubscribeMessage;
import com.startup.app.analytic.message.from.VideoMessageFrom;
import com.startup.app.analytic.message.from.VoiceMessageFrom;
import com.startup.app.analytic.message.to.ImageAndTextMessageTo;
import com.startup.app.analytic.message.to.PictureMessageTo;
import com.startup.app.analytic.message.to.TextMessageTo;
import com.startup.app.analytic.message.to.VideoMessageTo;
import com.startup.app.analytic.message.to.VoiceMessageTo;
import com.startup.app.analytic.utils.MessageGenerater;
import com.startup.app.analytic.utils.MessageParser;

@RestController
@RequestMapping("/service")
public class Gather {
	
	   public static List<String> openIds = new ArrayList<String>();

		@RequestMapping(value="verify",method = RequestMethod.GET)
		public String  hello(@RequestParam Map<String, Object> allParams) {  
            HashMap<String, String> map = new HashMap<String,String>();
//            String signature = allParams.get("signature");
//            String timestamp = allParams.get("timestamp");
//            String nonce = allParams.get("nonce");
            String echostr = (String) allParams.get("echostr");
			return echostr;

		}
		
		@RequestMapping(value="verify",method = RequestMethod.POST)
		public void processMessage(HttpServletRequest request, HttpServletResponse response, @RequestParam Map<String, Object> allParams) {  
 
			try {
				InputStream msgStream  = request.getInputStream();
				BaseMessage msg = null;
				msg = MessageParser.parseMsg(msgStream);
				System.out.println(MessageGenerater.generate(msg));
				
				if(!openIds.contains(msg.getFromUserName())){
					openIds.add(msg.getFromUserName());
				}
				
				String responseXml = "";
				Date now = new Date();
				
				if(msg.getMsgType().equals(MessageType.MSG_TYPE_TEXT)){
				    TextMessageTo responseMsg = new TextMessageTo();
				    
				    responseMsg.setToUserName(msg.getFromUserName());
				    responseMsg.setFromUserName(msg.getToUserName());
				    responseMsg.setCreateTime(now.getTime());
				    responseMsg.setMsgType(msg.getMsgType());
				    responseMsg.setContent("hi");
				    responseXml = MessageGenerater.generate(responseMsg);
					System.out.println("response: "+responseMsg.getContent());
				}else if(msg.getMsgType().equals(MessageType.MSG_TYPE_PICTURE)){
					PictureMessageTo responseMsg = new PictureMessageTo();
				    responseMsg.setToUserName(msg.getFromUserName());
				    responseMsg.setFromUserName(msg.getToUserName());
				    responseMsg.setCreateTime(now.getTime());
				    responseMsg.setMsgType(msg.getMsgType());
				    PictureMessageTo.Image image = new PictureMessageTo.Image();
				    image.setMediaId((((PictureMessageFrom)msg).getMediaId()));
				    responseMsg.setImage(image);
				    responseXml = MessageGenerater.generate(responseMsg);
				}else if(msg.getMsgType().equals(MessageType.MSG_TYPE_VOICE)){
					VoiceMessageTo responseMsg = new VoiceMessageTo();
				    responseMsg.setToUserName(msg.getFromUserName());
				    responseMsg.setFromUserName(msg.getToUserName());
				    responseMsg.setCreateTime(now.getTime());
				    responseMsg.setMsgType(msg.getMsgType());
				    VoiceMessageTo.Voice voice = new VoiceMessageTo.Voice();
				    voice.setMediaId(((VoiceMessageFrom)msg).getMediaId());
				    responseMsg.setVoice(voice);
				    responseXml = MessageGenerater.generate(responseMsg);
				}else if(msg.getMsgType().equals(MessageType.MSG_TYPE_VIDEO)){
					VideoMessageTo responseMsg = new VideoMessageTo();
				    responseMsg.setToUserName(msg.getFromUserName());
				    responseMsg.setFromUserName(msg.getToUserName());
				    responseMsg.setCreateTime(now.getTime());
				    responseMsg.setMsgType(msg.getMsgType());
				    VideoMessageTo.Video video = new VideoMessageTo.Video();
				    video.setTitle("Video");
				    video.setDescription("amazing");
				    video.setMediaId(((VideoMessageFrom)msg).getMediaId());
				    responseMsg.setVideo(video);
				    responseXml = MessageGenerater.generate(responseMsg);
				}else if(msg.getMsgType().equals(MessageType.MSG_TYPE_LOCATION)){
					TextMessageTo responseMsg = new TextMessageTo();
				    responseMsg.setToUserName(msg.getFromUserName());
				    responseMsg.setFromUserName(msg.getToUserName());
				    responseMsg.setCreateTime(now.getTime());
				    responseMsg.setMsgType(MessageType.MSG_TYPE_TEXT);
				    responseMsg.setContent("SH in China");
				    responseXml = MessageGenerater.generate(responseMsg);
				}else if(msg.getMsgType().equals(MessageType.MSG_TYPE_LINK)){
					TextMessageTo responseMsg = new TextMessageTo();
				    responseMsg.setToUserName(msg.getFromUserName());
				    responseMsg.setFromUserName(msg.getToUserName());
				    responseMsg.setCreateTime(now.getTime());
				    responseMsg.setMsgType(MessageType.MSG_TYPE_TEXT);
				    responseMsg.setContent(((LinkMessageFrom)msg).getUrl());
				    responseXml = MessageGenerater.generate(responseMsg);
					System.out.println("response: "+responseMsg.getContent());
				}else if(msg.getMsgType().equals(MessageType.MSG_TYPE_EVENT)){
					String eventType = ((Event)msg).getEvent();
					if(eventType.equals(EventType.EVENT_TYPE_SUBSCRIBE)){
						ImageAndTextMessageTo responseMsg = new ImageAndTextMessageTo();
						List<ImageAndTextMessageTo.Article> articles = new ArrayList<ImageAndTextMessageTo.Article>();
						ImageAndTextMessageTo.Article article = new ImageAndTextMessageTo.Article();
						article.setTitle(new String("百货聘长腿男模帮女顾客试鞋".getBytes("UTF-8")));
						article.setDescription("first description");
						article.setPicUrl("http://r3.sinaimg.cn/2/2014/0601/e2/a/06484615/original.jpg");
						article.setUrl("http://www.sina.cn/?vt=4");
						articles.add(article);
						
						article = new ImageAndTextMessageTo.Article();
						article.setTitle("深港澳车展美车模");
						article.setDescription("beautiful girl");
						article.setPicUrl("http://u1.sinaimg.cn/upload/2014/0530/17/549a9efc.jpg");
						article.setUrl("http://www.sina.cn/?vt=4");
						articles.add(article);
						
						responseMsg.setToUserName(msg.getFromUserName());
						responseMsg.setFromUserName(msg.getToUserName());
					    responseMsg.setCreateTime(now.getTime());
					    responseMsg.setMsgType(MessageType.MSG_TYPE_NEWS);
					    responseMsg.setArticleCount(articles.size());
					    responseMsg.setArticles(articles);
					    responseXml = MessageGenerater.generate(responseMsg);
					}else if(eventType.equals(EventType.EVENT_TYPE_MENU_CLICK)){
						
					}else if(eventType.equals(EventType.EVENT_TYPE_MENU_VIEW)){
						
					}
				}
				
				PrintWriter out = null;
				out = response.getWriter();
				out.print(responseXml);
				System.out.println(responseXml);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		@RequestMapping(value="receive",method = RequestMethod.GET)
		public String receive(@RequestParam Map<Object, Object> allParams) {  
			String mapAsJson;
			try {
				mapAsJson = new ObjectMapper().writeValueAsString(allParams);
				System.out.println(mapAsJson);
			} catch (JsonGenerationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			return "success";

		}
		
	}