package com.example.farahreza.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ClinicPassChange extends AppCompatActivity {

    EditText OldPass,Pass,NewPass;
    String Name,Phone,Email,oldPass,insert,pass1,newPass,L;
    Button Save;
    DatabaseReference reference;
    Session session;
   ClinicSignUpInformation user;
    FirebaseAuth mAuth;
    Query usrqry;
    ProgressDialog dialouge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_pass_change);
        OldPass=findViewById(R.id.oldpass);
        Pass=findViewById(R.id.Pass);
        NewPass=findViewById(R.id.conpass);
        Save=findViewById(R.id.Save);
        dialouge=new ProgressDialog(this);

        session=new Session(this);
        mAuth=FirebaseAuth.getInstance();
        reference= FirebaseDatabase.getInstance().getReference("ClinicSignUpInformation");
        usrqry=reference.orderByKey().equalTo(session.getusename());

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialouge.setMessage("Please Wait!");
                dialouge.show();


                usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot value:dataSnapshot.getChildren())
                        {
                            user=value.getValue(ClinicSignUpInformation.class);
                        }

                        Name=user.getName();
                        Phone=user.getPhoneNo();
                        Email=user.getEmail();
                        oldPass=user.getPassword();
                        L=user.getLocation();

                        insert=OldPass.getText().toString().trim();



                        if(insert.isEmpty())
                        {  //dialouge.dismiss();
                            Toast.makeText(getApplicationContext(), "Please Enter Current Password", Toast.LENGTH_LONG).show();
                        }
                        else {

                            if (oldPass.compareTo(insert) == 0) {
                                pass1 = Pass.getText().toString().trim();
                                newPass = NewPass.getText().toString().trim();
                                if(pass1.isEmpty())
                                {
                                    Toast.makeText(getApplicationContext(), "Please Enter New Password", Toast.LENGTH_LONG).show();
                                }
                                else {
                                    if(newPass.isEmpty())
                                    {
                                        Toast.makeText(getApplicationContext(), "Please Re-Enter New Pass", Toast.LENGTH_LONG).show();
                                    }
                                    else {

                                        if (pass1.compareTo(newPass) == 0) {

                                            final FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                                            final String userid = user1.getUid();
                                            AuthCredential credential = EmailAuthProvider
                                                    .getCredential(Email, oldPass);
                                            user1.reauthenticate(credential)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            if (task.isSuccessful()) {
                                                                user1.updatePassword(newPass).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        if (task.isSuccessful()) {

                                                                            ClinicSignUpInformation newuser = new ClinicSignUpInformation(Name, Email, Phone,L, pass1);


                                                                            reference.child(userid).setValue(newuser);
                                                                            //dialouge.dismiss();

                                                                            Toast.makeText(getApplicationContext(), "Successfully Changed", Toast.LENGTH_LONG).show();
                                                                            session.clearAll();
                                                                            final Intent ec = new Intent(getApplicationContext(), MainActivity.class);
                                                                            startActivity(ec);
                                                                        } else {
                                                                            Toast.makeText(getApplicationContext(), "Failed to Change Passwords", Toast.LENGTH_LONG).show();
                                                                        }
                                                                    }
                                                                });
                                                            } else {
                                                                Toast.makeText(getApplicationContext(), "Auth Failed", Toast.LENGTH_LONG).show();
                                                            }
                                                        }
                                                    });

                                        } else {
                                            Toast.makeText(getApplicationContext(), "Passwords Don't Match", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                                //Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Invalid Password ", Toast.LENGTH_LONG).show();
                            }
                            dialouge.dismiss();

                            //FirebaseUser user = mAuth.getCurrentUser();
                            //String userid=user.getUid();
                            //Toast.makeText(getApplicationContext()," "+userid,Toast.LENGTH_SHORT).show();

                            // PatientUsers newuser=new PatientUsers(Name,Phone,Email,oldPass);


                            // reference.child(userid).setValue(newuser);

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
                    }
                });


            }
        });
    }
}
