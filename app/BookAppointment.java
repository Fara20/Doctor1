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

public class BookAppointment extends AppCompatActivity {

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
        list.add("Dhaka");
        list.add("Chittagong");
        list.add("Rajshahi");
        list.add("Sylhet");
        list.add("Commilla");
        list.add("Barishal");
        list.add("Khulna");
        list.add("Mymensingh");
        list.add("Rangpur");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.layout.my_spinner, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }

    public void addListenerOnButton(){
        spinner = (Spinner) findViewById(R.id.spinner2);
        BookConfirm = (Button) findViewById(R.id.btnSubmit);

        final Intent g =new Intent(getApplicationContext(),BookDocArea.class);





        BookConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(String.valueOf(spinner.getSelectedItem())=="Dhaka")
                    startActivity(g);
                else  if(String.valueOf(spinner.getSelectedItem())=="Chittagong")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Comilla")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Rangpur")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Rajshahi")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Sylhet")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Mymensingh")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Barishal")
                    startActivity(g);
                else   if(String.valueOf(spinner.getSelectedItem())=="Khulna")
                    startActivity(g);

                else
                    Toast.makeText(getApplicationContext(),"Please Select an Option", Toast.LENGTH_LONG).show();







            }
        });

    }

}
