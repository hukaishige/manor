package com.startup.app.analytic;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
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

import com.startup.app.analytic.message.TextMessage;

@RestController
@RequestMapping("/service")
public class Gather {

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
 
			TextMessage msg = null;
			try {
				InputStream msgStream  = request.getInputStream();
				msg = MessageParser.parseMsg(msgStream);
				
				String responseXml = "";
				Date now = new Date();
				TextMessage responseMsg = new TextMessage();
				if(msg!=null){
					System.out.println("request: "+msg.getContent());
				    responseMsg.setToUserName(msg.getFromUserName());
				    responseMsg.setFromUserName(msg.getToUserName());
				    responseMsg.setCreateTime(now.getTime()+"");
				    responseMsg.setMsgType(msg.getMsgType());
				    responseMsg.setContent("Welcome");
				    responseXml = MessageGenerater.generate(responseMsg);
				}
				
				PrintWriter out = null;
				out = response.getWriter();
				out.print(responseXml);
				System.out.println("response: "+responseMsg.getContent());
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