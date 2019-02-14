package com.example.dung.assigment_update.SQlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.nio.DoubleBuffer;

public class Database extends SQLiteOpenHelper {
    public Database(Context context) {
        super(context, "assignment_update2.sqlite",null,1);
    }

    public void SendData(String sql){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }

    public Cursor GetData(String sql){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery(sql,null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE KHOANTHU(ID INTEGER PRIMARY KEY AUTOINCREMENT, NGAYTHANG NVARCHAR, KHOANTHU NVARCHAR) ");
        db.execSQL("CREATE TABLE LOAITHU(ID INTEGER PRIMARY KEY AUTOINCREMENT, NGAYTHANG NVARCHAR, LOAITHU NVARCHAR)");
        db.execSQL("CREATE TABLE KHOANCHI(ID INTEGER PRIMARY KEY AUTOINCREMENT, NGAYTHANG NVARCHAR, KHOANCHI NVARCHAR) ");
        db.execSQL("CREATE TABLE LOAICHI(ID INTEGER PRIMARY KEY AUTOINCREMENT, NGAYTHANG NVARCHAR, LOAICHI NVARCHAR)");
        db.execSQL("CREATE TABLE NGAYTHANG(NGAYTHANG NVARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS KHOANTHU");
        db.execSQL("DROP TABLE IF EXISTS LOAITHU");
        db.execSQL("DROP TABLE IF EXISTS KHOANCHI");
        db.execSQL("DROP TABLE IF EXISTS LOAICHI");
        db.execSQL("DROP TABLE IF EXISTS NGAYTHANG");
        onCreate(db);
    }
}
