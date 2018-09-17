package com.example.farahreza.demo;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SIgnup extends AppCompatActivity {
  EditText Email,Password,RePass,name,mobile;
    Button SignUpbtn;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String email,password,repass,nameF,mobileF;
    DatabaseReference reference,reff;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        name=findViewById(R.id.SignupName);
        mobile=findViewById(R.id.signupPhoneNumber);
        SignUpbtn=findViewById(R.id.SignUpButton);
        Email=findViewById(R.id.SignupEmail);
        Password=findViewById(R.id.signupPass);
        RePass=findViewById(R.id.pass);
        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        reference= FirebaseDatabase.getInstance().getReference("PatientUsers");
        reff=FirebaseDatabase.getInstance().getReference("Users");


password=Password.getText().toString().trim();
repass=RePass.getText().toString().trim();



        SignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(!password.equals(repass))
               {
                   Toast.makeText(getApplicationContext(),"Passwords doesn't Match",Toast.LENGTH_SHORT).show();
               }
               else
               {
                   createAccount();
               }

            }
        });

    }

    void createAccount()
    {
        email=Email.getText().toString().trim();
        nameF=name.getText().toString();
        mobileF=mobile.getText().toString();
        password=Password.getText().toString().trim();
        repass=RePass.getText().toString().trim();
        Intent i=getIntent();
        final String P=i.getStringExtra("patient");
        if(!email.isEmpty()&&!password.isEmpty()) {
            progressDialog.setMessage("Please Wait!!");
            progressDialog.show();
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information



                                FirebaseUser user = mAuth.getCurrentUser();
                                String userid=user.getUid();
                               // Toast.makeText(getApplicationContext(),"Success",Toast.LENGTH_SHORT).show();

                                PatientUsers newuser=new PatientUsers(nameF,mobileF,email,password);
                                Users usr= new Users(P,userid);
                                reference.child(userid).setValue(newuser);
                                reff.child(userid).setValue(usr);


                                Intent c=new Intent(getApplicationContext(),MainActivity.class);
                                progressDialog.dismiss();
                                startActivity(c);

                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(),"Failed to create an account",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                            }
                            progressDialog.dismiss();

                            // ...
                        }
                    });
        }

    }
}
