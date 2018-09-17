package com.example.farahreza.demo;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class clinicsignup extends AppCompatActivity {
    EditText cn;
    EditText ce;
    EditText pass;
    EditText repass;
    EditText cc;
    EditText spn;
    Button su;

    DatabaseReference refDatabase;
    FirebaseAuth mAuth;
    ClinicSignUpInformation clinicInfo;
    String sName;
    String sEmail,sPhoneNo,sPass;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cn=findViewById(R.id.ClinicName);
        ce=findViewById(R.id.ClinicEmail);
        pass=findViewById(R.id.Password);
        repass=findViewById(R.id.Repassword);
        cc=findViewById(R.id.CountryCode);
        spn=findViewById(R.id.SignupPhoneNumber);
        su=findViewById(R.id.SignUpButton);
        setContentView(R.layout.activity_clinicsignup);

        mAuth=FirebaseAuth.getInstance();
        refDatabase= FirebaseDatabase.getInstance().getReference();
        pd = new ProgressDialog(this);

        su.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllInputData();
                createClinic();
                createAccountAndSaveInfo();
            }
        });
    }

    void getAllInputData(){
        sName=cn.getText().toString();
        sPhoneNo= spn.getText().toString();
        sEmail=ce.getText().toString();
        sPass=pass.getText().toString();
    }
    void createClinic(){
        clinicInfo=new ClinicSignUpInformation(sName,sPhoneNo,sEmail);
    }

    void createAccountAndSaveInfo(){
        pd.setMessage("please wait");
        pd.show();
        mAuth.createUserWithEmailAndPassword(sEmail, sPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MainActivity", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            refDatabase=FirebaseDatabase.getInstance().getReference();
                            refDatabase.child(user.getUid()).setValue(clinicInfo);
                            // updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MainActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(clinicsignup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                        pd.dismiss();

                        // ...
                    }
                });
    }
}
