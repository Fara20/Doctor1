package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BookAppointment extends AppCompatActivity {

    Button BookConfirm; //zakir

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        BookConfirm=findViewById(R.id.button1);

        BookConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent g=new Intent(getApplicationContext(),BookingConfirmation.class);
                startActivity(g);
            }
        });
    }
}
