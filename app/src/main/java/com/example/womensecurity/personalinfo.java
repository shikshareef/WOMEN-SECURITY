package com.example.womensecurity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class personalinfo extends AppCompatActivity {
    EditText a1 , a2 , a3 ,a4;
    Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        a1 = findViewById(R.id.pi1);
        a2 = findViewById(R.id.pi2);
        a3 = findViewById(R.id.pi3);
        a4 = findViewById(R.id.pi4);
        save = findViewById(R.id.save);
        // get SharedPreferences instance
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        if (prefs.getBoolean("persoupdated", false)) {
            String name = prefs.getString("pname", "");
            String age = prefs.getString("page", "");
            String phoneno = prefs.getString("pnumber", "");
            String addr = prefs.getString("paddress", "");
            a1.setText(name);
            a2.setText(age);
            a3.setText(phoneno);
            a4.setText(addr);

        }
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("persoupdated", true);
                String name= a1.getText().toString();
                String age= a2.getText().toString();
                String phonenum= a3.getText().toString();
                String address= a4.getText().toString();
                editor.putString("pname", name );
                editor.putString("page", age);
                editor.putString("pnumber",phonenum);
                editor.putString("paddress",address);
                editor.apply();
                Toast.makeText(personalinfo.this, "Data saved", Toast.LENGTH_SHORT).show();
            }
        });

    }
}