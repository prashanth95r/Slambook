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

public class Registration extends Activity{
	EditText name,username,password,confirm_password,phone1;
	Button submit;
	String user,pass,cpass,nam,result,phone;
	ConnectivityManager cm; 
	NetworkInfo activenet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.ablayout);
		setContentView(R.layout.register);
		
		name=(EditText)findViewById(R.id.name);
		phone1=(EditText)findViewById(R.id.phone);
		username=(EditText)findViewById(R.id.uname);
		password=(EditText)findViewById(R.id.pass);
		confirm_password=(EditText)findViewById(R.id.cpass);
		submit=(Button)findViewById(R.id.signup);
		
		StrictMode.ThreadPolicy policy = new
				StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
				StrictMode.setThreadPolicy(policy);
		
		cm = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		activenet = cm.getActiveNetworkInfo();
		
		
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				user=username.getText().toString();
				phone=phone1.getText().toString();
				nam=name.getText().toString();
				pass=password.getText().toString();
				cpass=confirm_password.getText().toString();
				
				if (activenet != null
						&& activenet.isConnectedOrConnecting()) {
				
				if(!nam.isEmpty() || !user.isEmpty() || !phone.isEmpty() || !pass.isEmpty() || !cpass.isEmpty() )
				{
					if(pass.equals(cpass))
					{
						List<BasicNameValuePair> params_sub = new ArrayList<BasicNameValuePair>();
						params_sub.add(new BasicNameValuePair("tag", "registration"));
						params_sub.add(new BasicNameValuePair("name", nam));
						params_sub.add(new BasicNameValuePair("phone", phone));
						params_sub.add(new BasicNameValuePair("username", user));
						params_sub.add(new BasicNameValuePair("password", pass));

						JSON_Data json = new JSON_Data();
						JSONObject jobj = json.getjson(params_sub);
						try
						{
						result=jobj.getString("success");
						Log.d("result",result);
						
						if(result.equals("true"))
							Toast.makeText(getApplicationContext(), "Registration Successful", Toast.LENGTH_SHORT).show();
						else
							Toast.makeText(getApplicationContext(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();
						}
						catch(Exception e)
						{
							Log.d("registration","error");
						}
						
					}
					else
						Toast.makeText(getApplicationContext(), "Passwords did not match", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(getApplicationContext(), "Please enter all the fields", Toast.LENGTH_SHORT).show();
			}
				else
					Toast.makeText(getApplicationContext(), "No Network Connection", Toast.LENGTH_SHORT).show();
			}
		});
		
		
		
	}

}
