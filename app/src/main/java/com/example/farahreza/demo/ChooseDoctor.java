package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ChooseDoctor extends AppCompatActivity {
    EditText et;
     Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_doctor);
        btn1= findViewById(R.id.button1);
        et =findViewById(R.id.searchEditText);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent c=new Intent(getApplicationContext(),DocProfile.class);
                startActivity(c);
            }
        });
    }
}
