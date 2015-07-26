package rogersilva.bora.data.dal;

import rogersilva.bora.data.helpers.DatabaseHelper;

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
}