package com.example.oo.googlemapapidemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.text.Text;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by oo on 29/7/2559.
 */
public class buildingAdapter extends ArrayAdapter {
        Context mContext;
        int mResource;
        ArrayList<IRoom> mObjects;
        public buildingAdapter(Context context, int resource, ArrayList<IRoom> objects) {
            super(context, resource, objects);
            mContext = context;
            mResource = resource;
            mObjects = objects;

        }
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(mResource, parent, false);

        TextView roomcode = (TextView) convertView.findViewById(R.id.roodcode_actor);
        TextView buildingName = (TextView) convertView.findViewById(R.id.buildingname_actor);
        TextView floor = (TextView) convertView.findViewById(R.id.floor_actor);
        TextView note =(TextView)  convertView.findViewById(R.id.note_actor);


//        Picasso.with(mContext).load(mObjects.get(position).get_roomcode()).into(roomcode);
        roomcode.setText(mObjects.get(position).get_roomcode());

        buildingName.setText(mObjects.get(position).get_buildingname());
        floor.setText(mObjects.get(position).get_floor());
        note.setText(mObjects.get(position).get_note());


        return  convertView;
    }


}


