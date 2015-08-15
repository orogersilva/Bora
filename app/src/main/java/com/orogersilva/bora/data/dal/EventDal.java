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

package com.orogersilva.bora.data.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.orogersilva.bora.data.contracts.EventContract.EventEntry;
import com.orogersilva.bora.data.helpers.DatabaseHelper;
import com.orogersilva.bora.models.Event;
import com.orogersilva.bora.utils.StringUtils;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public class EventDal {

    // region INSTANCE VARIABLES

    private DatabaseHelper mDbHelper;

    // endregion

    // region CONSTRUCTORS

    public EventDal(DatabaseHelper mDbHelper) {

        this.mDbHelper = mDbHelper;
    }

    // endregion

    public Event getEvent(long id) {

        // Id must be greater than zero
        if (id <= 0)
            return null;

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String selection = EventEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        Cursor c = db.query(EventEntry.TABLE_NAME, null, selection, selectionArgs, null, null, null);
        c.moveToFirst();

        Event retrievedEvent;

        try {

            long eventId = c.getLong(c.getColumnIndexOrThrow(EventEntry.COLUMN_NAME_ID));
            String eventName = c.getString(c.getColumnIndexOrThrow(EventEntry.COLUMN_NAME_NAME));
            String eventDescription = c.getString(c.getColumnIndexOrThrow(EventEntry.COLUMN_NAME_DESCRIPTION));

            retrievedEvent = new Event(eventId, eventName, eventDescription);
        }
        catch (IllegalArgumentException e) {

            retrievedEvent = null;
        }
        finally {

            c.close();
            db.close();
        }

        return retrievedEvent;
    }

    public boolean insertEvent(long id, String name, String description) {

        // Id must be greater than zero and name cannot be null or empty
        if (id <= 0 || StringUtils.isNullOrEmpty(name))
            return false;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EventEntry.COLUMN_NAME_ID, id);
        values.put(EventEntry.COLUMN_NAME_NAME, name);
        values.put(EventEntry.COLUMN_NAME_DESCRIPTION, description);

        long newRowId = db.insert(EventEntry.TABLE_NAME, null, values);

        db.close();

        return (newRowId > 0) ? true : false;
    }

    public boolean updateEvent(long id, String name, String description) {

        // Id must be greater than zero
        if (id <= 0)
            return false;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EventEntry.COLUMN_NAME_NAME, name);
        values.put(EventEntry.COLUMN_NAME_DESCRIPTION, description);

        String selection = EventEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        int affectedRows = db.update(EventEntry.TABLE_NAME, values, selection, selectionArgs);

        return ((affectedRows > 0) ? true : false);
    }

    public boolean deleteEvent(long id) {

        // Id must be greater than zero
        if (id <= 0)
            return false;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        String selection = EventEntry.COLUMN_NAME_ID + " = ?";
        String[] selectionArgs = {
                String.valueOf(id)
        };

        int deletedRows = db.delete(EventEntry.TABLE_NAME, selection, selectionArgs);

        db.close();

        return ((deletedRows > 0) ? true : false);
    }
}