package com.startup.app.analytic;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.startup.app.analytic.message.BaseMessage;
import com.startup.app.analytic.message.MessageType;
import com.startup.app.analytic.message.from.LinkMessageFrom;
import com.startup.app.analytic.message.from.LocationMessageFrom;
import com.startup.app.analytic.message.from.PictureMessageFrom;
import com.startup.app.analytic.message.from.SubscribeMessage;
import com.startup.app.analytic.message.from.TextMessageFrom;
import com.startup.app.analytic.message.from.VideoMessageFrom;
import com.startup.app.analytic.message.from.VoiceMessageFrom;

public class MessageParser{
	public static BaseMessage parseMsg(InputStream stream) throws Exception{
		SAXReader reader = new SAXReader();
		Document document = reader.read(stream);
		Element root = document.getRootElement();
		
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();
		
		Map<String, String> map = new HashMap<String, String>();

		for (Element e : elementList){
			String name = e.getName();
		    String text = e.getText();
		    map.put(name, text);
	    }
		
		stream.close();
		stream = null;
		
		String type = map.get(BaseMessage.MSG_TYPE);
		if(type.equals(MessageType.MSG_TYPE_TEXT)){
			TextMessageFrom msg = new TextMessageFrom();
			msg.deserialize(map);
			
	        return msg;
		}else if(type.equals(MessageType.MSG_TYPE_PICTURE)){
			PictureMessageFrom msg = new PictureMessageFrom();
			msg.deserialize(map);
			
	        return msg;
		}else if(type.equals(MessageType.MSG_TYPE_VOICE)){
			VoiceMessageFrom msg = new VoiceMessageFrom();
			msg.deserialize(map);
			
	        return msg;
		}else if(type.equals(MessageType.MSG_TYPE_VIDEO)){
			VideoMessageFrom msg = new VideoMessageFrom();
			msg.deserialize(map);
			
	        return msg;
		}else if(type.equals(MessageType.MSG_TYPE_LOCATION)){
			LocationMessageFrom msg = new LocationMessageFrom();
			msg.deserialize(map);
			
	        return msg;
		}else if(type.equals(MessageType.MSG_TYPE_LINK)){
			LinkMessageFrom msg = new LinkMessageFrom();
			msg.deserialize(map);
			
	        return msg;
		}else if(type.equals(MessageType.MSG_TYPE_EVENT)){
			SubscribeMessage msg = new SubscribeMessage();
			msg.deserialize(map);
			return msg;
		}
		
		return null;
	}
}
