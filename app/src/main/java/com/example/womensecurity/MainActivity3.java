package com.example.womensecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {
    EditText e1,e2,e3,e4;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        e1 = findViewById(R.id.p1);
        e2 = findViewById(R.id.p2);
        e3 = findViewById(R.id.p3);
        e4 = findViewById(R.id.p4);
        update = findViewById(R.id.save);
        // get SharedPreferences instance
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if (prefs.getBoolean("isupdated", false)) {
            String name1 = prefs.getString("person1", "");
            String name2 = prefs.getString("person2", "");
            String name3 = prefs.getString("person3", "");
            String name4 = prefs.getString("person4", "");
            e1.setText(name1);
            e2.setText(name2);
            e3.setText(name3);
            e4.setText(name4);

        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editor.putBoolean("isupdated", true);
                String person1= e1.getText().toString();
                String person2= e2.getText().toString();
                String person3= e3.getText().toString();
                String person4= e4.getText().toString();
                editor.putString("person1", person1);
                editor.putString("person2",person2);
                editor.putString("person3", person3);
                editor.putString("person4", person4);
                editor.apply();
                Toast.makeText(MainActivity3.this, "Data saved", Toast.LENGTH_SHORT).show();
            }
        });




    }
}