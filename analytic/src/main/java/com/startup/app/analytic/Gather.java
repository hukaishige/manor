package com.startup.app.analytic;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class Gather {

		@RequestMapping(value="hello",method = RequestMethod.GET,headers="Accept=application/json")
		public Map hello(@RequestParam Map<Object, Object> allParams) {  
            HashMap<String, String> map = new HashMap<String,String>();
            map.put("msg", "Hello");
			return map;

		}
		
	}