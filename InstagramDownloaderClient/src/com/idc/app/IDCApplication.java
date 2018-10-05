package com.idc.app;

import java.io.IOException;
import java.util.logging.Logger;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.idc.app.service.IDCApplicationDInstaService;

public class IDCApplication {
	
	private static final Logger log = Logger.getLogger(IDCApplication.class.getName());
	
	public static void main(String[] args) {
		
		boolean imageParsed = false;
		
		IDCApplicationInterface idcApplicationService = new IDCApplicationDInstaService();
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost httpPost = idcApplicationService.createRequest(args[0]); 
		log.info("Request Object D_INSTA:"+httpPost);
	    
	    CloseableHttpResponse httpResponse = null;
	    try {
	    	httpResponse = httpClient.execute(httpPost);
	    	log.info("Response Object D_INSTA:"+httpResponse);
	    	String imageURL = idcApplicationService.responseParse(httpResponse);
	    	log.info("imageURL D_INSTA:"+imageURL);
	    	if(imageURL != null) {
	    		IDCApplicationInterface.saveImage(imageURL);
	    		imageParsed = true;
	    	}else {
	    		log.info("image not found with D_INSTA");
	    	}
	    }catch(ParseException e) {
	    	e.printStackTrace();
	    }catch(IOException e) {
	    	e.printStackTrace();
	    }
		
	    //sample snippet to add more services
	    /*if(!imageParsed) {
	    	log.info("switching to D_INSTA service");
	    	idcApplicationService = new IDCApplicationDInstaService();
	    	httpPost = idcApplicationService.createRequest(args[0]);
	    	try {
		    	httpResponse = httpClient.execute(httpPost);
		    	String imageURL = idcApplicationService.responseParse(httpResponse);
		    	if(imageURL != null) {
		    		IDCApplicationInterface.saveImage(imageURL);
		    		imageParsed = true;
		    	}else {
		    		log.info("image not found with D_INSTA");
		    	}
		    }catch(ParseException e) {
		    	e.printStackTrace();
		    }catch(IOException e) {
		    	e.printStackTrace();
		    }
	    	
	    }*/
		

	}

	
}
