package rogersilva.bora.data.bll;

import rogersilva.bora.data.dal.EventDal;

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


}
