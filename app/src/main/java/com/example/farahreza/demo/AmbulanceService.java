package com.example.farahreza.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class AmbulanceService extends AppCompatActivity {
EditText vecname,contact,email;
    ClinicSignUpInformation user;
  //  Ambulance user;
    FirebaseAuth mAuth;
    DatabaseReference dRef, dRef1;
    String clinicname;
    Query usrqry;
    Session session;
    String type="Ambulance";
    Button btn;
    String strname,strcontact,stremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ambulance_service);
        vecname=findViewById(R.id.vecname);
        contact=findViewById(R.id.contact);
        btn=findViewById(R.id.SaveButton);
        email=findViewById(R.id.email);

        mAuth=FirebaseAuth.getInstance();
        dRef= FirebaseDatabase.getInstance().getReference("DoctorInfo");
        dRef1=FirebaseDatabase.getInstance().getReference("ClinicSignUpInformation");
        session=new Session(this);





        dRef1= FirebaseDatabase.getInstance().getReference().child("ClinicSignUpInformation");
        usrqry=dRef1.orderByKey().equalTo(session.getusename());

        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    user=value.getValue(ClinicSignUpInformation.class);
                }


                clinicname=user.getName();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strname=vecname.getText().toString().trim();
                strcontact=contact.getText().toString().trim();
                stremail=email.getText().toString().trim();





                Ambulance newuser=new Ambulance(strname,strcontact,stremail,type);
                dRef.child(clinicname).push().setValue(newuser);

                Toast.makeText(getApplicationContext(), "Ambulance Inserted successfully!", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
