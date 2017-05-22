package com.slambook;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MySlams extends ListActivity {
	String username,frndnames[],users[],data[],frnds,frndname,f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18;
	ConnectivityManager cm; 
	NetworkInfo activenet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.ablayout);
		Bundle bundle=getIntent().getExtras();
		username=bundle.getString("username");
		
		cm = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		activenet = cm.getActiveNetworkInfo();
		
		if (activenet != null
				&& activenet.isConnectedOrConnecting()) {
			
			List<BasicNameValuePair> params_sub = new ArrayList<BasicNameValuePair>();
			params_sub.add(new BasicNameValuePair("tag", "myslamslist"));
			params_sub.add(new BasicNameValuePair("username", username));
			
			JSON_Data json = new JSON_Data();
			JSONObject jobj = json.getjson(params_sub);
			try
			{
			int leng=jobj.length();
			frndnames=new String[leng/2];
			users=new String[leng/2];
			
			for(int i=0;i<leng/2;i++)
			{
				frndnames[i]=jobj.getString("name"+i);
				users[i]=jobj.getString("user"+i);
			}
			
			this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
					R.id.textView1, frndnames));

			ListView lv = getListView();

			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					
						frndname=users[position];
					
					if (activenet != null
							&& activenet.isConnectedOrConnecting()) {
						List<BasicNameValuePair> params_sub = new ArrayList<BasicNameValuePair>();
						params_sub.add(new BasicNameValuePair("tag", "myslam"));
						params_sub.add(new BasicNameValuePair("username", username));
						params_sub.add(new BasicNameValuePair("frndname", frndname));
						
						JSON_Data json = new JSON_Data();
						JSONObject jobj = json.getjson(params_sub);
						try
						{
							data=new String[18];
							for(int i=1;i<19;i++)
								data[i-1]=jobj.getString(String.valueOf(i));
					
						}
						catch(Exception e)
						{
							Log.d("slam data","error");
						}
					

					Intent map = new Intent(getApplicationContext(),
							DisplaySlamData.class);
					for(int i=0;i<18;i++)
						map.putExtra("f"+(i+1), data[i]);
					map.putExtra("username", username);
					map.putExtra("frndname", frndname);
					startActivity(map);
					}
					else
						Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
				}
			});
			
			}
			catch(Exception e)
			{
				Log.d("frnd name display","error");
			}
		
		}
		else
			Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
		
	}

}
