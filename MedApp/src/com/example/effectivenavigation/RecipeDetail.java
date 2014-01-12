package com.example.effectivenavigation;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecipeDetail extends Activity {

@Override
public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recipe_detail);

    Intent intent = getIntent();
    int position = intent.getIntExtra("position", 0);
    
    String[] myKeys = getResources().getStringArray(R.array.prescriptions);

    TextView medicine_name = (TextView) findViewById(R.id.medicine_name);
    medicine_name.setText(myKeys[position]);
    TextView medicine_dosage = (TextView) findViewById(R.id.medicine_dosage);
    
    Random r=new Random();
    int i=(r.nextInt(5)*10 + 10);
    medicine_dosage.setText(myKeys[position] + " tbl. " + Integer.toString(i) + " mg");


    }

}