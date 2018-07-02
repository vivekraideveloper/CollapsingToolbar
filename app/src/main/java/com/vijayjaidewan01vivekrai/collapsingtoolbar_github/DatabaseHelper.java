package com.vijayjaidewan01vivekrai.collapsingtoolbar_github;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.view.Menu;

import com.bumptech.glide.request.target.SquaringDrawable;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Data;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Menu_items;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.Results;
import com.vijayjaidewan01vivekrai.collapsingtoolbar_github.Models.ToolBar;

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
    private static final String KEY_NAV_HEADER = "nav_header_text";

    //TABLE DETAILS FOR TOOLBAR
    private static final String TABLE_TOOLBAR = "ToolBar";
    private static final String KEY_EXTENDED_TITLE = "extended_title";
    private static final String KEY_COLLAPSED_TITLE = "collapsed_title";
    private static final String KEY_TOOLBAR_BG = "toolbar_bg";
    private static final String KEY_EXTENDED_TITLE_COLOR = "extended_title_color";
    private static final String KEY_COLLAPSED_TITLE_COLOR = "collapsed_title_color";
    private static final String KEY_IS_BACK = "is_back";
    private static final String KEY_BACK_URL = "back_url";
    //Parameter to store the Title of Navigation Drawer

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DATA);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAV);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_VIEW);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOOLBAR);
        db.execSQL("DROP TABLE IF EXISTS NavHeadTitle ");

        //CREATE TABLE FOR DATA INFO
        db.execSQL("CREATE TABLE " + TABLE_DATA + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_TEXT1 + " TEXT," + KEY_TEXT1_SIZE + " TEXT," + KEY_TEXT1_COLOR + " TEXT," +
                    KEY_TEXT2 + " TEXT," + KEY_TEXT2_SIZE + " TEXT," + KEY_TEXT2_COLOR + " TEXT," +
                    KEY_TEXT3 + " TEXT," + KEY_TEXT3_SIZE + " TEXT," + KEY_TEXT3_COLOR + " TEXT," +
                    BACKGROUND + " TEXT," + URL + " TEXT)");

        //CREATE TABLE FOR NAVGITION DRAWER ITEMS
        db.execSQL("CREATE TABLE " + TABLE_NAV + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_TEXT1 + " TEXT," + KEY_TEXT1_COLOR + " TEXT," + URL + " TEXT)" );

        //CREATE TABLE FOR STORING THE VARIOUS COMPONENTS OF VIEW TYPE
        db.execSQL("CREATE TABLE " + TABLE_VIEW + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_TYPE + " INTEGER," + KEY_COL + " INTEGER," + KEY_ORIENTATION + " INTEGER," +
                    KEY_ISSEARCH + " INTEGER," + KEY_NAV_HEADER + " TEXT)");

        //CREATE TABLE FOR STORING VARIOUS COMPONENTS OF TOOLBAR
        db.execSQL("CREATE TABLE " + TABLE_TOOLBAR + "(" + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    KEY_EXTENDED_TITLE + " TEXT," + KEY_EXTENDED_TITLE_COLOR + " TEXT," +
                    KEY_COLLAPSED_TITLE + " TEXT," + KEY_COLLAPSED_TITLE_COLOR + " TEXT," +
                    KEY_TOOLBAR_BG + " TEXT," + KEY_IS_BACK + " INTEGER," + KEY_BACK_URL + " TEXT)");

        db.execSQL("CREATE TABLE NavHeadTitle (" + KEY_NAV_HEADER + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onCreate(db);
    }

    public void saveData(ArrayList<Data> data) {
        SQLiteDatabase db = this.getWritableDatabase();
//        onUpgrade(db, 1, 2);
        for (Data d : data) {
            ContentValues cv = new ContentValues();
            cv.put(KEY_TEXT1, d.getText1());
            cv.put(KEY_TEXT1_SIZE, d.getText_header_size());
            cv.put(KEY_TEXT1_COLOR, d.getText_header_color());
            cv.put(KEY_TEXT2, d.getText2());
            cv.put(KEY_TEXT2_SIZE, d.getText_subheader_size());
            cv.put(KEY_TEXT2_COLOR, d.getText_subheader_color());
            cv.put(KEY_TEXT3, d.getText3());
            cv.put(KEY_TEXT3_SIZE, d.getText_description_size());
            cv.put(KEY_TEXT3_COLOR, d.getText_description_color());
            cv.put(BACKGROUND, d.getBg_color());
            cv.put(URL, d.getUrl());
            long rowID = db.insert(TABLE_DATA, null, cv);
            Log.i("DATA:Row ID ", "" + rowID);
        }
    }

    public void saveMenu(ArrayList<Menu_items> menu)
    {
        SQLiteDatabase db = getWritableDatabase();
//        onUpgrade(db, 1, 2);
        for (Menu_items d : menu)
        {
            ContentValues cv = new ContentValues();
            cv.put(KEY_TEXT1,d.getItem());
            cv.put(KEY_TEXT1_COLOR,d.getText_color());
            cv.put(URL,d.getUrl());
            long rowID = db.insert(TABLE_NAV,null,cv);
            Log.i("NAV:Row ID",""+rowID);
        }
    }

    public void saveToolbar(ToolBar toolBar){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_EXTENDED_TITLE,toolBar.getExtended_top_title());
        cv.put(KEY_EXTENDED_TITLE_COLOR,toolBar.getExtended_top_title_color());
        cv.put(KEY_COLLAPSED_TITLE,toolBar.getCollapsed_top_title());
        cv.put(KEY_COLLAPSED_TITLE_COLOR,toolBar.getCollapsed_top_title_color());
        cv.put(KEY_TOOLBAR_BG,toolBar.getToolbar_bg());
        cv.put(KEY_IS_BACK,Integer.parseInt(toolBar.getIs_back()));
        cv.put(KEY_BACK_URL,toolBar.getBack_url());
        long rowID = db.insert(TABLE_TOOLBAR,null,cv);
        Log.i("TOOLBAR:Row ID",""+rowID);
    }

    public void saveView(Results results)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_TYPE,Integer.parseInt(results.getView_type()));
        cv.put(KEY_COL,Integer.parseInt(results.getGrid_columns()));
        cv.put(KEY_ORIENTATION,Integer.parseInt(results.getGrid_orientation()));
        cv.put(KEY_ISSEARCH,Integer.parseInt(results.getIs_search()));
//        cv.put(KEY_NAV_HEADER,results.getNavDrawer().getHeader_layout().getText());
        long rowID = db.insert(TABLE_VIEW,null,cv);
        Log.i("VIEW:Row ID",""+rowID);
    }

    public void saveHeaderTitle(String title)
    {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAV_HEADER,title);
        long rowID = db.insert("NavHeadTitle",null,cv);
        Log.i("Title:Row ID", ""+rowID);
    }

    public ArrayList<Data> readData()
    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Data> data = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_DATA ;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst())
        {
            do {
                Data d = new Data();
                d.setBg_color(cursor.getString(cursor.getColumnIndex(BACKGROUND)));
                d.setText1(cursor.getString(cursor.getColumnIndex(KEY_TEXT1)));
                d.setText_header_size(cursor.getString(cursor.getColumnIndex(KEY_TEXT1_SIZE)));
                d.setText_header_color(cursor.getString(cursor.getColumnIndex(KEY_TEXT1_COLOR)));
                d.setText2(cursor.getString(cursor.getColumnIndex(KEY_TEXT2)));
                d.setText_subheader_size(cursor.getString(cursor.getColumnIndex(KEY_TEXT2_SIZE)));
                d.setText_subheader_color(cursor.getString(cursor.getColumnIndex(KEY_TEXT2_COLOR)));
                d.setText3(cursor.getString(cursor.getColumnIndex(KEY_TEXT3)));
                d.setText_description_size(cursor.getString(cursor.getColumnIndex(KEY_TEXT3_SIZE)));
                d.setText_description_color(cursor.getString(cursor.getColumnIndex(KEY_TEXT3_COLOR)));
                d.setUrl(cursor.getString(cursor.getColumnIndex(URL)));

                data.add(d);
            }while (cursor.moveToNext());
        }
        db.close();

        return data;
    }

    public ArrayList<Menu_items> readMenu()
    {
        SQLiteDatabase db = getReadableDatabase();
        ArrayList<Menu_items> menu_items = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_NAV;
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst())
        {
            do {
                Menu_items menu = new Menu_items();
                menu.setItem(cursor.getString(cursor.getColumnIndex(KEY_TEXT1)));
                menu.setText_color(cursor.getString(cursor.getColumnIndex(KEY_TEXT1_COLOR)));
                menu.setUrl(cursor.getString(cursor.getColumnIndex(URL)));

                menu_items.add(menu);
            }while (cursor.moveToNext());
        }
        db.close();

        return menu_items;
    }

    public ToolBar readToolbar()
    {
        SQLiteDatabase db = getReadableDatabase();
        ToolBar toolBar = new ToolBar();

        String selectQuery = "SELECT * FROM " + TABLE_TOOLBAR ;
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst())
        {
            toolBar.setBack_url(cursor.getString(cursor.getColumnIndex(KEY_BACK_URL)));
            toolBar.setCollapsed_top_title(cursor.getString(cursor.getColumnIndex(KEY_COLLAPSED_TITLE)));
            toolBar.setCollapsed_top_title_color(cursor.getString(cursor.getColumnIndex(KEY_COLLAPSED_TITLE_COLOR)));
            toolBar.setExtended_top_title(cursor.getString(cursor.getColumnIndex(KEY_EXTENDED_TITLE)));
            toolBar.setExtended_top_title_color(cursor.getString(cursor.getColumnIndex(KEY_EXTENDED_TITLE_COLOR)));
            toolBar.setIs_back(String.valueOf(cursor.getInt(cursor.getColumnIndex(KEY_IS_BACK))));
            toolBar.setToolbar_bg(cursor.getString(cursor.getColumnIndex(KEY_TOOLBAR_BG)));
        }
        db.close();

        return toolBar;
    }

    public Results readResults()
    {
        SQLiteDatabase db = getReadableDatabase();
        Results results = new Results();

        String selectQuery = "SELECT * FROM " + TABLE_VIEW ;
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst())
        {
            results.setView_type(String.valueOf(cursor.getInt(cursor.getColumnIndex(KEY_TYPE))));
            results.setGrid_columns(String.valueOf(cursor.getInt(cursor.getColumnIndex(KEY_COL))));
            results.setGrid_orientation(String.valueOf(cursor.getInt(cursor.getColumnIndex(KEY_ORIENTATION))));
            results.setIs_search(String.valueOf(cursor.getInt(cursor.getColumnIndex(KEY_ISSEARCH))));
//            results.getNavDrawer().getHeader_layout().setText(cursor.getString(cursor.getColumnIndex(KEY_NAV_HEADER)));
        }
        db.close();

        return results;
    }

    public String readTitle()
    {
        SQLiteDatabase db = getReadableDatabase();
        String title = "";

        String selectQuery = "SELECT * FROM NavHeadTitle";
        Cursor cursor = db.rawQuery(selectQuery,null);

        if (cursor.moveToFirst())
        {
            title = cursor.getString(cursor.getColumnIndex(KEY_NAV_HEADER));
        }
        db.close();

        return title;
    }
}
