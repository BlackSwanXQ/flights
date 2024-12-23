package rules;

import com.gridnine.testing.Flight;
import interfaces.FlightFilter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Filter class for flights that have departed so far.
 */
public class DepartureBeforeNowFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        LocalDateTime now = LocalDateTime.now();
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getDepartureDate().isBefore(now.truncatedTo(ChronoUnit.MINUTES))))
                .collect(Collectors.toList());
    }
}