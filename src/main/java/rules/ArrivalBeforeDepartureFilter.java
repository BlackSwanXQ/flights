package rules;

import com.gridnine.testing.Flight;
import interfaces.FlightFilter;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;


        /**
        * Filter class for flights that arrived before departure.
        */
public class ArrivalBeforeDepartureFilter implements FlightFilter {
    @Override
    public List<Flight> filter(List<Flight> flights) {
        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate()
                                .isBefore(segment.getDepartureDate())))
                .collect(Collectors.toList());
    }
}