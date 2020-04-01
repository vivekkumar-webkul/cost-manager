package com.example.costmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "register.db";
    public static final String TABLE_NAME = "registeruser";
    public static final String COL_ID= "ID";
    public static final String COL_NAME= "name";
    public static final String COL_USERNAME= "username";
    public static final String COL_PASSWORD= "password";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE registeruser (ID INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, username TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long addUser(String name, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_NAME, name);
        contentValues.put(COL_USERNAME, username);
        contentValues.put(COL_PASSWORD, password);
        long res = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return res;
    }

    public Cursor checkUser(String username, String password) {
        String[] columns = { COL_ID };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_USERNAME + "=?" + " and " + COL_PASSWORD + "=?";
        String[] selectionArgs = { username, password };

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        return cursor;
    }

    public Cursor getUserData(int userId) {
        String[] columns = { COL_ID, COL_NAME, COL_USERNAME, COL_PASSWORD };
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_ID + "=?";
        String[] selectionArgs = { String.valueOf(userId) };

        Cursor cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null);
        return cursor;
    }
    public void  updatePasswordAndName(String newPassword,String name, int userId)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        db.execSQL("UPDATE " + TABLE_NAME +" SET " + COL_PASSWORD +" ="+ "'" +newPassword + "'" + ","+ COL_NAME +" =" + "'" + name + "'" + " WHERE "+
        COL_ID +" = " + userId);
        db.close();
    }
}
