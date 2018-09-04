package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChooseDoctor extends AppCompatActivity {
    private EditText et;
    private Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_doctor);
        Intent i = getIntent();
        btn1= findViewById(R.id.button1);
        et =findViewById(R.id.searchEditText);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent j = new Intent(getApplicationContext(),DocProfile.class);
                    if(!(et.getText().toString()).equals(""))
                    startActivity(j);
                    else
                        Toast.makeText(getApplicationContext(),"please choose a doctor first",Toast.LENGTH_LONG).show();
                }
            });

    }
}
