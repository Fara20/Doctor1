package com.example.farahreza.demo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class InsertDoctor extends AppCompatActivity {

    Button insertbtn;
    EditText name, capacity;
    RadioGroup grpspec, grpgender;
    RadioButton specbtn , genderbtn;
    Switch switch1, switch2, switch3;
    ClinicSignUpInformation user;
    DoctorInfo docuser;
    FirebaseAuth mAuth;
    DatabaseReference dRef, dRef1;
    String clinicname;
    Query usrqry;
    Session session;
    String type="Doctor";

    String strname,strcapacity,timeslot1,timeslot2,timeslot3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_doctor);

        insertbtn = findViewById(R.id.btn);
        name = findViewById(R.id.docname);
        capacity = findViewById(R.id.capacity);
        grpgender=findViewById(R.id.grpgender);
        grpspec=findViewById(R.id.grpspec);
        switch1=findViewById(R.id.switch1);
        switch2=findViewById(R.id.switch2);
        switch3=findViewById(R.id.switch3);



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


        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged (CompoundButton buttonView,boolean isChecked){
                // do something, the isChecked will be
                // true if the switch is in the On position
                timeslot1 = "8am-2pm";
            }
        });

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                timeslot2 = "4pm-8pm";
            }
        });
        switch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // do something, the isChecked will be
                // true if the switch is in the On position
                timeslot3 = "9pm-12am";
            }
        });


        strname=name.getText().toString().trim();
        strcapacity=capacity.getText().toString().trim();


        if(switch1.isChecked()){

            if (switch2.isChecked()){

                if (switch3.isChecked()){



                }

                timeslot3=null;

            }
            if (switch3.isChecked()){

                timeslot2=null;



            }

            timeslot2=null;
            timeslot3=null;



        }
        if (switch2.isChecked()){
            if (switch3.isChecked()){

                timeslot1=null;

            }
            timeslot1=null;
            timeslot3=null;

        }
        if (switch3.isChecked()){
            timeslot1=null;
            timeslot2=null;

        }


        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname=name.getText().toString().trim();
                strcapacity=capacity.getText().toString().trim();


                  int id1 = grpgender.getCheckedRadioButtonId();
                  genderbtn = findViewById(id1);
                String gender = "female";//genderbtn.getText().toString();

                int id2 = grpspec.getCheckedRadioButtonId();
                specbtn = findViewById(id2);
                String speciality = specbtn.getText().toString();


                DoctorInfo newuser=new DoctorInfo(strname,gender,speciality,strcapacity,timeslot1,timeslot2,timeslot3,type);
                dRef.child(clinicname).push().setValue(newuser);

                Toast.makeText(getApplicationContext(), "Doctor Inserted successfully!", Toast.LENGTH_SHORT).show();

            }
        });











    }
}
