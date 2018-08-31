package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SignupOptions extends AppCompatActivity  {

    Spinner spinner;
    Button btnSubmit;
    String a="Signed up";
    String bb="Congrats!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_options);

        addItemsOnSpinner2();
        addListenerOnButton();

    }

        public void addItemsOnSpinner2(){

            spinner = (Spinner) findViewById(R.id.spinner2);
            List<String> list = new ArrayList<String>();
            list.add("Select an Option");
            list.add("Clinic");
            list.add("Patient");
            list.add("Vet");
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.my_spinner, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
        }
    public void addListenerOnButton(){
        spinner = (Spinner) findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

      final  Intent b=new Intent(getApplicationContext(),SIgnup.class);

       // final  Intent c=new Intent(getApplicationContext(),Reminder.class);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(spinner.getSelectedItem())=="Patient")
                    startActivity(b);
                else  if(String.valueOf(spinner.getSelectedItem())=="Clinic")
                    Toast.makeText(getApplicationContext(),"For clinic", Toast.LENGTH_LONG).show();
                else   if(String.valueOf(spinner.getSelectedItem())=="Vet")
                    Toast.makeText(getApplicationContext(),a + bb, Toast.LENGTH_LONG).show();
                   // startActivity(c);
                else
                    Toast.makeText(getApplicationContext(),"Please Select an Option", Toast.LENGTH_LONG).show();







            }
        });

    }





}
