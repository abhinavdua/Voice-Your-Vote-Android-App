package com.facebook.scrumptious;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

public class CustomAdapter extends BaseAdapter  implements OnCheckedChangeListener{

	public ArrayList<String> list = new ArrayList<String>();
	public Context ctx;
	public int checkedId = -1;
	public String checkedString = "";

	public CustomAdapter(ArrayList<String> list, Context ctx) {
		this.list = list;
		this.ctx = ctx;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup group) {
		LayoutInflater inflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.custom_list, group, false);
		TextView textView = (TextView) rowView.findViewById(R.id.text);
		textView.setText(list.get(position));
		CheckBox ck = (CheckBox) rowView.findViewById(R.id.chkWindows);
		ck.setTag(position);
		ck.setOnCheckedChangeListener(this);

		if (checkedId == position) {

			ck.setChecked(true);
			textView.setTextColor(Color.parseColor("#FF0000"));

		} else {
			ck.setChecked(false);
			textView.setTextColor(Color.parseColor("#000000"));
		}

		return rowView;
	}

	public void setChecked(int id) {
		this.checkedId = id;
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		setChecked((Integer)arg0.getTag());
		notifyDataSetChanged();
		this.checkedString = list.get((Integer)arg0.getTag());
		//Toast.makeText(ctx, " --- >>"+checkedString, Toast.LENGTH_LONG).show();
		
	}
	public String getstr(){
		
		return checkedString;
	}
	public String getCheckedString(){
		return this.checkedString;
	}
}
