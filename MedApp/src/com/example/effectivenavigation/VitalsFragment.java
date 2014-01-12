package com.example.effectivenavigation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

public class VitalsFragment extends DialogFragment {

    public static final String ARG_SECTION_NUMBER = "section_number";
    int id;
    int new_image;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_vitals, container, false);
        
        ImageButton add1 = (ImageButton) rootView.findViewById(R.id.add1);
        add1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	id = R.id.imageView1;
            	new_image = R.drawable.heart_rate2;
            	create_dialog(true);
            }
        });
        ImageButton undo1 = (ImageButton) rootView.findViewById(R.id.undo1);
        undo1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	id = R.id.imageView1;
            	new_image = R.drawable.heart_rate;
            	change_image();
            }
        });
        
        ImageButton add2 = (ImageButton) rootView.findViewById(R.id.add2);
        add2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	id = R.id.imageView2;
            	new_image = R.drawable.steps2;
            	create_dialog(false);
            }
        });
        ImageButton undo2 = (ImageButton) rootView.findViewById(R.id.undo2);
        undo2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	id = R.id.imageView2;
            	new_image = R.drawable.steps;
            	change_image();
            }
        });
        
        ImageButton add3 = (ImageButton) rootView.findViewById(R.id.add3);
        add3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	id = R.id.imageView3;
            	new_image = R.drawable.blood_sugar2;
            	create_dialog(true);
            }
        });
        ImageButton undo3 = (ImageButton) rootView.findViewById(R.id.undo3);
        undo3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	id = R.id.imageView3;
            	new_image = R.drawable.blood_sugar;
            	change_image();
            }
        });
        
        return rootView;
    }
    
    public void create_dialog(boolean show_clock){
    	AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    	builder.setTitle("Add value");
    	LayoutInflater inflater = getActivity().getLayoutInflater();
    	if (show_clock)
    		builder.setView(inflater.inflate(R.layout.activity_add_time, null));
    	else
    		builder.setView(inflater.inflate(R.layout.activity_add_value, null));
    	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
            	change_image();
            }
        });
    	builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {}
        }); 
    	AlertDialog dialog = builder.create();
    	dialog.show();
    }
    
    public void change_image(){
		ImageView image = (ImageView) getActivity().findViewById(id);
		image.setImageResource(new_image);
    }
}