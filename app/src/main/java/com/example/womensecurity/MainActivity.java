package com.example.womensecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText name , number , pass;
    Button login , getstart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // get SharedPreferences instance
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);

        // check if user has already logged in
        if (prefs.getBoolean("isLoggedIn", false)) {
            // redirect the user to the main activity
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            startActivity(intent);
            finish();
        } else {
            setContentView(R.layout.activity_main);
            name = findViewById(R.id.nameholder);
            number = findViewById(R.id.numberholder);
            pass = findViewById(R.id.passholder);
            login = findViewById(R.id.login);
            getstart = findViewById(R.id.getstart);
            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putBoolean("isLoggedIn", true);
                    String username = name.getText().toString();
                    String mobnumber = number.getText().toString();
                    String password =  pass.getText().toString();
                    editor.putString("name", username);
                    editor.putString("number",mobnumber);
                    editor.putString("password",password);
                    editor.apply();
                    Toast.makeText(MainActivity.this, "Data saved", Toast.LENGTH_SHORT).show();
                }
            });
            getstart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // redirect the user to the main activity
                    Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
}
