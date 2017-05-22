package com.slambook;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	EditText username,password;
	Button login;
	TextView register;
	String user,pass,result;
	ConnectivityManager cm; 
	NetworkInfo activenet;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.ablayout);
		setContentView(R.layout.login);
		
		username=(EditText)findViewById(R.id.username);
		password=(EditText)findViewById(R.id.password);
		login=(Button)findViewById(R.id.login);
		register=(TextView)findViewById(R.id.register);
		
		StrictMode.ThreadPolicy policy = new
				StrictMode.ThreadPolicy.Builder()
				.permitAll().build();
				StrictMode.setThreadPolicy(policy);
		
		cm = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		activenet = cm.getActiveNetworkInfo();
		
		login.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				
				if (activenet != null
						&& activenet.isConnectedOrConnecting()) {
				
				user=username.getText().toString();
				pass=password.getText().toString();
				
				if(!user.isEmpty() || !pass.isEmpty())
				{
					if(login_validation())
					{
						Intent i=new Intent(getApplicationContext(),Home.class);
						i.putExtra("username", user);
						startActivity(i);
					}
					else
						Toast.makeText(getApplicationContext(), "Wrong Username or Password", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(getApplicationContext(), "Please enter Username and Password", Toast.LENGTH_SHORT).show();
				}
				else
					Toast.makeText(getApplicationContext(), "No Network Connection", Toast.LENGTH_SHORT).show();
			}

		});
		
		register.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),Registration.class);
				startActivity(i);
			}
		});
		
	}
	
	private boolean login_validation() {
		List<BasicNameValuePair> params_sub = new ArrayList<BasicNameValuePair>();
		params_sub.add(new BasicNameValuePair("tag", "login"));
		params_sub.add(new BasicNameValuePair("username", user));
		params_sub.add(new BasicNameValuePair("password", pass));
		
		JSON_Data json = new JSON_Data();
		JSONObject jobj = json.getjson(params_sub);
		try
		{
		result=jobj.getString("success");
		Log.d("login result",result);
		}
		catch(Exception e)
		{
			Log.d("login","error");
		}
		if(result.equals("true"))
			return true;
		else
			return false;
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
*/
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			moveTaskToBack(true);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	

}
