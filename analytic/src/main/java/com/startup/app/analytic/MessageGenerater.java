package com.startup.app.analytic;

import java.io.Writer;

import com.startup.app.analytic.message.BaseMessage;
import com.startup.app.analytic.message.to.ImageAndTextMessageTo;
import com.startup.app.analytic.message.to.TextMessageTo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

public class MessageGenerater {

	private static XStream xstream = new XStream(new XppDriver(){

		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out){
				boolean cdata = true;
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
				@Override
				public void startNode(String name) {
					super.startNode(name);
					if(name.equals("ArticleCount")||name.equals("CreateTime")){
						cdata = false;
					}else{
						cdata = true;
					}
				}
				
				
			};
		}

		
		
	});
	
	
	public static String generate(BaseMessage message){
		xstream.processAnnotations(message.getClass());
		return xstream.toXML(message);
	}
	
	private static String toUpperCaseFirstOne(String s)
    {
        if(Character.isUpperCase(s.charAt(0))){
            return s;
        }
        else{
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }
}
