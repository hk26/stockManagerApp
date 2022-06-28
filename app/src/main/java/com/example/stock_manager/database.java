package com.example.stock_manager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class database extends SQLiteOpenHelper {
    public database(Context context) {
        super(context,"STdatabase.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Stock(id integer PRIMARY KEY AUTOINCREMENT,item text,itemcount integer,profitcount integer)");
        db.execSQL("create table TodoList(id integer  PRIMARY KEY AUTOINCREMENT,task text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    String tname = "TodoList",col1="id",col2="task";

    public boolean insert(String id,String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col1,id);
        contentValues.put(col2,task);
        long res = db.insert(tname,null,contentValues);
        if (res == -1)
            return false;
        else
            return true;

    }

//    public Cursor showdb(){
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cur = db.rawQuery("select * from ce12",null);
//        return  cur;
//    }

    String TABLE_NAME = "TodoList";

    // we have created a new method for reading all the courses.
    public ArrayList<ModelClass> readCourses() {
        // on below line we are creating a
        // database for reading our database.
        SQLiteDatabase db = this.getReadableDatabase();

        // on below line we are creating a cursor with query to read data from database.
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        // on below line we are creating a new array list.
        ArrayList<ModelClass> ModelClassArrayList = new ArrayList<>();

        // moving our cursor to first position.
        if (cursorCourses.moveToFirst()) {
            do {
                // on below line we are adding the data from cursor to our array list.
                ModelClassArrayList.add(new ModelClass(cursorCourses.getString(1)));
            } while (cursorCourses.moveToNext());
            // moving our cursor to next.
        }
        // at last closing our cursor
        // and returning our array list.
        cursorCourses.close();
        return ModelClassArrayList;
    }

}
