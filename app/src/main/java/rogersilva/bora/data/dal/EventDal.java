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

    /**
     * Get event by id
     * @param id
     * @return Event object
     */
    public Event getEvent(long id) {

        return null;
    }

    /**
     * Insert event on database
     * @param id
     * @param name
     * @param description
     * @return Row id
     */
    public long insertEvent(long id, String name, String description) {

        final int INVALID_ROW_ID = -1;

        // Id must be greater than zero and name cannot be null or empty
        if (id <= 0 || StringUtils.isNullOrEmpty(name))
            return INVALID_ROW_ID;

        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(EventEntry.COLUMN_NAME_ID, id);
        values.put(EventEntry.COLUMN_NAME_NAME, name);

        long newRowId = db.insert(EventEntry.TABLE_NAME, null, values);

        db.close();

        return newRowId;
    }
}