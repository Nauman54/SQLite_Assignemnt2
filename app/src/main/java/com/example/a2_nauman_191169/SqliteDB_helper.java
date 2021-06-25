package com.example.a2_nauman_191169;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class SqliteDB_helper extends SQLiteOpenHelper {

    private Context mainContext;
    private static String DATABASE_NAME = "StudentRecords.db";
    private static int DATABASE_VERSION = 1;
    private static String TABLE_NAME = "StudentInfo";
    private String query_create = "create table " + TABLE_NAME + " (std_id TEXT primary key, " +
            "std_name TEXT, std_city TEXT, std_age TEXT)";

    public SqliteDB_helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mainContext = context;
        Toast.makeText(context, "Database Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(query_create);
        Toast.makeText(mainContext, "Table Created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean insertStudentData(String s_id, String s_name, String s_city, String s_age){
        SQLiteDatabase sql_db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("std_id", s_id);
        contentValues.put("std_name", s_name);
        contentValues.put("std_city", s_city);
        contentValues.put("std_age", s_age);
        long insertCheck = sql_db.insert(TABLE_NAME, null, contentValues);
        if(insertCheck == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getStudentData(){
        SQLiteDatabase sql_db = this.getWritableDatabase();
        Cursor cursor = sql_db.rawQuery("select * from " + TABLE_NAME, null);
        return cursor;
    }

}
