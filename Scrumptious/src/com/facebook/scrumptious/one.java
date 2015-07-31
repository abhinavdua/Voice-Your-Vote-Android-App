package com.facebook.scrumptious;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
import android.os.Handler;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class one extends Activity {
	private class DownloadPackets extends AsyncTask<String, Integer, String> {

		public ProgressDialog dialog;

		protected void onPreExecute() {
			dialog = new ProgressDialog(one.this);
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
			System.out.println(response);
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

	final Handler mHandler = new Handler();
	private ImageView view1;
	private ImageView view2;
	private Context ctx;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.one);
		new DownloadPackets().execute("http://gulelscore.appspot.com/register/"+qwerty.uid+"/"+qwerty.str);
		ctx = getApplicationContext();
		view1 = (ImageView) findViewById(R.id.imageView1);
		view2 = (ImageView) findViewById(R.id.imageView2);
		view2.setVisibility(View.INVISIBLE);
		final Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	
            	final Runnable mUpdateResults = new Runnable() {
        		    public void run() {
        		    	System.out.println("HELLO BC0");
        		    	Toast.makeText(getApplicationContext(), "Verified", Toast.LENGTH_SHORT).show();
        		    	if(view1.getVisibility() == View.VISIBLE && view2.getVisibility() == View.INVISIBLE)
                        {
                			view1.setVisibility(View.INVISIBLE);
                			view2.setVisibility(View.VISIBLE);
                        }
        		    };
        		        
        		};

        		new Thread() {
        		    public void run() {
        		    	try {
        		    		System.out.println("HELLO BC8888");
        					sleep(10000);
        					System.out.println("HELLO BC9999");
        				} catch (InterruptedException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
        		        mHandler.post(mUpdateResults);
        		    }
        		}.start();
            	
        		
        		
        		
                // Perform action on click
            	
        		 
               
                
            }
               
        		
            
        });
	}

	 
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

	
