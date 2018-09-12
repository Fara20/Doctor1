package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignupOptions extends AppCompatActivity  {

    Spinner spinner;
    Button btnSubmit;
   CheckBox Patient,Clinic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_options);

        Patient=findViewById(R.id.cb1);
        Clinic=findViewById(R.id.cb2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        Patient.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Clinic.setChecked(false);


            }
        });

        Clinic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Patient.setChecked(false);


            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Patient.isChecked())
                {


                    final  Intent b=new Intent(getApplicationContext(),SIgnup.class);
                    startActivity(b);
                }
                else if(Clinic.isChecked())
                {
                    Toast.makeText(getApplicationContext(),"For clinic", Toast.LENGTH_LONG).show();
                }

               else
                {
                    Toast.makeText(getApplicationContext(),"Please select an option", Toast.LENGTH_LONG).show();
                }
            }
        });



    }



    }






