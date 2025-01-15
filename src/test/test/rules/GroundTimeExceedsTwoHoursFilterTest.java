package rules;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import interfaces.FlightFilter;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.Assert.*;


public class GroundTimeExceedsTwoHoursFilterTest {

    @Test
    public void FilterGroundTimeExceedsTwoHoursTest() {
        LocalDateTime daysFromNow = LocalDateTime.now();
        Segment segment1 = new Segment(daysFromNow, daysFromNow.plusHours(1).plusMinutes(30));
        Segment segment2 = new Segment(daysFromNow.plusHours(3), daysFromNow.plusHours(4));
        Segment segment3 = new Segment(daysFromNow.plusHours(6), daysFromNow.plusHours(7));

        Flight flight1 = new Flight(List.of(segment1, segment2));
        Flight flight2 = new Flight(List.of(segment1, segment2, segment3));
        Flight flight3 = new Flight(List.of(segment1));

        List<Flight> flights = Arrays.asList(flight1, flight2, flight3);

        List<Flight> filteredFlights = new GroundTimeExceedsTwoHoursFilter().filter(flights);

        assertEquals(1, filteredFlights.size());
        assertTrue(filteredFlights.contains(flight1));
        assertFalse(filteredFlights.contains(flight2));
        assertFalse(filteredFlights.contains(flight3));
    }
}