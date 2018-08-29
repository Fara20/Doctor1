package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView SignUp1;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUp1=findViewById(R.id.SignUp);
        btn=findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(getApplicationContext(),PatientServices.class);
                startActivity(i);
            }
        });

        SignUp1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent b=new Intent(getApplicationContext(),SignupOptions.class);
                startActivity(b);

            }
        });
    }
}
