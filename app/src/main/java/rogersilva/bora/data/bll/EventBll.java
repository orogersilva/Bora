package rogersilva.bora.data.bll;

import android.content.Context;

import rogersilva.bora.data.dal.EventDal;
import rogersilva.bora.data.helpers.DatabaseHelper;
import rogersilva.bora.models.Event;

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
