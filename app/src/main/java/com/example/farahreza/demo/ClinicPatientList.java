package com.example.farahreza.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ClinicPatientList extends AppCompatActivity {

    ListView lv;

    ArrayList<AppointmentInfo> dctrlst;
    AutoCompleteTextView autoCompleteTextView;
    DatabaseReference dr,dreff;
    Query usrqry,qry;
    String clinicName;
    Button btn;
    Session session;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_patient_list);

        autoCompleteTextView=findViewById(R.id.List);
        lv=findViewById(R.id.RetriveInfo);
        dctrlst=new ArrayList<AppointmentInfo>();
        session=new Session(this);

        btn=findViewById(R.id.btnSubmit1);

        lv.setOnTouchListener(new ListView.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        // Disallow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(true);
                        break;

                    case MotionEvent.ACTION_UP:
                        // Allow ScrollView to intercept touch events.
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        break;
                }

                // Handle ListView touch events.
                v.onTouchEvent(event);
                return true;
            }
        });

        dr= FirebaseDatabase.getInstance().getReference("AppointmentInfo");
        dreff=FirebaseDatabase.getInstance().getReference("ClinicSignUpInformation");
        usrqry=dreff.orderByKey().equalTo(session.getusename());

        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot value : dataSnapshot.getChildren())


                {
                   ClinicSignUpInformation user = value.getValue(ClinicSignUpInformation.class);
                   clinicName=user.getName();
                }

                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {
                           AppointmentInfo user1=ds.getValue(AppointmentInfo.class);
                            if(clinicName.compareTo(user1.getHospital())==0) {
                                dctrlst.add(user1);
                            }
                            //dctrlst.add(user);


                        }




                    /* ArrayAdapter<String> adapter = new ArrayAdapter<String>(DoctorList.this,android.R.layout.simple_list_item_1,dctname){
                        @Override
                        public View getView(int position, View convertView, ViewGroup parent) {
                            View view =super.getView(position, convertView, parent);

                            TextView textView=(TextView) view.findViewById(android.R.id.text1);

                            /*YOUR CHOICE OF COLOR*/
                        // textView.setTextColor(Color.parseColor("#a50303"));

                        // return view;
                        // }
                        //  };

                        //sort();

                        AppointmentAdapter adapter=new AppointmentAdapter(ClinicPatientList.this,dctrlst);
                        //  lv.setAdapter(adapter);

                        //  ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(DoctorList.this,android.R.layout.simple_list_item_1,dctname);
                        //  ArrayAdapter<DoctorInfo> adapter2 = new ArrayAdapter<DoctorInfo>(DoctorList.this,android.R.layout.simple_list_item_1,dctrlst);

                        autoCompleteTextView.setAdapter(adapter);

                        lv.setAdapter(adapter);


                       /* autoCompleteTextView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(lv.getVisibility()==View.VISIBLE){
                                    lv.setVisibility(View.GONE);
                                }else{
                                    lv.setVisibility(View.VISIBLE);
                                }
                            }
                        });*/

                        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                String txt=autoCompleteTextView.getText().toString();
                                Toast.makeText(ClinicPatientList.this,txt,Toast.LENGTH_LONG).show();
                            }
                        });



                        // studentAdapter adapter=new studentAdapter(DoctorList.this,dctrlst);
                        //  lv.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
                    }
                });

                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String text=lv.getItemAtPosition(i).toString();
                        Toast.makeText(ClinicPatientList.this,text,Toast.LENGTH_LONG).show();
                    }
                });





            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ClinicPatientList.this,clinicName,Toast.LENGTH_LONG).show();
            }
        });


    }
}
