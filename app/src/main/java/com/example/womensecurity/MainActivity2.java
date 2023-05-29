package com.example.womensecurity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    TextView t1;
    CardView c1, c2, c3, c4;
    ImageView alert;
    LocationManager locationManager;
    Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        t1 = findViewById(R.id.namedabba);
        alert = findViewById(R.id.alert);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // Check if the location permission is granted for the location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        }

        // check if the permission is given for the sms
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 2);
        } else {
            // SMS permission is already granted
        }

        // get the user's name from SharedPreferences
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String name = prefs.getString("name", "");

        // display the user's name in a TextView
        t1.setText("welcome"+"   " +name);
        c1 = findViewById(R.id.cardView1);
        c2 = findViewById(R.id.cardView2);
        c4 = findViewById(R.id.cardView4);
        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(intent);
//                finish();
            }
        });
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, personalinfo.class);
                startActivity(intent);
//                finish();

            }
        });
        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, developerinfo.class);
                startActivity(intent);

            }
        });
        String phoneNumber[] = {prefs.getString("person1", "6304372784"), prefs.getString("person2", "6304372784"),
                prefs.getString("person3", "6304372784"), prefs.getString("person4", "6304372784")};
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    String message = "Danger please help me !!";
                    if (location != null) {
                        message += "\nLocation: " + location.getLatitude() + "," + location.getLongitude();
                    }
                    for (int i = 0; i < phoneNumber.length; i++) {
                            smsManager.sendTextMessage(phoneNumber[i], null, message, null, null);
                    }

//                    Toast.makeText(getApplicationContext(), "SMS sent successfully.", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
//                    Toast.makeText(getApplicationContext(), "SMS failed to send.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

            }
        });

    }
}
