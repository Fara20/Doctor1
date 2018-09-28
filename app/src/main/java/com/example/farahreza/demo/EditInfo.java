package com.example.farahreza.demo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
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

public class EditInfo extends AppCompatActivity {
    EditText Name,Email,Pass,RePass,Phone,OldPass;
    String Placename,name,email,phone,pass,oldpass,name1,email1,phone1,Place1,pass1,repass1;
    DatabaseReference reference,reff;
    Query usrqry,qry;
    ClinicSignUpInformation user;
    Button btn;
    Users usr;
    Session session;
    FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    TextWatcher textWatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);
        Name = findViewById(R.id.SignupName);
        Email = findViewById(R.id.SignupEmail);
        Pass = findViewById(R.id.signupPass);
        RePass = findViewById(R.id.pass);
        Phone = findViewById(R.id.signupPhoneNumber);
        btn = findViewById(R.id.SaveButton);
        mAuth = FirebaseAuth.getInstance();
        OldPass=findViewById(R.id.oldpass);
        progressDialog = new ProgressDialog(this);
        Place1=null;


        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        session = new Session(this);
        reference = FirebaseDatabase.getInstance().getReference().child("ClinicSignUpInformation");
        usrqry = reference.orderByKey().equalTo(session.getusename());

        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot value : dataSnapshot.getChildren()) {
                    user = value.getValue(ClinicSignUpInformation.class);
                }

                name = user.getName();
                email = user.getEmail();
                pass = user.getPassword();
                phone = user.getPassword();
                Placename = user.getLocation();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });


        Name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Name.setHint(user.getName());
                }
            }
        });


        Email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Email.setHint(user.getEmail());
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
                    Phone.setHint(user.getPhoneNo());
                }
            }
        });


        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("BD")
                .build();

        autocompleteFragment.setFilter(typeFilter);


        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                // Log.i(TAG, "Place: " + place.getName());
                Place1 = place.getName().toString();


                //Toast.makeText(getApplicationContext()," "+Placename,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
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
        name1=Name.getText().toString();
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
        if(Place1!=null && !Place1.isEmpty())
        {
            Placename=Place1;
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

        ClinicSignUpInformation newuser=new ClinicSignUpInformation(name,email,phone,Placename,pass);

        reference.child(userid).setValue(newuser);



        Intent c=new Intent(getApplicationContext(),ClinicService.class);
        Toast.makeText(getBaseContext(),"Info Saved",Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();
        startActivity(c);


    }
    }

