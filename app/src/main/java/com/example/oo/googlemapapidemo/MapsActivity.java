package com.example.oo.googlemapapidemo;

import android.*;
import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import Modules.DirectionFinder;
import Modules.DirectionFinderListener;
import Modules.Route;
import cz.msebera.android.httpclient.Header;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, DirectionFinderListener {


    //marker
    //private  static final String jsonUrl2 = "https://api.myjson.com/bins/2n66z";
    private  static final String jsonUrl2 = "https://1f99f4d7f9fa4de30eaeb10ebda674de689327f0.googledrive.com/host/0B6YeQ42-X0UXcHdYUU53QThvczg";
    //  private  static final String jsonUrl = "https://api.myjson.com/bins/3vbp7";

    MarkerOptions[] placeMarker = new MarkerOptions[4];
    ArrayList<MarkerOnMap> mBuildingList2;
    LatLng[] p1Latlng ;
    //
    private GoogleMap mMap;
    private Button searchRoomCode;
    private EditText roomcode;
   // MyHBHandler dbHandler;
    TextView test2;

    //
    AsyncHttpClient client;
    //
    //////////////GPS Variable////////////
    LocationManager locationManager;
    double longitudeGPS, latitudeGPS;
    TextView longitudeValueGPS, latitudeValueGPS;

    /////////////END GPS/////////////////

    ////// LatLng destination
    String Latdestination ="";
    String Lngdestination = "";
    final ArrayList<IRoom> mBuildingList  = new ArrayList<>();;


    //    private EditText etDestination;
    private List<Marker> originMarkers = new ArrayList<>();
    private List<Marker> destinationMarkers = new ArrayList<>();
    private List<Polyline> polylinePaths = new ArrayList<>();
    private ProgressDialog progressDialog;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client2;

    public MapsActivity() {
    }

    private static final String jsonUrl = "https://cc80551a20ff26d4fbcc2eea1f94217f8621c88b-www.googledrive.com/host/0B1MI36XS00J6YUJ5aHZxX1lKT1k";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        //o Obtain the SupportMapFragment and get notified when the map is ready to be used.
        final SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        searchRoomCode = (Button) findViewById(R.id.SearchButton);
        roomcode = (EditText) findViewById(R.id.roomCode);
        //////////GPS//////////////

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        longitudeValueGPS = (TextView) findViewById(R.id.longitudeValueGPS);
//        latitudeValueGPS = (TextView) findViewById(R.id.latitudeValueGPS);


        ////////////////////////


        client = new AsyncHttpClient();
        client.get(jsonUrl, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {

                    ;
                    final JSONArray buildings = response.getJSONArray("room");
                    for (int i = 0; i < buildings.length(); i++) {
                        JSONObject buildingObject = buildings.getJSONObject(i);
                        IRoom item = new IRoom();
                        String roomcodename = buildingObject.getString("code");

                        item.set_roomcode(buildingObject.getString("code"));
                        item.set_buildingname(buildingObject.getString("building"));
                        item.set_floor(buildingObject.getString("floor"));
                        item.set_note(buildingObject.getString("Note"));
                        item.set_lat(buildingObject.getString("lat"));
                        item.set_lng(buildingObject.getString("lng"));

                        mBuildingList.add(item);


                    }
                    searchRoomCode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String roomcodeinput = roomcode.getText().toString();
                            if (roomcode.getText().toString().isEmpty()) {
                                Toast.makeText(MapsActivity.this, "Please enter room code!!", Toast.LENGTH_SHORT).show();
                            }else if (checkroom()==false&&roomcode.getText().toString().isEmpty()==false){
                                Toast.makeText(MapsActivity.this, "Wrong room code , try again!!", Toast.LENGTH_SHORT).show();}
                            else {

                                for (int i = 0; i < mBuildingList.size(); i++) {

                                    if (roomcodeinput.equals(mBuildingList.get(i).get_roomcode().toString())) {
                                        Intent intent = new Intent(MapsActivity.this, pop.class);
                                        intent.putExtra("roomcode_pop", mBuildingList.get(i).get_roomcode().toString());

                                        intent.putExtra("buildingname_pop", mBuildingList.get(i).get_buildingname().toString());
                                        intent.putExtra("floor_pop", mBuildingList.get(i).get_floor().toString());
                                        intent.putExtra("note_pop", mBuildingList.get(i).get_note().toString());
                                        startActivity(intent);

                                    }
                                }


                            }
                        }
                    });


                } catch (JSONException e) {
                    Toast.makeText(MapsActivity.this, "type wrong name of json array", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
    private boolean checkroom (){
        boolean result = false;
        String roomcodeinput = roomcode.getText().toString();
        for (int i = 0; i < mBuildingList.size(); i++) {
        if(roomcodeinput.equals(mBuildingList.get(i).get_roomcode().toString())){
                result = true;
            }
        }

        return  result;
    }

///////////////////////////START GPS////////////////////////////////////////////
    private boolean checkLocation() {
        if (!isLocationEnabled())
            showAlert();
        return isLocationEnabled();
    }

    private void showAlert() {
        final android.support.v7.app.AlertDialog.Builder dialog = new android.support.v7.app.AlertDialog.Builder(this);
        dialog.setTitle("Enable Location")
                .setMessage("Your Locations Settings is set to 'Off'.\nPlease Enable Location to " +
                        "use this app")
                .setPositiveButton("Location Settings", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        Intent myIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(myIntent);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                    }
                });
        dialog.show();
    }

    private boolean isLocationEnabled() {
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void toggleGPSUpdates(View view) {
        if (!checkLocation())
            return;
        Button button = (Button) view;
        sendRequest();
        if (button.getText().equals(getResources().getString(R.string.pause))) {
            locationManager.removeUpdates(locationListenerGPS);
            button.setText(R.string.resume);
        } else {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 2 * 60 * 1000, 10, locationListenerGPS);
            button.setText(R.string.pause);
        }
    }

    private final LocationListener locationListenerGPS = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitudeGPS = location.getLongitude();
            latitudeGPS = location.getLatitude();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
//                   longitudeValueGPS.setText(longitudeGPS + "");
//                  latitudeValueGPS.setText(latitudeGPS + "");
                    Toast.makeText(MapsActivity.this, "GPS Provider update", Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };
////////////////////////////////END GPS/////////////////////////////////////


    private void sendRequest() {
        String latitudDes ="";
        String lngtitudDes ="";
        String roomcodeinput = roomcode.getText().toString();
        if(roomcodeinput.isEmpty()){
            Toast.makeText(MapsActivity.this, "Please enter room code!! ", Toast.LENGTH_SHORT).show();}
        else if (checkroom()==false){   Toast.makeText(MapsActivity.this, "Wrong room code , try again!!", Toast.LENGTH_SHORT).show();}
        else {
            for (int i = 0; i < mBuildingList.size(); i++) {


                if (roomcodeinput.equals(mBuildingList.get(i).get_roomcode().toString())) {
                    latitudDes = mBuildingList.get(i).get_lat().toString();
                    lngtitudDes = mBuildingList.get(i).get_lng().toString();
                }
            }
            String lngGPS = String.valueOf(longitudeGPS);
            String latGPS = String.valueOf(latitudeGPS);

            String origin = latGPS + "," + lngGPS;

            String destination = latitudDes + "," + lngtitudDes;
            if (origin.isEmpty()) {
                Toast.makeText(this, "Room code is wrong or using lower case character!", Toast.LENGTH_SHORT).show();
                return;
            }
            if (destination.isEmpty()) {
                Toast.makeText(this, "Room code is wrong or using lower case character!", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                new DirectionFinder(this, origin, destination).execute();
            } catch (UnsupportedEncodingException e) {
                Toast.makeText(this, "Room code is wrong or using lower case character!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(18.8043626, 98.9506613), 15));
        //  mMap.getUiSettings().setZoomGesturesEnabled(false);
//        LatLng cm = new LatLng(18.7997008, 98.9508558);
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(cm, 16.25f));
//        originMarkers.add(mMap.addMarker(new MarkerOptions()
//                .title("Chaing Mai Unibversity")
//                .position(cm)));
        // AsyncHttpClient client = new AsyncHttpClient();

        client.get(jsonUrl2,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    mBuildingList2 = new ArrayList<>();
                    //    mListView  = (ListView) findViewById(R.id.list_actor);

                    JSONArray buildings = response.getJSONArray("building");

                    for (int i = 0; i < buildings.length(); i++) {
                        JSONObject building = buildings.getJSONObject(i);
                        MarkerOnMap item = new MarkerOnMap();

                        item.setBuildingName(building.getString("name"));
                        item.setAddress(building.getString("address"));
                        item.setTel(building.getString("tel"));
                        item.setFax(building.getString("fax"));
                        item.setOpenTime(building.getString("opentime"));
                        item.setWebsite(building.getString("website"));
                        item.setPhoto(building.getString("img"));
                        item.setLat(Double.parseDouble(building.getString("lat")));
                        item.setLng(Double.parseDouble(building.getString("lng")));
                        mBuildingList2.add(item);
                    }
                    markerSet(mBuildingList2);
                    mMap.addMarker(placeMarker[0]);
                    mMap.addMarker(placeMarker[1]);
                    mMap.addMarker(placeMarker[2]);
                    mMap.addMarker(placeMarker[3]);

                    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener(){
                        @Override
                        public boolean onMarkerClick(Marker marker) {
                            //  if(marker.getTitle().equals("place1")) // if marker source is clicked
                            MarkerOnMap markerInfo = new MarkerOnMap("q","q","q","q","q","q","q",55.22,55.22);
                            Intent nextScreen = new Intent(MapsActivity.this, mapFragmentActivity.class);
                            if (marker.getSnippet().equals("0")){
                                markerInfo = (MarkerOnMap) mBuildingList2.get(0);
                            }
                            else if(marker.getSnippet().equals("1")){
                                markerInfo = (MarkerOnMap) mBuildingList2.get(1);
                            }
                            else if(marker.getSnippet().equals("2")){
                                markerInfo =(MarkerOnMap) mBuildingList2.get(2);
                            }
                            else if(marker.getSnippet().equals("3")){
                                markerInfo = (MarkerOnMap) mBuildingList2.get(3);
                            }
                            else {
                                Toast.makeText(MapsActivity.this, "type wrong", Toast.LENGTH_SHORT).show();
                            }

                            nextScreen.putExtra("Info",(Serializable) markerInfo);
                            startActivity(nextScreen);
                            return false;
                        }
                    });
                } catch (JSONException e1) {
                    Toast.makeText(MapsActivity.this, "type wrong name of json array", Toast.LENGTH_SHORT).show();
                }}

        });
    }

    @Override
    public void onDirectionFinderStar() {
        progressDialog = ProgressDialog.show(this, "Please wait.",
                "Finding direction..!", true);

        if (originMarkers != null) {
            for (Marker marker : originMarkers) {
                marker.remove();
            }
        }

        if (destinationMarkers != null) {
            for (Marker marker : destinationMarkers) {
                marker.remove();
            }
        }

        if (polylinePaths != null) {
            for (Polyline polyline : polylinePaths) {
                polyline.remove();
            }
        }
    }

    @Override
    public void onDirectionFinderSuccess(List<Route> routes) {
        progressDialog.dismiss();
        polylinePaths = new ArrayList<>();
        originMarkers = new ArrayList<>();
        destinationMarkers = new ArrayList<>();

        for (Route route : routes) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(route.startLocation, 16));
//            ((TextView) findViewById(R.id.tvDuration)).setText(route.duration.text);
            ((TextView) findViewById(R.id.tvDistance)).setText(route.distance.text);

            originMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_start))
                    .title(route.startAddress)
                    .position(route.startLocation)));
            destinationMarkers.add(mMap.addMarker(new MarkerOptions()
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.mark_end))
                    .title(route.endAddress)
                    .position(route.endLocation)));

            PolylineOptions polylineOptions = new PolylineOptions().
                    geodesic(true).
                    color(Color.BLUE).
                    width(10);

            for (int i = 0; i < route.points.size(); i++)
                polylineOptions.add(route.points.get(i));

            polylinePaths.add(mMap.addPolyline(polylineOptions));
        }
    }

    //marker//
    public MarkerOptions[] markerSet(ArrayList<MarkerOnMap> list){

        for (int i = 0; i< list.size();i++){
            // p1Latlng[i] = new LatLng(list.get(i).getLat(), list.get(i).getLng());
            placeMarker[i]= new MarkerOptions().position(new LatLng(list.get(i).getLat(), list.get(i).getLng())).snippet(Integer.toString(i)).icon(BitmapDescriptorFactory.fromResource(R.drawable.rpin));
            System.out.print(list.get(i).getLat());
            System.out.print(list.get(i).getLng());
        }
        return placeMarker;
    }


}
