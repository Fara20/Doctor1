package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
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

public class PatientDocList extends AppCompatActivity {

    ListView lv;

    ArrayList<DoctorInfo> dctrlst;

    AutoCompleteTextView autoCompleteTextView;
    Button btn;
    int flag=0;

    DatabaseReference dr;
    String clinicname,location,docname,capacity;

     ClinicSignUpInformation user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_doc_list);

        autoCompleteTextView=findViewById(R.id.List);
        btn=findViewById(R.id.btnSubmit1);

        lv=findViewById(R.id.RetriveInfo);
        dctrlst=new ArrayList<DoctorInfo>();

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



        Intent i=getIntent();
        clinicname=i.getStringExtra("hos");
        location=i.getStringExtra("loca");
        //Toast.makeText(getApplicationContext(),clinicname,Toast.LENGTH_LONG).show();

        //Toast.makeText(getApplicationContext(),location,Toast.LENGTH_LONG).show();



        dr=FirebaseDatabase.getInstance().getReference("DoctorInfo").child(clinicname);

        dr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    DoctorInfo user=ds.getValue(DoctorInfo.class);
                    dctrlst.add(user);
                    //dctname.add(user.getName());

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

                studentAdapter adapter=new studentAdapter(PatientDocList.this,dctrlst);
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





                // studentAdapter adapter=new studentAdapter(DoctorList.this,dctrlst);
                //  lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                docname=autoCompleteTextView.getText().toString();
                flag=1;
                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            DoctorInfo user=ds.getValue(DoctorInfo.class);
                            if(user.getName().compareTo(docname)==0)
                            {
                                capacity=user.getCapacity();
                            }
                            // dctrlst.add(user);
                            //dctname.add(user.getName());

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

                //Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_LONG).show();
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              docname=lv.getItemAtPosition(i).toString();
              flag=1;
              dr.addValueEventListener(new ValueEventListener() {
                  @Override
                  public void onDataChange(DataSnapshot dataSnapshot) {
                      for (DataSnapshot ds:dataSnapshot.getChildren())
                      {
                          DoctorInfo user=ds.getValue(DoctorInfo.class);
                          if(user.getName().compareTo(docname)==0)
                          {
                              capacity=user.getCapacity();
                          }
                          // dctrlst.add(user);
                          //dctname.add(user.getName());

                      }
                  }

                  @Override
                  public void onCancelled(DatabaseError databaseError) {

                  }
              });

              autoCompleteTextView.setText(docname);
                //Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(flag==0)
                {
                    Toast.makeText(getApplicationContext(),"Please Select a Doctor",Toast.LENGTH_LONG).show();
                }

                else {
                    final Intent c = new Intent(getApplicationContext(), DocAppointmentSelect.class);
                    //c.putExtra("clinic",C);
                    c.putExtra("hos", clinicname);
                    c.putExtra("loca", location);
                    c.putExtra("doc", docname);
                    c.putExtra("capa",capacity);
                    startActivity(c);
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
}
