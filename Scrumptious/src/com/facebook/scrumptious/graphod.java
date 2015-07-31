package com.facebook.scrumptious;

 

import com.facebook.scrumptious.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
 
public class graphod extends Activity {
 
  private RadioGroup radioSexGroup;
  private RadioButton radioSexButton;
  private Button btnDisplay;
 
  @Override
  public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.selectgraph);
 
	addListenerOnButton();
 
  }
 
  public void addListenerOnButton() {
 
	radioSexGroup = (RadioGroup) findViewById(R.id.r1);
	btnDisplay = (Button) findViewById(R.id.btnDisplay);
 
	btnDisplay.setOnClickListener(new OnClickListener() {
		Intent x= getIntent();
		int n= x.getIntExtra("con", 0);
		@Override
		public void onClick(View v) {
 
		        // get selected radio button from radioGroup
			int selectedId = radioSexGroup.getCheckedRadioButtonId();
 
			// find the radiobutton by returned id
		        radioSexButton = (RadioButton) findViewById(selectedId);
 
		     Intent y =new Intent(graphod.this,asdasddass.class);   
		    y.putExtra("con", String.valueOf(n));
		    if(radioSexButton.getText().toString().contentEquals("Constituency"))
		    y.putExtra("radio","con");
		    if(radioSexButton.getText().toString().contentEquals("City"))
			    y.putExtra("radio","city");
		    if(radioSexButton.getText().toString().contentEquals("Country"))
			    y.putExtra("radio","country");
		    if(radioSexButton.getText().toString().contentEquals("State"))
			    y.putExtra("radio","state");
		    startActivity(y);
			//Toast.makeText(graphod.this,
			//	radioSexButton.getText(), Toast.LENGTH_SHORT).show();
 
		}
 
	});
 
  }
}