package com.slambook;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendRequest extends Activity {
	EditText phone;
	Button submit;
	String username,phoneno,result;
	ConnectivityManager cm; 
	NetworkInfo activenet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.ablayout);
		setContentView(R.layout.sendrequest);
		
		Bundle bundle=getIntent().getExtras();
		username=bundle.getString("username");
		
		phone=(EditText)findViewById(R.id.requestphone);
		submit=(Button)findViewById(R.id.requestsubmit);
		
		cm = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		activenet = cm.getActiveNetworkInfo();
		
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (activenet != null
						&& activenet.isConnectedOrConnecting()) {
					
					phoneno=phone.getText().toString();
					if(!phoneno.isEmpty())
					{
					
					List<BasicNameValuePair> params_sub = new ArrayList<BasicNameValuePair>();
					params_sub.add(new BasicNameValuePair("tag", "request"));
					params_sub.add(new BasicNameValuePair("username", username));
					params_sub.add(new BasicNameValuePair("phone", phoneno));
					
					JSON_Data json = new JSON_Data();
					JSONObject jobj = json.getjson(params_sub);
					try
					{
					result=jobj.getString("success");
					if(result.equals("true"))
						Toast.makeText(getApplicationContext(), "Request Sent", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(getApplicationContext(), "Error. Please Check Phone Number", Toast.LENGTH_SHORT).show();
					}
					catch(Exception e)
					{
						Log.d("send requests","error");
					}
				}
					else
						Toast.makeText(getApplicationContext(), "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
					
				}
				else
					Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}

}
