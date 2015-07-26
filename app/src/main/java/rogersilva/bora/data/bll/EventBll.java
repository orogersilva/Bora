package rogersilva.bora.data.bll;

import rogersilva.bora.data.dal.EventDal;
import rogersilva.bora.models.Event;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public class EventBll {

    // region INSTANCE VARIABLES

    private EventDal mEventDal;

    // endregion

    // region CONSTRUCTORS

    public EventBll(EventDal eventDal) {

        this.mEventDal = eventDal;
    }

    // endregion

    public Event getEvent(long id) {

        // Id must be greater than zero
        if (id <= 0)
            return null;

        return mEventDal.getEvent(id);
    }

    public void insertEvent(long id, String name, String description) {

        mEventDal.insertEvent(id, name, description);
    }
}
