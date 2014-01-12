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

public class EventAdapter extends SimpleAdapter {
	Activity activity;
	ArrayList<HashMap<String,String>> data;
	ArrayList<Boolean> not_seen = new ArrayList<Boolean>();
	ArrayList<Integer> types = new ArrayList<Integer>();
	static final int[] possible_types= {R.drawable.stethoscope, R.drawable.syringe};

	public EventAdapter(Context context, ArrayList<HashMap<String,String>> data, int resource, String[] from, int[] to) {
		super(context, data, resource, from, to);
		//int size = data.size();
		activity = (Activity) context;
		this.data = data;
		//Random r = new Random(); 
		//for(int i = 0;i<size;i++){
			//this.not_seen.add(true);
			
			//this.types.add(possible_types[1]);
			//this.types.add(possible_types[r.nextInt(possible_types.length)]);
		//}	
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = activity.getLayoutInflater().inflate(R.layout.list_line, null);
        }

        HashMap<String, String> values = data.get(position);
        
        TextView text1 = (TextView) convertView.findViewById(R.id.textView1);
        String event = (String) values.get("event");
        text1.setText(event);
        TextView text2 = (TextView) convertView.findViewById(R.id.textView2);
        text2.setText((String) values.get("date"));
        TextView text3 = (TextView) convertView.findViewById(R.id.textView3);
        text3.setTextColor(0xFF000000);
        String place = (String) values.get("place");
        text3.setText(place);
        /*
        Random r = new Random(); 
        if (r.nextInt(2) == 0)
    		text3.setText("YTHS Otaniemi");
        else
        	text3.setText("Meilahden sairaala");*/
        
        ImageView image1 = (ImageView) convertView.findViewById(R.id.imageView1);
        String type = (String) values.get("type");
        image1.setImageResource(R.drawable.pill);
        if (type.equals("Vaccination"))
        	image1.setImageResource(R.drawable.syringe);
        if (type.equals("Checkup"))
        	image1.setImageResource(R.drawable.stethoscope);
        /*
        image1.setImageResource(R.drawable.pill);
        if (event.contains("Vaccination"))
        	image1.setImageResource(R.drawable.syringe);
        if (event.contains("Checkup"))
        	image1.setImageResource(R.drawable.stethoscope);
        	*/

        return convertView;
    }
	
	/*
	public void mark_viewed(int position){
		not_seen.set(position, false);
        notifyDataSetChanged();
	}*/
}
