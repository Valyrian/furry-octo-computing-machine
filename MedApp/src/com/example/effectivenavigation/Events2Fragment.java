package com.example.effectivenavigation;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class Events2Fragment extends ListFragment {
	ArrayList<HashMap<String,String>> list;
	EventAdapter notes;
	EditText editText;
	Spinner spinner;
	Spinner spinner2;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
	    View view = inflater.inflate(R.layout.events, container, false);
	    
	    Button button = (Button) view.findViewById(R.id.button1);
	    button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	reserve_time();
            }
        });
	    
	    Button button2 = (Button) view.findViewById(R.id.button2);
	    button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Toast toast = Toast.makeText(getActivity().getApplicationContext(), "All events exported to calendar!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        
	    return view;
	}
	
	public void reserve_time(){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	builder.setTitle("Reserve time");
    	//builder.setMessage("Do you really want to cancel this reservation?");
    	LayoutInflater inflater = getActivity().getLayoutInflater();
    	View view = inflater.inflate(R.layout.reserve_time, null);
    	//builder.setView(inflater.inflate(R.layout.reserve_time, null));
    	builder.setView(view);
    	Context context = (Context)view.getContext();
    	
    	//Activity activity = getActivity();
    	editText = (EditText) view.findViewById(R.id.editText1);
    	spinner = (Spinner) view.findViewById(R.id.spinner1);
    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(context, R.array.event_types, android.R.layout.simple_spinner_item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner.setAdapter(adapter);
    	
    	spinner2 = (Spinner) view.findViewById(R.id.spinner2);
    	ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(context, R.array.locations, android.R.layout.simple_spinner_item);
    	adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	spinner2.setAdapter(adapter2);
    	
    	builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	//remove_item(position);
            	add_item();
            }
        });
    	builder.setNegativeButton(android.R.string.no, null).show();
	}
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] events = getResources().getStringArray(R.array.events);
        String[] places = getResources().getStringArray(R.array.hospitals);
        String[] types = getResources().getStringArray(R.array.actual_event_types);
        
        String[] names = new String[] { "event", "date", "place", "type"};
        int[] ids = new int[]{R.id.textView1, R.id.textView2, R.id.textView3, R.id.imageView1};
        
        list = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<events.length;i++){
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("event", events[i]);
			item.put("date", random_date(i));
			item.put("type", types[i]);
			item.put("place", places[i]);
			list.add(item);
        }
        notes = new EventAdapter(getActivity(), list,R.layout.list_line,names,ids);
        setListAdapter(notes);
    }
    
    public static String random_date(int i){
    	long today = System.currentTimeMillis();
    	long day = 86400000;
        Random r=new Random();
        long days=(r.nextInt(200)+i*200);
        long timestamp = today+day*days;
        return(DateFormat.getDateTimeInstance().format(timestamp));
    }
    
    public void remove_item(int position){
    	list.remove(position);
    	notes.notifyDataSetChanged();
    	Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Reservation cancelled", Toast.LENGTH_SHORT);
        toast.show();
    }
    
    public void add_item(){
    	HashMap<String, String> item = new HashMap<String, String>();
		String name = editText.getText().toString();
		String type = spinner.getSelectedItem().toString();
		String place = spinner2.getSelectedItem().toString();
		item.put("event", name);
		item.put("type", type);
		item.put("place", place);
		int i = list.size();
		item.put("date", random_date(i));
    	list.add(item);
    	notes.notifyDataSetChanged();
    	Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Reservation made", Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	builder.setTitle("Cancel reservation?");
    	builder.setMessage("Do you really want to cancel this reservation?");
    	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	remove_item(position);
            	//add_item("sfasdf");
            }
        });
    	builder.setNegativeButton(android.R.string.no, null).show();
    }
}