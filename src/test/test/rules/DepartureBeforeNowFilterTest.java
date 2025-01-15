package rules;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import interfaces.FlightFilter;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class DepartureBeforeNowFilterTest {

    @Test
    public void FilterDepartureBeforeNowFilterTest() {

        LocalDateTime daysFromNow = LocalDateTime.now();
        Segment segment1 = new Segment(daysFromNow.plusMinutes(30), daysFromNow.plusHours(1));
        Segment segment2 = new Segment(daysFromNow.minusHours(1), daysFromNow.plusHours(4));
        Segment segment3 = new Segment(daysFromNow.plusHours(6), daysFromNow.plusHours(7));

        Flight flight1 = new Flight(List.of(segment1, segment2, segment3));
        Flight flight2 = new Flight(List.of(segment1, segment3));
        Flight flight3 = new Flight(List.of(segment3));

        List<Flight> flights = Arrays.asList(flight1, flight2, flight3);

        List<Flight> filteredFlights = new DepartureBeforeNowFilter().filter(flights);

        assertEquals(2, filteredFlights.size());
        assertFalse(filteredFlights.contains(flight1));
        assertTrue(filteredFlights.contains(flight2));
        assertTrue(filteredFlights.contains(flight3));
    }
}
