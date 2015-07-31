package com.facebook.scrumptious;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class two extends Activity {
	CustomAdapter arrayAdapter;
	ListView listView;
	String responsed;
	String fbid="100001491595031";
	String votedFor;//0 or 1 or 2
	String constId;//1-7
	private class DownloadPackets extends AsyncTask<String, Integer, String> {

		public ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = new ProgressDialog(two.this);
			dialog.setMessage("Updating...");
			dialog.show();

		}

		protected String doInBackground(final String... urls) {
			String html = null;

			try {
				if (isNetworkConnected()) {
					html = getInternetData(urls[0]);

				}
			} catch (Exception e) {
			}
			// dialog.dismiss();
			return html;
		}

		protected void onProgressUpdate(Integer... progress) {
		}

		protected void onPostExecute(final String response) {

			// jsonparse(response);and
			/*
			 * Data currrentData = (Data)dataList.get(0);
			 * 
			 * Toast.makeText(MainActivity.this, currrentData.email,
			 * Toast.LENGTH_SHORT).show();
			 */
			dialog.hide();
			responsed=response;
		}
	}

	//

	public String getInternetData(String url) throws Exception {
		String html = "";

		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(url);
		HttpResponse response;
		try {
			response = client.execute(request);
									
			InputStream in = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			StringBuilder str = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				str.append(line);
			}
			in.close();
			html = str.toString();

		} catch (ClientProtocolException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		return html;
	}
	
	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			// There are no active networks.
			return false;
		} else
			return true;
	}

	public boolean isChecked = false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.example_listview);
		
	
		
		ArrayList< String>arrayList; // list of the strings that should appear in ListView
		
		
		 listView = (ListView) findViewById(R.id.lstDemo);
		 
		 arrayList = new ArrayList<String>();
	        arrayList.add("BJP");
	        arrayList.add("AAP");
	        arrayList.add("CONGRESS");
	        
	        

	        arrayAdapter = new CustomAdapter(arrayList, this);
	        listView.setAdapter(arrayAdapter);
	        Log.d("Tag",  "------------------- >>");
	        listView.setOnItemClickListener( new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					Log.d("Tag",  "------------------- >>");
					arrayAdapter.setChecked(arg2);
					arrayAdapter.notifyDataSetChanged();
					
				}
	        	
			});
	        
	    /*    arrayAdapter = new ArrayAdapter(getApplicationContext(),android.R.layout.simple_list_item_single_choice,arrayList);
	        listView.setAdapter(arrayAdapter);
	        
	        
	        listView.setOnItemClickListener(new OnItemClickListener() {

	        	@Override
	        	   public void onItemClick(AdapterView arg0, View view, int position,
	        	     long itemId) {
	        	    
	        		   CheckedTextView textView = (CheckedTextView) view;
	        		    for (int i = 0; i < listView.getCount(); i++) {
	        		     textView= (CheckedTextView) listView.getChildAt(i);
	        		     if (textView != null) {
	        		      textView.setTextColor(Color.BLACK);
	        		     }
	        		     
	        		    }
	        		    listView.invalidate();
	        		    textView = (CheckedTextView) view;
	        		    if (textView != null) {
	        		     textView.setTextColor(Color.RED);
	        		    }

	        		   }
	        		  });
*/	        	   }
	        	  
	public void b(View v){
		
		String s= arrayAdapter.getCheckedString();
		if(s.contentEquals("BJP"))
		votedFor="0";
		if(s.contentEquals("CONGRESS"))
			votedFor="1";
		if(s.contentEquals("AAP"))
			votedFor="2";
		constId="3";
		new DownloadPackets().execute("http://gulelscore.appspot.com/vote/"+fbid+"/"+constId+"/"+votedFor);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
	
}
