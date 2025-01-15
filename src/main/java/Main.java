import com.gridnine.testing.Flight;
import com.gridnine.testing.FlightBuilder;
import com.gridnine.testing.Segment;
import rules.ArrivalBeforeDepartureFilter;
import rules.DepartureBeforeNowFilter;
import applyfilter.FlightFilterApplier;
import rules.GroundTimeExceedsTwoHoursFilter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Original list of flights:");
        flights.forEach(System.out::println);

        System.out.println("\nList of flights without departure before now:");
        List<Flight> filteredByDeparture = FlightFilterApplier.applyFilter(flights, new DepartureBeforeNowFilter());
        filteredByDeparture.forEach(System.out::println);

        System.out.println("\nList of flights without arrival before departure:");
        List<Flight> filteredByArrival = FlightFilterApplier.applyFilter(flights, new ArrivalBeforeDepartureFilter());
        filteredByArrival.forEach(System.out::println);


        System.out.println("\nList of flights without ground time exceeds two hours:");
        List<Flight> filteredByGroundTime = FlightFilterApplier.applyFilter(flights, new GroundTimeExceedsTwoHoursFilter());
        filteredByGroundTime.forEach(System.out::println);
    }
}
