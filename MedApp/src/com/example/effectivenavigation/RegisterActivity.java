package com.example.effectivenavigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        
        Button send_button = (Button) this.findViewById(R.id.register_button);
        send_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	register();
            }
        });
 
        TextView registerScreen = (TextView) findViewById(R.id.link_to_login);
        registerScreen.setOnClickListener(new View.OnClickListener() {
 
            public void onClick(View v) {
            	to_login();
            }
        });
    }
    
    public void register() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast toast = Toast.makeText(getApplicationContext(), "Registration succesful!", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
    
    public void to_login() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}