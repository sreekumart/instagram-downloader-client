package com.idc.app;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;

public interface IDCApplicationInterface {

	public HttpPost createRequest(String URL);
	public String responseParse(CloseableHttpResponse httpResponse) throws ParseException, IOException;
	
	
	public static void saveImage(String imageURL) throws IOException {
		URL url = new URL(imageURL);
		String fileName = url.getFile();
		String destName = System.getProperty("user.home")+fileName.substring(fileName.lastIndexOf("/"));
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destName);
		byte[] b = new byte[2048];
		int length;
		while ((length = is.read(b)) != -1) {
		os.write(b, 0, length);
		}
		is.close();
		os.close();
		}
}
