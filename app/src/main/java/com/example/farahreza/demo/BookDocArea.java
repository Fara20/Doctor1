package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BookDocArea extends AppCompatActivity {
    Button btn1;
     EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_doc_area);

        Intent g = getIntent();
        btn1=findViewById(R.id.button1);
        et = findViewById(R.id.searchEditText);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final  Intent c=new Intent(getApplicationContext(),DocSpeciality.class);
                startActivity(c);


            }
        });
    }
}
