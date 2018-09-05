package com.example.farahreza.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    TextView SignUpbtn;
    Button loginbtn;
    EditText Email;
    EditText Password;
    String email,password;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUpbtn=findViewById(R.id.SignUp);
        loginbtn=findViewById(R.id.button);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.pass);

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);


        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                SignIn();
                //startActivity(c);

            }
        });

        SignUpbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent b=new Intent(getApplicationContext(),SignupOptions.class);
                startActivity(b);

            }
        });
    }

    void SignIn()
    {
        email=Email.getText().toString().trim();
        password=Password.getText().toString().trim();
        if(!email.isEmpty()&&!password.isEmpty()) {
            progressDialog.setMessage("Please Wait!!");
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();

                                Intent c = new Intent(getApplicationContext(), PatientServices.class);

                                //Toast.makeText(getApplicationContext(),"LogInSuccess", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(c);
                            } else {
                                // If sign in fails, display a message to the user.
                                Toast.makeText(MainActivity.this, "Auth failed", Toast.LENGTH_SHORT).show();
                            }

                            progressDialog.dismiss();

                            // ...
                        }
                    });

        }

        else
        {
            Toast.makeText(MainActivity.this, "Field Empty", Toast.LENGTH_SHORT).show();
        }




    }
}
