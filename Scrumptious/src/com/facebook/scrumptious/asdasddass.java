package com.facebook.scrumptious;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

import com.jjoe64.graphview.BarGraphView;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewDataInterface;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.ValueDependentColor;

public class asdasddass extends Activity {
	static int count=0;
	String responsed;
	GraphViewSeries exampleSeries;
	BarGraphView graphView ;
	int a[]=new int[3];
	private class DownloadPackets extends AsyncTask<String, Integer, String> {

		public ProgressDialog dialog;
		
		protected void onPreExecute() {
			dialog = new ProgressDialog(asdasddass.this);
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
			asdasddass.this.responsed=response;
			try {
				asdasddass.this.processResult();
				System.out.println(" "+a[0]+" "+a[1]+" "+a[2]);
				exampleSeries.resetData(new GraphViewData[] {
                        new GraphViewData(1,a[0])
                        , new GraphViewData(2, a[1])
                        , new GraphViewData(3,a[2]) // another frequency
                        
                });
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	private boolean isNetworkConnected() 
	{
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null)
		{
			// There are no active networks.
			return false;
		} else
			return true;
	}
	}
	public void processResult() throws JSONException
	{
		JSONObject jObject = new JSONObject(responsed);
		JSONArray jArray = jObject.getJSONArray("Json");
		for (int i = 0; i < jArray.length(); i++) 
		{
			JSONObject oneObject = jArray.getJSONObject(i);

			HashMap<String, String> map = new HashMap<String, String>();

			map.put("bjp", oneObject.getString("id1"));
			map.put("congress", oneObject.getString("id2"));
			map.put("aap", oneObject.getString("id3"));
			int bjpCount=Integer.parseInt(map.get("bjp"));
			int congressCount=Integer.parseInt(map.get("congress"));
			int aapCount=Integer.parseInt(map.get("aap"));
			a[0]=bjpCount;
			a[1]=congressCount;
			a[2]=aapCount;
			
		}
	}
	
	String x;
	String x2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layoutg);
		
		Intent i = getIntent();
		 x= i.getStringExtra("con");
	    x2 = i.getStringExtra("radio");
	    new DownloadPackets().execute(constructUrl());
		GraphViewSeriesStyle seriesStyle = new GraphViewSeriesStyle();
		seriesStyle.setValueDependentColor(new ValueDependentColor() {
		  @Override
		  public int get(GraphViewDataInterface data) {
		    // the higher the more red
			   
			  if(count==0)
			  {
				  count++;
				  return Color.rgb(230,230,230);
			  }
			  if(count==1)
			  {
				  
				  count++;
				  return Color.GRAY;
				  
			  }
			  if(count==2)
			  {
				  count++;
				  return Color.BLUE;
				  
			  }
				  return Color.rgb((int)(100+((data.getY()/4)*500)), (int)(150-((data.getY()/3)*150)), (int)(150-((data.getY()/3)*150)));
		  }
		});
				
		
		// init example series data
		exampleSeries = new GraphViewSeries("aaa",seriesStyle,new GraphViewData[] {
		      new GraphViewData(1, 3.0d)
		      , new GraphViewData(2, 2.0d)
		      , new GraphViewData(3, 2.5d)
		      
		});
		 
		graphView = new BarGraphView(this, "");
		graphView.addSeries(exampleSeries); // data
		 graphView.setHorizontalLabels(new String[] {"BJP","CONGRESS","AAP"});
		 graphView.getGraphViewStyle().setGridColor(Color.RED);
		 graphView.setDrawValuesOnTop(true);
		 graphView.getGraphViewStyle().setTextSize(getResources().getDimension(R.dimen.big));
		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
		layout.addView(graphView);
		}
		

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public String constructUrl()
	{
		String url;
		int a[]=new int[3];
		String fbid="100001491595031";
	//	Intent i = getIntent();
		//String x = i.getExtra("con"); 
		
		String context=x2;//get con or country or state or city
		String constituencyId=x;//get constituencyid
		
		url="http://gulelscore.appspot.com/graphdata/"+context+"/"+constituencyId;
		System.out.println("Url is "+url);
		return url;
	}
	

	
}
