package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class bloodAvailable extends AppCompatActivity {


    ListView lv;

    ArrayList<BloodBankInfo> dctrlst;

    DatabaseReference dr;
    String type;
    DatabaseReference dRef, dRef1;
    Session session;
    Query usrqry;
    FirebaseAuth mAuth;
    ClinicSignUpInformation user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_available);

        Intent i=getIntent();
        //doc=i.getStringExtra("doc");
        //date=i.getStringExtra("date");
    }
}
