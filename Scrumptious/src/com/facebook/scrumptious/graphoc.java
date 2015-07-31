package com.facebook.scrumptious;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class graphoc extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grapho);
        
        ListView listView1 = (ListView) findViewById(R.id.listView1);
        
        String[] items = { "Gandhi nagar", "Rajkot", "Vellore", "Thiruvanamalei", "Sikar","Baakra" };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, items);
        
        listView1.setAdapter(adapter);
        final Intent i = new Intent(graphoc.this,graphod.class);    
        listView1.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
                final TextView mTextView = (TextView)view;
                switch (position) {
                  case 0:
                    
                	  i.putExtra("con", 1);
                      startActivity(i);
                  break;
                  case 1:
                   //Intent newActivity1 = new Intent(DialogActivity.this,NewActivity1.class);     
                	  i.putExtra("con", 2);
                   startActivity(i);
                  break;
                  case 2:
                   i.putExtra("con", 3);
                   startActivity(i);
                  break;
                  case 3:
                	  i.putExtra("con", 4);
                      startActivity(i);
                  break;
                  case 4:
                	  i.putExtra("con", 5);
                      startActivity(i);
                  break;
                  case 5:
                	  i.putExtra("con", 6);
                      startActivity(i);
                  break;
                  
                  default:
                    // Nothing do!
                }
            }
        });
       
}
}