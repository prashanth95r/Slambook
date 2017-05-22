package com.slambook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSON_Data {
	
	
	
	
	InputStream is=null;
	String json=null;
	JSONObject jobj=null;
	String URL="http://www.mvsrnews.herobo.com/slambook/android.php";
	
	 
	 
	
	 
	 protected JSONObject getjson(List<BasicNameValuePair> params) {
	

    try{
   	 DefaultHttpClient httpclient=new DefaultHttpClient();
   	 HttpPost httppost = new HttpPost(URL);
   	 httppost.setEntity(new UrlEncodedFormEntity(params));
   
   	 HttpResponse httpresponse= httpclient.execute(httppost);
   
   	 HttpEntity httpentity=httpresponse.getEntity();
   	 is=httpentity.getContent();
   	 
    }
    catch (UnsupportedEncodingException e) {
           e.printStackTrace();
           Log.d("tag", "data not recieved");
       } catch (ClientProtocolException e) {
           e.printStackTrace();
           Log.d("tag", "data not recieved");
       } catch (IOException e) {
           e.printStackTrace();
           Log.d("tag", "data not recieved");
       }
    
try{
	
	BufferedReader br=new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);

   StringBuilder sb= new StringBuilder();
	String line="";
	while((line=br.readLine())!=null){
		
		sb.append(line + "\n");
		
	}
	is.close();
	json=sb.toString();
	
	
	Log.d("json",json);
} catch(Exception e){
	 
e.printStackTrace();
Log.d("tag", "problem occured during conversion");
	 
}
	try {
		
		jobj=new JSONObject(json);
		
	
	} catch (JSONException e) {
		
		e.printStackTrace();
		return null;

	}
	
	
	return jobj;
}





	

	
		
	}
	

