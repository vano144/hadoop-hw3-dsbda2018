package twitterintegration;

import org.junit.Before;
import org.junit.Test;
import twitter4j.Place;
import twitter4j.Status;
import twitterIntegration.TwitterConsumer;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestTwitterConsumer {

    private Date date;
    private String testCountry;
    private TwitterConsumer twitterConsumer;

    @Before
    public void setUp() {
        date = new GregorianCalendar(2014, Calendar.FEBRUARY, 11).getTime();
        testCountry = "TestCountry";
        twitterConsumer = new TwitterConsumer();
    }

    @Test
    public void testGetJsonFromStatus() {

        Status status = mock(Status.class);
        Place place = mock(Place.class);
        when(status.getCreatedAt()).thenReturn(date);
        when(status.getPlace()).thenReturn(place);
        when(place.getCountry()).thenReturn(testCountry);
        assert ("{\"country\":\"TestCountry\",\"created_at\":\"2014-02-11 00:00\"}".equals(twitterConsumer.getJsonFromStatus(status)));

    }

    @Test
    public void testFormatter() {
        assert ("2014-02-11 00:00".equals(twitterConsumer.getFormatter().format(date)));
    }
}
