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
import android.widget.TextView;
import android.widget.Toast;

public class FillRequest extends Activity {
	TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11,tv12,tv13,tv14,tv15,tv16,tv17,tv18;
	EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7,ed8,ed9,ed10,ed11,ed12,ed13,ed14,ed15,ed16,ed17,ed18;
	String f1,f2,f3,f4,f5,f6,f7,f8,f9,f10,f11,f12,f13,f14,f15,f16,f17,f18,result;
	Button submit;
	String username,frndname;
	ConnectivityManager cm; 
	NetworkInfo activenet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
		getActionBar().setCustomView(R.layout.ablayout);
		setContentView(R.layout.fillrequest);
		
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
		
		ed1=(EditText)findViewById(R.id.editText1);
		ed2=(EditText)findViewById(R.id.editText2);
		ed3=(EditText)findViewById(R.id.editText3);
		ed4=(EditText)findViewById(R.id.editText4);
		ed5=(EditText)findViewById(R.id.editText5);
		ed6=(EditText)findViewById(R.id.editText6);
		ed7=(EditText)findViewById(R.id.editText7);
		ed8=(EditText)findViewById(R.id.editText8);
		ed9=(EditText)findViewById(R.id.editText9);
		ed10=(EditText)findViewById(R.id.editText10);
		ed11=(EditText)findViewById(R.id.editText11);
		ed12=(EditText)findViewById(R.id.editText12);
		ed13=(EditText)findViewById(R.id.editText13);
		ed14=(EditText)findViewById(R.id.editText14);
		ed15=(EditText)findViewById(R.id.editText15);
		ed16=(EditText)findViewById(R.id.editText16);
		ed17=(EditText)findViewById(R.id.editText17);
		ed18=(EditText)findViewById(R.id.editText18);
		
		submit=(Button)findViewById(R.id.button1);
		
		submit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (activenet != null
						&& activenet.isConnectedOrConnecting()) {
					
					f1=ed1.getText().toString();
					f2=ed2.getText().toString();
					f3=ed3.getText().toString();
					f4=ed4.getText().toString();
					f5=ed5.getText().toString();
					f6=ed6.getText().toString();
					f7=ed7.getText().toString();
					f8=ed8.getText().toString();
					f9=ed9.getText().toString();
					f10=ed10.getText().toString();
					f11=ed11.getText().toString();
					f12=ed12.getText().toString();
					f13=ed13.getText().toString();
					f14=ed14.getText().toString();
					f15=ed15.getText().toString();
					f16=ed16.getText().toString();
					f17=ed17.getText().toString();
					f18=ed18.getText().toString();
										
					List<BasicNameValuePair> params_sub = new ArrayList<BasicNameValuePair>();
					params_sub.add(new BasicNameValuePair("tag", "filledreq"));
					params_sub.add(new BasicNameValuePair("username", username));
					params_sub.add(new BasicNameValuePair("frndname", frndname));
					params_sub.add(new BasicNameValuePair("f1", f1));
					params_sub.add(new BasicNameValuePair("f2", f2));
					params_sub.add(new BasicNameValuePair("f3", f3));
					params_sub.add(new BasicNameValuePair("f4", f4));
					params_sub.add(new BasicNameValuePair("f5", f5));
					params_sub.add(new BasicNameValuePair("f6", f6));
					params_sub.add(new BasicNameValuePair("f7", f7));
					params_sub.add(new BasicNameValuePair("f8", f8));
					params_sub.add(new BasicNameValuePair("f9", f9));
					params_sub.add(new BasicNameValuePair("f10", f10));
					params_sub.add(new BasicNameValuePair("f11", f11));
					params_sub.add(new BasicNameValuePair("f12", f12));
					params_sub.add(new BasicNameValuePair("f13", f13));
					params_sub.add(new BasicNameValuePair("f14", f14));
					params_sub.add(new BasicNameValuePair("f15", f15));
					params_sub.add(new BasicNameValuePair("f16", f16));
					params_sub.add(new BasicNameValuePair("f17", f17));
					params_sub.add(new BasicNameValuePair("f18", f18));
					
					JSON_Data json = new JSON_Data();
					JSONObject jobj = json.getjson(params_sub);
					try
					{
					result=jobj.getString("success");
					if(result.equals("true"))
						Toast.makeText(getApplicationContext(), "Details sent successfully", Toast.LENGTH_SHORT).show();
					else
						Toast.makeText(getApplicationContext(), "Unsuccessful. Please try again later.", Toast.LENGTH_SHORT).show();
					}
					catch(Exception e)
					{
						Log.d("Fill request","error");
					}
				}
				else
					Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
				
			}
		});
		
	}
	

}
