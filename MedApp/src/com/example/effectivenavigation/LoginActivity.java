package com.example.effectivenavigation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        Button send_button = (Button) this.findViewById(R.id.login_button);
        send_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	login();
            }
        });
 
        TextView registerScreen = (TextView) findViewById(R.id.link_to_register);
        registerScreen.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	to_register();
            }
        });
    }
    
    public void login() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Toast toast = Toast.makeText(getApplicationContext(), "Login succesful!", Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
    
    public void to_register() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}