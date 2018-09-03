package com.example.farahreza.demo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SIgnup extends AppCompatActivity {

    Button SignUp2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        SignUp2=findViewById(R.id.SignUpButton);

        SignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  Intent c=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(c);//raisa

            }
        });

    }
}
