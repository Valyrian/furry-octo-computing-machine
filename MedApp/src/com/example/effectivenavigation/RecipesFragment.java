package com.example.effectivenavigation;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class RecipesFragment extends ListFragment {
	//boolean[] not_seen = {true, false, false, false, false, false, false};
	ArrayList<HashMap<String,String>> list;
	MultiArrayAdapter notes;
    
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] prescriptions = getResources().getStringArray(R.array.prescriptions);
        
        String[] names = new String[] { "line1","line2", "line3", "image1"};
        int[] ids = new int[]{R.id.textView1, R.id.textView2, R.id.textView3, R.id.imageView1};
        
        list = new ArrayList<HashMap<String,String>>();
        for(int i=0;i<prescriptions.length;i++){
			HashMap<String, String> item = new HashMap<String, String>();
			item.put("line1", prescriptions[i]);
			item.put("line2", random_date(i));
			list.add(item);
        }
        notes = new MultiArrayAdapter(getActivity(), list,R.layout.list_line,names,ids);
        setListAdapter(notes);
    }
    
    public static String random_date(int i){
    	long today = System.currentTimeMillis();
    	long day = 86400000;
        Random r=new Random();
        long days=(r.nextInt(200)+i*200);
        long timestamp = today-day*days;
        return(DateFormat.getDateInstance().format(timestamp));
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        notes.mark_viewed(position);
        Intent intent = new Intent();
        intent.setClass(getActivity(), RecipeDetail.class);
        intent.putExtra("position", position);
        intent.putExtra("id", id);
        startActivity(intent);
    }
}