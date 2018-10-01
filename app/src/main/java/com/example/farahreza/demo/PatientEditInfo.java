package com.example.farahreza.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class PatientEditInfo extends AppCompatActivity {

    EditText Name1,Email,Pass,RePass,Phone,OldPass;
    String Placename,name,email,phone,pass,oldpass,name1,email1,phone1,Place1,pass1,repass1;
    DatabaseReference reference,reff;
    Query usrqry,qry;
    PatientUsers user;
    Button btn;
    Users usr;
    Session session;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    TextWatcher textWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_edit_info);


       Name1 = findViewById(R.id.Name);
        Email = findViewById(R.id.SignupEmail);
        Pass = findViewById(R.id.signupPass);
        RePass = findViewById(R.id.pass);
        Phone = findViewById(R.id.signupPhoneNumber);
        btn = findViewById(R.id.SaveButton);
        mAuth = FirebaseAuth.getInstance();
        OldPass=findViewById(R.id.oldpass);
        progressDialog = new ProgressDialog(this);
        Place1=null;

        session = new Session(this);
        reference = FirebaseDatabase.getInstance().getReference().child("PatientUsers");
        usrqry = reference.orderByKey().equalTo(session.getusename());

        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    user = value.getValue(PatientUsers.class);
                }

                name = user.getName();
                email = user.getEmail();
                pass = user.getPassword();
                phone = user.getPassword();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });

       Name1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Name1.setHint(name);
                }
            }
        });


        Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Email.setHint(email);
                }
            }
        });

        OldPass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    OldPass.setHint("Enter Old Password");
                }
            }
        });

        Pass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Pass.setHint("Enter New Password");
                }
            }
        });


        RePass.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    RePass.setHint("Re-Enter New Password");
                }
            }
        });

        Phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Phone.setHint(user.getPhone());
                }
            }
        });



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SaveInfo();


            }
        });


    }

    void SaveInfo()
    {


        email1=Email.getText().toString().trim();
        name1=Name1.getText().toString();
        phone1=Phone.getText().toString();
        oldpass=OldPass.getText().toString();
        pass1=Pass.getText().toString().trim();
        repass1=RePass.getText().toString().trim();

        if(!email1.isEmpty())
        {
            email=email1;
        }
        if(!name1.isEmpty())
        {
            name=name1;
        }
        if(!phone1.isEmpty())
        {
            phone=phone1;
        }

        if(!oldpass.isEmpty()) {
            if(oldpass.equals(pass)) {
                if (!pass1.isEmpty()) {
                    if (!repass1.isEmpty()) {
                        if (!pass1.equals(repass1)) {
                            Toast.makeText(getBaseContext(), "Passwords doesnt Match", Toast.LENGTH_SHORT).show();
                        } else {
                            pass = pass1;
                        }
                    } else {
                        Toast.makeText(getBaseContext(), "Please Re-enter Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            else
            {
                Toast.makeText(getBaseContext(), "Incorrect Password", Toast.LENGTH_SHORT).show();
            }
        }
        FirebaseUser user = mAuth.getCurrentUser();
        
        String userid=user.getUid();
        // Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();

        PatientUsers newuser=new PatientUsers(name,phone,email,pass);

        reference.child(userid).setValue(newuser);



        Intent c=new Intent(getApplicationContext(),PatientServices.class);
        Toast.makeText(getBaseContext(),"Info Saved",Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        startActivity(c);


    }
}
