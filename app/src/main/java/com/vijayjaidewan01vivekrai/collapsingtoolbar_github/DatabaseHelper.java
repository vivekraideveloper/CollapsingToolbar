package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Menu;

import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Data;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Menu_items;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String LOG = "DatabaseHelper";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Information";

    //TABLE DETAILS FOR DATA
    private static final String TABLE_DATA = "dataInfo";
    private static final String KEY_ID = "id";
    private static final String KEY_TEXT1 = "text_1";
    private static final String KEY_TEXT2 = "text_2";
    private static final String KEY_TEXT3 = "text_3";
    private static final String KEY_TEXT1_SIZE = "text_1_size";
    private static final String KEY_TEXT2_SIZE = "text_2_size";
    private static final String KEY_TEXT3_SIZE = "text_3_size";
    private static final String KEY_TEXT1_COLOR = "text_1_color";
    private static final String KEY_TEXT2_COLOR = "text_2_color";
    private static final String KEY_TEXT3_COLOR = "text_3_color";
    private static final String BACKGROUND = "background";
    private static final String URL = "url";

    //TABLE DETAILS FOR NAVIGATION DRAWER DATA
    private static final String TABLE_NAV = "NavDrawerData";

    //TABLE DETAILS FOR VIEW STATE
    private static final String TABLE_VIEW = "ViewData";
    private static final String KEY_TYPE = "type";
    private static final String KEY_COL = "columns";
    private static final String KEY_ORIENTATION = "orientation";
    private static final String KEY_ISSEARCH = "is_search";

    //TABLE DETAILS FOR TOOLBAR
    private static final String TABLE_TOOLBAR = "ToolBar";
    private static final String KEY_EXTENDED_TITLE = "extended_title";
    private static final String KEY_COLLAPSED_TITLE = "collapsed_title";
    private static final String KEY_TOOLBAR_BG = "toolbar_bg";
    private static final String KEY_EXTENDED_TITLE_COLOR = "extended_title_color";
    private static final String KEY_COLLAPSED_TITLE_COLOR = "collapsed_title_color";
//    private static final String KEY_
//    private static final String KEY_



    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(" CREATE TABLE " + TABLE_DATA + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_TEXT1 + " TEXT," + KEY_TEXT1_SIZE + " TEXT," + KEY_TEXT1_COLOR + " TEXT," +
                    KEY_TEXT2 + " TEXT," + KEY_TEXT2_SIZE + " TEXT," + KEY_TEXT2_COLOR + " TEXT," +
                    KEY_TEXT3 + " TEXT," + KEY_TEXT3_SIZE + " TEXT," + KEY_TEXT3_COLOR + " TEXT," +
                    BACKGROUND + " TEXT," + URL + " TEXT)");

        db.execSQL("CREATE TABLE " + TABLE_NAV + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TEXT1 + " TEXT," + KEY_TEXT1_COLOR + " TEXT," + URL + " TEXT)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAV);

        onCreate(db);
    }

    public void saveToDatabase(ArrayList<Data> data)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        onUpgrade(db,1,2);
        for (Data d : data)
        {
            ContentValues cv = new ContentValues();
            cv.put(KEY_TEXT1,d.getText1());
            cv.put(KEY_TEXT1_SIZE,d.getText_header_size());
            cv.put(KEY_TEXT1_COLOR,d.getText_header_color());
            cv.put(KEY_TEXT2,d.getText2());
            cv.put(KEY_TEXT2_SIZE,d.getText_subheader_size());
            cv.put(KEY_TEXT2_COLOR,d.getText_subheader_color());
            cv.put(KEY_TEXT3,d.getText3());
            cv.put(KEY_TEXT3_SIZE,d.getText_description_size());
            cv.put(KEY_TEXT3_COLOR,d.getText_description_color());
            cv.put(BACKGROUND,d.getBg_color());
            cv.put(URL,d.getUrl());
            long rowID = db.insert(TABLE_DATA,null,cv);
            Log.i("DATA:Row ID ",""+rowID);
        }

//        for (Menu_items d : menu)
//        {
//            ContentValues cv = new ContentValues();
//            cv.put(KEY_TEXT1,d.getItem());
//            cv.put(KEY_TEXT1_COLOR,d.getText_color());
//            cv.put(URL,d.getUrl());
//            long rowID = db.insert(TABLE_NAV,null,cv);
//            Log.i("NAV:Row ID",""+rowID);
//        }



    }

    public ArrayList<Data> readDatabase()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Data> data = new ArrayList<>();


        return data;
    }
}
