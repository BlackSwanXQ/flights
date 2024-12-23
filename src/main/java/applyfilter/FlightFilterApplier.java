package applyfilter;

import com.gridnine.testing.Flight;
import interfaces.FlightFilter;


import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterApplier {
    public static List<Flight> applyFilter(List<Flight> flights, FlightFilter filter) {
        return filter.filter(flights);
    }

    public static List<Flight> listFilter(List<Flight> flights, List<List<Flight>> flightFilters) {
        return flights.stream()
                .filter(item -> flightFilters.stream()
                        .noneMatch(filterList -> filterList.contains(item)))
                .collect(Collectors.toList());
    }
}