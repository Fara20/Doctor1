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

public class SIgnup extends AppCompatActivity {
    EditText Email;
    EditText Password,RePass;
    Button SignUp2;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    String email,password,rePass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Email=findViewById(R.id.SignupEmail);
        Password=findViewById(R.id.signupPass);
        RePass=findViewById(R.id.pass);

        mAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);



        SignUp2=findViewById(R.id.SignUpButton);

        SignUp2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                password=Password.getText().toString().trim();
                rePass=RePass.getText().toString().trim();
                if(!password.equals(rePass))
                {
                    Toast.makeText(getApplicationContext(),"Passwords Don't match", Toast.LENGTH_LONG).show();

                }
                else {
                    createAccount();
                }

            }
        });

    }

    void createAccount()
    {
        email=Email.getText().toString().trim();
        password=Password.getText().toString().trim();

        if(!email.isEmpty()&&!password.isEmpty())
        {
            progressDialog.setMessage("Please Wait!!");
            progressDialog.show();

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                final  Intent c=new Intent(getApplicationContext(),MainActivity.class);
                                progressDialog.dismiss();
                                startActivity(c);
                               // Toast.makeText(getApplicationContext(),"Created Successfully", Toast.LENGTH_LONG).show();
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(getApplicationContext(), "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();

                            }

                            progressDialog.dismiss();

                            // ...
                        }
                    });

        }

    }
}
