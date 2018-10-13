package com.example.farahreza.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientCancelAppointment extends AppCompatActivity {

    ListView lv;

    ArrayList<AppointmentCancel> dctrlst;
    AutoCompleteTextView autoCompleteTextView;
    DatabaseReference dr,dreff;
    Query usrqry,qry;
    String name;
    Button btn;
    Session session;
    String dname;
    String date;
    String capacity,datcap,cap,key,fixcap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_cancel_appointment);

        autoCompleteTextView=findViewById(R.id.List);
        lv=findViewById(R.id.RetriveInfo);
        dctrlst=new ArrayList<AppointmentCancel>();
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

        dreff=FirebaseDatabase.getInstance().getReference("PatientUsers");
        usrqry=dreff.orderByKey().equalTo(session.getusename());
        usrqry.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot value:dataSnapshot.getChildren())
                {
                    PatientUsers users=value.getValue(PatientUsers.class);

                    name= users.getName();
                }

                dr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            AppointmentCancel user1=ds.getValue(AppointmentCancel.class);
                            if(name.compareTo(user1.getName())==0) {
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

                        CancelAdapter adapter=new CancelAdapter(PatientCancelAppointment.this,dctrlst);
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

                               dname=autoCompleteTextView.getText().toString();
                                //Toast.makeText(PatientCancelAppointment.this,txt,Toast.LENGTH_LONG).show();
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

                       dname=lv.getItemAtPosition(i).toString();
                        autoCompleteTextView.setText(dname);

                        //Toast.makeText(PatientCancelAppointment.this,text,Toast.LENGTH_LONG).show();
                    }
                });



                //  Name=user.getName();


                //FirebaseUser user = mAuth.getCurrentUser();
                //  String userid=user.getUid();
                //Toast.makeText(getApplicationContext()," "+userid,Toast.LENGTH_SHORT).show();

                //PatientUsers newuser=new PatientUsers(Name,Phone,email,P);


                //reference.child(userid).setValue(newuser);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                qry=dr.orderByChild("docname").equalTo(dname);
                qry.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot value:dataSnapshot.getChildren())
                        {
                           date=value.getValue(AppointmentInfo.class).getDate();
                            value.getRef().removeValue();
                        }

                  final   DatabaseReference nwdr=FirebaseDatabase.getInstance().getReference("Capacity").child(dname);
                        Query qry1=nwdr.orderByChild("date").equalTo(date);
                        qry1.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for(DataSnapshot val:dataSnapshot.getChildren())
                                {
                                    capacity=val.getValue(CapacityDoc.class).getCurcapacity();
                                    datcap=val.getValue(CapacityDoc.class).getDate();
                                    cap=val.getValue(CapacityDoc.class).getCapacity();
                                    key=val.getKey();
                                  //  flag=1;
                                }
                              CapacityDoc  newcap=new CapacityDoc(dname,date,Integer.toString(Integer.parseInt(cap)+1),Integer.toString(Integer.parseInt(capacity)-1));
                                nwdr.child(key).setValue(newcap);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(),"Error", Toast.LENGTH_LONG).show();

                    }
                });



                Toast.makeText(getApplicationContext(),"Appointment Cancelled Sucessfully!", Toast.LENGTH_LONG).show();

                final Intent c=new Intent(getApplicationContext(),PatientCancelAppointment.class);
                //c.putExtra("clinic",C);

                startActivity(c);




            }
        });



    }
}
