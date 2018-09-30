package com.example.farahreza.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class ClinicBlood extends AppCompatActivity {

Button btn;
EditText aplus, bplus,abplus,oplus,aminus,bminus,abminus,ominus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_blood);
        btn=findViewById(R.id.btn);
        aplus=findViewById(R.id.aplus);
        bplus=findViewById(R.id.bplus);
        abplus=findViewById(R.id.abplus);
        oplus=findViewById(R.id.oplus);
        aminus=findViewById(R.id.aminus);
        bminus=findViewById(R.id.bminus);
        abminus=findViewById(R.id.abminus);
        ominus=findViewById(R.id.ominus);
    }
}
