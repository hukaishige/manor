package com.startup.app.analytic;

import java.io.InputStream;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.startup.app.analytic.message.TextMessage;

public class MessageParser{
	public static TextMessage parseMsg(InputStream stream) throws Exception{
		TextMessage msg = new TextMessage();
		SAXReader reader = new SAXReader();
		Document document = reader.read(stream);
		Element root = document.getRootElement();
		
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();

		for (Element e : elementList){
			String name = e.getName();
		    String text = e.getText();
			if("ToUserName".equals(name)){  
                msg.setToUserName(text); 
            }else if("FromUserName".equals(name)){  
                msg.setFromUserName(text);
            }else if("CreateTime".equals(name)){  
                msg.setCreateTime(text);
            }else if("MsgType".equals(name)){  
                msg.setMsgType(text);
            }else if("Content".equals(name)){  
                msg.setContent(text);
            }else if("MsgId".equals(name)){  
                msg.setMsgId(text);
            }
	    }

		stream.close();
		stream = null;
        
        return msg;
	}
}
