//package com.example.oo.googlemapapidemo;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
///**
// * Created by oo on 28/7/2559.
// */
//public class MyHBHandler extends SQLiteOpenHelper {
//    private static final int DATABASE_VERSTION  = 1;
//    private static final String DATABASE_NAME = "DBRoom.db";
//    public static  final String TABLE_ICROOM  = "Icroom";
//    public static final String COLUMN_iD = "_id";
//    public static final String COLUMN_ROOMCODE = "_roomcode";
//    public static final String COLUMN_BUILDINGNANE = "_buildingname";
//    public static final String COLUMN_NOTE = "_note";
//    public MyHBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, DATABASE_NAME, factory, DATABASE_VERSTION);
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
//        sqLiteDatabase.execSQL("DROP_TABLE IF EXISTS" +TABLE_ICROOM);
//        onCreate(sqLiteDatabase);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String query = "CREATE TABLE "+ TABLE_ICROOM+"("
//                +COLUMN_iD+" INTEGER PRIMARY KEY AUTOINCREMENT, " +
//                COLUMN_ROOMCODE+" TEXT, "+
//                COLUMN_BUILDINGNANE+" TEXT, "+
//                COLUMN_NOTE+" TEXT"+
//                ");";
//        sqLiteDatabase.execSQL(query);
//    }
//    public String getCodeNamebyCode(String code){
//        String dbStringS ="";
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT*FROM " + TABLE_ICROOM+" WHERE "+ COLUMN_ROOMCODE + "=\"" + code +"\";";
//        Cursor c = db.rawQuery(query,null);
//        c.moveToFirst();
//        while (!c.isAfterLast()){
//            if(c.getString(c.getColumnIndex("_roomcode"))!=null){
//                dbStringS = c.getString(c.getColumnIndex("_roomcode"));
////                dbStringS += "\n";
////                dbStringS += c.getString(c.getColumnIndex("productname"));
////                dbStringS += "\n";
//            }
//            c.moveToNext();
//        }
//        c.close();
//
//        db.close();
//        return  dbStringS;
//
//    }
//    public String getBuildingnamebyCode(String code){
//        String dbStringS ="";
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT*FROM " + TABLE_ICROOM+" WHERE "+ COLUMN_ROOMCODE + "=\"" + code +"\";";
//        Cursor c = db.rawQuery(query,null);
//        c.moveToFirst();
//        while (!c.isAfterLast()){
//            if(c.getString(c.getColumnIndex("_roomcode"))!=null){
//                dbStringS = c.getString(c.getColumnIndex("_buildingname"));
////                dbStringS += "\n";
////                dbStringS += c.getString(c.getColumnIndex("productname"));
////                dbStringS += "\n";
//            }
//            c.moveToNext();
//        }
//        c.close();
//
//        db.close();
//        return  dbStringS;
//
//    }
//    public String getNotebyCode(String code){
//        String dbStringS ="";
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT*FROM " + TABLE_ICROOM+" WHERE "+ COLUMN_ROOMCODE + "=\"" + code +"\";";
//        Cursor c = db.rawQuery(query,null);
//        c.moveToFirst();
//        while (!c.isAfterLast()){
//            if(c.getString(c.getColumnIndex("_roomcode"))!=null){
//                dbStringS = c.getString(c.getColumnIndex("_note"));
////                dbStringS += "\n";
////                dbStringS += c.getString(c.getColumnIndex("productname"));
////                dbStringS += "\n";
//            }
//            c.moveToNext();
//        }
//        c.close();
//
//        db.close();
//        return  dbStringS;
//
//    }
//    public void addroom (IRoom room){
//        ContentValues value = new ContentValues();
//        value.put(COLUMN_ROOMCODE,room.get_roomcode());
//        value.put(COLUMN_BUILDINGNANE,room.get_buildingname());
//        value.put(COLUMN_NOTE,room.get_note());
//
//        SQLiteDatabase db = getWritableDatabase();
//        db.insert(TABLE_ICROOM,null,value);
//        db.close();;
//    }
//    public String databaseToString(){
//        String dbString = "";
//        SQLiteDatabase db = getWritableDatabase();
//        String query = "SELECT * FROM " + TABLE_ICROOM+" WHERE 1";
//
//        //Cursor point to a location in your results
//        Cursor c = db.rawQuery(query,null);
//
//        c.moveToFirst();
//        while (!c.isAfterLast()){
//            if(c.getString(c.getColumnIndex("_roomcode"))!=null){
//                dbString += c.getString(c.getColumnIndex("_roomcode"))+" "+c.getString(c.getColumnIndex("_buildingname"))+" "+c.getString(c.getColumnIndex("_note"));
//                dbString += "\n";
//            }
//            c.moveToNext();
//        }
//        c.close();
//
//        db.close();
//        return  dbString;
//    }
//}
//
