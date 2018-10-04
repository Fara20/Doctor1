package com.example.farahreza.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class emergencyAdapter extends BaseAdapter {

    private Context activity;
    private ArrayList<EmergencyJava> allStudent = new ArrayList<>();

    private LayoutInflater layoutInflater = null;


    public emergencyAdapter(Context activity, ArrayList<EmergencyJava> allStudent) {
        this.activity = activity;
        this.allStudent = allStudent;
        Collections.sort(allStudent, new Comparator<EmergencyJava>() {
            @Override
            public int compare(EmergencyJava t2, EmergencyJava t1) {

                EmergencyJava e1=(EmergencyJava) t2;
                EmergencyJava e2=(EmergencyJava) t1;

                return  e1.getName().compareToIgnoreCase(e2.getName());

            }
        });
        this.layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    private static class ViewHolder{
        private TextView name, nmbr,place;
    }
    private emergencyAdapter.ViewHolder viewHolder = null;

    @Override
    public int getCount() {
        return allStudent.size();
    }

    @Override
    public EmergencyJava getItem(int position) {
        return allStudent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view=convertView;
        final int pos = position;
        if(view == null){
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.row_cell_student2,null);
            viewHolder.name = view.findViewById(R.id.name);
            viewHolder.nmbr = view.findViewById(R.id.contact);
            viewHolder.place= view.findViewById(R.id.place);


            view.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) view.getTag();
        }

        viewHolder.name.setText(allStudent.get(pos).getName());
        viewHolder.nmbr.setText(allStudent.get(pos).getNmbr());
        viewHolder.place.setText(allStudent.get(pos).getLocation());

        return view;
    }



}







