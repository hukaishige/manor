package com.startup.app.analytic;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class Gather {

		@RequestMapping(value="hello",method = RequestMethod.GET)
		public Map hello(@RequestParam Map<Object, Object> allParams) {  
            HashMap<String, String> map = new HashMap<String,String>();
            map.put("msg", "Hello");
			return map;

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