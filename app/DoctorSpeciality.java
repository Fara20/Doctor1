package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DoctorSpeciality extends AppCompatActivity {

    Button BookConfirm;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        addItemsOnSpinner2();
        addListenerOnButton();

    }
    public void addItemsOnSpinner2(){

        spinner = (Spinner) findViewById(R.id.spinner2);
        List<String> list = new ArrayList<String>();
        list.add("Select an Option");
        list.add("Cardiology");
        list.add("Neurology");
        list.add("Surgery");
        list.add("Gynaecology");
        list.add("Pathology");
        list.add("Neurosurgery");
        list.add("Pediatrics");
        list.add("Psychiatry");
        list.add("Nephrology");
        list.add("Dental");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.my_spinner, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void addListenerOnButton(){
        spinner = (Spinner) findViewById(R.id.spinner2);
        BookConfirm = (Button) findViewById(R.id.btnSubmit);

        final Intent g =new Intent(getApplicationContext(),ChooseDoctor.class);





        BookConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(spinner.getSelectedItem())=="Cardiology")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Neurology")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Surgery")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Gynaecology")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Pathology")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Neurosurgery")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Pediatrics")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Psychiatry")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Nephrology")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Dental")
                    startActivity(g);

                else
                    Toast.makeText(getApplicationContext(),"Please Select an Option", Toast.LENGTH_LONG).show();







            }
        });

    }

}
