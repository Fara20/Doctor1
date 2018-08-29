package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PatientServices extends AppCompatActivity {
Button reminder;
Button tips;
Button sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_services);
        reminder=findViewById(R.id.button7);
        tips=findViewById(R.id.button6);
        sms=findViewById(R.id.button8);
        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Intent c=new Intent(getApplicationContext(),Reminder.class);
                startActivity(c);
            }
        });

        tips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent d=new Intent(getApplicationContext(),HealthTips.class);
                startActivity(d);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent f=new Intent(getApplicationContext(),SmsPage.class);
                startActivity(f);
            }
        });
    }
}
