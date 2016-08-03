package com.example.oo.googlemapapidemo;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

public class mapFragmentActivity extends Activity {
   // private GoogleMap googleMap;
   private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_fragment);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.9),(int)(height*.8));

        ImageButton closeButton = (ImageButton) findViewById(R.id.bClose);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        MarkerOnMap info = (MarkerOnMap) getIntent().getSerializableExtra("Info");

        TextView nameBuilding=(TextView) findViewById(R.id.name);;
        nameBuilding.setText(info.getBuildingName());
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        ImageView img = (ImageView) findViewById(R.id.img);
        Picasso.with(this).load(info.getPhoto()).into(img);

        TextView address =(TextView) findViewById(R.id.address);
        TextView tel =(TextView) findViewById(R.id.tel);
        TextView fax =(TextView) findViewById(R.id.fax);
        TextView website =(TextView) findViewById(R.id.website);
        TextView opentime =(TextView) findViewById(R.id.opentime);

        address.setText(info.getAddress());
        tel.setText(info.getTel());
        fax.setText(info.getFax());
        website.setText(info.getWebsite());
        opentime.setText(info.getOpenTime());

    }


}
