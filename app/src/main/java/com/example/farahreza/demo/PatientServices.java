package com.example.farahreza.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class PatientServices extends AppCompatActivity {
    Button reminder1;
    Button tips;
    Button Sms1;
 CardView book;
    Button blood;
    CardView Ambb;
    CardView emer;
    CardView Blood;

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_services);
        dl=findViewById(R.id.activity_patient_service);
        book=findViewById(R.id.Book);
        Sms1=findViewById(R.id.sms);
        reminder1=findViewById(R.id.reminder);
        Ambb=findViewById(R.id.ambb);
        emer=findViewById(R.id.emerr);
        Blood=findViewById(R.id.blood);
      /*  reminder=findViewById(R.id.button7);
        tips=findViewById(R.id.button6);
        sms=findViewById(R.id.button8);
        book = findViewById(R.id.button1);
        blood=findViewById(R.id.button4);
        */
        t = new ActionBarDrawerToggle(this, dl, R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setTitle("Dashboard");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView) findViewById(R.id.nv);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.profile:
                        Toast.makeText(PatientServices.this, "My Account", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.notifications:
                        Toast.makeText(PatientServices.this, "Settings", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Sign_Out:
                        final  Intent ec=new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(ec);

                }
                return true;
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent c=new Intent(getApplicationContext(),BookAppointment.class);
                startActivity(c);

            }
        });

        Sms1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent ss=new Intent(getApplicationContext(),SmsPage.class);
                startActivity(ss);
            }
        });


        reminder1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Intent rr=new Intent(getApplicationContext(),Reminder.class);
                startActivity(rr);
            }
        });
        Ambb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Intent abb=new Intent(getApplicationContext(),AmbulenceService.class);
                startActivity(abb);
            }
        });
        emer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Intent emm=new Intent(getApplicationContext(),EmergecyUnit.class);
                startActivity(emm);
            }
        });
        Blood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  Intent emm=new Intent(getApplicationContext(),BloodBank.class);
                startActivity(emm);
            }
        });

        /*

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

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent g=new Intent(getApplicationContext(),BookAppointment.class);
                startActivity(g);
            }
        });

        */

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
