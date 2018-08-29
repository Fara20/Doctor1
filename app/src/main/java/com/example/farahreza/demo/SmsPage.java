package com.example.farahreza.demo;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SmsPage extends AppCompatActivity {
    Button Enter;
    EditText txtt;
    EditText num;
    String Phn,Txt;
    private static final int PERMISSION_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms_page);
        Enter=findViewById(R.id.button);
        txtt=findViewById(R.id.Text);
        num=findViewById(R.id.PhoneNumber);

        Enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phn=txtt.getText().toString();
                Txt=num.getText().toString();

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {

                    if (checkSelfPermission(Manifest.permission.SEND_SMS)
                            == PackageManager.PERMISSION_DENIED) {

                        Log.d("permission", "permission denied to SEND_SMS - requesting it");
                        String[] permissions = {Manifest.permission.SEND_SMS};

                        requestPermissions(permissions, PERMISSION_REQUEST_CODE);

                    }
                }

                SmsManager sms= SmsManager.getDefault();
                sms.sendTextMessage(Phn,null,Txt,null,null);


            }
        });

    }
}
