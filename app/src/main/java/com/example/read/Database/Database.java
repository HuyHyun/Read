package com.example.read.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import com.example.read.Services.TinTuc;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }


    public void INSERT_Data(String tenbang, TinTuc tinTuc) {
        try {
            SQLiteDatabase database = getWritableDatabase();
            String sql = "INSERT INTO '" + tenbang + "' VALUES(null,?,?,?,?)";
            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();
            statement.bindString(1, tinTuc.getTieuDe());
            statement.bindString(2, tinTuc.getHinhAnh());
            statement.bindString(3, tinTuc.getLink());
            statement.bindString(4, tinTuc.getDate());
            statement.executeInsert();
        } catch (Exception e) {
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
