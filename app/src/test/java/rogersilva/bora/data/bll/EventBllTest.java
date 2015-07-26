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

    // region INSTANCE VARIABLES

    private static EventDal eventDalMock;
    private static EventBll eventBll;

    // endregion

    // region SETUP TEST METHODS
    @BeforeClass
    public static void setUpClass() {

        eventDalMock = mock(EventDal.class);
        eventBll = new EventBll(eventDalMock);
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

        when(eventDalMock.getEvent(NOT_VALID_EVENT_ID)).thenReturn(null);

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

        when(eventDalMock.getEvent(NOT_FOUND_EVENT_ID)).thenReturn(null);

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

        Event expectedEvent = new Event(EVENT_ID, EVENT_NAME, EVENT_DESCRIPTION);

        when(eventDalMock.getEvent(EVENT_ID)).thenReturn(expectedEvent);

        // ACT
        Event retrievedEvent = eventBll.getEvent(EVENT_ID);

        // ASSERT
        assertEquals(ERROR_MESSAGE, expectedEvent, retrievedEvent);
    }

    @SmallTest
    @Test
    public void insertEvent_whenIdIsNotValid_returnsFalse() {

        // ARRANGE
        final long NOT_VALID_EVENT_ID = -1;
        final String EVENT_NAME = "Show do Guri de Uruguaiana";
        final String EVENT_DESCRIPTION = "Um show para ficar marcado na história do planeta Terra!!!";

        when(eventDalMock.insertEvent(NOT_VALID_EVENT_ID, EVENT_NAME, EVENT_DESCRIPTION)).thenReturn(false);

        // ACT
        boolean retrievedStatus = eventBll.insertEvent(NOT_VALID_EVENT_ID, EVENT_NAME, EVENT_DESCRIPTION);

        // ASSERT
        assertFalse(retrievedStatus);
    }

    @SmallTest
    @Test
    public void insertEvent_whenIdIsValid_returnTrue() {

        // ARRANGE
        final long EVENT_ID = 1;
        final String EVENT_NAME = "Show do Guri de Uruguaiana";
        final String EVENT_DESCRIPTION = "Um show para ficar marcado na história do planeta Terra!!!";

        when(eventDalMock.insertEvent(EVENT_ID, EVENT_NAME, EVENT_DESCRIPTION)).thenReturn(true);

        // ACT
        boolean retrievedStatus = eventBll.insertEvent(EVENT_ID, EVENT_NAME, EVENT_DESCRIPTION);

        // ASSERT
        assertTrue(retrievedStatus);
    }

    // endregion

    // region TEARDOWN TEST METHODS

    @After
    public void tearDown() {
    }

    @AfterClass
    public static void tearDownClass() {

        eventBll = null;
        eventDalMock = null;
    }

    // endregion
}
