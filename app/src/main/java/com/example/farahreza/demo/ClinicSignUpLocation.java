package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

public class ClinicSignUpLocation extends AppCompatActivity {

    String Placename,type;
    Button sbmt;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_sign_up_location);
        sbmt=findViewById(R.id.btnSubmit1);
        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setHint("Search  Location");
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setCountry("BD")
                .build();

        autocompleteFragment.setFilter(typeFilter);





        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                // Log.i(TAG, "Place: " + place.getName());
             Placename=place.getName().toString();
             flag=1;


                //Toast.makeText(getApplicationContext()," "+Placename,Toast.LENGTH_LONG).show();

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.
                //Log.i(TAG, "An error occurred: " + status);
            }
        });



        sbmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag!=0)
                {
                    Intent i=getIntent();
                    final String C=i.getStringExtra("clinic");

                    final  Intent c=new Intent(getApplicationContext(),clinicsignup.class);
                    c.putExtra("clinic",C);
                    c.putExtra("place",Placename);
                    startActivity(c);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Please Select a Location",Toast.LENGTH_LONG).show();
                }
                }

        });


    }
}
