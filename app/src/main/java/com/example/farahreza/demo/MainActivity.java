package com.example.farahreza.demo;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    TextView SignUpbtn;
    Button loginbtn;
    EditText Email;
    EditText Password;
    String email,password;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    Session session;
    DatabaseReference ref;
    Query qry;
    PatientUsers user1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SignUpbtn=findViewById(R.id.SignUp);
        loginbtn=findViewById(R.id.button);
        Email=findViewById(R.id.email);
        Password=findViewById(R.id.pass);
        ref= FirebaseDatabase.getInstance().getReference().child("PatientUsers");

        session=new Session(this);
        if(!session.getusename().isEmpty())
        {
            Intent n = new Intent(getApplicationContext(), PatientServices.class);
            startActivity(n);
        }

        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        Email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int  actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {

                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });


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
                                String userid=user.getUid();
                                qry=ref.orderByKey().equalTo(userid);

                            /*    qry.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for(DataSnapshot value:dataSnapshot.getChildren())
                                        {
                                            user1=value.getValue(PatientUsers.class);
                                        }
                                        if(user1.getType=="Patient")
                                        {

                                        }
                                        else if()
                                        {

                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                */
                                Intent c = new Intent(getApplicationContext(), PatientServices.class);

                                //Toast.makeText(getApplicationContext(),"LogInSuccess", Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                session.setusename(userid);
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
