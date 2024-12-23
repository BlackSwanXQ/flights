package rules;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


import static com.gridnine.testing.FlightBuilder.createFlight;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class GroundTimeExceedsTwoHoursFilterTest {

    @Test
    public void filterWithSegments() {

        LocalDateTime daysFromNow = LocalDateTime.now();

        List<Flight> flights = Arrays.asList(
                createFlight(daysFromNow, daysFromNow.plusHours(2), daysFromNow.plusHours(5), daysFromNow.plusHours(6)),
                createFlight(daysFromNow, daysFromNow.plusHours(1), daysFromNow.plusHours(5), daysFromNow.plusHours(6)),
                createFlight(daysFromNow, daysFromNow.plusHours(1), daysFromNow.plusHours(2), daysFromNow.plusHours(3)),
                createFlight(daysFromNow, daysFromNow.plusHours(4), daysFromNow.plusHours(5), daysFromNow.plusHours(6))
        );
        List<Flight> flights2 = Arrays.asList(
                createFlight(daysFromNow, daysFromNow.plusHours(2), daysFromNow.plusHours(3), daysFromNow.plusHours(4), daysFromNow.plusHours(5), daysFromNow.plusHours(6)),
                createFlight(daysFromNow, daysFromNow.plusHours(2), daysFromNow.plusHours(3), daysFromNow.plusHours(4), daysFromNow.plusHours(6), daysFromNow.plusHours(7)),
                createFlight(daysFromNow, daysFromNow.plusHours(1), daysFromNow.plusHours(2), daysFromNow.plusHours(3), daysFromNow.plusHours(4), daysFromNow.plusHours(5)),
                createFlight(daysFromNow, daysFromNow.plusHours(1), daysFromNow.plusHours(2), daysFromNow.plusHours(4), daysFromNow.plusHours(4), daysFromNow.plusHours(3))
        );

        List<Flight> filteredFlights = new GroundTimeExceedsTwoHoursFilter().filter(flights);
        List<Flight> filteredFlights2 = new GroundTimeExceedsTwoHoursFilter().filter(flights2);

        assertEquals(2, filteredFlights.size());
        assertEquals(3, filteredFlights2.size());

    }

    @Test
    public void filterWithoutSegments() {

        LocalDateTime daysFromNow = LocalDateTime.now();

        List<Flight> flights = Arrays.asList(
                createFlight(daysFromNow, daysFromNow.plusHours(4), daysFromNow.plusHours(5), daysFromNow.plusHours(6)),
                createFlight(daysFromNow, daysFromNow.plusHours(4), daysFromNow.plusHours(5), daysFromNow.plusHours(6)),
                createFlight(daysFromNow, daysFromNow.plusHours(4), daysFromNow.plusHours(5), daysFromNow.plusHours(6)),
                createFlight(daysFromNow, daysFromNow.plusHours(4), daysFromNow.plusHours(5), daysFromNow.plusHours(6))
        );
        List<Flight> flights2 = Arrays.asList(
                createFlight(daysFromNow, daysFromNow.plusHours(1), daysFromNow.plusHours(2), daysFromNow.plusHours(4), daysFromNow.plusHours(4), daysFromNow.plusHours(3)),
                createFlight(daysFromNow, daysFromNow.plusHours(1), daysFromNow.plusHours(2), daysFromNow.plusHours(4), daysFromNow.plusHours(4), daysFromNow.plusHours(3)),
                createFlight(daysFromNow, daysFromNow.plusHours(1), daysFromNow.plusHours(2), daysFromNow.plusHours(4), daysFromNow.plusHours(4), daysFromNow.plusHours(3)),
                createFlight(daysFromNow, daysFromNow.plusHours(1), daysFromNow.plusHours(2), daysFromNow.plusHours(4), daysFromNow.plusHours(4), daysFromNow.plusHours(3))
        );

        List<Flight> filteredFlights = new GroundTimeExceedsTwoHoursFilter().filter(flights);
        List<Flight> filteredFlights2 = new GroundTimeExceedsTwoHoursFilter().filter(flights2);

        assertEquals(Collections.emptyList(), filteredFlights);
        assertEquals(Collections.emptyList(), filteredFlights2);
    }
}