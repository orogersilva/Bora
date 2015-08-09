package com.orogersilva.bora.data.bll;

import android.content.Context;

import com.orogersilva.bora.data.dal.EventDal;
import com.orogersilva.bora.data.helpers.DatabaseHelper;
import com.orogersilva.bora.models.Event;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public class EventBll {

    // region INSTANCE VARIABLES

    private EventDal mEventDal;

    // endregion

    // region CONSTRUCTORS

    public EventBll(Context context) {

        mEventDal = new EventDal(new DatabaseHelper(context));
    }

    public EventBll(EventDal eventDal) {

        mEventDal = eventDal;
    }

    // endregion

    public Event getEvent(long id) {

        return mEventDal.getEvent(id);
    }

    public boolean insertEvent(long id, String name, String description) {

        return mEventDal.insertEvent(id, name, description);
    }

    public boolean updateEvent(long id, String name, String description) {

        return mEventDal.updateEvent(id, name, description);
    }

    public boolean deleteEvent(long id) {

        return mEventDal.deleteEvent(id);
    }
}
