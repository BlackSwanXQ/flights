package rules;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static com.gridnine.testing.FlightBuilder.createFlight;
import static org.junit.Assert.assertEquals;

public class ArrivalBeforeDepartureFilterTest {

    @Test
    public void filterWithSegments() {

        Random random = new Random();
        List<Flight> flights = generateRandomFlights(random, 5);
        List<Flight> filteredFlights = new ArrivalBeforeDepartureFilter().filter(flights);

        List<Flight> expectedFilteredFlights = new ArrayList<>();
        for (Flight flight : flights) {
            for (Segment segment : flight.getSegments()) {
                if (segment.getArrivalDate().truncatedTo(ChronoUnit.MINUTES).isBefore(segment.getDepartureDate().truncatedTo(ChronoUnit.MINUTES))) {
                    expectedFilteredFlights.add(flight);
                    break;
                }
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
        int segmentCount = random.nextInt(1) + 1;
        List<Segment> segments = new ArrayList<>();
        for (int i = 0; i < segmentCount; i++) {
            segments.add(generateRandomSegment(random));
        }
        return new Flight(segments);
    }

    private Segment generateRandomSegment(Random random) {
        LocalDateTime departureDate = LocalDateTime.now().plusDays(random.nextInt(11) - 5);
        LocalDateTime arrivalDate = LocalDateTime.now().plusDays(random.nextInt(5) + 1);
        return new Segment(departureDate, arrivalDate);
    }

    @Test
    public void filterWithoutSegments() {

        LocalDateTime daysFromNow = LocalDateTime.now();
        List<Flight> flights = Arrays.asList(
                createFlight(daysFromNow.plusDays(1), daysFromNow.plusDays(2)),
                createFlight(daysFromNow.plusDays(1), daysFromNow.plusDays(2)),
                createFlight(daysFromNow.plusDays(1), daysFromNow.plusDays(2)),
                createFlight(daysFromNow.plusDays(1), daysFromNow.plusDays(2))
        );

        List<Flight> filteredFlights = new ArrivalBeforeDepartureFilter().filter(flights);
        assertEquals(Collections.emptyList(), filteredFlights);
    }

}