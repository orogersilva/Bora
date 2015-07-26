package rogersilva.bora.data.bll;

import android.test.suitebuilder.annotation.SmallTest;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import rogersilva.bora.data.dal.EventDal;
import rogersilva.bora.models.Event;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * Created by RogerSilva on 7/26/2015.
 */
@RunWith(MockitoJUnitRunner.class)
public class EventBllTest {

    // region SETUP TEST METHODS
    @BeforeClass
    public static void setUpClass() {

    }

    @Before
    public void setUp() {
    }

    // endregion

    // region TEST METHODS

    @SmallTest
    @Test
    public void getEvent_whenIdIsNotValid_returnsNull() {

        // ARRANGE
        final long NOT_VALID_EVENT_ID = -1;

        EventDal eventDalMock = mock(EventDal.class);
        EventBll eventBll = new EventBll(eventDalMock);

        // ACT
        Event retrievedEvent = eventBll.getEvent(NOT_VALID_EVENT_ID);

        // ASSERT
        assertNull(retrievedEvent);
    }

    @SmallTest
    @Test
    public void getEvent_whenIdIsNotFound_returnsNull() {

        // ARRANGE
        final long NOT_FOUND_EVENT_ID = 1;

        EventDal eventDalMock = mock(EventDal.class);
        EventBll eventBll = new EventBll(eventDalMock);

        // ACT
        Event retrievedEvent = eventBll.getEvent(NOT_FOUND_EVENT_ID);

        // ASSERT
        assertNull(retrievedEvent);
    }

    @SmallTest
    @Test
    public void getEvent_whenIdIsFound_returnsEvent() {

        // ARRANGE
        final long EVENT_ID = 1;
        final String EVENT_NAME = "Show do Guri de Uruguaiana";
        final String EVENT_DESCRIPTION = "Um show para ficar marcado na história do planeta Terra!!!";

        final String ERROR_MESSAGE = "Expected event is not equal to retrieved event.";

        EventDal eventDalMock = mock(EventDal.class);
        EventBll eventBll = new EventBll(eventDalMock);

        Event expectedEvent = new Event(EVENT_ID, EVENT_NAME, EVENT_DESCRIPTION);

        when(eventBll.getEvent(EVENT_ID)).thenReturn(expectedEvent);

        // ACT
        Event retrievedEvent = eventBll.getEvent(EVENT_ID);

        // ASSERT
        assertEquals(ERROR_MESSAGE, expectedEvent, retrievedEvent);
    }

    @SmallTest
    @Test
    public void insertEvent_whenIdIsNotValid_returnsException() {

        // ARRANGE


        // ACT

        // ASSERT
    }

    @SmallTest
    @Test
    public void insertEvent_whenIdIsValid_doNothing() {

        // ARRANGE

        // ACT

        // ASSERT
    }

    // endregion

    // region TEARDOWN TEST METHODS

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    // endregion
}
