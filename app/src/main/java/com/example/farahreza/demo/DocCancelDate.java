package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

public class DocCancelDate extends AppCompatActivity {


    TextView t1,t2,t3;
    Button btn1;
    DatePicker dob;
    DatabaseReference dr,reference,reff;
    String date,time,clinic,location,doc,pname,uid,num;
   // Switch switch1, switch2, switch3;
    Query usrqry,qry;
    Session session;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_cancel_date);

        dob=findViewById(R.id.dob);
        btn1=findViewById(R.id.btn);
        //switch1=findViewById(R.id.switch1);
        //switch2=findViewById(R.id.switch2);
        //switch3=findViewById(R.id.switch3);
        t1=findViewById(R.id.tt1);
        t2=findViewById(R.id.tt2);
        t3=findViewById(R.id.tt3);
        session=new Session(this);
        mAuth=FirebaseAuth.getInstance();



        Intent i=getIntent();
        doc=i.getStringExtra("doc");
        //location=i.getStringExtra("loca");



        /*switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(switch1.isChecked())
                {
                    switch2.setChecked(false);
                    switch3.setChecked(false);



                }
                else if(switch2.isChecked())
                {
                    switch1.setChecked(false);
                    switch3.setChecked(false);



                }
                else if(switch3.isChecked())
                {
                    switch1.setChecked(false);
                    switch2.setChecked(false);



                }



            }
        });

        switch1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch2.setChecked(false);
                switch3.setChecked(false);
                time="8am-2pm";

            }
        });

        switch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch1.setChecked(false);
                switch3.setChecked(false);
                time="4pm-8pm";

            }
        });

        switch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch2.setChecked(false);
                switch1.setChecked(false);
                time="9pm-12am";

            }
        });*/

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int year=dob.getYear();
                int month=dob.getMonth()+1;
                int day=dob.getDayOfMonth();

                date=new StringBuilder().append(year).append("-")
                        .append(month).append("-")
                        .append(day).append("").toString();

                final  Intent c=new Intent(getApplicationContext(),DocDateList.class);
                //c.putExtra("clinic",C);
                c.putExtra("date",date);
               // c.putExtra("time",time);
                c.putExtra("doc",doc);
                startActivity(c);
            }
        });

    }


}
