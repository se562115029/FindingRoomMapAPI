package com.example.oo.googlemapapidemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

/**
 * Created by oo on 28/7/2559.
 */
public class pop extends Activity {
    public pop (){}
    TextView coderoomT;
    TextView buildinameT;
    TextView floorT;
    TextView noteT;
    ///// display
//    EditText userinput;
    TextView coderoomText;
    TextView buildinameText;
    TextView noteText;
    TextView floorText;
//    MyHBHandler dbHandler;
private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        userinput = (EditText) findViewById(R.id.editText);
        setContentView(R.layout.popwindow);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int)(width*.8),(int)(height*.6)); //// ปรับกว้างยาว
        Button close = (Button) findViewById(R.id.close_button);
        close.setOnClickListener(new View.OnClickListener() {

            public void onClick(View View) {
                finish();
            }
        });
        coderoomText= (TextView) findViewById(R.id.roodcode_actor);
        buildinameText = (TextView) findViewById(R.id.buildingname_actor);
        floorText= (TextView) findViewById(R.id.floor_actor);
        noteText = (TextView) findViewById(R.id.note_actor);
        TextView topic = (TextView) findViewById(R.id.Topic_actor);
//
//        IRoom info = (IRoom) getIntent().getSerializableExtra("buildingroom") ;
//        coderoomText.setText(info.get_roomcode());

        Intent intent = getIntent();
        String _roomcode = intent.getStringExtra("roomcode_pop");
        String _buildingname = intent.getStringExtra("buildingname_pop");
        String _floor = intent.getStringExtra("floor_pop");
        String _note = intent.getStringExtra("note_pop");



        coderoomText.setText(_roomcode);
        buildinameText.setText(_buildingname );
        floorText.setText(_floor);
        noteText.setText(_note );
        topic.setText("   Building Information");
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
//        ///// text topic
//        coderoomT = (TextView)findViewById(R.id.roomcodeView);
//        buildinameT = (TextView)findViewById(R.id.builingnmaeView);
//        floorT = (TextView) findViewById(R.id.floorView);
//        noteT = (TextView) findViewById(R.id.noteView);
//        ////// display
//        coderoomText = (TextView) findViewById(R.id.roodcode_actor);
//        buildinameText = (TextView) findViewById(R.id.buildingname_actor);
//        floorText = (TextView) findViewById(R.id.floor_actor);
//        noteText = (TextView) findViewById(R.id.note_actor);
//        dbHandler = new MyHBHandler(this,null,null,1);
//
//        String displayRoomCode =  dbHandler.getCodeNamebyCode(_roomcode);
//        String displayBuildingName =  dbHandler.getBuildingnamebyCode(_roomcode);
//        String displaynote =  dbHandler.getNotebyCode(_roomcode);
//        String displayFloor = getFloorbyRoomcode(_roomcode);
//        coderoomText.setText(displayRoomCode);
//        buildinameText.setText(displayBuildingName);
//        floorText.setText(displayFloor);
//        noteText.setText(displaynote );
//
//        //////////// topic
//        coderoomT.setText("Code Room :");
//        buildinameT.setText("Building Name :");
//        floorT.setText("Floor :");
//        noteT.setText("Note : ");
    }
    private void bindView() {

        coderoomText= (TextView) findViewById(R.id.roodcode_actor);
        buildinameText = (TextView) findViewById(R.id.buildingname_actor);
        floorText= (TextView) findViewById(R.id.floor_actor);
        noteText = (TextView) findViewById(R.id.note_actor);
    }

//    private void setViewToView() {
//        coderoomText.setText(_roomcode);
//        buildinameText = (TextView) findViewById(R.id.buildingname_actor);
//        floorText= (TextView) findViewById(R.id.floor_actor);
//        noteText = (TextView) findViewById(R.id.note_actor);
////        Picasso.with(this).load(selectedActor.getImage()).into(actorImage);
//        actorName.setText(_roomfode.getName());
//        actorDesc.setText(selectedActor.getDescription());
//        actorDob.setText(selectedActor.getDob());
//        actorCountry.setText(selectedActor.getCountry());
//        actorHeight.setText(selectedActor.getHeight());
//        actorSpouse.setText(selectedActor.getSpouse());
//        actorChildren.setText(selectedActor.getChildren());
    }
//    public void SearchButtonClicked(String roomcode){
////        String inputSearch = userinput.getText().toString();
//        String displayRoomCode =  dbHandler.getCodeNamebyCode(roomcode);
//        String displayBuildingName =  dbHandler.getBuildingnamebyCode(roomcode);
//        String displaynote =  dbHandler.getNotebyCode(roomcode);
//        String displayFloor = getFloorbyRoomcode(roomcode);
//        coderoomText.setText(displayRoomCode);
//        buildinameText.setText(displayBuildingName);
//        floorText.setText(displayFloor);
//        noteText.setText(displaynote );
//
//        //////////// topic
//        coderoomT.setText("Code Room :");
//        buildinameT.setText("Building Name :");
//        floorT.setText("Floor :");
//        noteT.setText("Note : ");
//    }

//    public String getFloorbyRoomcode (String roomcode){
//        String test = roomcode;
//        String[] t = test.split("(?!^)");
//        int count = t.length;
//
//        int index = (count-3);
//
//        return t[index];
//    }
//    public void Mock(){
//        AddproductMock("เข้าประตูเกษตรอยู่ซ้ายมือ","อาคารเรียนวิจิตรศิลป์","FA1307");
//        AddproductMock("เข้าประตูเกษตรอยู่ซ้ายมือ","อาคารเรียนวิจิตรศิลป์","FA1207");
//        AddproductMock("อยู่ข้าง อมช ","อาคารเรียนรวม 5","RB5210");
//        AddproductMock("อยู่ข้าง อมช ","อาคารเรียนรวม 5","RB5312");
//
//    }
//    public void AddproductMock(String note, String buildingname, String roomcode){
//        IRoom croom = new IRoom(note, buildingname, roomcode);
//
//
//        dbHandler.addroom(croom);
//
//    }
//    public void hideResult(){
//        coderoomT.setText("");
//        buildinameT.setText("");
//        floorT.setText("");
//        noteT.setText("");
//        ////// display
//        coderoomText.setText("");
//        buildinameText.setText("");
//        floorText.setText("");
//        noteText.setText("");
//    }
////    public void printDatabase(){
////        String dbString = dbHandler.databaseToString();
////        noteText.setText(dbString);
////        userinput.setText("");
////    }
//}
//
//
