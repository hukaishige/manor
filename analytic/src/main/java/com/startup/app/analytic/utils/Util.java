package com.startup.app.analytic.utils;

import java.io.IOException;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.startup.app.analytic.Constants;
import com.startup.app.analytic.message.AccessToken;

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
}
