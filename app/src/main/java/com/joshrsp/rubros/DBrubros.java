package com.joshrsp.rubros;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Usuario on 27/03/2015.
 */
public class DBrubros extends SQLiteOpenHelper{

    private static final String DB_NAME = "rubros";
    private static final int DB_SCHEME_VERSION =1;

    public DBrubros(Context context) {
        super(context, DB_NAME, null, DB_SCHEME_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(bdManager.CREATE_TABLE);
        db.execSQL(bdManager.CREATE_TABLE2);
        db.execSQL(bdManager.CREATE_TABLE3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
