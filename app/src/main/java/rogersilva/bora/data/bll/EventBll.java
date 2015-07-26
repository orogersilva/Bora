package rogersilva.bora.data.bll;

import rogersilva.bora.data.dal.EventDal;
import rogersilva.bora.models.Event;

/**
 * Created by RogerSilva on 7/26/2015.
 */
public class EventBll {

    // region INSTANCE VARIABLES

    private EventDal eventDal;

    // endregion

    // region CONSTRUCTORS

    public EventBll(EventDal eventDal) {

        this.eventDal = eventDal;
    }

    // endregion

    public Event getEvent(long id) {

        return eventDal.getEvent(id);
    }

    public void insertEvent(long id, String name, String description) {

        eventDal.insertEvent(id, name, description);
    }
}
