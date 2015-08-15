/*
 * Copyright (C) 2015 Roger Silva
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

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

    @Override public void onCreate(SQLiteDatabase db) {

        db.execSQL(EventEntry.SQL_CREATE_TABLE);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(EventEntry.SQL_DROP_TABLE);
        onCreate(db);
    }

    @Override public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        onUpgrade(db, oldVersion, newVersion);
    }
}
