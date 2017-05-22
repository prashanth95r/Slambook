package com.slambook;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity{
	Button profile,myslams,slamrequest,sendrequest;
	TextView logout;
	String username;
	ConnectivityManager cm; 
	NetworkInfo activenet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.ablayout);
		setContentView(R.layout.home);
		cm = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		activenet = cm.getActiveNetworkInfo();
		
		Bundle bundle = getIntent().getExtras();
		username=bundle.getString("username");
		
		profile=(Button)findViewById(R.id.profile);
		myslams=(Button)findViewById(R.id.myslams);
		slamrequest=(Button)findViewById(R.id.slamrequest);
		sendrequest=(Button)findViewById(R.id.sendrequest);
		logout=(TextView)findViewById(R.id.logout);
		
		profile.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (activenet != null
						&& activenet.isConnectedOrConnecting()) {
					Intent i=new Intent(getApplicationContext(),Profile.class);
					i.putExtra("username", username);
					startActivity(i);
				}
				else
					Toast.makeText(getApplicationContext(), "No Internet Connection!!",Toast.LENGTH_SHORT).show();
			}
		});
		
		myslams.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (activenet != null
						&& activenet.isConnectedOrConnecting()) {
				Intent i=new Intent(getApplicationContext(),MySlams.class);
				i.putExtra("username", username);
				startActivity(i);
				}
				else
					Toast.makeText(getApplicationContext(), "No Internet Connection!!",Toast.LENGTH_SHORT).show();
			}
		});
		
		slamrequest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (activenet != null
						&& activenet.isConnectedOrConnecting()) {
				Intent i=new Intent(getApplicationContext(),SlamRequest.class);
				i.putExtra("username", username);
				startActivity(i);
				}
				else
					Toast.makeText(getApplicationContext(), "No Internet Connection!!",Toast.LENGTH_SHORT).show();
			}
		});
		
		sendrequest.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (activenet != null
						&& activenet.isConnectedOrConnecting()) {
				Intent i=new Intent(getApplicationContext(),SendRequest.class);
				i.putExtra("username", username);
				startActivity(i);
				}
				else
					Toast.makeText(getApplicationContext(), "No Internet Connection!!",Toast.LENGTH_SHORT).show();
			}
		});
		
		logout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(), Login.class);
				i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(i);
			}
		});
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode==KeyEvent.KEYCODE_BACK){
			Intent intent = new Intent(Intent.ACTION_MAIN);
	          intent.addCategory(Intent.CATEGORY_HOME);
	          intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
	          startActivity(intent);
	          finish();
	          System.exit(0);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
