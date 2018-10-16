package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DocDateList extends AppCompatActivity {

    String date,time,doc;
    DatabaseReference dr,dreff;
    ArrayList<AppointmentInfo> dctrlst;
    AutoCompleteTextView autoCompleteTextView;
    String uid;
    ListView lv;
    Button btn;
    Query usrqry,qry;
    ClinicCancelAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_date_list);

        lv=findViewById(R.id.RetriveInfo);
        autoCompleteTextView=findViewById(R.id.List);
        dctrlst=new ArrayList<AppointmentInfo>();
        //session=new Session(this);

        btn=findViewById(R.id.btnSubmit1);

        Intent i=getIntent();
        doc=i.getStringExtra("doc");
        date=i.getStringExtra("date");
        //time=i.getStringExtra("time");

        dreff= FirebaseDatabase.getInstance().getReference("AppointmentInfo");

        dreff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    AppointmentInfo user1=ds.getValue(AppointmentInfo.class);
                    if(doc.compareTo(user1.getDocname())==0) {
                        if(date.compareTo(user1.getDate())==0)
                        {
                            dctrlst.add(user1);
                        }
                    }



                     adapter=new ClinicCancelAdapter(DocDateList.this,dctrlst);
                    lv.setAdapter(adapter);

                    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            String sme=autoCompleteTextView.getText().toString();
                            //Toast.makeText(PatientCancelAppointment.this,txt,Toast.LENGTH_LONG).show();
                        }
                    });

                    //dctrlst.add(user);


                }





            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AppointmentInfo ty= adapter.getItem(i);
                autoCompleteTextView.setText(ty.getName());
                uid=ty.getUid();

               // Toast.makeText(DocDateList.this,uid,Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qry=dreff.orderByChild("uid").equalTo(uid);

                qry.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot value:dataSnapshot.getChildren())
                        {
                           value.getRef().removeValue();
                        }


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();

                    }
                });


                Toast.makeText(getApplicationContext(),"Appointment Cancelled Sucessfully!", Toast.LENGTH_LONG).show();

                final Intent c=new Intent(getApplicationContext(),DocDateList.class);
                //c.putExtra("clinic",C);

                startActivity(c);

            }
        });






    }
}
