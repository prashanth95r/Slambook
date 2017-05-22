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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SlamRequest extends ListActivity{
	String name[],user[],username;
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
			params_sub.add(new BasicNameValuePair("tag", "getreq"));
			params_sub.add(new BasicNameValuePair("username", username));
			
			JSON_Data json = new JSON_Data();
			JSONObject jobj = json.getjson(params_sub);
			try
			{
				int length=jobj.length();
				name=new String[length/2];
				user=new String[length/2];
				for(int i=0;i<length/2;i++)
				{
					name[i]=jobj.getString("name"+i);
					user[i]=jobj.getString("username"+i);
				}
			
			this.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item,
					R.id.textView1, name));

			ListView lv = getListView();

			lv.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String frndname=user[position];

					Intent map = new Intent(getApplicationContext(),
							FillRequest.class);
					map.putExtra("username", username);
					map.putExtra("frndname", frndname);
					startActivity(map);

				}
			});
			}
			catch(Exception e)
			{
				Log.d("my requests","error");
			}
		
	
		}
		else
			Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
	}

}
