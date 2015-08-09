package com.orogersilva.bora.data.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.orogersilva.bora.data.contracts.EventContract.EventEntry;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    // if changes the database schema, must increment the database version
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Bora.db";

    // region CONSTRUCTORS

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // endregion

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(EventEntry.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(EventEntry.SQL_DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onUpgrade(db, oldVersion, newVersion);
    }
}
