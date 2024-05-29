package com.temxa.medas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SorguYap {
	
	static HttpClient httpclient = new DefaultHttpClient();
	static HttpPost httppost;
	static HttpGet httpget;
	String str;
	Element tesi;
	Iterator<Element> ite;
    
	
	Bitmap Resim(String url) throws IOException {
        HttpUriRequest request = new HttpGet(url.toString());
        httpclient = new DefaultHttpClient();
        HttpResponse response = httpclient.execute(request);
 
        StatusLine statusLine = response.getStatusLine();
        int statusCode = statusLine.getStatusCode();
        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            byte[] bytes = EntityUtils.toByteArray(entity);
 
            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0,
                    bytes.length);
            return bitmap;
        } else {
            throw new IOException("Download failed, HTTP response code "
                    + statusCode + " - " + statusLine.getReasonPhrase());
        }
    }
	
    public String sorgulamayap(String tesisatno, String resim) {
        

         httppost = new HttpPost("http://online.meramedas.com.tr/cgi-bin/cari.cgi");

        try {
        	
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("tesisat_no", tesisatno));
            nameValuePairs.add(new BasicNameValuePair("code", resim));
            nameValuePairs.add(new BasicNameValuePair("", "Gönder"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            HttpResponse response = httpclient.execute(httppost);
            
            str = inputStreamToString(response.getEntity().getContent()).toString();

        } catch (ClientProtocolException e) {
        	e.printStackTrace();
        } catch (IOException e) {
        	e.printStackTrace();
        }
		
		return str;
    } 
  
    private StringBuilder inputStreamToString(InputStream is) {
    	String line = "";
    	StringBuilder total = new StringBuilder();

    	BufferedReader rd = new BufferedReader(new InputStreamReader(is));

    	try {
			while ((line = rd.readLine()) != null) { 
				total.append(line); 
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

    	return total;
    }
    
	}

