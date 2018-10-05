package com.idc.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.idc.app.IDCApplicationInterface;
import com.idc.constants.IDCApplicationConstants;

public class IDCApplicationDInstaService implements IDCApplicationInterface {

	@Override
	public HttpPost createRequest(String URL) {
	
		HttpPost httpPost = new HttpPost(IDCApplicationConstants.DINSTA_URL);
		httpPost.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");

		List<NameValuePair> params = new ArrayList<NameValuePair>();
	    params.add(new BasicNameValuePair("url", URL));
	    try {
	    	httpPost.setEntity(new UrlEncodedFormEntity(params));
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    
	    return httpPost;
	}

	@Override
	public String responseParse(CloseableHttpResponse httpResponse) throws ParseException, IOException {
		
		String imageURL = null;
		HttpEntity responseEntity = httpResponse.getEntity();
    	if(responseEntity!=null) {
    	    String result = EntityUtils.toString(responseEntity);
    	    Document doc = Jsoup.parse(result);
    	    /*Elements links =  doc.getElementsByClass("img");
    	   
    	    for (Element link : links) {
    	    	 System.out.println(link);
    	    	 System.out.println(link.getElementsByAttribute("src"));
    	    	
    	    	}*/
    	    
    	    Element image = doc.select("img").first();
    	    imageURL = image.absUrl("src");
    	    
	}
    	return imageURL;
	}
}

