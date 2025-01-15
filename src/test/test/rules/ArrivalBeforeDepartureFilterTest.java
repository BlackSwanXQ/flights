package rules;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

public class ArrivalBeforeDepartureFilterTest {

    @Test
    public void FilterArrivalBeforeDepartureTest() {
        LocalDateTime daysFromNow = LocalDateTime.now();
        Segment segment1 = new Segment(daysFromNow, daysFromNow.minusHours(1));
        Segment segment2 = new Segment(daysFromNow.plusHours(1), daysFromNow.plusHours(4));
        Segment segment3 = new Segment(daysFromNow.plusHours(6), daysFromNow.plusHours(7));

        Flight flight1 = new Flight(List.of(segment1, segment2, segment3));
        Flight flight2 = new Flight(List.of(segment2, segment3));
        Flight flight3 = new Flight(List.of(segment3));

        List<Flight> flights = Arrays.asList(flight1, flight2, flight3);

        List<Flight> filteredFlights = new ArrivalBeforeDepartureFilter().filter(flights);

        assertEquals(2, filteredFlights.size());
        assertFalse(filteredFlights.contains(flight1));
        assertTrue(filteredFlights.contains(flight2));
        assertTrue(filteredFlights.contains(flight3));
    }


    @Test
    public void FilterArrivalBeforeDepartureTest_In_A_Different_Way() {

        Random random = new Random();
        List<Flight> flights = generateRandomFlights(random, 5);

        List<Flight> filteredFlights = new ArrivalBeforeDepartureFilter().filter(flights);

        List<Flight> expectedFilteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            boolean isValid = true;
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().isBefore(segment.getDepartureDate())) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) {
                expectedFilteredFlights.add(flight);
            }
        }

        assertEquals(expectedFilteredFlights, filteredFlights);
    }

    private List<Flight> generateRandomFlights(Random random, int count) {
        List<Flight> flights = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            flights.add(generateRandomFlight(random));
        }
        return flights;
    }

    private Flight generateRandomFlight(Random random) {
        int segmentCount = random.nextInt(3) + 1;
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < segmentCount; i++) {
            segments.add(generateRandomSegment(random));
        }
        return new Flight(segments);
    }

    private Segment generateRandomSegment(Random random) {
        LocalDateTime departureDate = LocalDateTime.now().plusDays(random.nextInt(11) - 5).plusMinutes(1);
        LocalDateTime arrivalDate = LocalDateTime.now().plusDays(random.nextInt(5) + 1);
        return new Segment(departureDate, arrivalDate);
    }
}