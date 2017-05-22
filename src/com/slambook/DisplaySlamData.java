package com.slambook;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DisplaySlamData extends Activity{
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv18,t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14,t15,t16,t17,t18;
	Button delete;
	String username,frndname;
	ConnectivityManager cm; 
	NetworkInfo activenet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.ablayout);
		setContentView(R.layout.displayslamdata);
		
		Bundle bundle=getIntent().getExtras();
		username=bundle.getString("username");
		frndname=bundle.getString("frndname");
		
		cm = (ConnectivityManager) getApplicationContext()
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		activenet = cm.getActiveNetworkInfo();
		
		tv1=(TextView)findViewById(R.id.textView1);
		tv2=(TextView)findViewById(R.id.textView2);
		tv3=(TextView)findViewById(R.id.textView3);
		tv4=(TextView)findViewById(R.id.textView4);
		tv5=(TextView)findViewById(R.id.textView5);
		tv6=(TextView)findViewById(R.id.textView6);
		tv7=(TextView)findViewById(R.id.textView7);
		tv8=(TextView)findViewById(R.id.textView8);
		tv9=(TextView)findViewById(R.id.textView9);
		tv10=(TextView)findViewById(R.id.textView10);
		tv11=(TextView)findViewById(R.id.textView11);
		tv12=(TextView)findViewById(R.id.textView12);
		tv13=(TextView)findViewById(R.id.textView13);
		tv14=(TextView)findViewById(R.id.textView14);
		tv15=(TextView)findViewById(R.id.textView15);
		tv16=(TextView)findViewById(R.id.textView16);
		tv17=(TextView)findViewById(R.id.textView17);
		tv18=(TextView)findViewById(R.id.textView18);
		
		t1=(TextView)findViewById(R.id.Text1);
		t2=(TextView)findViewById(R.id.Text2);
		t3=(TextView)findViewById(R.id.Text3);
		t4=(TextView)findViewById(R.id.Text4);
		t5=(TextView)findViewById(R.id.Text5);
		t6=(TextView)findViewById(R.id.Text6);
		t7=(TextView)findViewById(R.id.Text7);
		t8=(TextView)findViewById(R.id.Text8);
		t9=(TextView)findViewById(R.id.Text9);
		t10=(TextView)findViewById(R.id.Text10);
		t11=(TextView)findViewById(R.id.Text11);
		t12=(TextView)findViewById(R.id.Text12);
		t13=(TextView)findViewById(R.id.Text13);
		t14=(TextView)findViewById(R.id.Text14);
		t15=(TextView)findViewById(R.id.Text15);
		t16=(TextView)findViewById(R.id.Text16);
		t17=(TextView)findViewById(R.id.Text17);
		t18=(TextView)findViewById(R.id.Text18);
		
		delete=(Button)findViewById(R.id.delete);
		
		t1.setText(bundle.getString("f1"));
		t2.setText(bundle.getString("f2"));
		t3.setText(bundle.getString("f3"));
		t4.setText(bundle.getString("f4"));
		t5.setText(bundle.getString("f5"));
		t6.setText(bundle.getString("f6"));
		t7.setText(bundle.getString("f7"));
		t8.setText(bundle.getString("f8"));
		t9.setText(bundle.getString("f9"));
		t10.setText(bundle.getString("f10"));
		t11.setText(bundle.getString("f11"));
		t12.setText(bundle.getString("f12"));
		t13.setText(bundle.getString("f13"));
		t14.setText(bundle.getString("f14"));
		t15.setText(bundle.getString("f15"));
		t16.setText(bundle.getString("f16"));
		t17.setText(bundle.getString("f17"));
		t18.setText(bundle.getString("f18"));
		
		delete.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        switch (which){
				        case DialogInterface.BUTTON_POSITIVE:
				        	if (activenet != null
							&& activenet.isConnectedOrConnecting()) {
				        		List<BasicNameValuePair> params_sub = new ArrayList<BasicNameValuePair>();
				    			params_sub.add(new BasicNameValuePair("tag", "deleteslam"));
				    			params_sub.add(new BasicNameValuePair("username", username));
				    			params_sub.add(new BasicNameValuePair("frndname", frndname));
				    			
				    			JSON_Data json = new JSON_Data();
				    			JSONObject jobj = json.getjson(params_sub);
				    			try
				    			{
				    				String result=jobj.getString("success");
				    				if(result.equals("true"))
				    				{
				    					Toast.makeText(getApplicationContext(), "Delete successful.", Toast.LENGTH_SHORT).show();
				    					Intent i=new Intent(getApplicationContext(),MySlams.class);
				    					i.putExtra("username", username);
				    			        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				    					startActivity(i);
				    				}
				    				else
				    					Toast.makeText(getApplicationContext(), "Delete failed. Please try later.", Toast.LENGTH_SHORT).show();
				    			}
				    			catch(Exception e)
				    			{
				    				Log.d("display slam delete","error");
				    			}
				        	}
				        	else
				        		Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
				            break;

				        case DialogInterface.BUTTON_NEGATIVE:
				            break;
				        }
				    }
				};

				AlertDialog.Builder builder = new AlertDialog.Builder(DisplaySlamData.this);
				builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
				    .setNegativeButton("No", dialogClickListener).show();
			}
		});
	}
	

}
