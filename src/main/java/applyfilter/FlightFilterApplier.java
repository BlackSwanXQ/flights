package applyfilter;

import com.gridnine.testing.Flight;
import interfaces.FlightFilter;

import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterApplier {
    public static List<Flight> applyFilter(List<Flight> flights, FlightFilter filter) {
        return filter.filter(flights);
    }
}