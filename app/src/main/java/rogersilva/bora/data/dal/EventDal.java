package rogersilva.bora.data.dal;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import rogersilva.bora.data.contracts.EventContract.EventEntry;
import rogersilva.bora.data.helpers.DatabaseHelper;
import rogersilva.bora.models.Event;
import rogersilva.bora.utils.StringUtils;

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

        return null;
    }

    public boolean insertEvent(long id, String name, String description) {

        // Id must be greater than zero and name cannot be null or empty
        if (id <= 0 || StringUtils.isNullOrEmpty(name))
            return false;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EventEntry.COLUMN_NAME_ID, id);
        values.put(EventEntry.COLUMN_NAME_NAME, name);

        long newRowId = db.insert(EventEntry.TABLE_NAME, null, values);

        db.close();

        return (newRowId > 0) ? true : false;
    }
}