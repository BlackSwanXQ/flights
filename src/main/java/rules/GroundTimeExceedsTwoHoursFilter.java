package rules;

import com.gridnine.testing.Flight;
import com.gridnine.testing.Segment;
import interfaces.FlightFilter;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Filter class for flights that have been on the ground for more than two hours.
 */
public class GroundTimeExceedsTwoHoursFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> {
                    List<Segment> segments = flight.getSegments();
                    if (segments.size() < 2) {
                        return false;
                    }
                    Duration totalGroundTime = Duration.ZERO;
                    for (int i = 0; i < segments.size() - 1; i++) {
                        LocalDateTime arrival = segments.get(i).getArrivalDate();
                        LocalDateTime departure = segments.get(i + 1).getDepartureDate();
                        totalGroundTime = totalGroundTime.plus(Duration.between(arrival, departure));
                    }
                    return totalGroundTime.toHours() >= 2;
                })
                .collect(Collectors.toList());
    }
}