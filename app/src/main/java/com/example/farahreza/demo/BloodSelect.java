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

public class BloodSelect extends AppCompatActivity {

    Spinner  spinner2;
    Button btnSubmit;
    String val;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_select);

        spinner2 = (Spinner) findViewById(R.id.spinner2);
        btnSubmit=findViewById(R.id.btnSubmit);

        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                R.layout.spinner_dropdown_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(dataAdapter);

        btnSubmit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

              val= String.valueOf(spinner2.getSelectedItem());
                final Intent c=new Intent(getApplicationContext(),bloodAvailable.class);
                //c.putExtra("clinic",C);
                c.putExtra("type",val);
                startActivity(c);


            }

        });
    }
}
