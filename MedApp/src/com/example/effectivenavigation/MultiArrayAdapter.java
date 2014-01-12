package com.example.effectivenavigation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class MultiArrayAdapter extends SimpleAdapter {
	Activity activity;
	ArrayList<HashMap<String,String>> data;
	ArrayList<Boolean> not_seen = new ArrayList<Boolean>();
	ArrayList<Integer> types = new ArrayList<Integer>();
	static final int[] possible_types= {R.drawable.cream, R.drawable.pill, R.drawable.syringe};

	public MultiArrayAdapter(Context context, ArrayList<HashMap<String,String>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		int size = data.size();
		activity = (Activity) context;
		this.data = data;
		Random r = new Random(); 
		for(int i = 0;i<size;i++){
			this.not_seen.add(true);
			this.types.add(possible_types[r.nextInt(possible_types.length)]);
		}	
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.list_line, null);
        }

        HashMap<String, String> values = data.get(position);
        
        TextView text1 = (TextView) convertView.findViewById(R.id.textView1);
        text1.setText((String) values.get("line1"));
        TextView text2 = (TextView) convertView.findViewById(R.id.textView2);
        text2.setText((String) values.get("line2"));
        TextView text3 = (TextView) convertView.findViewById(R.id.textView3);
        if (position<not_seen.size() && not_seen.get(position))
    		text3.setText("New!");
        else
        	text3.setText("");
        
		ImageView image1 = (ImageView) convertView.findViewById(R.id.imageView1);
        image1.setImageResource(types.get(position));

        return convertView;
    }
	
	public void mark_viewed(int position){
		not_seen.set(position, false);
        notifyDataSetChanged();
	}
}
