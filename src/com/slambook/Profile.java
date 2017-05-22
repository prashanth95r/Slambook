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
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Profile extends Activity{
	EditText proname,dob,pno,email;
	Button submit;
	ConnectivityManager cm; 
	NetworkInfo activenet;
	String username,name,dateob,phone,mail,name1,dateob1,phone1,mail1,result;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.ablayout);
		setContentView(R.layout.profile);
		Bundle bundle=getIntent().getExtras();
		username=bundle.getString("username");
		
		StrictMode.ThreadPolicy policy = new
				StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
				StrictMode.setThreadPolicy(policy);
		
		cm = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		activenet = cm.getActiveNetworkInfo();
		
		proname=(EditText)findViewById(R.id.proname);
		dob=(EditText)findViewById(R.id.dob);
		pno=(EditText)findViewById(R.id.pno);
		email=(EditText)findViewById(R.id.email);
		submit=(Button)findViewById(R.id.submit);
		
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (activenet != null
						&& activenet.isConnectedOrConnecting()) {
					
					name1=proname.getText().toString();
					dateob1=dob.getText().toString();
					phone1=pno.getText().toString();
					mail1=email.getText().toString();
					
					if(!name1.isEmpty() && !dateob1.isEmpty() && !phone1.isEmpty() && !mail1.isEmpty())
					{
					List<BasicNameValuePair> params_sub = new ArrayList<BasicNameValuePair>();
					params_sub.add(new BasicNameValuePair("tag", "profile"));
					params_sub.add(new BasicNameValuePair("username", username));
					params_sub.add(new BasicNameValuePair("name",name1));
					params_sub.add(new BasicNameValuePair("dob",dateob1));
					params_sub.add(new BasicNameValuePair("mobile",phone1));
					params_sub.add(new BasicNameValuePair("email",mail1));
					
					JSON_Data json = new JSON_Data();
					JSONObject jobj = json.getjson(params_sub);
					try
					{
					result=jobj.getString("success");
					if(result.equals("true"))
					{
						Toast.makeText(getApplicationContext(), "Profile update successful",Toast.LENGTH_SHORT).show();
					}
					else
						Toast.makeText(getApplicationContext(), "Profile update unsuccessful",Toast.LENGTH_SHORT).show();
					}
					catch(Exception e)
					{
						Log.d("profile data update","error");
					}
				}
					else
						Toast.makeText(getApplicationContext(), "Please enter all the fields",Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(getApplicationContext(), "No Internet Connection!!",Toast.LENGTH_SHORT).show();
			}
		});
		
		
		if (activenet != null
				&& activenet.isConnectedOrConnecting()) {
			
			List<BasicNameValuePair> params_sub = new ArrayList<BasicNameValuePair>();
			params_sub.add(new BasicNameValuePair("tag", "getprofile"));
			params_sub.add(new BasicNameValuePair("username", username));
			
			JSON_Data json = new JSON_Data();
			JSONObject jobj = json.getjson(params_sub);
			try
			{
			name=jobj.getString("name");
			dateob=jobj.getString("dob");
			phone=jobj.getString("mobile");
			mail=jobj.getString("email");
			
			if(!name.equals("null") && !dateob.equals("null") && !phone.equals("null") && !mail.equals("null"))
				{
					proname.setText(name);
					dob.setText(dateob);
					pno.setText(phone);
					email.setText(mail);
				}
			
			}
			catch(Exception e)
			{
				Log.d("profile data","error");
			}
		}
		else
			Toast.makeText(getApplicationContext(), "No Internet Connection!!",Toast.LENGTH_SHORT).show();
	}
	

}
